package model.server;

/**
 *
 * @author Max/Michael
 */
public interface Server {
    /// <summary>
    /// Connects to the Server.
    /// </summary>
    /// <returns>true if successful</returns>

    /**
     *
     * @return
     */
    boolean connect();

    /// <summary>
    /// Closes the connection with the Server.
    /// </summary>

    /**
     *
     */
    void close();

    /// <summary>
    /// Sends the request to the server.
    /// </summary>
    /// <param name="request">The request.</param>

    /**
     *
     * @param request
     */
    void sendRequest(String request);
}
