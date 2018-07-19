package main.javafiles.utils;

//Class containing all the stored Procedure Variables.
public class StoredProcedures {

	public String dropinsertCustomer = "DROP PROCEDURE IF EXISTS RegisterCustomer;";
	public String insertCustomer = "CREATE PROCEDURE RegisterCustomer "
			+ "("
			+ "IN fn varchar(70),"
			+ "IN ln varchar(70),"
			+ "IN em varchar(70), "
			+ "IN un varchar(45),"
			+ "IN pwd varchar(20),"
			+ "IN addr varchar(150)) "			
			+ "BEGIN "
			+ "insert into Customer (firstname,lastname,email_id,usename,pass_word,Address) "
			+ "values (fn,ln,em,un,pwd,addr); "
			+ "END;";

}
