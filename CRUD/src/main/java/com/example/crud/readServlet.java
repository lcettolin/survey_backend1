package com.example.crud;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "readServlet", value = "/readServlet")
public class readServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<Crudist> crudists = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","jsbachBWV827");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from crudisti");
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("nome");
                String surname = rs.getString("cognome");
                crudists.add(new Crudist(id,name,surname) );
            }
            con.close();
            stmt.close();
        }
        catch (Exception e)
        {
            out.println("<h1>" + e.getMessage() + "</h1>");
            System.out.println(e.getStackTrace());
        }
        try {

            Gson gson = new Gson();
            String json = gson.toJson(crudists);

            out.print(json);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}


class Crudist{
    private String id;
    private String name;
    private String surname;

    public Crudist(String id, String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}