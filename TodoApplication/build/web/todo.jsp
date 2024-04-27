<%-- 
    Document   : todo
    Created on : Apr 20, 2024, 2:20:19 PM
    Author     : LENOVO
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="db.DbConnector"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .three{
                width:700px;
                border: solid;
                border-radius: 10px;
            }
            #red{
                text-align: center;
                font-size: 25px;
            }
            .container{
                width: 50%;
                margin: 0 auto;
            padding: 20px;
                       }
            
            input[type="submit"] {
            width: 75px;
            
            
        }
        .one:hover ,button:hover{
            background-color: #45a049;
        }
        h1{
            text-align: center;
            font-size: 30px;
        }
        .two{
             width: 50%;
            padding: 8px;
            margin-bottom: 10px;
           
            box-sizing: border-box;

        }
            
            </style>
    </head>
    <body>
        <div class="container">
             <h1><strong>To Do Task</strong>  </h1>
                <form action="ToDo" method="post">
                    
        <input type="text" name="task" placeholder="Add new task...">
        <tr><td> <input class="one" type="submit" name="task" value="Add task"> 
                <input type="reset" class="one"  value="reset"></td>
             </tr>
  
                </form>
        </div>
                     <div class="container">
        <table class="three"border="1" border-color="solid">
            
     
        <%
            try{
                Connection con=DbConnector.getConnection();
            
               
                String str="select * from task";
                PreparedStatement pst=con.prepareStatement(str);
                
                ResultSet rs=pst.executeQuery(str);
            
                
            
           %>
           <%String task=(String)session.getAttribute("task");
               while(rs.next()){%>
    <tr>
        <td id="red"><%=rs.getString(1)%><br></td>
        <td id="red"><button onclose="deletitem('<%=task%>">Delet</button>
      <input class="one" type="submit" value="update" name="Update"><td>
    </tr> 
    
           <%}%>
<%}
catch(SQLException e)
            {
                System.out.println(e);
            }
            %>
        </table> 
        </div>
    </body>
</html>
