package com.example.survey;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "PremiumServlet", value = "/PremiumServlet")
public class PremiumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int is_admin = Integer.parseInt(request.getParameter("is_admin"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","jsbachBWV827");
            PreparedStatement stmt = con.prepareStatement("update users set is_admin = ? where mail = ? ");
            stmt.setInt(1,is_admin);
            stmt.setString(2,username);
            stmt.executeUpdate();
            con.close();
            stmt.close();
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (SQLException e) {response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
        catch (Exception e1){response.setStatus(HttpServletResponse.SC_NOT_FOUND);}
    }
}
