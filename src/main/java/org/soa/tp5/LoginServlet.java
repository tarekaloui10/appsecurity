
package org.soa.tp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String version = request.getParameter("version");

        if (login == null || password == null) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp?status=error");
            return;
        }

        try {
            boolean authenticated;

            if ("vulnerable".equals(version)) {
                authenticated =
                        userDAO.loginVulnerable(login, password);
            } else {
                authenticated =
                        userDAO.loginSecure(login, password);
            }

            if (authenticated) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println(
                        "<h2>Connexion réussie !</h2>");
            } else {
                response.sendRedirect(
                        request.getContextPath()
                                + "/login.jsp?status=error");
            }

        } catch (SQLException e) {
            getServletContext().log(
                    "Erreur lors de l'authentification", e);

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Une erreur est survenue lors de l'authentification.");
        }
    }
}
