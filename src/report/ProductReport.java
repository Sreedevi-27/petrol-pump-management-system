package report;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import database.connection.DatabaseConnection;

public class ProductReport extends JPanel implements ActionListener {
	Connection con;
	JTable table;
	JLabel lbl_item_id, lbl_branch_id,lbl_empty;
	DefaultTableModel dtm;
	
	JComboBox<String> cbx_branch_id, cbx_item_id;
	DefaultComboBoxModel<String> model1, model2 ;
	JPanel panel;
	
	ProductReport() {
		
		panel = new JPanel();
		String[] columnNames = {"Branch ID", "Item ID", "Item Name", "Date", "Buy Price", "Sell Price"};
		dtm=new DefaultTableModel(columnNames,0);
	    table = new JTable(dtm);
	    
		DatabaseConnection db = new DatabaseConnection();
		con = db.getConnection();
		
		Vector<String> list = new Vector<>();
		try {
			Connection co= db.getConnection();
			String query = "Select Branch_ID from Item_Entry";
			PreparedStatement pstmt = co.prepareStatement(query);
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
			Connection co=db.getConnection();
			String query = "Select Item_ID from Item_Entry";
			PreparedStatement pstmt = co.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list1.add(rs.getString("Item_ID"));
			}
			model2 = new DefaultComboBoxModel<>(list1);
			cbx_item_id = new JComboBox<>(model2);
		} catch (SQLException e1) {
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
		
		lbl_item_id = new JLabel("Item ID");
		lbl_item_id.setBounds(10,0,100,30);
		add(lbl_item_id);
		
		cbx_item_id.setBounds(30,0,100,30);
		add(cbx_item_id);
		cbx_item_id.addActionListener(this);

		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(Object.class, centerRenderer);
		
		table.setRowHeight(50);
		int[] columnsWidth = {150,150,150,150,150,150};

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
		
	
	public static void main(String args[]) {
		new ProductReport();
		}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String item_id = cbx_item_id.getSelectedItem().toString();
		String branch_id = cbx_branch_id.getSelectedItem().toString();
		
		String query = "";
		String txt ="";
		
		if(ob == cbx_item_id) {
			query = "select Branch_ID, Item_ID, Item_Name, Date_cal, Buy_Price, Sell_Price from Item_Entry where Item_ID = ?";
	       txt = item_id;
		}
		
		if(ob == cbx_branch_id) {
			query = "select Branch_ID, Item_ID, Item_Name, Date_cal, Buy_Price, Sell_Price from Item_Entry where Branch_ID = ?";
			  txt = branch_id;
		}
		
		
		try {
		    PreparedStatement pst = con.prepareStatement(query);
		    pst.setString(1,txt);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	        	   String branch_no = rs.getString("Branch_ID");
	               String item_no = rs.getString("Item_ID");
	               String item_name = rs.getString("Item_Name");
	               String date = rs.getString("Date_cal");
	               String buy_price =  Float.toString(rs.getFloat("Buy_Price"));
	               String sell_price = Float.toString(rs.getFloat("Sell_Price"));

	               dtm.addRow(new String[]{branch_no,item_no, item_name,date,buy_price,sell_price});
	                   
	           }
	        con.setAutoCommit(true);
		}
		catch(Exception ae){
			System.out.println(ae.toString());
		}
	}
}

