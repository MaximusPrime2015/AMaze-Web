package model.ex4;

/**
 * the class MessageHiber
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class MessageHiber {
    private String content;
    private String user;
    private String time;
    
    /**
     * empty Constructor
     */
    public MessageHiber() { }
    
    /**
     * Initialize a new Message
     * @param message the content
     * @param user the sender
     * @param time the time
     */
    public MessageHiber(String message, String user, String time) {
        this.content = message;
        this.user = user;
        this.time = time;
    }
    
    /**
     * gets the content of the message
     * @return the content
     */
    public String getContent() { return this.content; }

    /**
     * gets the sending user
     * @return the user
     */
    public String getUser() { return this.user; }

    /**
     * gets the time of the message
     * @return the time
     */
    public String getTime() { return this.time; }
    
    /**
     * sets the content of the message
     * @param value the content
     */
    public void setContent(String value) { this.content = value; }

    /**
     * set the sending user
     * @param value the user
     */
    public void setUser(String value) { this.user = value; }

    /**
     * set the time
     * @param value the time
     */
    public void setTime(String value) { this.time = value; }
}
