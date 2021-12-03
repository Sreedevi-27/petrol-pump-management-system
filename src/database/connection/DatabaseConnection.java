package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnection 
{
	Connection con;
	public Connection getConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","sreedevi","sree");		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		return con;
	}

}
