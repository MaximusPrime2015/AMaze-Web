package model.ex4;

/**
 *
 * @author user
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private int icon_code;
    
    public User(String username, String password, String name, String email,
                    int icon) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.icon_code = icon;
    }
    
    public String getUsername() { return this.username; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public int getIcon() { return this.icon_code; }
    
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
