package com.example.survey;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","jsbachBWV827");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from category");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new Category(id,name) );
            }
            con.close();
            stmt.close();
            Gson gson = new Gson();
            String json = gson.toJson(categories);
            out.print(json);
        }
        catch (SQLException e) {response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
        catch (Exception e1){response.setStatus(HttpServletResponse.SC_NOT_FOUND);}

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

class Category{
    private int id;
    private String name;

    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }
}