package model;

/**
 *
 * @author user
 */
public class User {
    private String username = "";
    private String password = "";
    private String name = "";
    private String email = "";
    private int icon = 0;
    
    public User() { }
    
    public User(String username, String password, String name, String email, int icon) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.icon = icon;
    }
    
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public int getIcon() { return this.icon; }
    
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setIcon(int icon) { this.icon = icon; }
}
