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
	
	public String dropinsertTechnician = "DROP PROCEDURE IF EXISTS RegisterTechnician;";
	public String insertTechnician = "CREATE PROCEDURE RegisterTechnician "
			+ "("
			+ "IN fn varchar(70),"
			+ "IN ln varchar(70),"
			+ "IN em varchar(45), "
			+ "IN un varchar(45),"
			+ "IN pd varchar(16),"
			+ "IN adr varchar(150), "	
			+ "IN pc varchar(6), "
			+ "IN ph varchar(12), "
			+ "IN sk varchar(45), "
			+ "IN cat varchar(45)) "
			+ "BEGIN "
			+ "insert into Technician(f_name,last_name,email_id,u_name,pswd,address,postal_code,phone_num,skills,categories) "
			+ "values (fn,ln,em,un,pd,adr,pc,ph,sk,cat); "
			+ "END;";
	
	public String dropCustomerLogin = "DROP PROCEDURE IF EXISTS CustomerLoginCheck";
	public String getCustomerDetails = "CREATE PROCEDURE CustomerLoginCheck() "
			+ "BEGIN "
			+ "select customer_id,username,pwd,firstname from Customer; "
			+ "END;";
	
	
	public String dropTechnicianLogin = "DROP PROCEDURE IF EXISTS TechnicianLogin";
	public String getTechnicianDetails = "CREATE PROCEDURE TechnicianLogin() "
			+ "BEGIN "
			+ "select Technician_id,f_name,last_name,u_name,pswd from Technician; "
			+ "END;";
	
	public String dropCustomerDetails = "DROP PROCEDURE IF EXISTS CustomerDetails";
	public String getCustomerDetailsbycid = "CREATE PROCEDURE CustomerDetails "
			+ "("
			+ "IN cid int)"
			+ "BEGIN "
			+ "select firstname,lastname,emailid,phnumber,addr from Customer where customer_id = cid; "
			+ "END;";
	
	public String dropTechnicianDetails = "DROP PROCEDURE IF EXISTS TechnicianDetails";
	public String getTechnicianDetailsbytid = "CREATE PROCEDURE TechnicianDetails "
			+ "("
			+ "IN tid int)"
			+ "BEGIN "
			+ "select f_name,last_name,email_id,skills,phone_num,categories,address,u_name,pswd from Technician where Technician_id = tid; "
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
			+ "select defect_id,defect_name,details,flag_status from ServiceRequest where category = catname; "
			+ "END;";
	
	public String dropDefectidforCid = "DROP PROCEDURE IF EXISTS DefectidforCid";
	public String getDefectidforCid = "CREATE PROCEDURE DefectidforCid "
			+ "("
			+ "IN cid int)"
			+ "BEGIN "
			+ "select defect_id from CustomerServiceRequests where customer_id = cid; "
			+ "END;";
	public String dropCustomeridforDid = "DROP PROCEDURE IF EXISTS CustomeridforDid";
	public String getCustomeridforDid = "CREATE PROCEDURE CustomeridforDid "
			+ "("
			+ "IN did int)"
			+ "BEGIN "
			+ "select customer_id from CustomerServiceRequests where defect_id = did; "
			+ "END;";
	
	public String dropTechnicianidforDid = "DROP PROCEDURE IF EXISTS TechnicianidforDid";
	public String getTechnicianidforDid = "CREATE PROCEDURE TechnicianidforDid "
			+ "("
			+ "IN did int)"
			+ "BEGIN "
			+ "select techid,fee,comments from TechnicianServiceRequests where defectid = did; "
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
	
	public String droptechniciandefect = "DROP PROCEDURE IF EXISTS TagTechniciandefect;";
	public String inserttechniciandefect = "CREATE PROCEDURE TagTechniciandefect "
			+ "("
			+ "IN did int,"
			+ "IN tid int,"
			+ "IN fl varchar(45),"
			+ "IN fe varchar(45),"
			+ "IN com varchar(100))"
			+ "BEGIN "
			+ "insert into TechnicianServiceRequests (defectid,techid,flag,fee,comments) "
			+ "values (did,tid,fl,fe,com); "
			+ "END;";
	
	public String dropUpdateflag = "DROP PROCEDURE IF EXISTS UpdateFlag";
	public String insertUpdateflag = "CREATE PROCEDURE UpdateFlag "
			+ "("
			+ "IN did int,"
			+ "IN msg varchar(45))"		
			+ "BEGIN "
			+ "UPDATE ServiceRequest SET flag_status = msg WHERE defect_id = did; "
			+ "END;";
	
	public String dropUpdateflaginTech = "DROP PROCEDURE IF EXISTS UpdateflaginTech";
	public String getUpdateflaginTech = "CREATE PROCEDURE UpdateflaginTech "
			+ "("
			+ "IN did int,"
			+ "IN msg varchar(45))"		
			+ "BEGIN "
			+ "UPDATE TechnicianServiceRequests SET flag = msg WHERE defectid = did; "
			+ "END;";
	
	

}
