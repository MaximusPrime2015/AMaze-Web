package model.server;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author 
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
