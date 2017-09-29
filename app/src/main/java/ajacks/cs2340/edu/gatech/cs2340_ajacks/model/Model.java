package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KXC6120 on 9/29/2017.
 */

public class Model {

    private List<User> allUsers;

    public Model() {
        allUsers = new ArrayList<User>();
    }

    public void addUser(User newUser) {
        allUsers.add(newUser);
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
