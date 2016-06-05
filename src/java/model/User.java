package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Observable;
import java.util.Observer;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import model.server.ServerTCP;

/**
 *
 * @author Max/Michael
 */
public class User implements Observer {

    private String username;
    private String password;
    private String realName;
    private String email;
    private String profile;
    
    private ServerTCP connection = null;
    private AsyncContext async;

    /**
     * constructor.
     * @param username
     * @param password
     * @param realName
     * @param email
     * @param profile
     */
    public User(String username, String password, String realName,
            String email, String profile) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.profile = profile;
    }

    /**
     * getter for username.
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * getter for password.
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * getter for real name.
     * @return real name.
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * getter for email
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * getter for profile
     * @return profile
     */
    public String getProfile() {
        return this.profile;
    }

    /**
     * compares usernames.
     * @param username
     * @return result of equals function on a given username and member.
     */
    public boolean compareUsername(String username) {
        return this.username.equals(username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        if (other instanceof User) {
            User usr = (User) other;
            return this.username.equals(usr.getUsername())
                    && this.password.equals(usr.getPassword())
                    && this.email.equals(usr.getEmail());
        }
        return false;
    }

    /**
     * sends a multiplayer game request.
     * @param mazeName
     * @param async
     */
    public void requestMultiPlayerGame(String mazeName, AsyncContext async) {
        if (connection == null) {
            try {
                connection = new ServerTCP("127.0.0.1", 55000);
                connection.addObserver(this);
            } catch (IOException ex) {
                return;
            }
        }
        this.async = async;
        connection.sendRequest("multiplayer " + mazeName);
    }

    /**
     * adds self to server's observers to be notified of move info.
     * @param async
     */
    public void requestOthersMove(AsyncContext async) {
        if (connection == null) {
            try {
                connection = new ServerTCP("127.0.0.1", 55000);
                connection.addObserver(this);
            } catch (IOException ex) {
                return;
            }
        }
        this.async = async;
    }

    /**
     * sends movement info to game server.
     * @param move
     */
    public void makeMove(String move) {
        if (connection == null) {
            try {
                connection = new ServerTCP("127.0.0.1", 55000);
                connection.addObserver(this);
            } catch (IOException ex) {
                return;
            }
        }
        connection.sendRequest("play " + move);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (this.async == null)
            return;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arg.toString().getBytes(StandardCharsets.UTF_8));
        JsonReader jsonReader = Json.createReader(byteArrayInputStream);
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        
        AsyncContext asyncContext = this.async;
        HttpServletResponse peer = (HttpServletResponse) asyncContext.getResponse();
        peer.setContentType("application/json");
        try {
            peer.getWriter().write(object.toString());
            peer.getWriter().flush();
        } catch (IOException ex) {
            
        }
        peer.setStatus(HttpServletResponse.SC_OK);
        asyncContext.complete();
        this.async = null;
    }

    /**
     * closes connection.
     */
    public void disconnect() {
        connection = null;
    }
}
