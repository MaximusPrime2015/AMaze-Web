/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDatabase db = UserDatabase.getInstance();
        db.addUser(request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("real_name"),
                    request.getParameter("email"),
                    request.getParameter("profile"));
        
        HttpSession session = request.getSession();
        response.sendRedirect("secure/UserPrivateData");
        /*
        if (db.validateUser(username, password)) {
            HttpSession session = request.getSession();
            response.sendRedirect("secure/UserPrivateData");
        } else {
            request.setAttribute("error", true);
            request.getRequestDispatcher("Register.jsp").forward(request, response);

        }
        */
    }
}
