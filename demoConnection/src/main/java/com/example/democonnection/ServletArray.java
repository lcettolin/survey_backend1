package com.example.democonnection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

class Studente{
    private String nome;
    private String cognome;
    public Studente(String nome, String cognome){
        this.nome = nome;
        this.cognome = cognome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
}


@WebServlet(name = "servlet-Array", value = "/servlet-Array")
public class ServletArray extends HttpServlet {
    private String message;

    public void init() {
        message = "Lista studenti";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1 style=\'text-align:center\'>" + message + "</h1>");
        out.println("<p><span style=\"font-family:Courier New,Courier,monospace\">"+"I dati riportati in tabella corrispondo a studenti generati autumaticamente con il solo " +
                "scopo di testare il corretto funzionamento della lettura dati da parte dell'applicazione." +"</span></p>");
        ArrayList<Studente> studenti = new ArrayList<>();

        for(int i=1;i<=10; i++){
            studenti.add(new Studente("nome"+i, "cognome"+i));
        }

        out.println("<table align='center' border='10' cellpadding='1' cellspacing='1' style='width:500px'>");
        out.println("<thead><tr><th scope='col'>Nome</th><th scope='col'>Cognome</th></tr></thead>");
        out.println("<tbody>");
        for (int i=0; i<studenti.size();i++){
            out.println("<tr>");
            out.println("<td>"+studenti.get(i).getNome()+"</td>");
            out.println("<td>"+studenti.get(i).getCognome()+"</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");
        out.println("</body></html>");
    }

}
