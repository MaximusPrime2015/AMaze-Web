package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author user
 */
public class ex4MessageDataBase {
    ArrayList<ex4Message> messages;
    
    private ex4MessageDataBase() {
        this.messages = new ArrayList<>();
    }
    
    private static class Singleton {
        public static final ex4MessageDataBase singleton = new ex4MessageDataBase();
    }
    
    public static ex4MessageDataBase getDataBase() {
        return Singleton.singleton;
    }
    
    public ex4Message addMessage(String msgTxt, ex4User user, String time) {
        ex4Message message = new ex4Message(msgTxt, user, time);
        this.messages.add(message);
        
        //hibatnate code
        Message msg;
        msg = new Message(msgTxt, user.getUsername(), time);

        SessionFactory sessionFactory;
        sessionFactory = new Configuration().configure().buildSessionFactory(); 
        Session session = sessionFactory.openSession();
        MessageManager manager = new MessageManager(session);

        manager.saveMessage(msg);
        session.flush();
        
        return message;
    }
    
    public int getSize() {
        return this.messages.size();
    }
    
    public JsonObject getMessages(int numMessagesToRead) {
        JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();
        int size = this.messages.size();
        while (numMessagesToRead > 0 && size > 0) {
            ex4Message msg = this.messages.get(size - 1);
            jsonBuilder.add(Json.createObjectBuilder().add("msg", Json.createObjectBuilder()
                    .add("message", msg.getMessage())
                    .add("time", msg.getTime())
                    .add("username", msg.getUser().getUsername())
                    .add("name", msg.getUser().getName())
                    .add("icon", msg.getUser().getIcon())));
            --size;
            --numMessagesToRead;
        }
        return Json.createObjectBuilder().add("messages", jsonBuilder)
                .add("numTotalMessages", this.messages.size()).build();
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
