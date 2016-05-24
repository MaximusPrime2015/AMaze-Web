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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            response.sendRedirect("secure/Menu");
        }
        else {
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDatabase db = UserDatabase.getInstance();
        db.addUser(request.getParameter("username"),
                    request.getParameter("password"),
                    request.getParameter("real_name"),
                    request.getParameter("email"),
                    request.getParameter("icons"));
        
        HttpSession session = request.getSession();
        session.setAttribute("username", request.getParameter("username"));
        session.setAttribute("name", request.getParameter("real_name"));
        session.setAttribute("email", request.getParameter("email"));
        session.setAttribute("pic", request.getParameter("icons"));
        
        response.sendRedirect("secure/Menu");
    }
}
