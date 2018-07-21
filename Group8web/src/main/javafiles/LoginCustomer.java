package main.javafiles;



import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;
import main.javafiles.utils.DBConnection;
import main.javafiles.utils.StoredProcedures;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet("/checklogin")

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

		String message = "Invalid login details";
		String username = request.getParameter("lusername");
		String pwd = request.getParameter("lpassword");
		System.out.println(username);
	
	
        
     
         
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
			
			
			resultSet = calstat.executeQuery();
			
			while(resultSet.next()) {
				
				 userNameDB = resultSet.getString("username");
				 passwordDB = resultSet.getString("pwd");
				 
				 if(username.equals(userNameDB) && pwd.equals(passwordDB)) {
					 customerid=resultSet.getInt("customer_id");
					 HttpSession session = request.getSession(); //Creating a session for Student
			            session.setAttribute("cid", customerid); //setting session attribute
			            request.setAttribute("cid", customerid);
			            response.sendRedirect("CustomerPage.jsp");
					 
				 }
				
			}
			
			
         
             
            
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
        request.setAttribute("Message", message);

        // forwards to the message page
        //getServletContext().getRequestDispatcher("/Message.jsp").forward(request, response);
    }
}