package model;

/**
 *
 * @author user
 */
public class ex4Message {
    private String message;
    private ex4User sendingUser;
    private String time;
    
    public ex4Message(String message, ex4User user, String time) {
        this.message = message;
        this.sendingUser = user;
        this.time = time;
    }
    
    public String getMessage() { return this.message; }
    public ex4User getUser() { return this.sendingUser; }
    public String getTime() { return this.time; }
}
