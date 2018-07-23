package main.javafiles.utils;

//Class containing all the stored Procedure Variables.
public class StoredProcedures {

	public String dropinsertCustomer = "DROP PROCEDURE IF EXISTS RegisterCustomer;";
	public String insertCustomer = "CREATE PROCEDURE RegisterCustomer "
			+ "("
			+ "IN fn varchar(70),"
			+ "IN ln varchar(70),"
			+ "IN em varchar(45), "
			+ "IN un varchar(45),"
			+ "IN pd varchar(16),"
			+ "IN adr varchar(150), "	
			+ "IN pc varchar(6), "
			+ "IN ph varchar(12)) "
			+ "BEGIN "
			+ "insert into Customer (firstname,lastname,emailid,username,pwd,addr,postalcode,phnumber) "
			+ "values (fn,ln,em,un,pd,adr,pc,ph); "
			+ "END;";
	
	public String dropCustomerLogin = "DROP PROCEDURE IF EXISTS CustomerLoginCheck";
	public String getCustomerDetails = "CREATE PROCEDURE CustomerLoginCheck() "
			+ "BEGIN "
			+ "select customer_id,username,pwd,firstname from Customer; "
			+ "END;";
	
	public String dropDefectDetails = "DROP PROCEDURE IF EXISTS DefectDetails";
	public String getDefectDetails = "CREATE PROCEDURE DefectDetails "
			+ "("
			+ "IN did int)"
			+ "BEGIN "
			+ "select defect_name,category,details,flag_status from ServiceRequest where defect_id = did; "
			+ "END;";
	
	public String dropDefectbyCategory = "DROP PROCEDURE IF EXISTS DefectbyCategory";
	public String getDefectbyCategory = "CREATE PROCEDURE DefectbyCategory "
			+ "("
			+ "IN catname varchar(40))"
			+ "BEGIN "
			+ "select defect_name,details from ServiceRequest where category = catname; "
			+ "END;";
	
	public String dropDefectidforCid = "DROP PROCEDURE IF EXISTS DefectidforCid";
	public String getDefectidforCid = "CREATE PROCEDURE DefectidforCid "
			+ "("
			+ "IN cid int)"
			+ "BEGIN "
			+ "select defect_id from CustomerServiceRequests where customer_id = cid; "
			+ "END;";
	
	public String dropInsertDefect = "DROP PROCEDURE IF EXISTS AddDefect;";
	public String insertDefect = "CREATE PROCEDURE AddDefect "
			+ "("
			+ "IN dname varchar(100),"
			+ "IN dcategory varchar(40),"
			+ "IN ddescription VARCHAR(45), "
			+ "IN dphoto mediumblob, "
			+ "IN ddeadline date) "
			+ "BEGIN "
			+ "insert into ServiceRequest (defect_name,category,defectphoto,defectdeadline,details) "
			+ "values (dname,dcategory,dphoto,ddeadline,ddescription); "
			+ "END;";
	
	public String dropDefectid = "DROP PROCEDURE IF EXISTS Defectid";
	public String getDefectid = "CREATE PROCEDURE Defectid "
			+ "("
			+ "IN dname varchar(100)) "
			+ "BEGIN "
			+ "select defect_id from ServiceRequest where defect_name = dname; "
			+ "END;";
	
	public String dropcustomerdefect = "DROP PROCEDURE IF EXISTS TagCustomerDefect;";
	public String insertcustomerdefect = "CREATE PROCEDURE TagCustomerDefect "
			+ "("
			+ "IN cid int,"
			+ "IN did int)"
			+ "BEGIN "
			+ "insert into CustomerServiceRequests (customer_id,defect_id) "
			+ "values (cid,did); "
			+ "END;";
	

}
