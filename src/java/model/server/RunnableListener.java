package model.server;

/**
 *
 * @author Max/Michael
 */
public class RunnableListener implements Runnable{
    private SocketCommunicator sc;
    
    /**
     * constructor.
     * @param sc
     */
    public RunnableListener(SocketCommunicator sc){
        this.sc = sc;
    }
    
    /**
     * runs listenToResponses method in SocketCommunicator.
     */
    @Override
    public void run() {
        sc.listenToResponses();
    }
}
