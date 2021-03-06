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
@WebServlet(name = "SingleplayerServlet",
                                        urlPatterns = {"/secure/Singleplayer"})
public class SingleplayerServlet extends HttpServlet {

    /**
     * redirects to singleplayer page.
     * @param request http request
     * @param response http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../secure/Singleplayer.jsp")
                                        .forward(request, response);
    }
}
