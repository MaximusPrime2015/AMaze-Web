import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Max/Michael
 */
@WebServlet(name = "DisconnectServlet", urlPatterns = {"/secure/DisconnectServlet"})
public class DisconnectServlet extends HttpServlet {

    /**
     * redirects to login page after disconnection.
     * @param request Http request
     * @param response Htt response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("../LoginServlet");
    }
    
}
