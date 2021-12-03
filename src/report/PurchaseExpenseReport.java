package report;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

import database.connection.DatabaseConnection;


public class PurchaseExpenseReport extends JPanel implements ActionListener {
	JPanel panel;
	Connection con;
	JTable table;
	JLabel lbl_month,lbl_empty;
	DefaultTableModel dtm;
	JComboBox<String> cbx_month;
	DefaultComboBoxModel<String> model1, model2 ;
	
	PurchaseExpenseReport(){
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
		cbx_month.setBounds(0,0,100,50);
		cbx_month.addActionListener(this);
		
	    
		DatabaseConnection db = new DatabaseConnection();
		con = db.getConnection();
	
		String[] columnNames = {"Month", "Item ID", "Item Name", "Total Price", "Extra Charges"};
		dtm=new DefaultTableModel(columnNames,0);
	    table = new JTable(dtm);
		
		table.setRowHeight(50);
		int[] columnsWidth = {180,180,180,180,180};

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
		 panel.setPreferredSize(new Dimension(1000, 650));
		 add(panel);

	}
	public void actionPerformed(ActionEvent e) {
		String str_month = cbx_month.getSelectedItem().toString();
		System.out.println(str_month);
		String st_month = str_month.substring(0,3);
		System.out.println(st_month);
		
		try {
			String	query = "select Month, Item_ID, Item_Name, Total_Price,Extra_Charges from Item_Entry where Month = ?";
		    PreparedStatement pst = con.prepareStatement(query);
		    pst.setString(1,st_month);
	        ResultSet rs = pst.executeQuery();
	        float sum = 0;
	        while (rs.next()) {
	              String date = rs.getString("Month");
	               String item_no = rs.getString("Item_ID");
	               String item_name = rs.getString("Item_Name");
	               String total_price =  Float.toString(rs.getFloat("Total_Price"));
	               String extra_charges = Float.toString(rs.getFloat("Extra_Charges"));

	               dtm.addRow(new String[]{date,item_no, item_name,total_price,extra_charges});
	               sum += rs.getFloat("Total_Price")+rs.getFloat("Extra_Charges");
	           }
	        dtm.addRow(new String[]{"","","","Total",Float.toString(sum)}); 
	        con.setAutoCommit(true);
		}
		catch(Exception ae){
			System.out.println(ae.toString());
		}
	}

	
	public static void main(String args[]) {
		new PurchaseExpenseReport();
	}

}
