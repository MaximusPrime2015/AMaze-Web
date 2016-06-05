
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
 * @author Max/Michael
 */
@WebServlet(name = "GetMultiplayerMaze", urlPatterns = {"/secure/GetMultiplayerMaze"}, asyncSupported=true)
public class GetMultiplayerMaze extends HttpServlet {
    
    private volatile boolean createNewName = true;
    private String oldName;
    
    /**
     * Sends a multiplayer game request to game server.
     * @param request Http request
     * @param response Http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mazeName = GetRandomName();
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        AsyncContext async = request.startAsync(request, response);
        async.setTimeout(0);
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        User user = UserDatabase.getInstance().getUser(session.getAttribute("username").toString());
        user.requestMultiPlayerGame(mazeName, async);
    }
    
    /**
     * Generates a random name for a multiplayer game.
     * @return random name string
     * @throws ServletException
     * @throws IOException
     */
    private synchronized String GetRandomName() {
        if (createNewName) {
            createNewName = false;
            String name = "multi_maze_";
            Random rnd = new Random();
            for (int i = 0; i < 5; i++) {
                name += ('a' + rnd.nextInt(25));
            }
            oldName = name;
            return name;
        } else {
            createNewName = true;
            return oldName;
        }
    }
}
