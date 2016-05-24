package model.server;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Max
 */
public class ServerTCP extends Observable implements Observer, Server {

    private SocketCommunicator server;
    private volatile boolean isClosed;

    public ServerTCP(String ip, int port) throws IOException {
        this.server = new SocketCommunicator(ip, port);
    }

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
    
    public void sendRequest(String request){
        this.server.SendRequest(request);
    }
    
    public void close(){
        this.isClosed = true;
        this.server.deleteObserver(this);
        this.server.close();
    }

}
