package main.javafiles;



import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

import main.javafiles.utils.DBConnection;
import main.javafiles.utils.StoredProcedures;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
 
@WebServlet("/logincheck")

public class LoginCustomer extends HttpServlet {
     
    // database connection settings
	
	DBConnection dbConnection = new DBConnection();
	Connection conn;
	Statement stmt = null;
	Statement stmtDrop = null;
	StoredProcedures stp = new StoredProcedures();
	CallableStatement calstat= null;
	ResultSet resultSet = null;
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    	
        // gets values of text fields

		String message = null;
		String username = request.getParameter("lusername");
		String pwd = request.getParameter("lpassword");
		 String page = "/CustomerLogin.jsp";
		
	
        
     
         
        try {
            
            conn = dbConnection.getConnection();
 
            // constructs SQL statement
            
            
            stmtDrop = (Statement) conn.createStatement();
			//Dropping the existing procedure
			stmtDrop.execute(stp.dropCustomerLogin);
			stmt = (Statement) conn.createStatement();
			//Creating New Procedure
			stmt.execute(stp.getCustomerDetails);
			//Calling the Procedure

			calstat = (CallableStatement) conn.prepareCall("{call CustomerLoginCheck()}");
			String userNameDB = null;
			String passwordDB = null;
			int customerid=0;
			String name = null;
			resultSet = calstat.executeQuery();
			while(resultSet.next()) {
				
				 userNameDB = resultSet.getString("username");
				 passwordDB = resultSet.getString("pwd");
				 
				
				if(username.equals(userNameDB) && pwd.equals(passwordDB)) {
					 customerid=resultSet.getInt("customer_id");
					 name = resultSet.getString("firstname");
					 HttpSession session = request.getSession(); //Creating a session for Student
			            session.setAttribute("cid", customerid); //setting session attribute
			            session.setAttribute("fname", name);
			            //System.out.println(session.getAttribute("fname"));
			            page= "/CustomerPage.jsp";
			            break;
			            
					 
				 }
				
				
				 
				 
			}
			
			
			
         
             
            
        }
        catch (SQLException ex) {
        		
            message = "Sorry....Page is under maintenance";
            ex.printStackTrace();
            request.setAttribute("Message", message);

            // forwards to the message page
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        } 
        finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                    calstat.close();
                    DBConnection.CloseSSHConnection();
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        request.setAttribute("Message", message);

        // forwards to the message page
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}






















































































































































































































/*package main.javafiles;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;
import main.javafiles.utils.DBConnection;
import main.javafiles.utils.StoredProcedures;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet("/checklogin")

public class LoginCustomer extends HttpServlet {
     
    // database connection settings
	
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	DBConnection dbConnection = new DBConnection();
	Connection conn;
	Statement stmt = null;
	Statement stmtDrop = null;
	StoredProcedures stp = new StoredProcedures();
	CallableStatement calstat= null;
	ResultSet resultSet = null;
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    	
        // gets values of text fields
    	String page = "";

		String message = "Invalid login details";
		String username = request.getParameter("lusername");
		String pwd = request.getParameter("lpassword");
		System.out.println(username);
	    VerifyLogin v = new VerifyLogin();	
        
     
         
        try {
            
            conn = dbConnection.getConnection();
            System.out.println(conn);
 
            // constructs SQL statement
            
            
            stmtDrop = (Statement) conn.createStatement();
			//Dropping the existing procedure
			stmtDrop.execute(stp.dropCustomerLogin);
			stmt = (Statement) conn.createStatement();
			//Creating New Procedure
			stmt.execute(stp.getCustomerDetails);
			//Calling the Procedure

			calstat = (CallableStatement) conn.prepareCall("{call CustomerLoginCheck()}");
			
			
			String userNameDB = null;
			String passwordDB = null;
			int customerid=0;
			String name = null;
			
			
			resultSet = calstat.executeQuery();
			
			
			while(resultSet.next()) {
				
				 userNameDB = resultSet.getString("username");
				 passwordDB = resultSet.getString("pwd");
				 
				 if(username.equals(userNameDB) && pwd.equals(passwordDB)) {
					 customerid=resultSet.getInt("customer_id");
					 name = resultSet.getString("firstname");
					 HttpSession session = request.getSession(); //Creating a session for Student
			            session.setAttribute("cid", customerid); //setting session attribute
			            session.setAttribute("fname", name);
			            //System.out.println(session.getAttribute("fname"));
			            page= "/CustomerPage.jsp";
			            
					 
				 }
				 else {
					 message = "Invalid Credentials!...Please Try Again";
					 
					 
					 page = "/CustomerLogin.jsp";
				 }
				
				 
				 
			}
			
			
			
			
			
			
			
			// response.sendRedirect(page);
             
            
        }
        catch (SQLException ex) {
        		
            message = "Sorry....Page is under maintenance";
            ex.printStackTrace();
            request.setAttribute("Message", message);

            // forwards to the message page
            getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        } 
        finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                    calstat.close();
                    dbConnection.CloseSSHConnection();
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        

        // forwards to the message page
        //getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}*/