package model.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
public class SocketCommunicator extends Observable{
    private Socket server;
    private Thread listenerThread;
    private DataOutputStream outToServer;
    private DataInputStream inFromServer;

    /**
     * constructor.
     * @param serverIP string ip
     * @param serverPort port number
     * @throws IOException
     */
    public SocketCommunicator(String serverIP, int serverPort) throws IOException {
        this.server = new Socket(serverIP, serverPort);
        this.outToServer = new DataOutputStream(server.getOutputStream());
        this.inFromServer = new DataInputStream(server.getInputStream());
        this.listenerThread = new Thread(new RunnableListener(this));
        this.listenerThread.start();
    }

    /**
     * @return true
     */
    public boolean establishConnection() {
        return true;
    }

    /**
     * sends request to server.
     * @param request
     * @return
     */
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

    /**
     * awaits responses and notifies observers when response arrives.
     */
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

    /**
     * closes server.
     * @return true if closed successfully. false otherwise.
     */
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
