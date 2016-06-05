
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.server.ServerTCP;

/**
 *
 * @author Michael
 */
@WebServlet(name = "GetSinglePlayerMaze", urlPatterns = {"/secure/GetSinglePlayerMaze"}, asyncSupported=true)
public class GetSinglePlayerMaze extends HttpServlet implements Observer {

    ServerTCP server;
    Dictionary<String, AsyncContext> usersWaiting = new Hashtable<String, AsyncContext>();
    
    /**
     * initializes server, and adds self to its observers list.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        this.server = ServerTCP.getInstance();
        this.server.addObserver(this);
    }

    /**
     * removes self from server's observers.
     */
    @Override
    public void destroy() {
        this.server.deleteObserver(this);
    }

    /**
     * sends a 'generate maze' request to game server, and adds the request
     * to a wait list.
     * @param request http request
     * @param response http response
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
        this.usersWaiting.put(mazeName, async);
        server.sendRequest("generate " + mazeName + " 1");
    }
    
    /**
     * Generates a random name for a singleplayer game.
     * @return random name string
     * @throws ServletException
     * @throws IOException
     */
    private String GetRandomName() {
        String name = "maze_";
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            name += ('a' + rnd.nextInt(25));
        }
        return name;
    }

    /**
     * Handles responses from game server.
     * @param o object to update.
     * @param arg arguments.
     */
    @Override
    public void update(Observable o, Object arg) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arg.toString().getBytes(StandardCharsets.UTF_8));
        JsonReader jsonReader = Json.createReader(byteArrayInputStream);
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        String mazeName;
        if (object.get("Type").toString().equals("1")) {
            mazeName = object.getJsonObject("Content").getString("Name");
            server.sendRequest("solve " + mazeName + " 1");
        } else if (object.get("Type").toString().equals("2")) {
            try {
                mazeName = object.getJsonObject("Content").getString("Name");
                AsyncContext asyncContext = this.usersWaiting.get(mazeName);
                HttpServletResponse peer = (HttpServletResponse) asyncContext.getResponse();
                peer.setContentType("application/json");
                peer.getWriter().write(object.toString());
                peer.getWriter().flush();
                peer.setStatus(HttpServletResponse.SC_OK);
                asyncContext.complete();
                usersWaiting.remove(mazeName);
            } catch (IOException ex) {
                Logger.getLogger(GetSinglePlayerMaze.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
