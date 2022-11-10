package com.example.survey;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "SurveyListServlet", value = "/SurveyListServlet")
public class SurveyListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int currentEntry = (Integer.parseInt(request.getParameter("currentPage"))-1)*pageSize;
        String id_category = request.getParameter("id_category");
        String mail = request.getParameter("mail");

        ArrayList<Survey> surveys = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey","root","jsbachBWV827");
            PreparedStatement stmt = null;
            PreparedStatement stmt1 = null;

            if(id_category.isEmpty()){
                stmt = con.prepareStatement("select survey_table.*, category.name as category  " +
                        "from survey_table " +
                        "left join category on survey_table.id_category = category.id " +
                        " limit ?,?");
                stmt.setInt(1,currentEntry);
                stmt.setInt(2,pageSize);

            }else{
                stmt = con.prepareStatement("select survey_table.*, category.name as category  " +
                        "from survey_table " +
                        "left join category on survey_table.id_category = category.id " +
                        "where category.id = ? limit ?,?");
                stmt.setInt(1,Integer.parseInt(id_category));
                stmt.setInt(2,currentEntry);
                stmt.setInt(3,pageSize);
            }
            ResultSet rs = stmt.executeQuery();

            stmt1 = con.prepareStatement("select id_survey " +
                    "from submitted_survey " +
                    "where id_mail = ? ");
            stmt1.setString(1,mail);
            ResultSet rs1 = stmt1.executeQuery();
            ArrayList<Integer> surveysDone = new ArrayList<>();
            while (rs1.next()){
                surveysDone.add(rs1.getInt("id_survey"));
            }

            while (rs.next()){
                int id = rs.getInt("id");
                String id_mail = rs.getString("id_mail");
                String category = rs.getString("category");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String publish_date = rs.getString("publish_date");
                String ending_date = rs.getString("ending_date");
                Survey s = new Survey(id,id_mail,category,name,description,publish_date,ending_date);
                if(surveysDone.contains(id)){
                    s.setDone(true);
                }
                surveys.add(s);
            }

            con.close();
            stmt.close();
            stmt1.close();
            Gson gson = new Gson();
            String json = gson.toJson(surveys);
            out.print(json);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (SQLException e) {response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);}
        catch (Exception e1){response.setStatus(HttpServletResponse.SC_NOT_FOUND);}

    }
    public void destroy() {
    }
}

class Survey{
    private int id;
    private String mail;
    private String category;
    private String name;
    private String description;
    private String publish_date;
    private String ending_date;
    protected boolean done = false;
    public Survey(int id, String mail, String category, String name, String description, String publish_date, String ending_date){
        this.id = id;
        this.mail = mail;
        this.category = category;
        this.name = name;
        this.description = description;
        this.publish_date = publish_date;
        this.ending_date = ending_date;
    }
    public void setDone(boolean done) {this.done = done;}
    public boolean getDone() {return this.done;}
}