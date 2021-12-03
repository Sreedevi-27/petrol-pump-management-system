package report;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import database.connection.DatabaseConnection;

public class EmployeeReport extends JPanel implements ActionListener{
	JTable table;
	
	JPanel panel;
	JLabel lbl_employee, lbl_branch_id, lbl_empty;
	JComboBox<String> cbx_employee_id, cbx_branch_id;
	DefaultTableModel dtm;
	DefaultComboBoxModel<String> model1, model2;
	 Connection  con ;
	EmployeeReport(){
		
		panel = new JPanel();
		String[] columnNames = { "Branch Id", "Branch Name","Emplyoee ID", "Emplyoee Name",
				"Address","Phone Number","Age","Date of Joining","Qualification","Job","Salary"};
		dtm=new DefaultTableModel(columnNames,0);
	    table = new JTable(dtm);
	    
	    
	    DatabaseConnection db = new DatabaseConnection();
	
		
		Vector<String> list = new Vector<>();
		try {
			Connection con= db.getConnection();
			String query = "Select Branch_ID from Employee_Entry";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("Branch_ID"));
			}
			model1 = new DefaultComboBoxModel<>(list);
			cbx_branch_id = new JComboBox<>(model1);
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}

		
		Vector<String> list1 = new Vector<>();
		try {
			Connection co= db.getConnection();
			String query = "Select Emp_ID from Employee_Entry";
			PreparedStatement pstmt = co.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list1.add(rs.getString("Emp_ID"));
			}
			model2 = new DefaultComboBoxModel<>(list1);
			cbx_employee_id = new JComboBox<>(model2);
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}

		lbl_branch_id = new JLabel("Branch ID");
		lbl_branch_id.setBounds(100,-500,100,30);
		add(lbl_branch_id);
	
		cbx_branch_id.setBounds(100,-500,100,30);
		add(cbx_branch_id);
		cbx_branch_id.addActionListener(this);
		
		lbl_empty = new JLabel("   ");
		add(lbl_empty);
		
		lbl_employee = new JLabel("Employee ID");
		lbl_employee.setBounds(10,0,100,30);
		add(lbl_employee);
	
		cbx_employee_id.setBounds(30,0,100,30);
		add(cbx_employee_id);
		cbx_employee_id.addActionListener(this);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer);    
		
		
		table.setRowHeight(50);
		int[] columnsWidth = {80,160,90,130,90,120,50,100,100,90,90};

		int i = 0;
		for (int width : columnsWidth) {
			TableColumn column = table.getColumnModel().getColumn(i++);
			column.setMinWidth(width);
			column.setMaxWidth(width);
			column.setPreferredWidth(width);
		}
		
		table.setPreferredScrollableViewportSize(new Dimension(1100,600));
		table.setFillsViewportHeight(true); 

		 panel.add(new JScrollPane(table));
		 add(panel);
	
	}
	public static void main(String args[]) {
		new EmployeeReport();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String employee = cbx_employee_id.getSelectedItem().toString();
		String branch = cbx_branch_id.getSelectedItem().toString();
		String query="";
		String txt = "";
		
		if(ob == cbx_branch_id) {
			query = "select employee_entry.branch_id,employee_entry.branch_name,employee_entry.emp_id,employee_entry.emp_name, employee_entry.address,employee_entry.phone_no,employee_entry.age, employee_entry.date_of_joining, employee_entry.qualification,employee_entry.job ,salary_entry.salary from salary_entry, employee_entry where employee_entry.branch_id = ?";
			txt = branch;
		}
		
		if(ob == cbx_employee_id) {
			query = "select employee_entry.branch_id,employee_entry.branch_name,employee_entry.emp_id,employee_entry.emp_name, employee_entry.address,employee_entry.phone_no,employee_entry.age, employee_entry.date_of_joining, employee_entry.qualification,employee_entry.job ,salary_entry.salary from salary_entry, employee_entry where employee_entry.emp_id = ?";
			txt = employee;
		}
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, txt);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String branch_id = rs.getString("branch_id");
				String branch_name = rs.getString("branch_name");
				String emp_id = rs.getString("emp_id");
				String emp_name = rs.getString("emp_name");
				String address = rs.getString("address");
				String phone = rs.getString("phone_no");
				String age = Integer.toString(rs.getInt("age"));
				String date_join = rs.getDate("date_of_joining").toString();
				String qualification = rs.getString("qualification");
				String job = rs.getString("job");
				String salary = Float.toString(rs.getFloat("salary"));

				dtm.addRow(new String[] { branch_id, branch_name, emp_id, emp_name,address, phone, age, date_join, qualification, job, salary});

			}

			con.setAutoCommit(true);
		} catch (Exception ee) {
			System.out.println(ee.toString());
		}
	}

}