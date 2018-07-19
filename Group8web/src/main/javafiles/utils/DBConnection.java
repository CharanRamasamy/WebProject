package main.javafiles.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

//Class for Setting up of all connections related to the Database.
public class DBConnection {
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Connection connection;
	String username,url,password;
	
	//Method to set connection to DEVInt database and return Connection variable.
	public Connection setConnection() throws IOException, ClassNotFoundException
	{
		Properties props = new Properties();
		InputStream inputStream = DBConnection.class.getClassLoader()
                .getResourceAsStream("db.properties");
		if (props != null) {
			props.load(inputStream);
		String driver = props.getProperty("jdbc.driver");
		if (driver != null) {
		    Class.forName(driver);
		}
		 url = props.getProperty("jdbc.url");
		 username = props.getProperty("jdbc.username");
		 password = props.getProperty("jdbc.password");
		try {
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return connection;
	}
}
