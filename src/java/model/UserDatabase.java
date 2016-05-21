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
    private List<User> users;
    private static UserDatabase instance = null;
    
    private UserDatabase(){
        this.users = new ArrayList();
    }
    
    public UserDatabase getInstance(){
        if(this.instance == null){
            instance = new UserDatabase();
        }
        return instance;
    }
    
    public void addUser(User user){
        this.users.add(user);
    }
    
    public void addUser(String name, String password){
        this.users.add(new User(name, password));
    }
    
    public User getUser(String name){
        for (User user : this.users) {
            if(user.compareName(name))
                return user;
        }
        return null;
    }
}
