package com.example.login;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/get-all-students")
public class StudentServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //response.setContentType("text/html");

        try {
            ArrayList<Student> students = new ArrayList<>();
            for (int i=0; i<103; i++) {
                int index = i+1;
                Student s = new Student("Mario "+index, "Rossi "+index);
                students.add(s);
            }

            Gson gson = new Gson();
            String json = gson.toJson(students);

            PrintWriter out = response.getWriter();
            out.print(json);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}

class Student{
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
}