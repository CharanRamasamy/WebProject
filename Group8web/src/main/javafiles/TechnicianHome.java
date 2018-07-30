package main.javafiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

import main.javafiles.utils.DBConnection;
import main.javafiles.utils.StoredProcedures;

public class TechnicianHome {
	
	DBConnection dbConnection = new DBConnection();
	Connection conn;
	Statement stmt = null;
	Statement stmtDrop = null;
	StoredProcedures stp = new StoredProcedures();
	CallableStatement calstat= null;
	ResultSet resultSet = null;
	ResultSet resultSet1 = null;
	
public ArrayList<Defects> getDefectsbyCategory(int id) throws SQLException, ClassNotFoundException
	
	{
		ArrayList<Defects> defectlist = new ArrayList<Defects>();
		try {
			int cid = 0;
			conn = dbConnection.getConnection();
			
	            // constructs SQL statement
			
			 stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropTechnicianDetails);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.getTechnicianDetailsbytid);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call TechnicianDetails(?)}");
				 
				
				
				calstat.setInt(1,id);
				String categoryname = null;
				resultSet = calstat.executeQuery();
				while(resultSet.next()) {
					categoryname = resultSet.getString("categories");
				}
	            
			
			
	            stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropDefectbyCategory);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.getDefectbyCategory);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call DefectbyCategory(?)}");
				 
				int defectid =0;
				int tech_id=0;
				String flag = null;
				
				calstat.setString(1, categoryname);
				
				resultSet = calstat.executeQuery();
				
			while (resultSet.next()) {
				
				flag=resultSet.getString("flag_status");
				defectid = resultSet.getInt("defect_id");
				stmtDrop = (Statement) conn.createStatement();
				//Dropping the existing procedure
				stmtDrop.execute(stp.dropTechnicianidforDid);
				stmt = (Statement) conn.createStatement();
				//Creating New Procedure
				stmt.execute(stp.getTechnicianidforDid);
				//Calling the Procedure

				calstat = (CallableStatement) conn.prepareCall("{call TechnicianidforDid(?)}");
				calstat.setInt(1, defectid);
				resultSet1 = calstat.executeQuery();
				while(resultSet1.next()) {
					tech_id = resultSet1.getInt("techid");
					System.out.println("bjhbsbv:"+tech_id);
					
				}
				
				if(flag.equals("Initiated")) {
				Defects defect = new Defects();
                defect.setDefect_id(resultSet.getInt("defect_id"));
				defect.setDefect_Name(resultSet.getString("defect_name"));
				defect.setdefect_Description(resultSet.getString("details"));
				defect.setdefect_Status(flag);
				defectlist.add(defect);
				}
				if(id==tech_id&&(flag.equals("Requested")||(flag.contains("Assigned")))) {
					Defects defect = new Defects();
					defect.setDefect_id(resultSet.getInt("defect_id"));
					defect.setDefect_Name(resultSet.getString("defect_name"));
					defect.setdefect_Description(resultSet.getString("details"));
					defect.setdefect_Status(flag);
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
