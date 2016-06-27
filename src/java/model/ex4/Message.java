package model.ex4;

/**
 *
 * @author user
 */
public class Message {
    private String message;
    private User sendingUser;
    private String time;
    
    public Message(String message, User user, String time) {
        this.message = message;
        this.sendingUser = user;
        this.time = time;
    }
    
    public String getMessage() { return this.message; }
    public User getUser() { return this.sendingUser; }
    public String getTime() { return this.time; }
}
