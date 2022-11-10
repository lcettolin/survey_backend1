package com.example.democonnection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


import java.io.*;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "servlet-File", value = "/servlet-File")

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 *1,
        maxFileSize = 1024 * 1024 *10,
        maxRequestSize = 1024*1024*100
)
public class ServletFile extends HttpServlet {
    private String message;

    public void init() {
        message = "Lista studenti";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Unlucky! File not found :(</h1>");
        out.println("<p><span style=\"font-family:Courier New,Courier,monospace\">File has to be upload by Postmann </span></p>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        try{
            Part filePart = req.getPart("file");
            if(filePart == null){
                resp.getWriter().print("The file is null");
                out.println("</body>");
                out.println("</html>");
                System.out.println("Passo di qui");
                return;
            }
            String fileName = filePart.getSubmittedFileName();
            for(Part part : req.getParts()){
                part.write(getServletContext().getRealPath("/WEB-INF/")+fileName);
            }
            resp.getWriter().println("<h1>The file was uploaded successfully</h1>");
            File outFile = new File(getServletContext().getRealPath("/WEB-INF/")+fileName);
            if(!outFile.exists()){
                resp.getWriter().print("The file is null");
                out.println("</body>");
                out.println("</html>");
                System.out.println("Passo di qui");
                return;
            }
            Scanner fileRead = new Scanner(outFile);

            String splitBy =",";
            out.println("<table align='center' border='10' cellpadding='1' cellspacing='1' style='width:500px'>");
            out.println("<thead><tr><th scope='col'>Nome</th><th scope='col'>Cognome</th></tr></thead>");
            out.println("<tbody>");
            while (fileRead.hasNextLine()){
                out.println("<tr>");
                String data = fileRead.nextLine();
                String[] studente = data.split(splitBy);
                out.println("<td>"+studente[0]+"</td>");
                out.println("<td>"+studente[1]+"</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            fileRead.close();
        }
        catch (IOException e) {
            resp.getWriter().println(e.getMessage());
        }
        out.println("</body>");
        out.println("</html>");
    }

    public void destroy() {
    }
}