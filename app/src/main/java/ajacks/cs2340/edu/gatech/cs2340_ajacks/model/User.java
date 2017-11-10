package ajacks.cs2340.edu.gatech.cs2340_ajacks.model;

/**
 * Created by KXC6120 on 9/29/2017.
 * Creates an User
 */
public class User {

    private String userName;
    private String password;
    private String email;
    private String userType;
    private String id;
    private String accountStatus;

    /**
     * The constructor to create a new user from login
     * @param id the id in the firebase db
     * @param username the username of the new user
     * @param password the password for the new account
     * @param email the email associated with this account
     * @param usertype either admin or user; access is based upon this value
     * @param accountstatus if the account is locked, unlocked, banned
     */
    public User(String id, String username, String password, String email, String usertype, String accountstatus) {
        this.id = id;
        this.userName = username;
        this.password = password;
        this.email = email;
        this.userType = usertype;
        this.accountStatus = accountstatus;
    }

    /**
     * Getter method to get user's id
     * @return the user's id
     */
    public String getId() { return this.id; }

    /**
     * Setter method for user's id
     * @param id the new id to set for the user
     */
    public void setId(String id) { this.id = id; }

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

    /**
     * Getter method to return the user type of the user
     * @return user's type (admin or user)
     */
    public String getUserType() { return this.userType; }

    /**
     * Getter method to return the user's account status
     * @return user's account status "unlocked", "locked"
     */
    public String getAccountStatus() { return this.accountStatus; }

    /**
     * Setter method set the user's account status, "unlocked", "locked"
     * @param accountStatus the replacement
     */
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
    @Override
    public String toString() {
        return id + ", " + userName + ", " + password + ", " + email + ", " + userType + ", " + accountStatus;
    }


    /**
     * Checks to see if current user has same id as another user (for duplicates in DB)
     * @param u the user being compared
     * @return true if same id in database, false otherwise
     */
    public boolean isSameUser(User u) {
            if (u.id.equals(this.id)) {
            return true;
        }
        return false;
    }
}
