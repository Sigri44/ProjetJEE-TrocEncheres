package fr.eni.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DbConnection {

	private static final  String URL= "jdbc:sqlserver://10.12.200.16:1433;databasename=ENCHERES";
	private static final String User = "sa";
	private static final String Password = "Pa$$w0rd";
	
	public static Connection seConnecter() {
		Connection cnx = null;
		
		try {
			//Etape 1 - Charger le driver jdbc
			DriverManager.registerDriver(new SQLServerDriver());
			cnx = DriverManager.getConnection(URL, User, Password);
		} catch (SQLException e) {
			
			e.printStackTrace();			
		}
		return cnx;
		
	}
	
}
