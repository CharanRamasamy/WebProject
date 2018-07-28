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
import javax.servlet.http.Part;
 
@WebServlet("/addcustomer")

public class Customerregister extends HttpServlet {
     
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
		String ln = request.getParameter("lname");
		String fn = request.getParameter("fname");
		System.out.println(ln);
		
		String em = request.getParameter("email");
		String un = request.getParameter("username");
		String pwd = request.getParameter("password");
		String addr = request.getParameter("address");
		String ph = request.getParameter("phone");
		String pc = request.getParameter("code");
		
		
	
        
     
         
        try {
            
            conn = dbConnection.getConnection();
 
            // constructs SQL statement
            
            
            stmtDrop = (Statement) conn.createStatement();
			//Dropping the existing procedure
			stmtDrop.execute(stp.dropinsertCustomer);
			stmt = (Statement) conn.createStatement();
			//Creating New Procedure
			stmt.execute(stp.insertCustomer);
			//Calling the Procedure

			calstat = (CallableStatement) conn.prepareCall("{call RegisterCustomer(?,?,?,?,?,?,?,?)}");
			
			
			calstat.setString(1,fn);
			calstat.setString(2,ln);
			calstat.setString(3,em);
			calstat.setString(4,un);
			calstat.setString(5,pwd);
			calstat.setString(6,addr);
			calstat.setString(7,pc);
			calstat.setString(8, ph);
			
			
			calstat.execute();
			
			
         
             
            
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
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}