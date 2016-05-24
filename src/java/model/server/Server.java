package model.server;

/**
 *
 * @author Max
 */
public interface Server {
    /// <summary>
    /// Connects to the Server.
    /// </summary>
    /// <returns>true if successful</returns>
    boolean connect();

    /// <summary>
    /// Closes the connection with the Server.
    /// </summary>
    void close();

    /// <summary>
    /// Sends the request to the server.
    /// </summary>
    /// <param name="request">The request.</param>
    void sendRequest(String request);
}
