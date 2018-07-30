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

 

public class DefectDetails  {
	
	DBConnection dbConnection = new DBConnection();
	Connection conn;
	Statement stmt = null;
	Statement stmtDrop = null;
	StoredProcedures stp = new StoredProcedures();
	CallableStatement calstat= null;
	ResultSet resultSet = null;
	ResultSet resultSet1 = null;
	
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
				resultSet1 = calstat.executeQuery();
			while (resultSet1.next()) {
				
				Defects defect = new Defects();
				defect.setDefect_Name(resultSet1.getString("defect_name"));
				defect.setdefect_Category(resultSet1.getString("category"));
				defect.setdefect_Description(resultSet1.getString("details"));
				defect.setdefect_Status(resultSet1.getString("flag_status"));
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
	
	
	
	
public ArrayList<Defects> getRequestDefects(int cid) throws SQLException, ClassNotFoundException
	
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
				resultSet1 = calstat.executeQuery();
			while (resultSet1.next()) {
				
				if(resultSet1.getString("flag_status").equals("Requested")) {
				Defects defect = new Defects();
				defect.setDefect_Name(resultSet1.getString("defect_name"));
				defect.setdefect_Category(resultSet1.getString("category"));
				defect.setdefect_Description(resultSet1.getString("details"));
				defect.setdefect_Status(resultSet1.getString("flag_status"));
				defectlist.add(defect);
			}
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
	
	
	
	
	
	public CustomerDefect getCustomerandDefect(String defectname) {
		
		CustomerDefect cd = new CustomerDefect();
		
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
		
		calstat.setString(1,defectname);
		
		resultSet = calstat.executeQuery();
		int defectid = 0;
		while(resultSet.next()) {
		defectid = resultSet.getInt("defect_id");
	
	}
		
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
		
		//Defects defect = new Defects();
		cd.setDefect_Name(resultSet.getString("defect_name"));
		cd.setdefect_Category(resultSet.getString("category"));
		cd.setdefect_Description(resultSet.getString("details"));
	}
	
	 stmtDrop = (Statement) conn.createStatement();
		//Dropping the existing procedure
		stmtDrop.execute(stp.dropCustomeridforDid);
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.getCustomeridforDid);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call CustomeridforDid(?)}");
		 
		
		calstat.setInt(1, defectid);
		int customerid =0;
		resultSet = calstat.executeQuery();
		while(resultSet.next()) {
			customerid = resultSet.getInt("customer_id");
		
		}
		stmtDrop = (Statement) conn.createStatement();
		//Dropping the existing procedure
		stmtDrop.execute(stp.dropCustomerDetails);
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.getCustomerDetailsbycid);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call CustomerDetails(?)}");
		 
		 System.out.println("cd "+customerid);
		
		calstat.setInt(1, customerid);
		
		resultSet = calstat.executeQuery();
		
	
	while (resultSet.next()) {
		
		
		cd.setFirstName(resultSet.getString("firstname"));
		cd.setLastName(resultSet.getString("lastname"));
		cd.setEmail(resultSet.getString("emailid"));
		cd.setPhone(resultSet.getString("phnumber"));
		cd.setAddress(resultSet.getString("addr"));
		
		
	}
        
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		System.out.println(cd);
		return cd;

}
	
	
public TechnicianDefect getTechnicianandDefect(String defectname) {
		
		TechnicianDefect td = new TechnicianDefect();
		
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
		
		calstat.setString(1,defectname);
		
		resultSet = calstat.executeQuery();
		int defectid = 0;
		while(resultSet.next()) {
		defectid = resultSet.getInt("defect_id");
	
	}
		
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
		
		//Defects defect = new Defects();
		td.setDefect_Name(resultSet.getString("defect_name"));
		td.setdefect_Category(resultSet.getString("category"));
		td.setdefect_Description(resultSet.getString("details"));
	}
	
	 stmtDrop = (Statement) conn.createStatement();
		//Dropping the existing procedure
		stmtDrop.execute(stp.dropTechnicianidforDid);
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.getTechnicianidforDid);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call TechnicianidforDid(?)}");
		 
		
		calstat.setInt(1, defectid);
		int techid =0;
		resultSet = calstat.executeQuery();
		while(resultSet.next()) {
			techid = resultSet.getInt("techid");
		
		}
		stmtDrop = (Statement) conn.createStatement();
		//Dropping the existing procedure
		stmtDrop.execute(stp.dropTechnicianDetails);
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.getTechnicianDetailsbytid);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call TechnicianDetails(?)}");
		 
		 System.out.println("cd "+techid);
		
		calstat.setInt(1, techid);
		
		resultSet = calstat.executeQuery();
		
	
	while (resultSet.next()) {
		
		
		td.setFirstName(resultSet.getString("f_name"));
		td.setLastName(resultSet.getString("last_name"));
		td.setEmail(resultSet.getString("email_id"));
		td.setPhone(resultSet.getString("phone_num"));
		td.setAddress(resultSet.getString("address"));
		td.setSkills(resultSet.getString("skills"));
		
		
	}
        
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return td;

}
}
