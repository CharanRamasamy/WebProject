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
 
@WebServlet("/DeclineRequest")

public class DeclineDecision extends HttpServlet {
     
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
		String defect_name = request.getParameter("defectname");
	
        
     
         
        try {
            
        	conn = dbConnection.getConnection();
			stmtDrop = (Statement) conn.createStatement();
		
		//Dropping the existing procedure
		
			stmtDrop.execute(stp.dropDefectid);
		
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.getDefectid);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call Defectid(?)}");
		
		calstat.setString(1,defect_name);
		
		resultSet = calstat.executeQuery();
		int defectid = 0;
		while(resultSet.next()) {
		defectid = resultSet.getInt("defect_id");
	
	}
		
		stmtDrop.execute(stp.dropUpdateflaginTech);
		
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.getUpdateflaginTech);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call UpdateflaginTech(?,?)}");
		
		calstat.setInt(1,defectid );
		calstat.setString(2, "Declined");
		calstat.execute();
		
		stmtDrop.execute(stp.dropUpdateflag);
		
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.insertUpdateflag);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call UpdateFlag(?,?)}");
		
		calstat.setInt(1,defectid );
		
		
		calstat.setString(2, "Initiated");
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
        getServletContext().getRequestDispatcher("/CustomerPage.jsp").forward(request, response);
    }
}


















































































