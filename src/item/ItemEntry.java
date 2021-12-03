package item;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;

import org.jdesktop.swingx.JXDatePicker;

import com.toedter.calendar.JDateChooser;

import database.connection.DatabaseConnection;

public class ItemEntry extends JPanel implements ActionListener,FocusListener 
{

	JLabel lbl_branch_id, lbl_branch_name, lbl_item_id, lbl_item_name, lbl_unit, lbl_buy_price, lbl_sell_price,
		   lbl_purchase_quantity, lbl_total_price, lbl_available_quantity, lbl_date,lbl_extra_charges;
	JTextField  txt_branch_name, txt_item_name, txt_unit, txt_buy_price, txt_sell_price, txt_purchase_quantity,
			txt_total_price, txt_available_quantity,txt_extra_charges;
	JButton btn_add, btn_delete, btn_update, btn_to_do;
	JXDatePicker date_picker;
	//JComboBox cbox_branch_id,cbox_item_id;

	JComboBox<String> cbox_branch_id,cbox_item_id;
	DefaultComboBoxModel<String> model1 ;
	
	public ItemEntry() {

		setLayout(null);
		setSize(1600, 830);
		setVisible(true);

	//	ImageIcon background = new ImageIcon(getClass().getResource("temp.jpg"));
		JLabel fr = new JLabel();
		fr.setBounds(0, -50, 1440, 360);
		add(fr);

		DatabaseConnection db=new DatabaseConnection();
		db.getConnection();
		
		String item_id[]= {""};
		cbox_item_id =new JComboBox(item_id);
		
		
		lbl_branch_id = new JLabel("Branch Id");
		lbl_branch_name = new JLabel("Branch Name");
		lbl_item_id = new JLabel("Item Id");
		lbl_item_name = new JLabel("Item Name");
		lbl_unit = new JLabel("Unit");
		lbl_buy_price = new JLabel("Buy Price");
		lbl_sell_price = new JLabel("Sell Price");
		lbl_purchase_quantity = new JLabel("Purchase Quantity");
		lbl_total_price = new JLabel("Total Price");
		lbl_available_quantity = new JLabel("Available Quantity");
		lbl_extra_charges = new JLabel("Extra Charges");
		lbl_date = new JLabel("Date");

		lbl_branch_id.setBounds(100, 350, 150, 30);
		lbl_branch_name.setBounds(100, 400, 150, 30);
		lbl_date.setBounds(100, 450, 150, 30);
		lbl_unit.setBounds(100, 500, 150, 30);
		lbl_extra_charges.setBounds(100, 550, 150, 30);
		lbl_available_quantity.setBounds(100, 600, 150, 30);
		
		lbl_item_id.setBounds(650, 350, 150, 30);
		lbl_item_name.setBounds(650, 400, 150, 30);
		lbl_buy_price.setBounds(650, 450, 150, 30);
		lbl_sell_price.setBounds(650, 500, 150, 30);
		lbl_purchase_quantity.setBounds(650, 550, 150, 30);
		lbl_total_price.setBounds(650, 600, 150, 30);
		

		lbl_branch_id.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_branch_name.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_item_id.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_item_name.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_unit.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_buy_price.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_sell_price.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_purchase_quantity.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_total_price.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_available_quantity.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_extra_charges.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_date.setFont(new Font("Cambria", Font.BOLD, 16));

		add(lbl_branch_id);
		add(lbl_branch_name);
		add(lbl_item_id);
		add(lbl_item_name);
		add(lbl_unit);
		add(lbl_buy_price);
		add(lbl_sell_price);
		add(lbl_purchase_quantity);
		add(lbl_total_price);
		add(lbl_available_quantity);
		add(lbl_extra_charges);
		add(lbl_date);

		date_picker = new JXDatePicker();
		date_picker.setDate(Calendar.getInstance().getTime());
		date_picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		add(date_picker);

		JDateChooser calender = new JDateChooser();
		add(calender);

		//cbox_branch_id = new JComboBox();
		txt_branch_name = new JTextField();
		//txt_item_id = new JTextField();
		txt_item_name = new JTextField();
		txt_unit = new JTextField();
		txt_buy_price = new JTextField();
		txt_sell_price = new JTextField();
		txt_purchase_quantity = new JTextField();
		txt_total_price = new JTextField();
		txt_available_quantity = new JTextField();
		//txt_date = new JTextField();
		txt_extra_charges = new JTextField();

		Border blackline = BorderFactory.createLineBorder(Color.BLACK, 2);
		
		
		
		txt_branch_name.setBounds(260, 400, 150, 30);
		txt_branch_name.setBorder(blackline);
		date_picker.setBounds(260, 450, 150, 30);
		date_picker.setBorder(blackline);
		txt_unit.setBounds(260, 500, 150, 30);
		txt_unit.setBorder(blackline);
		txt_extra_charges.setBounds(260, 550, 150, 30);
		txt_extra_charges.setBorder(blackline);
		txt_available_quantity.setBounds(260, 600, 150, 30);
		txt_available_quantity.setBorder(blackline);
		
		cbox_item_id.setBounds(810, 350, 150, 30);
		cbox_item_id.setBorder(blackline);
		txt_item_name.setBounds(810, 400, 150, 30);
		txt_item_name.setBorder(blackline);
		txt_buy_price.setBounds(810, 450, 150, 30);
		txt_buy_price.setBorder(blackline);
		txt_sell_price.setBounds(810, 500, 150, 30);
		txt_sell_price.setBorder(blackline);
		txt_purchase_quantity.setBounds(810, 550, 150, 30);
		txt_purchase_quantity.setBorder(blackline);
		txt_total_price.setBounds(810, 600, 150, 30);
		txt_total_price.setBorder(blackline);
				
		
		add(txt_branch_name);
		add(cbox_item_id);
		add(txt_item_name);
		add(txt_unit);
		add(txt_buy_price);
		add(txt_sell_price);
		add(txt_purchase_quantity);
		add(txt_total_price);
		add(txt_available_quantity);
		add(date_picker);
		add(txt_extra_charges);

		btn_add = new JButton("Add");
		btn_add.setBounds(1200, 400, 100, 30);
		btn_add.setFont(new Font("Cambria", Font.BOLD, 16));
		add(btn_add);
		btn_add.addActionListener(this);

		btn_to_do = new JButton("To Do");
		btn_to_do.setBounds(1200, 450, 100, 30);
		btn_to_do.setFont(new Font("Cambria", Font.BOLD, 16));
		add(btn_to_do);
		btn_to_do.addActionListener(this);

		btn_update = new JButton("Update");
		btn_update.setBounds(1200, 500, 100, 30);
		btn_update.setFont(new Font("Cambria", Font.BOLD, 16));
		add(btn_update);
		btn_update.addActionListener(this);

		btn_delete = new JButton("Delete");
		btn_delete.setBounds(1200, 550, 100, 30);
		btn_delete.setFont(new Font("Cambria", Font.BOLD, 16));
		add(btn_delete);
		btn_delete.addActionListener(this);
		
		
		txt_total_price.addFocusListener(new FocusListener()
		{
			@Override
			public void focusGained(FocusEvent e)
			{				
				//System.out.println("hi");
				float buy_price = Float.parseFloat(txt_buy_price.getText());
				float purchase_quantity = Float.parseFloat(txt_purchase_quantity.getText());
				
				float total_price=buy_price*purchase_quantity;
				txt_total_price.setText(Float.toString(total_price));
			}
			
			public void focusLost(FocusEvent e) {
				 
			}
		});
		
		txt_purchase_quantity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				float purchase_quantity = Float.parseFloat(txt_purchase_quantity.getText());
				float available_quantity = Float.parseFloat(txt_available_quantity.getText());
				float result=purchase_quantity+available_quantity;
				txt_available_quantity.setText(Float.toString(result));
			}
		});
		
		Vector<String> list = new Vector<>();
		try {
			Connection co= db.getConnection();
			String query = "Select branch_id from Branch_Details";
			PreparedStatement pstmt = co.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("branch_id"));
			}
			model1 = new DefaultComboBoxModel<>(list);
			cbox_branch_id = new JComboBox<>(model1);
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	
		String branch_id=cbox_branch_id.getSelectedItem().toString();
		try {
			String query = "Select item_id from Item_Entry where branch_id=?";
			Connection co= db.getConnection();
			PreparedStatement ps = co.prepareStatement(query);
			ps.setString(1, branch_id);
			ResultSet rst = ps.executeQuery();
				
			while (rst.next()) 
			{
				cbox_item_id.addItem(rst.getString("item_id"));
				//System.out.println(rst.getString("emp_id"));
			}	
			co.setAutoCommit(true);
			co.close();
		}
		catch (SQLException e1){
			JOptionPane.showMessageDialog(null, e1.toString());
		}
			
		
		
		cbox_branch_id.setBounds(260, 350, 150, 30);
		cbox_branch_id.setBorder(blackline);
		add(cbox_branch_id);
		
		cbox_item_id.addFocusListener(new FocusListener()
		{
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String item_id = cbox_item_id.getSelectedItem().toString();
				String branch_id=cbox_branch_id.getSelectedItem().toString();
				
				try
				{			
					DatabaseConnection db=new DatabaseConnection();
					Connection con=db.getConnection();
			        String q  = "select item_name,available_quantity,unit from Item_Entry where item_id=? and branch_id=?";
			        		        
			        PreparedStatement pst = con.prepareStatement(q);
			        pst.setString(1, item_id);
			        pst.setString(2, branch_id);
			        ResultSet rs = pst.executeQuery();
			        
			        if(rs!=null)
			        {
			        	while(rs.next())
			        	{	
			        		txt_item_name.setText(rs.getString("item_name"));
			        		txt_available_quantity.setText(Float.toString(rs.getFloat("available_quantity")));
			        		txt_unit.setText(rs.getString("unit"));			        		
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
		cbox_branch_id.addFocusListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj_source = e.getSource();
		int x=0;
		String regex_name="^[a-zA-Z]*$";
		if(!Pattern.matches(regex_name, txt_item_name.getText())){
			x++;
			JOptionPane.showMessageDialog(null, "Enter a valid item name");
		}
		
		String regex_unit="^[a-zA-Z]*$";
		if(!Pattern.matches(regex_unit, txt_unit.getText())){
			x++;
			JOptionPane.showMessageDialog(null, "Enter a valid Unit");
		}
		
		String regex_buy_price="([0-9]*[.])?[0-9]+";
		if(!Pattern.matches(regex_buy_price, txt_buy_price.getText())){
			x++;
			JOptionPane.showMessageDialog(null, "Enter a valid Buy Price");
		}
		String regex_sell_price="([0-9]*[.])?[0-9]+";
		if(!Pattern.matches(regex_sell_price, txt_sell_price.getText())){
			x++;
			JOptionPane.showMessageDialog(null, "Enter a valid Sell Price");
		}
		String regex_purchase_quantity="([0-9]*[.])?[0-9]+";
		if(!Pattern.matches(regex_purchase_quantity, txt_purchase_quantity.getText())){
			x++;
			JOptionPane.showMessageDialog(null, "Enter a valid Purchase Quantity");
		}
		String regex_total_price="([0-9]*[.])?[0-9]+";
		if(!Pattern.matches(regex_total_price, txt_total_price.getText())){
			x++;
			JOptionPane.showMessageDialog(null, "Enter a valid Total Price");
		}
		
		
		String dates[] = {"","Jan","Feb","Mar","Apr","May","Jun","Jul",
				"Aug","Sep","Oct","Nov","Dec"};
		if (obj_source == btn_add) {
			String branch_id = cbox_branch_id.getSelectedItem().toString();
			String branch_name = txt_branch_name.getText();
			String item_id = cbox_item_id.getSelectedItem().toString();
			String item_name = txt_item_name.getText();
			String unit = txt_unit.getText();
			float buy_price = Float.parseFloat(txt_buy_price.getText());
			float sell_price = Float.parseFloat(txt_sell_price.getText());
			float purchase_quantity = Float.parseFloat(txt_purchase_quantity.getText());
			float total_price = Float.parseFloat(txt_total_price.getText());
			float available_quantity = Float.parseFloat(txt_available_quantity.getText());
			String cal = date_picker.getDate().toString();
			String[] arr = cal.split(" ");
			String date = arr[2] + "/" + arr[1] + "/" + arr[5];
			String month=arr[1];
			float extra_charges = Float.parseFloat(txt_extra_charges.getText());
			
			String branchid = cbox_branch_id.getSelectedItem().toString();
			String temp_b_id = branchid.substring(0,2);
			
			String itemname = txt_item_name.getText();
			String temp_name=itemname.substring(0,1);
			
			String i_id = "";
			String temp_i_id = cbox_item_id.getSelectedItem().toString();
			if(temp_i_id=="")
			{
				String t_id = "1";
				i_id = temp_b_id + temp_name+t_id;
			}
			else
			{
				int temp_id = Integer.parseInt(temp_i_id.substring(3,4));
				System.out.println(temp_id);
				int id = temp_id + 1;
				if(id>=10)
				{
					String id1 = Integer.toString(id);
					i_id = temp_i_id.replace((temp_i_id.substring(3,4)), id1);
					System.out.println(i_id);
				}
				else
				{
					String id1 = "0" + Integer.toString(id);
					i_id = temp_i_id.replace((temp_i_id.substring(3,4)), id1);
					System.out.println(i_id);
				}		
			}
			
			
			ItemJavaBean jbean = new ItemJavaBean();
			jbean.setBranch_id(branch_id);
			jbean.setBranch_name(branch_name);
			jbean.setItem_id(i_id);
			jbean.setItem_name(item_name);
			jbean.setDate(date);
			jbean.setUnit(unit);
			jbean.setBuy_price(buy_price);
			jbean.setSell_price(sell_price);
			jbean.setPurchase_quantity(purchase_quantity);
			jbean.setTotal_price(total_price);
			jbean.setAvailable_quantity(available_quantity);
			jbean.setDate(date);
			jbean.setMonth(month);
			jbean.setExtra_charges(extra_charges);

			ItemConnection conn = new ItemConnection();
			conn.connectWithOracle(jbean);
		}

		else if (obj_source == btn_to_do) {
			String item_id = cbox_item_id.getSelectedItem().toString();
			String branch_id = cbox_branch_id.getSelectedItem().toString();
			try {
				DatabaseConnection db=new DatabaseConnection();
				Connection con=db.getConnection();
				String q1 = " select branch_name,date_cal,unit,extra_charges,item_name,buy_price,sell_price,purchase_quantity,available_quantity,total_price from Item_Entry where item_id=? and branch_id=?";

				PreparedStatement pst = con.prepareStatement(q1);
				pst.setString(1, item_id);
				pst.setString(2, branch_id);
				ResultSet rs = pst.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						
						txt_branch_name.setText(rs.getString("branch_name"));
						txt_item_name.setText(rs.getString("item_name"));
						txt_unit.setText(rs.getString("unit"));
						txt_buy_price.setText(Float.toString(rs.getFloat("buy_price")));
						txt_sell_price.setText(Float.toString(rs.getFloat("sell_price")));
						txt_purchase_quantity.setText(Float.toString(rs.getFloat("purchase_quantity")));
						txt_total_price.setText(Float.toString(rs.getFloat("total_price")));
						txt_available_quantity.setText(Float.toString(rs.getFloat("available_quantity")));
						//txt_date.setText(rs.getString("date_cal"));
						txt_extra_charges.setText(Float.toString(rs.getFloat("extra_charges")));

					}
				}
				
				con.setAutoCommit(true);
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		}

		else if (obj_source == btn_update) {
			String branch_id = cbox_branch_id.getSelectedItem().toString();
			String branch_name = txt_branch_name.getText();
			String item_id = cbox_item_id.getSelectedItem().toString();
			String item_name = txt_item_name.getText();
			String unit = txt_unit.getText();
			float buy_price = Float.parseFloat(txt_buy_price.getText());
			float sell_price = Float.parseFloat(txt_sell_price.getText());
			float purchase_quantity = Float.parseFloat(txt_purchase_quantity.getText());
			float total_price = Float.parseFloat(txt_total_price.getText());
			float available_quantity = Float.parseFloat(txt_available_quantity.getText());
			String date = date_picker.getDate().toString();
			System.out.println(date);
			String month=date.substring(4,7);
			//System.out.println(month);
			float extra_charges = Float.parseFloat(txt_extra_charges.getText());

			try {
				DatabaseConnection db=new DatabaseConnection();
				Connection con=db.getConnection();
				String q2 = "update Item_Entry set branch_name=?,item_name=?,unit=?,buy_price=?,sell_price=?,purchase_quantity=?,total_price=?,available_quantity=?,month=?,extra_charges=? where branch_id=? and item_id=?  ";
				PreparedStatement pst = con.prepareStatement(q2);

				
				pst.setString(1, branch_name);
				pst.setString(2, item_id);
				pst.setString(3, item_name);
				pst.setString(4, unit);
				pst.setFloat(5, buy_price);
				pst.setFloat(6, sell_price);
				pst.setFloat(7, purchase_quantity);
				pst.setFloat(8, total_price);
				pst.setFloat(9, available_quantity);
				//pst.setString(10, date);
				pst.setString(10, month);
				pst.setFloat(11, extra_charges);
				pst.setString(12, branch_id);
				pst.executeUpdate();
				con.setAutoCommit(true);
				con.close();
				JOptionPane.showMessageDialog(null, "Updation Successfully! ");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		} else if (obj_source == btn_delete) {
			try {
				String item_id = cbox_item_id.getSelectedItem().toString();
				String branch_id = cbox_branch_id.getSelectedItem().toString();
				DatabaseConnection db=new DatabaseConnection();
				Connection con=db.getConnection();
				String query = "delete from Item_Entry where item_id=?,branch_id=?";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, item_id);
				pst.setString(2, branch_id);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Deleted Successfully! ", "Alert", JOptionPane.WARNING_MESSAGE);
				con.setAutoCommit(true);
				con.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
			}
		}
	}

	public void focusGained(FocusEvent e) 
	{
		
	}

	@Override
	public void focusLost(FocusEvent e) 
	{
		String Branch_ID = cbox_branch_id.getSelectedItem().toString();
		try
		{			
			DatabaseConnection db=new DatabaseConnection();
			Connection con=db.getConnection();
	        String query  = "select branch_name from Branch_Details where branch_id='"+Branch_ID+"'";
	        		        
	        PreparedStatement pst = con.prepareStatement(query);
	        ResultSet rs = pst.executeQuery();
	        
	        if(rs!=null)
	        {
	        	while(rs.next())
	        	{	
	        		txt_branch_name.setText(rs.getString("branch_name"));
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
}