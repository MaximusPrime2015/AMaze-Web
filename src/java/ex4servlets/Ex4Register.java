package ex4servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "Ex4Register", urlPatterns = {"/ex4Register"})
public class Ex4Register extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        this.doPost(req, resp);
    }
    
    /**
     * pulls user details from session, creates a new entry in the database,
     * and redirects to menu page.
     * @param request http request
     * @param resp http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int icon = Integer.parseInt(request.getParameter("icon"));
        
        UserDataBase userDataBase = UserDataBase.getDataBase();
        JsonObject jsonResponse = null;
        if (userDataBase.checkIfUserExists(username)) {
            // user already in data base
            // return a failure json response
            jsonResponse =  Json.createObjectBuilder()
                                .add("isSuccessful", false).build();
        } else {
            try {
                // add the user
                userDataBase.addUser(username, password, name, email, icon);
            } catch (Exception ex) {
                Logger.getLogger(Ex4Register.class.getName())
                        .log(Level.SEVERE, null, ex);
                return;
            }
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
