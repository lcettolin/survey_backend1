package com.example.login;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login-servlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {


    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ArrayList<String> users = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        /*users.add("user");
        passwords.add("pass");*/

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/username","root","jsbachBWV827");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from utenti");
            while (rs.next()){
                String user = rs.getString("utente");
                users.add(user);
                String pass = rs.getString("password");
                passwords.add(pass);
            }
            con.close();
            stmt.close();
        }
        catch (Exception e)
        {
            out.println("<h1>" + e.getMessage() + "</h1>");
            System.out.println(e.getStackTrace());
        }

        if(users.contains(username)&&passwords.contains(password)){
            out.println("<h2>Correct! </h2>");
            int status =HttpServletResponse.SC_OK;
            response.setStatus(status);
        }
        else {
            out.println("<h2>Incorrect username or password </h2>");
            int status = HttpServletResponse.SC_UNAUTHORIZED;
            response.setStatus(status);
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
