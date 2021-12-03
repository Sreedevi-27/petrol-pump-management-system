package report;
import database.connection.DatabaseConnection;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class EmployeeExpenseReport extends JPanel implements ActionListener{
	JPanel panel;
	JTable table;
	JLabel lbl_month, lbl_empty;
	DefaultTableModel dtm;
	JComboBox<String> cbx_month;
	
	EmployeeExpenseReport(){
		
		panel = new JPanel();

		 lbl_empty = new JLabel("   ");
		 add(lbl_empty);
	     lbl_empty.setBounds(100,200,100,50);
		 
		lbl_month = new JLabel("Month");
		add(lbl_month);
		lbl_month.setBounds(650,600,100,50);
		lbl_month.setBounds(650,600,100,50);
		
		String months[] = {"January","February","March","April","May","June","July",
	            "August","September","October","November","December"};
		cbx_month = new JComboBox<>(months);
		add(cbx_month);
		cbx_month.setBounds(100,200,100,50);
		cbx_month.addActionListener(this);
		
		 
		
		String[] columnNames = {"Month", "Employee ID", "Employee Name", "Salary"};
		
		dtm=new DefaultTableModel(columnNames,0);
	    table = new JTable(dtm);
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer);    
		
	    table.setRowHeight(50);
		int[] columnsWidth = {220,240,220,220};

		int i = 0;
		for (int width : columnsWidth) {
			TableColumn column = table.getColumnModel().getColumn(i++);
			column.setMinWidth(width);
			column.setMaxWidth(width);
			column.setPreferredWidth(width);
		}
		
		table.setPreferredScrollableViewportSize(new Dimension(900,600));
		table.setFillsViewportHeight(true); 
		 panel.add(new JScrollPane(table));
		 panel.setPreferredSize(new Dimension(1000,650));
		 add(panel);	
	}
	
	public static void main(String args[]) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str_month = cbx_month.getSelectedItem().toString();
		try {
			DatabaseConnection db = new DatabaseConnection();
			Connection con = db.getConnection();
			String	query = "select Month, Emp_ID, Emp_Name, Salary from Salary_Entry where Month = ?";
		    PreparedStatement pst = con.prepareStatement(query);
		    pst.setString(1,str_month);
	        ResultSet rs = pst.executeQuery();
	        int sum = 0;
	        while (rs.next()) {
	               String month = rs.getString("Month");
	               String emp_id = rs.getString("Emp_ID");
	               String emp_name  =  rs.getString("Emp_Name");
	               String salary = Integer.toString(rs.getInt("Salary"));
	               dtm.addRow(new String[]{month,emp_id,emp_name,salary});  
	               sum += rs.getInt("Salary");
	           }
	        dtm.addRow(new String[]{"","","Total",Integer.toString(sum)}); 
	     
	        con.setAutoCommit(true);
		}
		catch(Exception ae){
			System.out.println(ae.toString());
		}
		
	}

}

