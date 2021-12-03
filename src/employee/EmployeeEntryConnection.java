package employee;
/*
import java.sql.*;
import javax.swing.JOptionPane;

public class EmployeeEntryConnection 
{
	void createconnections(EmployeeJavaBean etb, int count)
	{
		String b_id = etb.getB_id();
		String b_name = etb.getB_name();
		String id = etb.getE_id();
		String name = etb.getE_name();
		String address = etb.getE_address();
		long phone = etb.getE_phone();
		int age = etb.getE_age();
		Date date = etb.getE_date_of_joining();
		String qualification = etb.getE_qualification();
		String job = etb.getE_job();
		Date current_date = etb.getE_current_date();
		String status = etb.getE_status();
		int days = etb.getE_days();
		int initialsalary = etb.getE_initial_salary();
		int dailywages = etb.getE_Daily_wages();
		
		if(count==0)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
		        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","project1","ppms");		        
		        
		        String query = "insert into Employee_Entry values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		        PreparedStatement pst = con.prepareStatement(query);
		        
		        pst.setString(1, b_id);
		        pst.setString(2, b_name);
		        pst.setString(3, id);
		        pst.setString(4, name);
		        pst.setString(5, address);
		        pst.setLong(6, phone);
		        pst.setInt(7, age);
		        pst.setDate(8, date);
		        pst.setString(9,qualification);
		        pst.setString(10, job);
		        pst.setDate(11, current_date);
		        pst.setString(12, status);
		        pst.setInt(13, days);
		        pst.setInt(14, initialsalary);
		        pst.setInt(15, dailywages);
		        
		        pst.executeUpdate();
		        con.setAutoCommit(true);
		        con.close();
		        
		        JOptionPane.showMessageDialog(null, "DETAILS ADDED SUCCESSFULLY!!");
		        
			}
			
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		
	}

}
*/