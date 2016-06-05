package model.server;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
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
