
package model;

/**
 *
 * @author Max
 */
public class User {
    private String name;
    private String password;
    
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public boolean compareName(String name){
        return this.name.equals(name);
    }
    
    @Override
    public int hashCode(){
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if(other == null) return false;
        
        if(other instanceof User){
            User usr = (User)other;
            return this.name.equals(usr.getName())
                  && this.password.equals(usr.getPassword());
        }
        return false;
    }
}
