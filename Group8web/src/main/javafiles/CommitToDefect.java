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
 
@WebServlet("/commitDefect")

public class CommitToDefect extends HttpServlet {
     
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
		String fee = request.getParameter("proposedfee");
		
		String comments = request.getParameter("Comments");
		String defect_name = request.getParameter("defectname");
		
		String pass = request.getParameter("passvalue");
        
     
         
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
		int techid=0;
		HttpSession session = request.getSession(false); 
		if(session!=null && session.getAttribute("tid") != null) 
		{	
			
			  techid = (int)session.getAttribute("tid");
		}
		stmtDrop.execute(stp.droptechniciandefect);
		
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.inserttechniciandefect);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call TagTechniciandefect(?,?,?,?,?)}");
		
		calstat.setInt(1,defectid );
		
		calstat.setInt(2,techid );
		calstat.setString(3, "Requested");
		calstat.setString(4, fee);
		
		calstat.setString(5,comments);
		calstat.execute();
		
		stmtDrop.execute(stp.dropUpdateflag);
		
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.insertUpdateflag);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call UpdateFlag(?,?)}");
		
		calstat.setInt(1,defectid );
		
		if(pass.equals("commit")) {
		calstat.setString(2, "Requested");
		}
		else if(pass.equals("commit")) {
			calstat.setString(2, "Defect Closed successfully");
		}
		
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
        getServletContext().getRequestDispatcher("/TechnicianHome.jsp").forward(request, response);
    }
}


















































































