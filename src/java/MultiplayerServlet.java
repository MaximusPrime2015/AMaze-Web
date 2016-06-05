import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author 
 */
@WebServlet(name = "MultiplayerServlet", urlPatterns = {"/secure/Multiplayer"})
public class MultiplayerServlet extends HttpServlet {

    /**
     * redirects to multiplayer page.
     * @param request http request
     * @param resp http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        request.getRequestDispatcher("../secure/Multiplayer.jsp")
                                        .forward(request, resp);
    }
}
