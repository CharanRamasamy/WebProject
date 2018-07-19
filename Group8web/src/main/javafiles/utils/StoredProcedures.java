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

}
