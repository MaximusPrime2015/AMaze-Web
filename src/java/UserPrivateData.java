
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Max
 */
@WebServlet(name = "UserPrivateData", urlPatterns = {"/secure/UserPrivateData"})
public class UserPrivateData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> list = new ArrayList<>();
        list.add(new User("DanTheMan","123"));
        list.add(new User("Jerald","Arr"));
        list.add(new User("Georgi","georg"));

        request.getSession().setAttribute("list", list);
        request.getRequestDispatcher("Data.jsp").forward(request, response);
    }
}
