package model.ex4;

/**
 * the Message class
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class Message {
    private final String message;
    private final User sendingUser;
    private final String time;
    
    /**
     * initializes a new Message object
     * @param message the content
     * @param user the sender
     * @param time the time
     */
    public Message(String message, User user, String time) {
        this.message = message;
        this.sendingUser = user;
        this.time = time;
    }
    
    /**
     * gets the content of the message
     * @return the content
     */
    public String getMessage() { return this.message; }

    /**
     * gets the sending user
     * @return the user
     */
    public User getUser() { return this.sendingUser; }

    /**
     * gets the time of the message
     * @return the time
     */
    public String getTime() { return this.time; }
}
