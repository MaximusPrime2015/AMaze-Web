/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDatabase;

/**
 *
 * @author Max
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        if (session != null) {
            response.sendRedirect("secure/Menu");
        }
        else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDatabase db = UserDatabase.getInstance();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (db.validateUser(username, password)) {
            HttpSession session = request.getSession();
            response.sendRedirect("secure/UserPrivateData");
        } else {
            request.setAttribute("error", true);
            request.getRequestDispatcher("Login.jsp").forward(request, response);

        }
    }
}
