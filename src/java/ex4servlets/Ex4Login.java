package ex4servlets;

import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ex4.UserDataBase;

/**
 * exe 3
 * @author Michael Vassernis 319582888 vaserm3
 * @author 
 */
@WebServlet(name = "Ex4Login", urlPatterns = {"/ex4Login"})
public class Ex4Login extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UserDataBase userDataBase = UserDataBase.getDataBase();
        JsonObject jsonResponse = null;
        if (!userDataBase.checkIfUserExists(username) || 
                !userDataBase.getUser(username).checkPassword(password)) {
            // return a failure json response
            jsonResponse =  Json.createObjectBuilder()
                                .add("isSuccessful", false).build();
        } else {
            // create a session for him
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            // return a success json response
            jsonResponse =  Json.createObjectBuilder()
                                .add("isSuccessful", true).build();
        }
        // write back the json response
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse.toString());
        resp.getWriter().flush();
    }
}
