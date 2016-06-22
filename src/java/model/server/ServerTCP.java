package model.server;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class ServerTCP extends Observable implements Observer, Server {
    private final SocketCommunicator server;
    private volatile boolean isClosed;
    private static ServerTCP instance = null;
    
    /**
     * get instance method for singleton.
     * @return
     */
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
   
    /**
     * constructor
     * @param ip ip string
     * @param port port number
     * @throws IOException
     */
    public ServerTCP(String ip, int port) throws IOException {
        this.server = new SocketCommunicator(ip, port);
        this.server.addObserver(this);
        this.isClosed = false;
    }

    /**
     * connects to a server using details stored in members.
     * @return true if connected successfully. false otherwise.
     */
    @Override
    public boolean connect() {
        this.server.addObserver(this);
        this.isClosed = false;
        return this.server.establishConnection();
    }

    /**
     * notifies observers if the server is not closed.
     * @param o object that executes update method.
     * @param arg arguments
     */
    @Override
    public void update(Observable o, Object arg) {
        if (!this.isClosed) {
            this.setChanged();
            notifyObservers(arg);
        }
    }
    
    /**
     * sends request to server.
     * @param request
     */
    @Override
    public void sendRequest(String request){
        this.server.SendRequest(request);
    }
    
    /**
     * closes server.
     */
    @Override
    public void close(){
        this.isClosed = true;
        this.server.deleteObserver(this);
        this.server.close();
    }

}
