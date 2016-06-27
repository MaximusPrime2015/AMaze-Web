import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserEx3;
import model.UserDatabaseEx3;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/secure/Menu"})
public class MenuServlet extends HttpServlet {
    
    /**
     * closes connection with server.
     * @param request http request
     * @param resp http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        UserEx3 user = UserDatabaseEx3.getInstance()
                        .getUser(session.getAttribute("username").toString());
        user.disconnect();
        request.getRequestDispatcher("../secure/Menu.jsp")
                                    .forward(request, resp);
    }
}
