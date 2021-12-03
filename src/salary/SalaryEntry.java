package salary;

import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

import database.connection.*;

public class SalaryEntry extends JPanel implements FocusListener, ActionListener
{
	private static final JComponent lbl_Branch_ID = null;

	JLabel lbl_branch_id, lbl_emp_id, lbl_emp_name, lbl_fixed_salary, lbl_no_of_days,
			lbl_salary, lbl_month, lbl_daily_wages;
	
	JTextField  txt_emp_name, txt_fixed_salary, txt_salary,
				txt_days, txt_daily_wages;
	
	JComboBox cbox_branch_id, cbox_month, cbox_emp_id;
	
	JButton btn_submit;
	
	DatabaseConnection db = new DatabaseConnection();
	
	public SalaryEntry()
	{
		setLayout(null);
		
		ImageIcon background = new ImageIcon(getClass().getResource("temp.jpeg"));
		JLabel fr = new JLabel(background);
		fr.setBounds(0, -50, 1440, 360);
		add(fr);
		
		ImageIcon background_emp = new ImageIcon(getClass().getResource("sal_image1bg.png"));
		JLabel fr1 = new JLabel(background_emp);
		fr1.setBounds(780, 320, 600, 420);
		add(fr1);
		
		lbl_branch_id = new JLabel("Branch ID");
		lbl_emp_id = new JLabel("Employee ID");
		lbl_emp_name = new JLabel("Employee NAME");
		lbl_fixed_salary = new JLabel("Fixed Salary");
		lbl_no_of_days = new JLabel("No Of Days");
		lbl_month = new JLabel("Month");
		lbl_daily_wages = new JLabel("Daily Wages");
		lbl_salary = new JLabel("Salary");
		
		txt_emp_name = new JTextField();
		txt_fixed_salary = new JTextField();
		txt_days = new JTextField();
		txt_salary = new JTextField();
		txt_daily_wages = new JTextField();
		
		String branchid[] = {"CH600001","MA625001","CM641001","KC631502",
				"PN605001","TI627001","KR639001","TJ613001",
				"TL602003","TC6200001","TV606601","VE632001",
				"VL605103",	"VR626001"};
		
		String months[] = {"January","February","March","April","May",
							"June","July","August","September",
							"October","November","December"};
		
		cbox_emp_id = new JComboBox();
		cbox_branch_id = new JComboBox(branchid);
		cbox_month = new JComboBox(months);
		
		btn_submit = new JButton("SUBMIT");
	
		lbl_branch_id.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_emp_id.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_emp_name.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_fixed_salary.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_no_of_days.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_daily_wages.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_month.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_salary.setFont(new Font("Cambria", Font.BOLD, 16));
		
		lbl_branch_id.setBounds(70,350,150,30);
		lbl_emp_id.setBounds(70,400,150,30);
		lbl_emp_name.setBounds(70,450,150,30);
		lbl_fixed_salary.setBounds(70,500,150,30);
		lbl_no_of_days.setBounds(70,550,150,30);
		
		lbl_daily_wages.setBounds(485,350,150,30);
		lbl_month.setBounds(485,400,150,30);
		lbl_salary.setBounds(485,450,150,30);
		
		cbox_branch_id.setBounds(200,350,150,30);
		cbox_emp_id.setBounds(200,400,150,30);
		txt_emp_name.setBounds(200,450,150,30);
		txt_fixed_salary.setBounds(200,500,150,30);
		txt_days.setBounds(200,550,150,30);
		
		txt_daily_wages.setBounds(630,350,150,30);
		cbox_month.setBounds(630,400,150,30);
		txt_salary.setBounds(630,450,150,30);
		
		//btn_ok.setBounds(475,500,90,30);
		btn_submit.setBounds(475,550,100,30);
		
		add(lbl_branch_id);
		add(lbl_emp_id);
		add(lbl_emp_name);
		add(lbl_fixed_salary);
		add(lbl_no_of_days);
		add(lbl_month);
		add(lbl_salary);
		add(lbl_daily_wages);
		add(cbox_branch_id);
		add(cbox_emp_id);
		add(txt_emp_name);
		add(txt_fixed_salary);
		add(txt_days);
		add(cbox_month);
		add(txt_salary);
		add(txt_daily_wages);
		add(btn_submit);
		
		cbox_branch_id.addActionListener(e -> 
		{
			String b_id = cbox_branch_id.getSelectedItem().toString();
			try
			{			
				Connection con = db.getConnection();
				
		        String q  = "select emp_id from Employee_Entry where branch_id=?";
		        		        
		        PreparedStatement pst = con.prepareStatement(q);
		        pst.setString(1, b_id);
		        ResultSet rs = pst.executeQuery();
		        
		        if(rs!=null)
		        {
		        	while(rs.next())
		        	{	
		        		cbox_emp_id.addItem(rs.getString("emp_id"));
		        	}
		        }
		        con.setAutoCommit(true);
		        con.close();
		        
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		        
		});
			
		
	// Calculating salary using days and daily_wages
		cbox_emp_id.addActionListener(e -> 
		{			
			String e_id = cbox_emp_id.getSelectedItem().toString();			
			try
			{
				Connection con = db.getConnection();
				
		        String q1  = "Select branch_id, emp_name, initial_salary, days, daily_wages from Employee_Entry where emp_id=?";
		        
		        PreparedStatement pst = con.prepareStatement(q1);
		        pst.setString(1, e_id);
		        ResultSet rs = pst.executeQuery();
		        
		        if(rs!=null)
		        {
		        	while(rs.next())
		        	{		
		        		cbox_branch_id.setSelectedItem(rs.getString("branch_id"));
				        txt_emp_name.setText(rs.getString("emp_name"));
				        txt_days.setText(Integer.toString(rs.getInt("days")));
				        txt_fixed_salary.setText(Integer.toString(rs.getInt("initial_salary")));
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

		});
		
	// Fetching all data's from table Employee_Entry
		txt_salary.addFocusListener(this);
				
	// Updating the Table Salary_Entry
		btn_submit.addActionListener(this);
		
		setVisible(true);
		setSize(1600,830);
	
	}

	@Override
	public void focusGained(FocusEvent e) 
	{
		int days = Integer.parseInt(txt_days.getText());
		int daily = Integer.parseInt(txt_daily_wages.getText());		
					
		if(days>=26)
		{
			int tot = daily * 30;
			txt_salary.setText(Integer.toString(tot));
		}
		else if(days<26)
		{
			int tot_sal = daily * days;					
			txt_salary.setText(Integer.toString(tot_sal));
		}
	}

	@Override
	public void focusLost(FocusEvent e) 
	{				
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();
		
		if(obj==btn_submit)
		{
			String b_id = cbox_branch_id.getSelectedItem().toString();
			String e_id = cbox_emp_id.getSelectedItem().toString();			
			String e_name = txt_emp_name.getText();
			String month = cbox_month.getSelectedItem().toString();
			int salary = Integer.parseInt(txt_salary.getText());
			
			try
			{
				SalaryJavaBean stb = new SalaryJavaBean();
				
				stb.setB_id(b_id);
				stb.setE_id(e_id);
				stb.setE_name(e_name);
				stb.setMonth(month);
				stb.setSalary(salary);
				
				SalaryEntryConnection jd = new SalaryEntryConnection();
				jd.createconnections(stb);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		}
	}

}
