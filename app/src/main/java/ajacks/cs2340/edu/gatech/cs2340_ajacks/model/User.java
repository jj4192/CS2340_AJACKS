package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Created by KXC6120 on 9/29/2017.
 */

public class User {
    private String userName;
    private String password;
    private String name;
    private String lastName;
    private String email;

    public User(String userName, String password, String name, String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }
    //for now
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }
}
