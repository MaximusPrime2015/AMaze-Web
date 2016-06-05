package model.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author 
 */
public class SocketCommunicator extends Observable{
    private Socket server;
    private Thread listenerThread;
    private DataOutputStream outToServer;
    private DataInputStream inFromServer;

    public SocketCommunicator(String serverIP, int serverPort) throws IOException {
        this.server = new Socket(serverIP, serverPort);
        this.outToServer = new DataOutputStream(server.getOutputStream());
        this.inFromServer = new DataInputStream(server.getInputStream());
        this.listenerThread = new Thread(new RunnableListener(this));
        this.listenerThread.start();
    }

    public boolean establishConnection() {
//        if (this.server.isConnected()) {
//            return true;
//        }
//        if (this.listenerThread != null) {
//            return false;
//        }
//
//        try {
//            this.listenerThread = new Thread(new RunnableListener(this));
//            this.listenerThread.start();
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
        return true;
    }

    public boolean SendRequest(String request) {
        if (this.server.isConnected() && request != null) {
            try {
                this.outToServer.write(request.getBytes(), 0, request.getBytes().length);
                return true;
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public void listenToResponses() {
        String response;
        
        while (true) {
            try {
                byte[] b = new byte[4096];
                int len = this.inFromServer.read(b);
                //int len = response.length();
                if (len == 0) {
                    // server closed
                    notifyObservers(null);
                    break;
                }
                response = new String(b);
                //this.OnResponse(response);
                this.setChanged();
                notifyObservers(response);
            } catch (Exception ex) {
                // server closed
                //this.OnResponse(null);
                notifyObservers(null);
                break;
            }
        }
    }

    /// <summary>
    /// Closes the connection with the server and stops listenning.
    /// </summary>
    /// <returns></returns>
    public boolean close() {
        try {
            this.server.close();
            this.listenerThread.join();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
