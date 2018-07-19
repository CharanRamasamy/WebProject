package main.javafiles.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
 
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


 
public class DBConnection
{
	private static Session session = null;
	private static String strSshUser = "shanmukha";                  // SSH loging username
	private static String strSshPassword = "B00777449";                   // SSH login password
	private static String strSshHost = "bluenose.cs.dal.ca";          // hostname or ip or SSH server
	private static int nSshPort = 22;                                    // remote SSH host port number
	private static String strRemoteHost = "db.cs.dal.ca";  // hostname or ip of your database server
	private static int nLocalPort = 3366;                                // local port number use to bind SSH tunnel
	private static int nRemotePort = 3306;                               // remote port number of your database
	private static String strDbUser = "shanmukha";                    // database loging username
	private static String strDbPassword = "B00777449";                    // database login password
	
  private static void doSshTunnel( String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort ) throws JSchException
  {
    final JSch jsch = new JSch();
    session = jsch.getSession( strSshUser, strSshHost, 22 );
    session.setPassword( strSshPassword );
     
    final Properties config = new Properties();
   // config.put( "StrictHostKeyChecking", "no" );
    config.put( "StrictHostKeyChecking", "no" );
    session.setConfig( config );
    
    session.connect();
    session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
    
  }
  
  public static void destriySession( ) throws JSchException {
	  if(session!=null)
	  session.delPortForwardingL(nLocalPort);
  }
  
  public static Connection getConnection( )  {
	  Connection con = null;
	  try
	    {
	      
	       
	      DBConnection.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);
	       
	      Class.forName("com.mysql.jdbc.Driver");
	       con = DriverManager.getConnection("jdbc:mysql://localhost:3366/shanmukha?useSSL=false", strDbUser, strDbPassword);
	      System.out.println(con);
	      
	    }
	    catch( Exception e )
	    {
	      e.printStackTrace();
	    }
	return con;
 
   
  
}
  public static void CloseSSHConnection() {
      if (session != null && session.isConnected()) {
          System.out.println("Closing SSH Connection");
          session.disconnect();
      }
  }
}
