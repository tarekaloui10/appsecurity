
package org.soa.tp5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/appsecurity_db";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Ouvrir une connexion à MySQL
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // VERSION 1 : VULNERABLE

    public boolean loginVulnerable(String login, String password)
            throws SQLException {

        String sql = "SELECT * FROM users WHERE login = '"
                + login + "' AND password = '" + password + "'";

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next();
        }
    }

    // VERSION 2 : SECURISEE
    public boolean loginSecure(String login, String password)
            throws SQLException {

        String sql = "SELECT * FROM users WHERE login = ? AND password = ?";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, login);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
