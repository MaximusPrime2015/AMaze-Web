import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ex4Message;
import model.ex4MessageDataBase;
import model.ex4User;
import model.ex4UserDataBase;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author 
 */
@WebServlet(name = "ex4AddMessage", urlPatterns = {"/ex4AddMessage"})
public class ex4AddMessage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
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
        String message = request.getParameter("message");
        String time = new SimpleDateFormat("HH:mm:ss").format(
                                Calendar.getInstance().getTime());
        ex4UserDataBase userDataBase = ex4UserDataBase.getDataBase();
        ex4MessageDataBase messageDataBase = ex4MessageDataBase.getDataBase();
        ex4User user = userDataBase.getUser((String)request.getSession().getAttribute("username"));
        ex4Message msg = messageDataBase.addMessage(message, user, time);
        
        // write back the json response
        resp.setContentType("application/json");
        resp.getWriter().write(messageDataBase.messageToJson(msg).toString());
        resp.getWriter().flush();
    }
}
