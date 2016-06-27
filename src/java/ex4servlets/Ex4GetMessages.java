package ex4servlets;

import java.io.IOException;
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
 * @author 
 */
@WebServlet(name = "Ex4GetMessages", urlPatterns = {"/ex4GetMessages"})
public class Ex4GetMessages extends HttpServlet {

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
        int toRead = Integer.parseInt(request.getParameter("messagesToGet"));
        MessageDataBase messageDataBase = MessageDataBase.getDataBase();
        
        // write back the json response
        JsonObject jsonResponse = messageDataBase.getMessages(toRead);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse.toString());
        resp.getWriter().flush();
    }
}
