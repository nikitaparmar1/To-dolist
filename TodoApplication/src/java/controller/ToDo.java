/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db.DbConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TaskAuthenticator;

/**
 *
 * @author LENOVO
 */
public class ToDo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String task= request.getParameter("task"); 
      TaskAuthenticator t1=new TaskAuthenticator();
      boolean task1=t1.istaskk(task);
      if(task1)
        {
           HttpSession session=request.getSession(true);
           try
   { Connection con=DbConnector.getConnection();
       String query="insert into task values(?)";   
       System.out.println("query"+query);
       PreparedStatement pst = con.prepareStatement(query);
       pst.setString(1,task);
       int i=pst.executeUpdate();
       if(i>0)
       {
           System.out.println(i+"record inserted");
       }
       else
       {
           System.out.println("insertion failed.... ");
       }
   }
   catch(SQLException e)
   {
       System.out.println("e");
   }
    session.setAttribute("task",task);
    response.sendRedirect("todo.jsp");
        }
     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendRedirect("index.html");
    }

    
}
