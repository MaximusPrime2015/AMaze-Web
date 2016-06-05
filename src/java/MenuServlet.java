
import java.io.IOException;
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
 * @author Michael/Max
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/secure/Menu"})
public class MenuServlet extends HttpServlet {
    
    /**
     * closes connection with server.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = UserDatabase.getInstance().getUser(session.getAttribute("username").toString());
        user.disconnect();
        request.getRequestDispatcher("../secure/Menu.jsp").forward(request, response);
    }
}
