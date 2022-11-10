package com.example.servlet2;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

class Divisione{
    private double a;
    private double b;

    public Divisione(double a, double b){
        this.a = a;
        this.b = b;
    }
    public Double div() throws Exception {
        if (this.b ==0){ throw new Exception("Divisione per zero: impossibile");}
        return this.a/this.b;
    }

}

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Disione!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String a = request.getParameter("a");
        String b = request.getParameter("b");
        Double a1 = Double.parseDouble(a);
        Double b1 = Double.parseDouble(b);
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        try {
            Divisione d = new Divisione(a1, b1);
            Double r = d.div();
            out.println("<p>" + "Il risulato della divisione è: " + r + "</p>");
            out.println("</body></html>");

        } catch (Exception e) { out.println("<p>" + e.getMessage() + "</p>");}
    }

    public void destroy() {
    }
}