package model.server;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max
 */
public class ServerTCP extends Observable implements Observer, Server {

    private static ServerTCP instance = null;
    
    public synchronized static ServerTCP getInstance(){
        if (ServerTCP.instance == null) {
            try {
                instance = new ServerTCP("127.0.0.1", 55000);
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
    
    private final SocketCommunicator server;
    private volatile boolean isClosed;

    public ServerTCP(String ip, int port) throws IOException {
        this.server = new SocketCommunicator(ip, port);
    }

    @Override
    public boolean connect() {
        this.server.addObserver(this);
        this.isClosed = false;
        return this.server.establishConnection();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!this.isClosed) {
            notifyObservers(arg);
        }
    }
    
    @Override
    public void sendRequest(String request){
        this.server.SendRequest(request);
    }
    
    @Override
    public void close(){
        this.isClosed = true;
        this.server.deleteObserver(this);
        this.server.close();
    }

}
