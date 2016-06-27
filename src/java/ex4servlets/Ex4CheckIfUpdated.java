package ex4servlets;

import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ex4.MessageDataBase;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author Max Anisimov 322068487 anisimm
 */
@WebServlet(name = "Ex4CheckIfUpdated", urlPatterns = {"/ex4CheckIfUpdated"})
public class Ex4CheckIfUpdated extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
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
        int numMsgs = Integer.parseInt(
                            request.getParameter("currentNumOfMessages"));
        MessageDataBase messageDataBase = MessageDataBase.getDataBase();
        
        // write back the json response
        JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("isUpdated",
                    numMsgs == messageDataBase.getSize()).build();
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse.toString());
        resp.getWriter().flush();
    }
}
