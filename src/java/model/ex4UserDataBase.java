package model;

import java.util.HashMap;

/**
 *
 * @author user
 */
public class ex4UserDataBase {
    
    private HashMap<String, ex4User> users;
    
    private ex4UserDataBase() {
        this.users = new HashMap<>();
    }
    
    private static class Singleton {
        public static final ex4UserDataBase singleton = new ex4UserDataBase();
    }
    
    public static ex4UserDataBase getDataBase() {
        return Singleton.singleton;
    }
    
    public boolean checkIfUserExists(String username) {
        return this.users.containsKey(username);
    }
    
    public ex4User getUser(String username) {
        return this.users.get(username);
    }
    
    public void addUser(String username, String password, String name,
            String email, int icon) throws Exception {
        if (this.checkIfUserExists(username))
            throw new Exception("user already in data base");
        // add the user
        ex4User user = new ex4User(username, password, name, email, icon);
        this.users.put(username, user);
    }
}
