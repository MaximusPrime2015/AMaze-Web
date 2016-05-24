package model.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

/**
 *
 * @author Max
 */
public class SocketCommunicator extends Observable{
    private Socket server;
    private Thread listenerThread;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    public SocketCommunicator(String serverIP, int serverPort) throws IOException {
        this.server = new Socket(serverIP, serverPort);
        this.outToServer = new DataOutputStream(server.getOutputStream());
        this.inFromServer = new BufferedReader(
                new InputStreamReader(server.getInputStream()));
    }

    public boolean establishConnection() {
        if (this.server.isConnected()) {
            return true;
        }
        if (this.listenerThread != null) {
            return false;
        }

        try {
            this.listenerThread = new Thread(new RunnableListener(this));
            this.listenerThread.start();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean SendRequest(String request) {
        if (this.server.isConnected() && request != null) {
            try {
                this.outToServer.writeBytes(request + '\n');
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
                response = this.inFromServer.readLine();
                int len = response.length();
                if (len == 0) {
                    // server closed
                    notifyObservers(null);
                    break;
                }
                //this.OnResponse(response);
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
