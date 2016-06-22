import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ex4MessageDataBase;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author 
 */
@WebServlet(name = "ex4CheckIfUpdated", urlPatterns = {"/ex4CheckIfUpdated"})
public class ex4CheckIfUpdated extends HttpServlet {

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
        int numMsgs = Integer.parseInt(request.getParameter("currentNumOfMessages"));
        ex4MessageDataBase messageDataBase = ex4MessageDataBase.getDataBase();
        
        // write back the json response
        JsonObject jsonResponse = Json.createObjectBuilder()
                                    .add("isUpdated", numMsgs == messageDataBase.getSize()).build();
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse.toString());
        resp.getWriter().flush();
    }
}
