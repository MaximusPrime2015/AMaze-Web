package model.ex4;

/**
 * the User class
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class User {
    private final String username;
    private final String password;
    private final String email;
    private final String name;
    private final int iconCode;
    
    /**
     * generates a new User object
     * @param username the username
     * @param password the password
     * @param name the name
     * @param email the email
     * @param icon the icon id
     */
    public User(String username, String password, String name, String email,
                    int icon) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.iconCode = icon;
    }
    
    /**
     * gets the username
     * @return the username
     */
    public String getUsername() { return this.username; }

    /**
     * gets the name of the user
     * @return the name
     */
    public String getName() { return this.name; }

    /**
     * get the email of the user
     * @return the email
     */
    public String getEmail() { return this.email; }

    /**
     * gets the icon id of the user
     * @return the icon id
     */
    public int getIcon() { return this.iconCode; }
    
    /**
     * checks if the given password is the user's password
     * @param password the login attempt password
     * @return true if passwords match
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
