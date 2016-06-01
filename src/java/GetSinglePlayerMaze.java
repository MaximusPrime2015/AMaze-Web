
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
 * @author user
 */
@WebServlet(name = "GetSinglePlayerMaze", urlPatterns = {"/secure/GetSinglePlayerMaze"}, asyncSupported=true)
public class GetSinglePlayerMaze extends HttpServlet implements Observer {

    ServerTCP server;
    Dictionary<String, AsyncContext> usersWaiting = new Hashtable<String, AsyncContext>();
    
    @Override
    public void init() throws ServletException {
        this.server = ServerTCP.getInstance();
        this.server.addObserver(this);
    }

    @Override
    public void destroy() {
        this.server.deleteObserver(this);
    }
    
    String json2 = "{\"Type\":2,\"Content\":{\"Name\":\"iM\",\"Maze\":\"222220000100*00100021112111111121111102100210100222001010211121011121011101021002222222101010002111010101011101011200101010101222222221111111111121110122220010000002001012012111110111211111201200100010120012221121111111012111210002220010022200121011012111112111112110101200101200100200011121110121111121101002222222001002000111010101011111211000001010101#222200\",\"Start\":{\"Row\":0,\"Col\":12},\"End\":{\"Row\":18,\"Col\":12}}}";
    String json3 = "{\"Type\":2,\"Content\":{\"Name\":\"iM\",\"Maze\":\"222220000100*001000211121110111211111021002001010022200102111211101110121110#10120010001012222211012111011111010120122200100010101012012111110111011111222200100000001010022111111101011101112210101000101002222221010111011111210102222200101010021010011121111101112111101002001002222200100111211111210101110010021002221010101001112111210111110100100222221000000000\",\"Start\":{\"Row\":0,\"Col\":12},\"End\":{\"Row\":4,\"Col\":0}}}";

    
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
    
    private String GetRandomName() {
        String name = "maze_";
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            name += ('a' + rnd.nextInt(25));
        }
        return name;
    }

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
            } catch (IOException ex) {
                Logger.getLogger(GetSinglePlayerMaze.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
