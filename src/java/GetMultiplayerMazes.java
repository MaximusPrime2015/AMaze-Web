
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
 * @author Max
 */
@WebServlet(name = "GetMultiplayerMazes", urlPatterns = {"/secure/GetMultiplayerMazes"}, asyncSupported=true)
public class GetMultiplayerMazes extends HttpServlet implements Observer {

    ServerTCP server;
    private String gameName;
    private JsonObject multiplayerData;
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
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        AsyncContext async = request.startAsync(request, response);
        async.setTimeout(0);
        this.addToWaitingQueue(gameName, async);
        server.sendRequest("multiplayer " + this.gameName);
    }
    
    private void addToWaitingQueue(String user, AsyncContext async){
        if(this.usersWaiting.get(user+"_1") != null){
            this.usersWaiting.put(user+"_2", async);
            return;
        }
        this.usersWaiting.put(user+"_1", async);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.gameName = request.getParameter("name");
    }

    @Override
    public void update(Observable o, Object arg) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arg.toString().getBytes(StandardCharsets.UTF_8));
        JsonReader jsonReader = Json.createReader(byteArrayInputStream);
        JsonObject object = jsonReader.readObject();
        jsonReader.close();
        String mazeName;
        
        if (object.get("Type").toString().equals("2")) {
            /*
            try {  
                
                mazeName = object.getJsonObject("Content").getString("Name");
                AsyncContext asyncContext = this.usersWaiting.get(mazeName);
                HttpServletResponse peer = (HttpServletResponse) asyncContext.getResponse();
                peer.setContentType("application/json");
                //System.out.println("mp solve: "+object.getJsonObject("Content"));
                
                // generate new json object containing the solved maze of the client
                JsonObject responseObj = Json.createObjectBuilder()
                        .add("firstName",Json.createObjectBuilder()
                                .add("Name", this.multiplayerData.getString("Name"))
                                .add("MazeName", this.multiplayerData.getString("MazeName"))
                                .add("You", object.getJsonObject("Content"))
                                .add("Other", this.multiplayerData.getString("Other"))
                                )
                        .build();
                
                //System.out.println("mp response: "+object.getJsonObject("Content"));
                peer.getWriter().write(responseObj.toString());
                peer.getWriter().flush();
                peer.setStatus(HttpServletResponse.SC_OK);
                asyncContext.complete();
            } catch (IOException ex) {
                Logger.getLogger(GetSinglePlayerMaze.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
        } else if(object.get("Type").toString().equals("3")){
            /*
            //System.out.println("multiplayer started");
            this.multiplayerData = object.getJsonObject("Content");
            mazeName = this.multiplayerData.getString("MazeName");
            this.server.sendRequest("solve " + mazeName + " 1");
            */
            
        } else if(object.get("Type").toString().equals("4")){
            
        }
    }
}