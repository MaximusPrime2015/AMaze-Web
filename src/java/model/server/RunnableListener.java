package model.server;

/**
 *
 * @author Max
 */
public class RunnableListener implements Runnable{
    private SocketCommunicator sc;
    
    public RunnableListener(SocketCommunicator sc){
        this.sc = sc;
    }
    
    @Override
    public void run() {
        sc.listenToResponses();
    }
}
