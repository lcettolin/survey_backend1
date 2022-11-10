package com.example.crud;
import com.google.gson.Gson;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "createServlet", value = "/createServlet")
public class createServlet extends HttpServlet {
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
            PreparedStatement stmt = con.prepareStatement("insert into crudisti values (?,?,?)");
            //PreparedStatement stmt = con.prepareStatement("insert into crudisti set column 1 = ?, column 2= ?, column 3=? ");
            stmt.setString(1,id);
            stmt.setString(2,name);
            stmt.setString(3,surname);
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
