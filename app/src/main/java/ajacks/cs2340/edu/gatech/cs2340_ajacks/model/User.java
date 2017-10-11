package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Created by KXC6120 on 9/29/2017.
 */
public class User {

    private String userName;
    private String password;
    private String email;
    private String userType;

    /**
     * The constructor to create a new user from login
     * @param userName the username of the new user
     * @param password the password for the new account
     * @param email the email associated with this account
     * @param userType either admin or user; access is based upon this value
     */
    public User(String userName, String password, String email, String userType) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    /**
     * Getter method to return user's username
     * @return the username of the user
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Getter method to return the user's password
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter method to return the email of the user
     * @return the user's email
     */
    public String getEmail() {
        return this.email;
    }
}
