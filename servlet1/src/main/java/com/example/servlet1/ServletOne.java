package com.example.servlet1;

import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "ServletOne", value = "/servlet-one")
public class ServletOne extends HttpServlet {
    private String message;

    public void init() {
        message = "Lista studenti";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String a = request.getParameter("a");
        String b = request.getParameter("b");
        String c = request.getParameter("c");
        String d = request.getParameter("d");
        String e = request.getParameter("e");

        PrintWriter out = response.getWriter();
        ArrayList<String> studenti = new ArrayList<>();
        studenti.add(a);
        studenti.add(b);
        studenti.add(c);
        studenti.add(d);
        studenti.add(e);

        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        for (int i=0; i<studenti.size();i++){
            out.println("<p>"+"Studente #"+(i+1) +": "+ studenti.get(i) + "</p>");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        String a = req.getParameter("a");
        String b = req.getParameter("b");
        String c = req.getParameter("c");
        String d = req.getParameter("d");
        String e = req.getParameter("e");
        ArrayList<String> studenti = new ArrayList<>();
        studenti.add(a);
        studenti.add(b);
        studenti.add(c);
        studenti.add(d);
        studenti.add(e);
        PrintWriter o = resp.getWriter();
        for (int i=0; i<studenti.size();i++){
            o.println("<p>"+"Studente #"+(i+1) +": "+ studenti.get(i) + "</p>");
        }
        o.println("</body></html>");
    }

    public void destroy() {
    }
}
