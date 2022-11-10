package com.example.crud;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "updateServlet", value = "/updateServlet")
public class updateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        //Gson gson = new Gson();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","jsbachBWV827");
            PreparedStatement stmt = con.prepareStatement("update crudisti set nome = ?, cognome = ?  where id = ?");
            stmt.setString(3,id);
            stmt.setString(1,name);
            stmt.setString(2,surname);
            int i = stmt.executeUpdate();
            con.close();
            stmt.close();
        }
        catch (Exception e)
        {
            out.println("<h1>" + e.getMessage() + "</h1>");
            System.out.println(e.getStackTrace());
        }

    }
}
