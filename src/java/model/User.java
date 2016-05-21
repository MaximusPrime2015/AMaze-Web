package model;

/**
 *
 * @author Max
 */
public class User {

    private String username;
    private String password;
    private String realName;
    private String email;
    private String profile;

    public User(String username, String password, String realName,
            String email, String profile) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.profile = profile;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRealName() {
        return this.realName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getProfile() {
        return this.profile;
    }

    public boolean compareUsername(String username) {
        return this.username.equals(username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        if (other instanceof User) {
            User usr = (User) other;
            return this.username.equals(usr.getUsername())
                    && this.password.equals(usr.getPassword())
                    && this.email.equals(usr.getEmail());
        }
        return false;
    }
}
