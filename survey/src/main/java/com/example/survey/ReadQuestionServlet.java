package com.example.survey;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "ReadQuestionServlet", value = "/ReadQuestionServlet")
public class ReadQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int id_survey = Integer.parseInt(request.getParameter("id_survey"));
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey", "root", "jsbachBWV827");
            PreparedStatement stmt = con.prepareStatement("select id_question_answer, id_question, id_category, question, id_answer, answer" +
                    " from (" +
                    "select id_question_answer,id_question, id_category, question, id_answer" +
                    " from(" +
                    "select *" +
                    "from survey_composition " +
                    "left join question_answer on survey_composition.id_question_answer = question_answer.id \n" +
                    "where id_survey = ? " +
                    ") as lol" +
                    " left join question on lol.id_question = question.id" +
                    " ) as lmao" +
                    " left join answer on lmao.id_answer = answer.id " +
                    " order by id_question");
            stmt.setInt(1,id_survey);
            ResultSet rs = stmt.executeQuery();
            Question q = null;
            boolean neverBeen = false;
            int id_prec = 0;
            while (rs.next()){
                int id_question = rs.getInt("id_question");
                if (id_question != id_prec){
                    if(neverBeen){questions.add(q);}
                    neverBeen = true;
                    id_prec = id_question;
                    int id_category = rs.getInt("id_category");
                    String question = rs.getString("question");
                    q = new Question(id_question,id_category,question);
                }
                int id_answer = rs.getInt("id_answer");
                String answer = rs.getString("answer");
                int id_qna = rs.getInt("id_question_answer");
                q.addAnswer(new Answer(id_answer,answer,id_qna));
            }
            questions.add(q);
            con.close();
            stmt.close();
            Gson gson = new Gson();
            String json = gson.toJson(questions);
            out.print(json);
        }
        catch (SQLException e) {response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
        catch (Exception e1){response.setStatus(HttpServletResponse.SC_NOT_FOUND);}


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

class Question{
    private int id;
    private int id_category;
    private String question;
    private ArrayList<Answer> answers = new ArrayList<>();

    public Question(int id, int id_category, String question) {
        this.id = id;
        this.id_category = id_category;
        this.question = question;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    public void addAnswer(Answer answer){ this.answers.add(answer); }
}

class Answer{
    private int id;
    private String answer;
    private int id_qna;

    public Answer(int id, String answer, int id_qna) {
        this.id = id;
        this.answer = answer;
        this.id_qna = id_qna;
    }
}