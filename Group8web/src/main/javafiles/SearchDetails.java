package main.javafiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

import main.javafiles.utils.DBConnection;
import main.javafiles.utils.StoredProcedures;

public class SearchDetails {
	
	DBConnection dbConnection = new DBConnection();
	Connection conn;
	Statement stmt = null;
	Statement stmtDrop = null;
	StoredProcedures stp = new StoredProcedures();
	CallableStatement calstat= null;
	ResultSet resultSet = null;
	ResultSet resultSet1 = null;
	
public ArrayList<TechnicianDefect> gettechnician(String search) throws SQLException, ClassNotFoundException
	
	{
	 String searchmsg = search;
	 System.out.println(searchmsg);
	 ArrayList<TechnicianDefect> techlist = new ArrayList<TechnicianDefect> ();
    
    try {
        
        conn = dbConnection.getConnection();

        // constructs SQL statement
        
        
        stmtDrop = (Statement) conn.createStatement();
		//Dropping the existing procedure
		stmtDrop.execute(stp.dropTechbycategory);
		stmt = (Statement) conn.createStatement();
		//Creating New Procedure
		stmt.execute(stp.getTechbycategory);
		//Calling the Procedure

		calstat = (CallableStatement) conn.prepareCall("{call Techbycategory(?)}");
		calstat.setString(1,searchmsg);
		resultSet = calstat.executeQuery();
		while(resultSet.next()) {
			
			TechnicianDefect td = new TechnicianDefect();
			td.setFirstName(resultSet.getString("f_name"));
			td.setLastName(resultSet.getString("last_name"));
			td.setEmail(resultSet.getString("email_id"));
			td.setSkills(resultSet.getString("skills"));
			td.setPhone(resultSet.getString("phone_num"));
			td.setdefect_Category(resultSet.getString("categories"));
			techlist.add(td);
			 
			 
		}
		
		
		   conn.close();
		    calstat.close();
		    dbConnection.CloseSSHConnection();
     
         
        
    }
    catch (Exception ex) {
    	ex.printStackTrace();
       
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
    conn.close();
    calstat.close();
    dbConnection.CloseSSHConnection();
	return techlist;
	}
	

}
