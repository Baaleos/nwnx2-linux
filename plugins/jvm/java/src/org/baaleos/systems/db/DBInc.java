package org.baaleos.systems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBInc {

	public static final String DBHOST = "localhost";
	public static final int Port = 3306;
	public static final String DBName = "nwn";
	public static final String Username = "";
	public static final String Password = "";
	public static Object doQuery(String query, String column)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver") ;
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBHOST+":"+Port+"/"+DBName, Username, Password) ;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query) ;
		return rs.getObject(column);
		}
		catch(Exception ee)
		{
			return null;
		}
	}
	public static void doUpdateQuery(String query)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver") ;
			Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBHOST+":"+Port+"/"+DBName, Username, Password) ;
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query) ;
		}
		catch(Exception ee)
		{
			
		}
	}
	
	
}
