package model;

/**
 *
 * @author user
 */
public class Message {
    private String content;
    private String user;
    private String time;
    
    public Message() { }
    
    public Message(String message, String user, String time) {
        this.content = message;
        this.user = user;
        this.time = time;
    }
    
    public String getContent() { return this.content; }
    public String getUser() { return this.user; }
    public String getTime() { return this.time; }
    
    public void setContent(String value) { this.content = value; }
    public void setUser(String value) { this.user = value; }
    public void setTime(String value) { this.time = value; }
}
