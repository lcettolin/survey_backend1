package com.example.survey;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","jsbachBWV827");
            PreparedStatement stmt = con.prepareStatement("insert into users values (?,?,0)");
            stmt.setString(1,mail);
            stmt.setString(2,password);
            stmt.executeUpdate();
            con.close();
            stmt.close();
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (SQLException e) {response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
        catch (Exception e1){response.setStatus(HttpServletResponse.SC_NOT_FOUND);}

    }
    public void destroy() {
    }
}
