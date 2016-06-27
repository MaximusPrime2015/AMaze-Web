package model.ex4;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * the class Message Data Base
 * @author user
 */
public class MessageDataBase {
    private final SessionFactory sessionFactory;
    
    private MessageDataBase() {
        this.sessionFactory = SessionFactoryHolder.sessionFactory;
    }
    
    private static class Singleton {
        public static final MessageDataBase SINGLETON
                = new MessageDataBase();
    }
    
    /**
     * gets the singleton instance of the message data base class
     * @return the instance
     */
    public static MessageDataBase getDataBase() {
        return Singleton.SINGLETON;
    }
    
    /**
     * adds the given message to the data base
     * @param msgTxt the content of the message
     * @param user the sending users
     * @param time the current time
     * @return resulted Message object
     */
    public Message addMessage(String msgTxt, User user, String time) {
        Message message = new Message(msgTxt, user, time);
        MessageHiber msgSql = new MessageHiber(msgTxt, user.getUsername(),
                                                        time);
        // open session with mySql
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            // add the message to mySql
            session.persist(msgSql);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        // close the session
        session.getTransaction().commit();
        session.close();
        
        return message;
    }
    
    /**
     * gets the amount of messages in the database
     * @return the size
     */
    public int getSize() {
        Session session = sessionFactory.openSession();
        // make a counter HSL query
        int size = ((Long)session
                .createQuery("SELECT COUNT(*) FROM MessageHiber")
                                                .uniqueResult()).intValue();
        session.close();
        return size;
    }
    
    /**
     * gets the last [numMessagesToRead] from the database
     * @param numMessagesToRead how many to read
     * @return the messages
     */
    public JsonObject getMessages(int numMessagesToRead) {
        JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
        // get the size of the database
        int size = this.getSize();
        // calculate the off set
        int offset = Math.max(0, size - numMessagesToRead);
        Session session = sessionFactory.openSession();
        // get the last [numMessagesToRead] messages from mySql
        List<MessageHiber> messages
                = (List<MessageHiber>) session.createQuery("FROM MessageHiber")
                .setFirstResult(offset).setMaxResults(numMessagesToRead)
                .list();
        java.util.Collections.reverse(messages);
        // create the json result
        for (MessageHiber m : messages) {
            User user = UserDataBase.getDataBase().getUser(m.getUser());
            Message msg = new Message(m.getContent(), user, m.getTime());
            jsonBuilder.add(Json.createObjectBuilder().add("msg",
                                                    Json.createObjectBuilder()
                    .add("message", msg.getMessage())
                    .add("time", msg.getTime())
                    .add("username", msg.getUser().getUsername())
                    .add("name", msg.getUser().getName())
                    .add("icon", msg.getUser().getIcon())));
        }
        session.close();
        return Json.createObjectBuilder().add("messages", jsonBuilder)
                .add("numTotalMessages", size).build();
    }
    
    /**
     * converts the given message object into json
     * @param msg the message object
     * @return the json result
     */
    public JsonObject messageToJson(Message msg) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
        jsonBuilder.add("msg", Json.createObjectBuilder()
                    .add("message", msg.getMessage())
                    .add("time", msg.getTime())
                    .add("username", msg.getUser().getUsername())
                    .add("name", msg.getUser().getName())
                    .add("icon", msg.getUser().getIcon()));
        return jsonBuilder.build();
    }
}
