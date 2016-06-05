
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
 * @author Max/Michael
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * redirects users to login screen if session has ended,
     * or redirects to Menu page after a successful login.
     * @param request http request.
     * @param response http response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            response.sendRedirect("secure/Menu");
        }
        else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    /**
     * validates user login details, and sets them in the session.
     * if user details are invalid they are redirected to login page.
     * @param request http request.
     * @param response http response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDatabase db = UserDatabase.getInstance();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (db.validateUser(username, password)) {
            HttpSession session = request.getSession();
            User user = db.getUser(username);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("name", user.getRealName());
            session.setAttribute("pic", user.getProfile());
            response.sendRedirect("secure/Menu");
        } else {
            request.setAttribute("error", true);
            request.getRequestDispatcher("Login.jsp").forward(request, response);

        }
    }
}
