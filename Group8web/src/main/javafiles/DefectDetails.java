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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

public class DefectDetails extends HttpServlet {
	
	DBConnection dbConnection = new DBConnection();
	Connection conn;
	Statement stmt = null;
	Statement stmtDrop = null;
	StoredProcedures stp = new StoredProcedures();
	CallableStatement calstat= null;
	ResultSet resultSet = null;
	
	public ArrayList<Defects> getDefects(int cid) throws SQLException, ClassNotFoundException
	
	{
		ArrayList<Defects> defectlist = new ArrayList<Defects>();
		try {
			conn = dbConnection.getConnection();
			
	            // constructs SQL statement
	            
	            
	            stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropDefectidforCid);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.getDefectidforCid);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call DefectidforCid(?)}");
				 int customerid = cid;
				 System.out.println("cd "+customerid);
				
				calstat.setInt(1, customerid);
				int defectid =0;
				resultSet = calstat.executeQuery();
			while (resultSet.next()) {
				
				defectid = resultSet.getInt("defect_id");
				System.out.println("dd "+defectid);
				stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropDefectDetails);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.getDefectDetails);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call DefectDetails(?)}");
				
				
				calstat.setInt(1, defectid);
				resultSet = calstat.executeQuery();
			while (resultSet.next()) {
				
				Defects defect = new Defects();
				defect.setDefect_Name(resultSet.getString("defect_name"));
				defect.setdefect_Category(resultSet.getString("category"));
				defect.setdefect_Description(resultSet.getString("details"));
				defect.setdefect_Status(resultSet.getString("flag_status"));
				defectlist.add(defect);
			}
		        
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
		return defectlist;
		}

}
