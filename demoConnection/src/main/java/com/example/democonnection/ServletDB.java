package com.example.democonnection;

import java.sql.*;

import java.io.*;
import java.sql.DriverManager;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "servlet-DB", value = "/servlet-DB")
public class ServletDB extends HttpServlet {
    private String message;

    public void init() {
        message = "Lista studenti";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1 style=\'text-align:center\'>" + message + "</h1>");
        out.println("<p>"+"<span style=\"font-family:Courier New,Courier,monospace\">"+"I dati riportati in tabella corrispondo agli studenti registrati." +"</span></p>");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prova","root","jsbachBWV827");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from studenti");
            out.println("<table align='center' border='10' cellpadding='1' cellspacing='1' style='width:500px'>");
            out.println("<thead><tr><th scope='col'>Matricola</th><th scope='col'>Nome</th><th scope='col'>Cognome</th></tr></thead>");
            out.println("<tbody>");
            while (rs.next()){
                int codiceByIndex = rs.getInt(1);
                String matricola = rs.getString("matricola");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                out.println("<td style='text-align:center'>"+matricola+"</td>");
                out.println("<td>"+nome+"</td>");
                out.println("<td>"+cognome+"</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            con.close();
            stmt.close();
        }
        catch (Exception e)
        {
            out.println("<h1>" + e.getMessage() + "</h1>");
            System.out.println(e.getStackTrace());
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
