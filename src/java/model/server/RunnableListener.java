package model.server;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
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
