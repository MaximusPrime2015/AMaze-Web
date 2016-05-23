/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Max
 */
public class UserDatabase {
    private final List<User> users;
    private static UserDatabase instance = null;
    
    private UserDatabase(){
        this.users = new ArrayList();
    }
    
    public static UserDatabase getInstance(){
        if (UserDatabase.instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }
    
    public void addUser(User user){
        this.users.add(user);
    }
    
    public void addUser(String name, String password, String realName,
            String email, String profile){
        this.users.add(new User(name, password, realName, email, profile));
    }
    
    public User getUser(String username) {
        for (User user : this.users) {
            if (user.compareUsername(username))
                return user;
        }
        return null;
    }
    
    public List<User> getList(){
        return this.users;
    }
    
    public boolean validateUser(String name, String password){
        User user = this.getUser(name);
        if(user == null) return false;
        return password.equals(user.getPassword());
    }
    
    public boolean validateUser(User user){
        User auxUser = this.getUser(user.getUsername());
        if(auxUser == null) return false;
        return user.getPassword().equals(auxUser.getPassword());
    }
}
