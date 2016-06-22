import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.UserDatabase;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm 
 */
@WebServlet(name = "GetMultiplayerMoves",
            urlPatterns = {"/secure/GetMultiplayerMoves"}, asyncSupported=true)
public class GetMultiplayerMoves extends HttpServlet {
    
    /**
     * wait for move from other user.
     * @param request http request
     * @param resp http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        // get the async object for long polling
        AsyncContext async = request.startAsync(request, resp);
        async.setTimeout(0);
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = UserDatabase.getInstance()
                    .getUser(session.getAttribute("username").toString());
        // make the user wait with long pollng for the opponents move
        user.requestOthersMove(async);
    }
    
    /**
     * sends movement information to game server.
     * @param request http request
     * @param resp http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        String move = request.getParameter("move");
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        // get user from session
        User user = UserDatabase.getInstance()
                        .getUser(session.getAttribute("username").toString());
        // send the move to the server
        user.makeMove(move);
    }
}
