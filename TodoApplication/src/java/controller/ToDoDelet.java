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
import model.ToDoDeletAuthenticator;

/**
 *
 * @author LENOVO
 */
public class ToDoDelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String task= request.getParameter("task"); 
      ToDoDeletAuthenticator d1=new ToDoDeletAuthenticator();
      boolean delet1=d1.isDelet(task);
      if(delet1)
        {
           HttpSession session=request.getSession(true);
           try
   { Connection con=DbConnector.getConnection();
       String query="delete from task where Task1=?;";   
       System.out.println("query"+query);
       PreparedStatement pst = con.prepareStatement(query);
       pst.setString(1,task);
       int i=pst.executeUpdate();
       if(i>0)
       {
           System.out.println(i+"deleted inserted");
       }
       else
       {
           System.out.println("deletion failed.... ");
       }
   }
   catch(SQLException e)
   {
       System.out.println(e);
  }
    session.setAttribute("task",task);
    response.sendRedirect("todo.jsp");
        }
     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendRedirect("todo.jsp");
    }

   
}
