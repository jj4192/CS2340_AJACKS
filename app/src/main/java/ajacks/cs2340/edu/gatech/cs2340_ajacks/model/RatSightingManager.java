package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import android.util.Log;

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
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import ajacks.cs2340.edu.gatech.cs2340_ajacks.R;

/**
 * Created by jj419_000 on 10/20/2017.
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
                Log.d("Firebase", "next sighting id: " + id);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.toString());
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
                    if (!alreadyInList(tempSighting)) {
                        allSightings.add(0, tempSighting);
                        DEBUG: Log.d("Firebase", tempSighting.toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.toString());
            }
        });

    }

    public void loadCSVData(InputStream is) {
        if (!loadedCSV) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

                String line;
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(",");
                    if (tokens.length == 53) {
                        int id = Integer.parseInt(tokens[0]);
                        Coordinates coord = new Coordinates(Float.parseFloat(tokens[49]), Float.parseFloat(tokens[50]));
                        Location location = new Location(coord, LocationType.getEnumValueByFullName(tokens[7]), tokens[8], tokens[9], tokens[16], Borough.getEnumValueByFullName((tokens[23])));
                        RatSighting temp = new RatSighting(id, location, tokens[1]);
                        allSightings.add(temp);
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
                return true;
            }
        }
        return false;
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

}
