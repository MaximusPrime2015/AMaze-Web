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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    /**
     * redirects users to login screen if session has ended,
     * or redirects to Menu page after a successful login.
     * @param request http request.
     * @param resp http response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            // already loged in -> send to menu
            resp.sendRedirect("secure/Menu");
        }
        else {
            // send to login page
            request.getRequestDispatcher("Login.jsp").forward(request, resp);
        }
    }

    /**
     * validates user login details, and sets them in the session.
     * if user details are invalid they are redirected to login page.
     * @param request http request.
     * @param resp http response.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        UserDatabaseEx3 db = UserDatabaseEx3.getInstance();
        // get the login info
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // check if registerd
        if (db.validateUser(username, password)) {
            HttpSession session = request.getSession();
            UserEx3 user = db.getUser(username);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("name", user.getRealName());
            session.setAttribute("pic", user.getProfile());
            resp.sendRedirect("secure/Menu");
        } else {
            // not registered -> send to login again
            request.setAttribute("error", true);
            request.getRequestDispatcher("Login.jsp").forward(request, resp);

        }
    }
}
