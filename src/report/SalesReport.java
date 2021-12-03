package report;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXDatePicker;

import database.connection.DatabaseConnection;


public class SalesReport extends JPanel implements ActionListener {
	Connection con;
	JTable table;
	JXDatePicker date_picker;
	JLabel lbl_bill, lbl_empty, lbl_date;
	JComboBox<String> cbx_bill;
	DefaultTableModel dtm;
	DefaultComboBoxModel<String> model1, model2;
	JPanel panel;

	SalesReport() {
		panel = new JPanel();

		String[] columnNames = { "Bill no", "Branch ID", "Item ID", "Item Name", "Date", "Amount" };
		dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtm);
		
		date_picker = new JXDatePicker();
		date_picker.setDate(Calendar.getInstance().getTime());
		date_picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		

		DatabaseConnection db = new DatabaseConnection();
		con = db.getConnection();

		Vector<String> list1 = new Vector<>();
		try {

			String query = "Select bill_no from Sales_Details";
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list1.add(rs.getString("bill_no"));

			}
			model2 = new DefaultComboBoxModel<>(list1);
			cbx_bill = new JComboBox<>(model2);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		lbl_bill = new JLabel("Bill No");
		lbl_bill.setBounds(100,-500,100,30);
		add(lbl_bill);
	
		cbx_bill.setBounds(100,-500,100,30);
		add(cbx_bill);
		cbx_bill.addActionListener(this);
		
		lbl_empty = new JLabel("  ");
		add(lbl_empty);
		
    	lbl_date = new JLabel("Date");
		lbl_date.setBounds(10,0,100,30);
		add(lbl_date);
		
		add(date_picker);
		date_picker.addActionListener(this);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer);    
		
		table.setRowHeight(50);
		int[] columnsWidth = { 150, 150, 150, 150, 150, 150 };

		int i = 0;
		for (int width : columnsWidth) {
			TableColumn column = table.getColumnModel().getColumn(i++);
			column.setMinWidth(width);
			column.setMaxWidth(width);
			column.setPreferredWidth(width);
		}
		table.setPreferredScrollableViewportSize(new Dimension(900, 600));
		table.setFillsViewportHeight(true);

		panel.add(new JScrollPane(table));
		 panel.setPreferredSize(new Dimension(1000, 650));
		add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String bill = cbx_bill.getSelectedItem().toString();
		String cal = date_picker.getDate().toString();
		String[] arr = cal.split(" ");
		String str_date = arr[1] + "/" + arr[2] + "/" + arr[5];
	//	System.out.println(str_date);
		
		String query = "";
		String txt = "";
		
		if (ob == cbx_bill) {
			query = "select Bill_No, Branch_ID, Item_ID, Item_Name, Sales_Date, Sales_Amount from Sales_Details where Bill_No = ?";
		    txt = bill;
	      }
		else {
		   query = "select Bill_No, Branch_ID, Item_ID, Item_Name, Sales_Date, Sales_Amount from Sales_Details where Sales_Date = ?";
		   txt = str_date;
		}
		
		try {
			DatabaseConnection db = new DatabaseConnection();
			con = db.getConnection();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, txt);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String bill_no = Integer.toString(rs.getInt("Bill_No"));
				String branch_id = rs.getString("Branch_ID");
				String item_no = rs.getString("Item_ID");
				String item_name = rs.getString("Item_Name");
				String date = rs.getString("Sales_date");
				String amount = Float.toString(rs.getFloat("Sales_Amount"));

				dtm.addRow(new String[] { bill_no, branch_id, item_no, item_name,date, amount });
			}
			con.setAutoCommit(true);
		} 
		catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}

