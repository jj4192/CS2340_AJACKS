package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Created by KXC6120 on 9/29/2017.
 */

public class User {
    private String userName;
    private String password;
    private String email;
    private String userType;

    public User(String userName, String password, String email, String userType) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userType = userType;
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

    public String getEmail() {
        return this.email;
    }
}
