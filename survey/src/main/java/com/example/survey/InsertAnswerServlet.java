package com.example.survey;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "InsertAnswerServlet", value = "/InsertAnswerServlet")
public class InsertAnswerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id_survey = Integer.parseInt(request.getParameter("id_survey"));
        String mail = request.getParameter("mail");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","jsbachBWV827");
            PreparedStatement stmt = con.prepareStatement("insert into submitted_survey (id_survey,id_mail) " +
                    "values (?,?)");
            stmt.setInt(1,id_survey);
            stmt.setString(2,mail);
            stmt.executeUpdate();

            Gson gson = new Gson();
            Reader reader = request.getReader();
            QnA id_qna = gson.fromJson(reader,QnA.class);

            for (int i=0; i<id_qna.getId_qna().size();i++){
                stmt = con.prepareStatement("insert into submitted_answer (id_submitted_survey, id_question_answer)" +
                        " values (" +
                        "(select id" +
                        " from submitted_survey" +
                        " where id_mail = ? and id_survey = ?),?)");
                stmt.setString(1,mail);
                stmt.setInt(2,id_survey);
                stmt.setInt(3,id_qna.getId_qna().get(i));

                stmt.executeUpdate();
            }
            con.close();
            stmt.close();
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (SQLException e) {response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
        catch (Exception e1){response.setStatus(HttpServletResponse.SC_NOT_FOUND);}



    }
    public void destroy() {
    }
}

class QnA{
    private ArrayList<Integer> id_qna;

    public QnA(ArrayList<Integer> id_qna) {
        this.id_qna = id_qna;
    }

    public ArrayList<Integer> getId_qna() {
        return id_qna;
    }
}