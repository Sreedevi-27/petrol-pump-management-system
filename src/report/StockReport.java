package report;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXDatePicker;

import com.toedter.calendar.JDateChooser;

import database.connection.DatabaseConnection;

public class StockReport extends JPanel implements ActionListener {
	JTable table;
	Connection con;
	JXDatePicker date_picker;
	JPanel panel;
	DefaultTableModel dtm;
	JLabel lbl_date, lbl_empty;

	StockReport() {
		panel = new JPanel();
		String[] columnNames = { "Branch ID", "Item ID", "Item Name", "Date", "Available Stock" };

		dtm = new DefaultTableModel(columnNames, 0);
		table = new JTable(dtm);

		date_picker = new JXDatePicker();
		date_picker.setDate(Calendar.getInstance().getTime());
		date_picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.setRowHeight(50);
		int[] columnsWidth = { 180, 180, 180, 180, 180 };

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
		add(panel);
		
	
    	lbl_date = new JLabel("Date");
		lbl_date.setBounds(0,0,100,30);
		add(lbl_date);
		
		add(date_picker);
		date_picker.addActionListener(this);
		
//		lbl_empty = new JLabel(" ");
//		add(lbl_empty);
//		
//		lbl_date = new JLabel("Date");
//		lbl_date.setBounds(10,0,100,30);
//		add(lbl_date);
//		
//		add(date_picker);
//		date_picker.addActionListener(this);

	}

	public static void main(String args[]) {
		new StockReport();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cal = date_picker.getDate().toString();
		String[] arr = cal.split(" ");
		String date = arr[1] + "/" + arr[2] + "/" + arr[5];
		System.out.println(date);
		try {
			DatabaseConnection db = new DatabaseConnection();
			con = db.getConnection();

			String query = "select Branch_ID, Item_ID, Item_Name, Sales_Date, Available_Stock from Sales_Details where Sales_Date = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, date);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String branch_id = rs.getString("Branch_ID");
				String item_no = rs.getString("Item_ID");
				String item_name = rs.getString("Item_Name");
				String sales_date = rs.getString("Sales_date");
				String quantity = Float.toString(rs.getFloat("Available_Stock"));

				dtm.addRow(new String[] { branch_id, item_no, item_name, sales_date, quantity });

			}
			con.setAutoCommit(true);
	        
		} 
		catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}
}
