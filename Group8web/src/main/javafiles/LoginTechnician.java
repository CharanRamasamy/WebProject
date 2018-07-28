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
 
@WebServlet("/logintechnician")

public class LoginTechnician extends HttpServlet {
     
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
		 String page = "/TechnicianLogin.jsp";
		
	
        
     
         
        try {
            
            conn = dbConnection.getConnection();
 
            // constructs SQL statement
            
            
            stmtDrop = (Statement) conn.createStatement();
			//Dropping the existing procedure
			stmtDrop.execute(stp.dropTechnicianLogin);
			stmt = (Statement) conn.createStatement();
			//Creating New Procedure
			stmt.execute(stp.getTechnicianDetails);
			//Calling the Procedure

			calstat = (CallableStatement) conn.prepareCall("{call TechnicianLogin()}");
			String userNameDB = null;
			String passwordDB = null;
			int technicianid=0;
			String name = null;
			resultSet = calstat.executeQuery();
			while(resultSet.next()) {
				
				 userNameDB = resultSet.getString("u_name");
				 passwordDB = resultSet.getString("pswd");
				 
				
				if(username.equals(userNameDB) && pwd.equals(passwordDB)) {
					technicianid=resultSet.getInt("Technician_id");
					 name = resultSet.getString("f_name");
					 HttpSession session = request.getSession(); //Creating a session for Student
			            session.setAttribute("tid", technicianid); //setting session attribute
			            session.setAttribute("tfname", name);
			            page= "/TechnicianHome.jsp";
			            break;
			            
					 
				 }
				
				
				 
				 
			}
			if(page=="/TechnicianLogin.jsp") {
				message = "Invalid Credentials...Try Again!";
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


















































































