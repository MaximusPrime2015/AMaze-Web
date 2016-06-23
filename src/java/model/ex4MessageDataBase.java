package model;

import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author user
 */
public class ex4MessageDataBase {
    //ArrayList<ex4Message> messages;
    private SessionFactory sessionFactory;
    
    private ex4MessageDataBase() {
        //this.messages = new ArrayList<>();
        this.sessionFactory = SessionFactoryHolder.sessionFactory;
    }
    
    private static class Singleton {
        public static final ex4MessageDataBase singleton = new ex4MessageDataBase();
    }
    
    public static ex4MessageDataBase getDataBase() {
        return Singleton.singleton;
    }
    
    public ex4Message addMessage(String msgTxt, ex4User user, String time) {
        ex4Message message = new ex4Message(msgTxt, user, time);
        Message msgSql = new Message(msgTxt, user.getUsername(), time);
        //this.messages.add(message);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(msgSql);
        } catch (Exception ex) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        
        return message;
    }
    
    public int getSize() {
        //return this.messages.size();
        Session session = sessionFactory.openSession();
        int size = ((Long)session.createQuery("SELECT COUNT(*) FROM Message").uniqueResult()).intValue();
        session.close();
        return size;
    }
    
    public JsonObject getMessages(int numMessagesToRead) {
        JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
        int size = this.getSize();
        int offset = Math.max(0, size - numMessagesToRead);
        Session session = sessionFactory.openSession();
        List<Message> messages = (List<Message>) session.createQuery("FROM Message")
                .setFirstResult(offset).setMaxResults(numMessagesToRead).list();
        java.util.Collections.reverse(messages);
        for (Message m : messages) {
            ex4User user = ex4UserDataBase.getDataBase().getUser(m.getUser());
            ex4Message msg = new ex4Message(m.getContent(), user, m.getTime());
            jsonBuilder.add(Json.createObjectBuilder().add("msg", Json.createObjectBuilder()
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
    
    public JsonObject messageToJson(ex4Message msg) {
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
