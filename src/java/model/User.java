package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import model.server.ServerTCP;

/**
 *
 * @author Max
 */
public class User implements Observer {

    private String username;
    private String password;
    private String realName;
    private String email;
    private String profile;
    
    private ServerTCP connection = null;
    private AsyncContext async;

    public User(String username, String password, String realName,
            String email, String profile) {
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.email = email;
        this.profile = profile;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRealName() {
        return this.realName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getProfile() {
        return this.profile;
    }

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

    public void disconnect() {
        connection = null;
    }
}
