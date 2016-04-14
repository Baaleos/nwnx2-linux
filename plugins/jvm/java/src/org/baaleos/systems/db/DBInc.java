package org.baaleos.systems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBInc {

	private static final String DBHOST = "127.0.0.1";
	private static final int Port = 3306;
	private static final String DBName = "nwn_linux";
	private static final String Username = "nwn";
	private static final String Password = "password";
	
	/**
	 * Return a single column from a query
	 * @param query
	 * @param column
	 * @return
	 */
	public static Object doQuery(String query, String column)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBHOST+":"+Port+"/"+DBName, Username, Password) ;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs.getObject(column);
		}
		catch(Exception ee)
		{
			return null;
		}
	}
	/**
	 * Return a resultset from a query, allowing access to all fields in the query
	 * @param query
	 * @return
	 */
	public static ResultSet doQuery(String query)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver") ;
		Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBHOST+":"+Port+"/"+DBName, Username, Password) ;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query) ;
		return rs;
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
