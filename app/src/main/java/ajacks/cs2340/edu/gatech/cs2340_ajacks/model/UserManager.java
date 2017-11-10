package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chsieh on 10/19/17.
 */

public class UserManager {

    private static List<User> allUsers;
    private static final FirebaseDatabase _database = FirebaseDatabase.getInstance();
    private static User appUser;

    public UserManager() {
        allUsers = new ArrayList<>();
        loadUsers();
    }

    /**
     * Private method to call to database and load users into allUsers list.
     * Listener will update whenever changes have been made
     */
    private void loadUsers() {
        DatabaseReference ref = _database.getReference("user");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot element: dataSnapshot.getChildren()) {
                    String id = element.getKey();
                    String currUserName = (String) element.child("userName").getValue();
                    String currPassword = (String) element.child("password").getValue();
                    String currEmail = (String) element.child("email").getValue();
                    String currUserType = (String) element.child("userType").getValue();
                    String currAccountStatus = (String) element.child("accountStatus").getValue();
                    User tempUser = new User(id, currUserName, currPassword, currEmail, currUserType, currAccountStatus);
                    //if this event listener is updating list, making sure no duplicates
                    if (!alreadyInList(tempUser)) {
                        allUsers.add(tempUser);
                        //DEBUG: Log.d("Firebase", "loading user " + tempUser.toString());
                    }
                    //will eventually need to implement updating the users if something has changed
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.toString());
            }
        });
    }

    /**
     * Checks to see if a given user already is in the list of all users. Safety measure since firebase
     * works based off of listeners
     * @param u the User being checked
     * @return true if in list, false otherwise
     */
    private boolean alreadyInList(User u) {
        for (User currUser: allUsers) {
            if (u.isSameUser(currUser)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to see if login credentials match an existing user in allUsers
     * @param username the username to check against allUsers
     * @param password the password to check against allUsers
     * @return
     */
    public int checkCredentials(String username, String password) {
        //DEBUG: Log.d("Firebase", "check credentials");
        for (User currUser: allUsers) {
            if (currUser.getUserName().equals(username) && "locked".equals(currUser.getAccountStatus())) {
                return -1;
            }
            if (currUser.getUserName().equals(username) && currUser.getPassword().equals(password)) {
                //if found a user, they are set to appUser to be used throughout app
                appUser = currUser;
                return 1;
            }
            //still need to implement locked out and banned
        }
        return 0;
    }

    /**
     * Returns username of the user
     * @return
     */
    public User getAppUser() {
        return appUser;
    }

    public void lockAccount(String username) {
        for (User currUser: allUsers) {
            if (currUser.getUserName().equals(username)) {
                //update account status
                currUser.setAccountStatus("locked");
                //update database
                _database.getReference("user").child(currUser.getId()).setValue(currUser);
            }
        }
    }

    /**
     * Checks to see if a username already exists in the list of users
     * @param username the username in question
     * @return true if it is taken, false otherwise
     */
    public boolean usernameTaken(String username) {
        for (User currUser: allUsers) {
            if (username.equals(currUser.getUserName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new user to the Firebase database (which automatically updates allUsers
     * @param u the new user to add
     * @return true if successful, false otherwise
     */
    public boolean addNewUser(User u) {
        DatabaseReference ref = _database.getReference("user");
        DatabaseReference newUserRef = ref.push();
        newUserRef.setValue(u);
        //String postId = newUserRef.getKey();
        //DEBUG: Log.d("Firebase", "key generated" + postId);
        return true;
    }
}
