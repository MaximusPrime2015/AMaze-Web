package model.ex4;

/**
 * the class UserHiber
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class UserHiber {
    private String username = "";
    private String password = "";
    private String name = "";
    private String email = "";
    private int icon = 0;
    
    /**
     * empty constuctor
     */
    public UserHiber() { }
    
    /**
     * Initialize a new User
     * @param username the username
     * @param password the password
     * @param name the name of the user
     * @param email the email of the user
     * @param icon the icon id of the user
     */
    public UserHiber(String username, String password, String name,
                                            String email, int icon) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.icon = icon;
    }
    
    /**
     * gets the username
     * @return the username
     */
    public String getUsername() { return this.username; }

    /**
     * gets the password
     * @return the password
     */
    public String getPassword() { return this.password; }

    /**
     * gets the name
     * @return the name of the user
     */
    public String getName() { return this.name; }

    /**
     * gets the email of the user
     * @return the email
     */
    public String getEmail() { return this.email; }

    /**
     * gets the icon code
     * @return the icon code
     */
    public int getIcon() { return this.icon; }
    
    /**
     * sets the username
     * @param username the username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * sets the password
     * @param password the password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * sets the name of the user
     * @param name the name
     */
    public void setName(String name) { this.name = name; }

    /**
     * sets the email of the user
     * @param email the email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * sets the icon code of the user
     * @param icon the icon code
     */
    public void setIcon(int icon) { this.icon = icon; }
}
