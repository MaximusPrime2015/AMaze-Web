/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class UserDatabase {
    private final List<User> users;
    private static UserDatabase instance = null;
    
    /**
     * private constructor for singleton.
     */
    private UserDatabase(){
        this.users = new ArrayList();
    }
    
    /**
     * get Instance method for singleton.
     * @return
     */
    public synchronized static UserDatabase getInstance(){
        if (UserDatabase.instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }
    
    /**
     * adds user to database.
     * @param user
     */
    public void addUser(User user){
        this.users.add(user);
    }
    
    /**
     * adds user to database using user details.
     * @param name
     * @param password
     * @param realName
     * @param email
     * @param profile
     */
    public void addUser(String name, String password, String realName,
            String email, String profile){
        this.users.add(new User(name, password, realName, email, profile));
    }
    
    /**
     * retrieves user by username.
     * @param username
     * @return
     */
    public User getUser(String username) {
        for (User user : this.users) {
            if (user.compareUsername(username))
                return user;
        }
        return null;
    }
    
    /**
     * returns users list.
     * @return
     */
    public List<User> getList(){
        return this.users;
    }
    
    /**
     * checks that a user with given name and password exists in the database.
     * @param name
     * @param password
     * @return
     */
    public boolean validateUser(String name, String password){
        User user = this.getUser(name);
        if(user == null) return false;
        return password.equals(user.getPassword());
    }
    
    /**
     * checks that a user exists in the database.
     * @param user
     * @return
     */
    public boolean validateUser(User user){
        User auxUser = this.getUser(user.getUsername());
        if(auxUser == null) return false;
        return user.getPassword().equals(auxUser.getPassword());
    }
}
