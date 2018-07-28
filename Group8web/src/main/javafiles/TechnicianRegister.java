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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/addtechnician")

public class TechnicianRegister extends HttpServlet {
     
    // database connection settings
	
	/**
	 * 
	 */
	
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
		String sk = request.getParameter("skills");
		String addr = request.getParameter("address");
		String ph = request.getParameter("phone");
		String pc = request.getParameter("code");
		String[] cat = request.getParameterValues("category");
		String ca = cat[0];
		
         
        try {
            
            conn = dbConnection.getConnection();
 
            // constructs SQL statement
            
            
            stmtDrop = (Statement) conn.createStatement();
			//Dropping the existing procedure
			stmtDrop.execute(stp.dropinsertTechnician);
			stmt = (Statement) conn.createStatement();
			//Creating New Procedure
			stmt.execute(stp.insertTechnician);
			//Calling the Procedure

			calstat = (CallableStatement) conn.prepareCall("{call RegisterTechnician(?,?,?,?,?,?,?,?,?,?)}");
			
			
			calstat.setString(1,fn);
			calstat.setString(2,ln);
			calstat.setString(3,em);
			calstat.setString(4,un);
			calstat.setString(5,pwd);
			calstat.setString(6,addr);
			calstat.setString(7,pc);
			calstat.setString(8, ph);
			calstat.setString(9, sk);
			calstat.setString(10,ca);
			
			
			calstat.execute();
			
			
			calstat.close();
         
             
            
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
                    dbConnection.CloseSSHConnection();
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        dbConnection.CloseSSHConnection();
        request.setAttribute("Message", message);

        // forwards to the message page
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
    
}