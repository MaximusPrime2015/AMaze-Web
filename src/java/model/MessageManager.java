package model;

import org.hibernate.Session;

/**
 *
 * @author user
 */
class MessageManager {

    private Session session = null;
    
    public MessageManager(Session session) {
        if(session == null)
          throw new 
            RuntimeException("Invalid session object.");
        this.session = session;
    }
    
    public void saveMessage(Message msg){
        session.save(msg);
    }
    
    public void updateMessage(Message msg){
        session.update(msg);
    }
    
    public void deleteMessage(Message msg) {
        session.delete(msg);
    }
    
}
