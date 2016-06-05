
import java.io.IOException;
import java.util.Random;
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
 *
 * @author user
 */
@WebServlet(name = "GetMultiplayerMoves", urlPatterns = {"/secure/GetMultiplayerMoves"}, asyncSupported=true)
public class GetMultiplayerMoves extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        AsyncContext async = request.startAsync(request, response);
        async.setTimeout(0);
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = UserDatabase.getInstance().getUser(session.getAttribute("username").toString());
        user.requestOthersMove(async);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String move = request.getParameter("move");
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = UserDatabase.getInstance().getUser(session.getAttribute("username").toString());
        user.makeMove(move);
    }
}
