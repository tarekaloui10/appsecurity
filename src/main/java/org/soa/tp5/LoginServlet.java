package org.soa.tp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if ("admin".equals(login) && "1234".equals(password)) {
            response.getWriter().println("Connexion réussie !");
        } else {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp?status=error"
            );
        }
    }
}