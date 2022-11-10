package com.example.survey;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","jsbachBWV827");
            PreparedStatement stmt = con.prepareStatement("select pass, is_admin from users where mail = ? ");
            stmt.setString(1,username);
            ResultSet rs =  stmt.executeQuery();

            if(rs.next() && password.equals(rs.getString("pass"))){
                String admin = rs.getString("is_admin");
                out.print(admin);
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            con.close();
            stmt.close();
        }
        catch (SQLException e) {response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
        catch (Exception e1){response.setStatus(HttpServletResponse.SC_NOT_FOUND);}

    }
    public void destroy() {
    }


}
