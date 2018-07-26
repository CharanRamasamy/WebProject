package main.javafiles;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;
import main.javafiles.utils.DBConnection;
import main.javafiles.utils.StoredProcedures;

 

public class CustomerDetails  {
	
	DBConnection dbConnection = new DBConnection();
	Connection conn;
	Statement stmt = null;
	Statement stmtDrop = null;
	StoredProcedures stp = new StoredProcedures();
	CallableStatement calstat= null;
	ResultSet resultSet = null;
	
	public Customers getCustomer(int cid) throws SQLException, ClassNotFoundException
	
	{
		Customers customer = new Customers();
		
		try {
			conn = dbConnection.getConnection();
			
	            // constructs SQL statement
	            
	            
	            stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropCustomerDetails);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.getCustomerDetailsbycid);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call CustomerDetails(?)}");
				 int customerid = cid;
				 System.out.println("cd "+customerid);
				
				calstat.setInt(1, customerid);
				
				resultSet = calstat.executeQuery();
				
			
			while (resultSet.next()) {
				
				
				customer.setFirstName(resultSet.getString("firstname"));
				customer.setLastName(resultSet.getString("lastname"));
				customer.setEmail(resultSet.getString("emailid"));
				customer.setPhone(resultSet.getString("phnumber"));
				customer.setAddress(resultSet.getString("addr"));
				
				
			}
		        
			
			 

		       
		        
			}
		catch (SQLException ex ) {
			ex.printStackTrace();
			}
		
			 finally
				{
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
		  conn.close();
          calstat.close();
          dbConnection.CloseSSHConnection();
          
          return customer;
		
		}

}
