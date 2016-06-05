import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDatabase;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    /**
     * redirects to menu page if there exists a session,
     * otherwise redirects to registration page.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            response.sendRedirect("secure/Menu");
        }
        else {
            request.getRequestDispatcher("Register.jsp")
                    .forward(request, response);
        }
    }

    /**
     * pulls user details from session, creates a new entry in the database,
     * and redirects to menu page.
     * @param request http request
     * @param resp http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        // register the user in the data base
        UserDatabase db = UserDatabase.getInstance();
        db.addUser(request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("real_name"),
                    request.getParameter("email"),
                    request.getParameter("icons"));
        // create a session for him
        HttpSession session = request.getSession();
        session.setAttribute("username", request.getParameter("username"));
        session.setAttribute("name", request.getParameter("real_name"));
        session.setAttribute("email", request.getParameter("email"));
        session.setAttribute("pic", request.getParameter("icons"));
        // send him to the menu
        resp.sendRedirect("secure/Menu");
    }
}
