package main.javafiles;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@WebServlet("/postnewdefect")
@MultipartConfig(maxFileSize = 16177215) 

public class PostDefect extends HttpServlet {
    
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
		String dname = request.getParameter("defectName");
		String[] dcategory = request.getParameterValues("category");
		 String ddeadline = request.getParameter("deadline");
		 String ddescription = request.getParameter("description");
		 Date deadline = null;
	       
	        DateFormat formater;
	        formater = new SimpleDateFormat("dd-MM-yyyy");
			try {
				deadline =  formater.parse(ddeadline);
				
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			java.sql.Date sqlddeadline = new java.sql.Date(deadline.getDate());
			
			System.out.println("from date:"+deadline);
			
	        InputStream inputStream = null; // input stream of the upload file
	         
	        // obtains the upload file part in this multipart request
	        Part filePart = request.getPart("photo");
	        if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	             
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();
	        }
	         
		
		System.out.println(dname);
	
	
        
     
         
        try {
            
            conn = DBConnection.getConnection();
 
            // constructs SQL statement
            
            
            stmtDrop = (Statement) conn.createStatement();
			//Dropping the existing procedure
			stmtDrop.execute(stp.dropInsertDefect);
			stmt = (Statement) conn.createStatement();
			//Creating New Procedure
			stmt.execute(stp.insertDefect);
			//Calling the Procedure

			calstat = (CallableStatement) conn.prepareCall("{call AddDefect(?,?,?,?,?)}");
			
			calstat.setString(1,dname);
			calstat.setString(2,dcategory[0]);
			calstat.setString(3,ddescription);
			
			if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
				calstat.setBlob(4, inputStream);
				
            }
			calstat.setDate(5,sqlddeadline);
			
			if(!calstat.execute()) {
				message = "defect added successfully";
			HttpSession Session = request.getSession(false); 
			if(Session!=null && Session.getAttribute("cid") != null) {
				int customer_id = (int) Session.getAttribute("cid");
				stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropDefectid);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.getDefectid);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call Defectid(?)}");
				
				calstat.setString(1,dname);
				
				resultSet = calstat.executeQuery();
				int defectid = 0;
				while(resultSet.next()) {
				defectid = resultSet.getInt("defect_id");
				}
				
				stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropcustomerdefect);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.insertcustomerdefect);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call TagCustomerDefect(?,?)}");
				
				calstat.setInt(1,customer_id);
				calstat.setInt(2,defectid);
				if(!calstat.execute()) {
					message = "Record added";
				}
				
				
			}
			
			}
	       
        }
        catch (SQLException ex) {
        		
            message = "error :"+ex.getMessage();
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
        request.setAttribute("Message", message);

        // forwards to the message page
        getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
    }
}