package employee;
/*
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.*;

import org.jdesktop.swingx.JXDatePicker;

import com.toedter.calendar.JDateChooser;

import database.connection.DatabaseConnection;

public class EmployeeEntry extends JPanel implements ActionListener
{
	JLabel lbl_branch_id, lbl_branch_name, lbl_emp_id, lbl_emp_name, 
			lbl_city, lbl_phone, lbl_emp_age, lbl_date_of_joining, 
			lbl_qualification, lbl_job, lbl_current_date, 
			lbl_status, lbl_days, lbl_initial_salary, lbl_daily_wages;
	
	JTextField txt_branch_name, txt_emp_name, txt_city, 
				txt_phone,txt_emp_age, txt_date_of_joining,
				 txt_initial_salary, txt_daily_wages;
	
	JComboBox<String> cbox_branch_id, cbox_job, cbox_qualification, cbox_status,
				cbox_days, cbox_emp_id;
	
	JButton btn_add, btn_update, btn_delete;
	
	JXDatePicker date_picker_jd, date_picker_cd;
	
	DefaultComboBoxModel<String> model1;
	DatabaseConnection db = new DatabaseConnection();
	
	
	public EmployeeEntry()
	{
		setLayout(null);
		
		ImageIcon background = new ImageIcon(getClass().getResource("temp.jpeg"));
		JLabel fr = new JLabel(background);
		fr.setBounds(0, -50, 1440, 360);
		add(fr);
		
		
		lbl_branch_id = new JLabel("Branch ID");
		lbl_branch_name = new JLabel("Branch Name");
		lbl_emp_id = new JLabel("Employee ID");
		lbl_emp_name = new JLabel("Employee NAME");
		lbl_city = new JLabel("City");
		lbl_phone = new JLabel("Phone");
		lbl_date_of_joining = new JLabel("Date Of Joining");
		lbl_emp_age = new JLabel("Age");
		lbl_qualification = new JLabel("Qualification");
		lbl_job = new JLabel("Job");
		lbl_current_date = new JLabel("Current Date");
		lbl_status = new JLabel("Status");
		lbl_days = new JLabel("No Of Days");
		lbl_initial_salary = new JLabel("Initial Salary");
		lbl_daily_wages = new JLabel("Daily Wages");
		
		
		txt_branch_name = new JTextField();
		txt_emp_name = new JTextField();
		txt_city = new JTextField();
		txt_phone = new JTextField();
		txt_date_of_joining = new JTextField();
		txt_emp_age = new JTextField();
		txt_initial_salary = new JTextField();
		txt_daily_wages = new JTextField();
		
		String jobs[] = {"","Manager","Accountant","Quality checker",
							"Tank filler","Air filler"};
		
		String qualifications[] = {"","B.com","M.com","B.sc","+2","SSLC",
										"ITI", "others"};
		
		String status[] = {"","Present","Absent"};
		
		String branchid[] = {"","CH600001","CM641001","MA625001","KC631502",
								"PN605001","TI627001","KR639001","TJ613001",
								"TL602003","TC6200001","TV606601","VE632001",
								"VL605103",	"VR626001"};
		
		String days[] = {"","1","2","3","4","5","6","7","8","9","10","11","12",
						"13","14","15","16","17","18","19","20","21","22",
						"23","24","25","26","27","28","29","30","31"};
		
		String empid[] = {""};
		
		cbox_emp_id = new JComboBox(empid);
		cbox_branch_id = new JComboBox(branchid);
		cbox_job = new JComboBox(jobs);
		cbox_qualification = new JComboBox(qualifications);
		cbox_status = new JComboBox(status);
		cbox_days = new JComboBox(days);
		
		btn_add = new JButton("ADD");
		btn_update = new JButton("UPDATE");
		btn_delete = new JButton("DELETE");
		//btn_to_do = new JButton("TO DO");
		
		date_picker_jd = new JXDatePicker();
		date_picker_jd.setDate(Calendar.getInstance().getTime());
		date_picker_jd.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		
		JDateChooser calender_jd = new JDateChooser();
		
		date_picker_cd = new JXDatePicker();
		date_picker_cd.setDate(Calendar.getInstance().getTime());
		date_picker_cd.setFormats(new SimpleDateFormat("dd.MM.yyyy"));

		JDateChooser calender_cd = new JDateChooser();
		
		
	// Fetching Branch_name	
		cbox_branch_id.addActionListener(e ->
		{
			String b_id = cbox_branch_id.getSelectedItem().toString();
			try
			{			
				Connection con = db.getConnection();
				
		        String q  = "select branch_name from Branch_Details where branch_id=?";
		        		        
		        PreparedStatement pst = con.prepareStatement(q);
		        pst.setString(1, b_id);
		        ResultSet rs = pst.executeQuery();
		        
		        if(rs!=null)
		        {
		        	while(rs.next())
		        	{	
		        		txt_branch_name.setText(rs.getString("branch_name"));
		        	}
		        }
		        
				String query = "Select emp_id from Employee_Entry where branch_id=?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, b_id);
				ResultSet rst = ps.executeQuery();
					
				while (rst.next()) 
				{
					cbox_emp_id.addItem(rst.getString("emp_id"));
					//System.out.println(rst.getString("emp_id"));
				}				
		        
		        con.setAutoCommit(true);
		        con.close();
		        
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		});
		
		
	//Fetching all the data's from Employee_Entry	
		cbox_emp_id.addFocusListener(new FocusListener()
		{	
			
			@Override
			public void focusGained(FocusEvent e)
			{
				
			}
			public void focusLost(FocusEvent e)
			{
				System.out.println("1");
				String e_id = cbox_emp_id.getSelectedItem().toString();
				
				try
				{			
					Connection con = db.getConnection();
					
			        String q1  = "select branch_id, branch_name, emp_name, address, phone_no, date_of_joining, age, qualification, job, status, days, initial_salary, daily_wages from Employee_Entry where emp_id=?";
			        		        
			        PreparedStatement pst = con.prepareStatement(q1);
			        pst.setString(1, e_id);
			        ResultSet rs = pst.executeQuery();
			        
			        if(rs!=null)
			        {
			        	while(rs.next())
			        	{		
			        		cbox_branch_id.setSelectedItem(rs.getString("branch_id"));
			        		txt_branch_name.setText(rs.getString("branch_name"));
					        txt_emp_name.setText(rs.getString("emp_name"));
					        txt_city.setText(rs.getString("address"));
					        txt_phone.setText(Long.toString(rs.getLong("phone_no")));
					        
					        txt_emp_age.setText(Integer.toString(rs.getInt("age")));
					        date_picker_jd.setDate(rs.getDate("date_of_joining"));
					        cbox_qualification.setSelectedItem(rs.getString("qualification"));
					        cbox_job.setSelectedItem(rs.getString("job"));				        
					        cbox_status.setSelectedItem(rs.getString("status"));
					        cbox_days.setSelectedItem(Integer.toString(rs.getInt("days")));
					        txt_initial_salary.setText(Integer.toString(rs.getInt("initial_salary")));
					        txt_daily_wages.setText(Integer.toString(rs.getInt("daily_wages")));
				
			        	}			        
			        }
			        con.setAutoCommit(true);
			        con.close();
				}
			    catch(Exception ex)
				{
			    	JOptionPane.showMessageDialog(null, ex.toString());
				} 
			}			
		});
			
		
	// Fetching initial_salary and daily_wages	
		cbox_job.addActionListener(e ->
		{
				
			JComboBox cb_job = (JComboBox) e.getSource();
			Object ob = cb_job.getSelectedItem();
			
			if(ob=="Manager")
			{
				int insal1 = 30000;
				int dw1 = 1000;
				txt_initial_salary.setText(Integer.toString(insal1));
				txt_daily_wages.setText(Integer.toString(dw1));
			}
			else if(ob=="Accountant")
			{
				int insal2 = 24000;
				int dw2 = 800;
				txt_initial_salary.setText(Integer.toString(insal2));
				txt_daily_wages.setText(Integer.toString(dw2));
			}
			else if(ob=="Quality checker")
			{
				int insal3 = 18000;
				int dw3 = 600;
				txt_initial_salary.setText(Integer.toString(insal3));
				txt_daily_wages.setText(Integer.toString(dw3));
			}
			else if(ob=="Tank filler")
			{
				int insal4 = 15000;
				int dw4 = 500;
				txt_initial_salary.setText(Integer.toString(insal4));
				txt_daily_wages.setText(Integer.toString(dw4));
			}
			else if(ob=="Air filler")
			{
				int insal5 = 9000;
				int dw5 = 300;
				txt_initial_salary.setText(Integer.toString(insal5));
				txt_daily_wages.setText(Integer.toString(dw5));
			}
		});
		
		
		btn_add.addActionListener(this);
		btn_update.addActionListener(this);
		btn_delete.addActionListener(this);
		
		lbl_branch_id.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_branch_name.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_current_date.setFont(new Font("Cambria", Font.BOLD, 16));
		
		lbl_emp_id.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_emp_id.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_city.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_phone.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_emp_age.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_date_of_joining.setFont(new Font("Cambria", Font.BOLD, 16));
		
		lbl_qualification.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_job.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_status.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_days.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_initial_salary.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_daily_wages.setFont(new Font("Cambria", Font.BOLD, 16));
		
		lbl_branch_id.setBounds(50,350,150,30);
		lbl_branch_name.setBounds(50,400,150,30);
		lbl_current_date.setBounds(50,450,150,30);
		
		lbl_emp_id.setBounds(485,350,150,30);
		lbl_emp_name.setBounds(485,400,150,30);
		lbl_city.setBounds(485,450,150,30);
		lbl_phone.setBounds(485,500,150,30);
		lbl_emp_age.setBounds(485,550,150,30);
		lbl_date_of_joining.setBounds(485,600,150,30);
		
		lbl_qualification.setBounds(935,350,150,30);
		lbl_job.setBounds(935,400,150,30);	
		lbl_status.setBounds(935,450,150,30);
		lbl_days.setBounds(935,500,150,30);
		lbl_initial_salary.setBounds(935,550,150,30);
		lbl_daily_wages.setBounds(935,600,150,30);
		
		cbox_branch_id.setBounds(200,350,150,30);
		txt_branch_name.setBounds(200,400,150,30);
		date_picker_cd.setBounds(200,450,150,30);
		
		cbox_emp_id.setBounds(675,350,150,30);
		txt_emp_name.setBounds(675,400,150,30);
		txt_city.setBounds(675,450,150,30);
		txt_phone.setBounds(675,500,150,30);		
		txt_emp_age.setBounds(675,550,150,30);
		date_picker_jd.setBounds(675,600,150,30);
		
		cbox_qualification.setBounds(1100,350,150,30);
		cbox_job.setBounds(1100,400,150,30);
		cbox_status.setBounds(1100,450,150,30);
		cbox_days.setBounds(1100,500,150,30);
		txt_initial_salary.setBounds(1100,550,150,30);
		txt_daily_wages.setBounds(1100,600,150,30);
		
		btn_add.setBounds(50,550,100,30);	
		btn_update.setBounds(50,600,100,30);
		btn_delete.setBounds(200,600,100,30);
		
		
		add(lbl_branch_id);
		add(lbl_branch_name);
		add(lbl_emp_id);
		add(lbl_emp_name);
		add(lbl_city);
		add(lbl_phone);
		add(lbl_date_of_joining);
		add(lbl_emp_age);
		add(lbl_qualification);
		add(lbl_job);
		add(lbl_current_date);
		add(lbl_status);
		add(lbl_days);
		add(lbl_initial_salary);
		add(lbl_daily_wages);
		
		add(txt_branch_name);
		add(cbox_emp_id);
		add(txt_emp_name);
		add(txt_city);
		add(txt_phone);
		add(txt_emp_age);
		add(txt_initial_salary);
		add(txt_daily_wages);
		add(cbox_branch_id);
		add(cbox_qualification);
		add(cbox_job);
		add(cbox_status);
		add(cbox_days);
		add(btn_add);
		add(btn_update);
		add(btn_delete);
		add(date_picker_jd);
		add(date_picker_cd);
		add(calender_jd);
		add(calender_cd);
		
		setVisible(true);
		setSize(1600,830);	
	}
	
	// Action performed on add, update, delete
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		String dates[] = {"","Jan","Feb","Mar","Apr","May","Jun","Jul",
				"Aug","Sep","Oct","Nov","Dec"};
		
		int m_jd = 0, m_cd = 0, i=0;
		String cal1 = "";
		String cal2 = "";
		
		
		Object obj = e.getSource();
		
		if(obj==btn_add)
		{
			String branch_id = cbox_branch_id.getSelectedItem().toString();
			String temp_b_id = branch_id.substring(0,2);
			
			String branch_name = txt_branch_name.getText();
			
			String e_id = "";
			String temp_e_id = cbox_emp_id.getSelectedItem().toString();
			if(temp_e_id=="")
			{
				String t_id = "E01";
				e_id = temp_b_id + t_id;
			}
			else
			{
				int temp_id = Integer.parseInt(temp_e_id.substring(3,5));
				System.out.println(temp_id);
				int id = temp_id + 1;
				if(id>=10)
				{
					String id1 = Integer.toString(id);
					e_id = temp_e_id.replace((temp_e_id.substring(3,5)), id1);
					System.out.println(e_id);
				}
				else
				{
					String id1 = "0" + Integer.toString(id);
					e_id = temp_e_id.replace((temp_e_id.substring(3,5)), id1);
					System.out.println(e_id);
				}		
			}
							
			String e_name = txt_emp_name.getText();
			String address = txt_city.getText();
			long phone = Long.parseLong(txt_phone.getText());	
			int age = Integer.parseInt(txt_emp_age.getText());
			String date1 = date_picker_jd.getDate().toString();
			String[] arr1= date1.split(" ");	
			String month_jd = arr1[1];
			for(i=0;i<dates.length;i++)
			{
				if(month_jd.equals(dates[i]))
				{
					m_jd = i;
				}
			}
			if(i>9)
				cal1 =arr1[5]+"-"+m_jd+"-"+arr1[2];
			else
				cal1 =arr1[5]+"-"+"0"+m_jd+"-"+arr1[2];
			Date c1 = Date.valueOf(cal1);
			System.out.println(cal1);
			
			String qualification = cbox_qualification.getSelectedItem().toString();
			String job = cbox_job.getSelectedItem().toString();
			String date2 = date_picker_cd.getDate().toString();
			String[] arr2= date2.split(" ");
			String month_cd = arr1[1];
			for(i=0;i<dates.length;i++)
			{
				if(month_cd.equals(dates[i]))
				{
					m_cd = i;
				}
			}
			if(i>9)
				cal2 =arr2[5]+"-"+m_cd+"-"+arr2[2];
			else
				cal2 =arr1[5]+"-"+"0"+m_jd+"-"+arr1[2];
			Date c2 = Date.valueOf(cal2);
			System.out.println(cal2);
			
			String status = cbox_status.getSelectedItem().toString();
			int days = Integer.parseInt(cbox_days.getSelectedItem().toString());
			int initial_salary = Integer.parseInt(txt_initial_salary.getText());	
			int daily_wages = Integer.parseInt(txt_daily_wages.getText());
			
			int d = 0;
			String regx_e_name = "^[A-Za-z\\s]+$";
			String em_name = txt_emp_name.getText();
			boolean checker1 = Pattern.matches(regx_e_name, em_name);
			if(!checker1)
			{
				d++;
				JOptionPane.showMessageDialog(null, "Enter a valid Employee Name");
			}
			
			String regx_city = "^[A-Za-z]*$";
			String city = txt_city.getText();
			boolean checker2 = Pattern.matches(regx_city, city);
			if(!checker2)
			{
				d++;
				JOptionPane.showMessageDialog(null, "Enter a valid City Name");
			}
			
			String regx_phone = "^[0-9]{10}$";
			String phone_no = txt_phone.getText();
			boolean checker3 = Pattern.matches(regx_phone, phone_no);
			if(!checker3)
			{
				d++;
				JOptionPane.showMessageDialog(null, "Phone Number should contains only 10 digits");
			}
			
			String regx_age = "^[0-9]{2}$";
			String age_ = txt_emp_age.getText();
			boolean checker4 = Pattern.matches(regx_age, age_);
			if(!checker4)
			{
				d++;
				JOptionPane.showMessageDialog(null, "age should contains only 2 digit");
			}
			
			try
			{
				EmployeeJavaBean etb = new EmployeeJavaBean();
				
				etb.setB_id(branch_id);
				etb.setB_name(branch_name);
				etb.setE_id(e_id);
				etb.setE_name(e_name);
				etb.setE_address(address);
				etb.setE_phone(phone);				
				etb.setE_age(age);
				etb.setE_date_of_joining(c1);
				etb.setE_qualification(qualification);
				etb.setE_job(job);
				etb.setE_current_date(c2);
				etb.setE_status(status);
				etb.setE_days(days);
				etb.setE_initial_salary(initial_salary);
				etb.setE_daily_wages(daily_wages);
							
				EmployeeEntryConnection jd = new EmployeeEntryConnection();
				jd.createconnections(etb, d);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, ex.toString());
			}
		}
		
		if(obj==btn_update)
		{
			String branch_id = cbox_branch_id.getSelectedItem().toString();
			String branch_name = txt_branch_name.getText();
			String e_id = cbox_emp_id.getSelectedItem().toString();
			String e_name = txt_emp_name.getText();
			String address = txt_city.getText();
			long phone = Long.parseLong(txt_phone.getText());
			int age = Integer.parseInt(txt_emp_age.getText());
	       // Date date1 = (Date) date_picker_jd.getDate();
			String qualification = cbox_qualification.getSelectedItem().toString();
			String job = cbox_job.getSelectedItem().toString();
		  // Date date2 = (Date)date_picker_cd.getDate();
			String status = cbox_status.getSelectedItem().toString();
			int days = Integer.parseInt(cbox_days.getSelectedItem().toString());
			String initial_salary = txt_initial_salary.getText();
			int daily_wages = Integer.parseInt(txt_daily_wages.getText());
			
			try
			{
				Connection con = db.getConnection();
				
		        String q2 = "update Employee_Entry set branch_id=?, branch_name=?, emp_name=?, address=?, phone_no=?, age=?, qualification=?, job=?, status=?, days=?, initial_salary=?, daily_wages=? where emp_id=?";
		 		        
		        PreparedStatement pst = con.prepareStatement(q2);
		        
		        pst.setString(1, branch_id);
		        pst.setString(2, branch_name);		        
		        pst.setString(3, e_name);
		        pst.setString(4, address);
		        pst.setLong(5, phone);
		        pst.setInt(6, age);
		        pst.setString(7, qualification);
		        pst.setString(8, job);
		        pst.setString(9, status);
		        pst.setInt(10, days);
		        pst.setString(11, initial_salary);
		        pst.setInt(12, daily_wages);
		        pst.setString(13, e_id);
		        
		        pst.executeUpdate();        		            		    
    		    con.setAutoCommit(true);
    		    con.close();
    		    JOptionPane.showMessageDialog(null, " Updation Successfull! ");
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		}
		if(obj==btn_delete)
		{
			try
			{
				String e_id = cbox_emp_id.getSelectedItem().toString();
				
				Connection con = db.getConnection();
				
				String query = "delete from Employee_Entry where emp_id=?";
				
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, e_id);
				pst.executeUpdate();
				
				JOptionPane.showMessageDialog(null, " Deleted Successfully! ", "Alert", JOptionPane.WARNING_MESSAGE);
				con.setAutoCommit(true);
				con.close();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		}
		
	}

}
*/
