package org.baaleos.systems.god;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBInc {

	public static final String DBHOST = "localhost";
	public static final int Port = 3306;
	public static final String DBName = "nwn";
	public static final String Username = "";
	public static final String Password = "";
	public String doQuery()
	{
		//Class.forName("com.mysql.jdbc.Driver") ;
		//Connection conn = DriverManager.getConnection("jdbc:mysql://"+DBHOST+":"+Port+"/"+DBName, Username, Password) ;
		//Statement stmt = conn.createStatement() ;
		//String query = "select columnname from tablename ;" ;
		//ResultSet rs = stmt.executeQuery(query) ;
		return "";
	}
	
}
