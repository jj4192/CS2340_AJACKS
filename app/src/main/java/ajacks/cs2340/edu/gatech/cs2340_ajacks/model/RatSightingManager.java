package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


/**
 * Created by jj419_000 on 10/20/2017.
 * Manages the rat sightings
 */

public class RatSightingManager {
    private static List<RatSighting> allSightings;
    private static int lastUsedSightingId = 0;
    private boolean loadedCSV = false;
    private static final FirebaseDatabase _database = FirebaseDatabase.getInstance();


    public RatSightingManager() {
        allSightings = new ArrayList<>();
        loadSightings();
    }
    /**
     * Private method that calls the database to load rat sightings into the sightings list
     * Listener will update whenever changes have been made
     */
    private void loadSightings() {
        DatabaseReference ref = _database.getReference("sighting");
        DatabaseReference idRef = _database.getReference("lastSightingID");
        idRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id = dataSnapshot.getValue().toString();
                lastUsedSightingId = Integer.parseInt(id);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot element: dataSnapshot.getChildren()) {
                    String id = (String) element.child("id").getValue();
                    String dateAndTime = (String) element.child("dateAndTime").getValue();
                    String zipCode = (String) element.child("zipCode").getValue();
                    String address = (String) element.child("address").getValue();
                    String city = (String) element.child("city").getValue();
                    String borough = (String) element.child("borough").getValue();
                    String locationType = (String) element.child("locationType").getValue();
                    String xCoord = (String) element.child("xCoord").getValue();
                    String yCoord = (String) element.child("yCoord").getValue();
                    RatSighting tempSighting = DBRatSighting.createDBRatSighting(id, dateAndTime, zipCode, address, city, borough, locationType, xCoord, yCoord);
                    //if this event listener is updating list, making sure no duplicates (by id)
                    if (alreadyInList(tempSighting)) {
                        allSightings.add(0, tempSighting);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    /**
     * method that reads the CSV file and creates a RatSighting object from each entry and adds them
     * all to a list
     * @param is to read the CSV
     */
    public void loadCSVData(InputStream is) {
        if (!loadedCSV) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                int count = 0;

                String line;
                br.readLine();

                while ((((line = br.readLine())) != null) && (count < 300)) {

                    String[] tokens = line.split(",");
                    if (tokens.length == 53) {
                        int id = Integer.parseInt(tokens[0]);
                        Coordinates coord = new Coordinates(Float.parseFloat(tokens[49]), Float.parseFloat(tokens[50]));
                        Location location = new Location(coord, LocationType.getEnumValueByFullName(tokens[7]), tokens[8], tokens[9], tokens[16], Borough.getEnumValueByFullName((tokens[23])));
                        RatSighting temp = new RatSighting(id, location, tokens[1]);
                        allSightings.add(temp);
                        count++;
                    }
                }
                br.close();
            } catch (IOException e) {
                e.getMessage();
            }
            loadedCSV = true;
        }
    }

    /***
     * checks if the created rat sighting is already added
     * @param sighting rat sighting to be submitted
     * @return true if it is not in the list or false if it is
     */
    private boolean alreadyInList(RatSighting sighting) {
        for (RatSighting currSighting: allSightings) {
            if (sighting.isSameSighting(currSighting)) {
                return false;
            }
        }
        return true;
    }


    /***
     * method that adds the rat sighting to the database, and adds the updated id to the database
     * @param sighting sighting to be added
     * @return true when added to database
     */
    public boolean addNewSighting(RatSighting sighting) {
        //add sighting
        DatabaseReference ref = _database.getReference("sighting");
        DatabaseReference newSightingRef = ref.push();
        newSightingRef.setValue(new DBRatSighting(sighting));
        //update lastSightingID to store what is used in sighting above
        DatabaseReference idRef = _database.getReference("lastSightingID");
        idRef.setValue(lastUsedSightingId);
        return true;
    }

    /**
     * Method to return the next ID that can be used to assign to a rat sighting
     * @return next possible id to use
     */
    public int useUniqueRatSightingID() {
        return ++lastUsedSightingId;
    }

    /**
     * Returns a list of all the rat sightings that have been stored
     * @return allSightings
     */
    public List<RatSighting> getAllSightings() {
        return allSightings;
    }

    /**
     *  Sorts the rat sightings by date and time from old to recent
     */
    private void sortRatSightingsByDateAndTime() {
        Collections.sort(allSightings, new SortByDateAndTime());
    }

    /**
     * Gets a list of unique Dates for use in the spinner
     * @return list of unique dates
     */
    public List<String> getDates() {
        List<String> dates = new ArrayList<>();
        sortRatSightingsByDateAndTime();
        for (RatSighting sighting : allSightings) {
            String currentDateAndTime = sighting.getDateAndTime();
            if (!dates.contains(currentDateAndTime)) {
                dates.add(currentDateAndTime);
            }
        }
        return dates;
    }

    /**
     * Inner class used for sorting method
     */

    public static class SortByDateAndTime implements Comparator<RatSighting>
    {
        // Used for sorting in ascending order of
        // date and time
        @Override
        public int compare(RatSighting ratSighting1, RatSighting ratSighting2)
        {
            String dateAndTime1 = ratSighting1.getDateAndTime();
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", java.util.Locale.getDefault());
            Date date1;
            String dateAndTime2 = ratSighting2.getDateAndTime();
            Date date2;
            // expected to parse the rat sighting's date and time string
            try {
                date1 = dateFormat.parse(dateAndTime1);
                date2 = dateFormat.parse(dateAndTime2);
                return date1.compareTo(date2);
            } catch (ParseException e) /* prints stack trace if parsing goes wrong */ {
                e.printStackTrace();
            }
            return 0;
        }
    }

}
