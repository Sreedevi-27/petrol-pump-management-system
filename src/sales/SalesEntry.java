package sales;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.JXDatePicker;
import com.toedter.calendar.JDateChooser;

import database.connection.DatabaseConnection;

public class SalesEntry extends JPanel implements ActionListener 
{

	private static final long serialVersionUID = 1L;

	// Declaration of Labels
	JLabel lbl_Branch_ID, lbl_Branch_Name,lbl_ExtraPurchase, lbl_Item_ID, lbl_Item_Name, lbl_Item_Unit, lbl_Item_Price,
			lbl_Sales_quantity, lbl_Sales_Amount, lbl_Available_Stock, lbl_Date;

	// Declaration of TextFields
	JTextField txt_Branch_Name, txt_Item_Name, txt_Item_Unit, txt_Item_price, txt_sales_quantity, txt_sales_amount,
			txt_Available_Stock;

	// Declaration of Combo Box
	JComboBox<String> cbx_Branch_ID, cbx_Item_ID,cbx_ExtraItem;
	// Declaration of Buttons
	JButton btn_Print;
	Container co;
	// Date Picker
	JXDatePicker date_picker;
	//Dynamic Allocation of ComboBox
	DefaultComboBoxModel<String> model1, model2;
	
	DatabaseConnection db_Object=new DatabaseConnection();
	
	//Border color
	Border blackline;
	//Decalring Panel
	JPanel pnl_purchase;
	DefaultTableModel model;
	//Declaration of Tables
	JTable tbl_Purchase_products_cart;
	
	public SalesEntry() {
		//co = getContentPane();
		setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adding Background Image
		ImageIcon background = new ImageIcon(getClass().getResource("temp.jpeg"));
		JLabel fr = new JLabel(background);
		fr.setBounds(0, -50, 1440, 360);
		add(fr);
		
		// Creating Labels
		lbl_Branch_ID = new JLabel("Branch ID");
		lbl_Branch_Name = new JLabel("Branch Name");
		lbl_Date = new JLabel("Date");
		lbl_Item_ID = new JLabel("Item ID");
		lbl_Item_Name = new JLabel("Item Name");
		lbl_Item_Unit = new JLabel("Unit");
		lbl_Item_Price = new JLabel("Price/liter");
		lbl_Sales_quantity = new JLabel("Quantity");
		lbl_Sales_Amount = new JLabel("Amount");
		lbl_Available_Stock = new JLabel("Available Stock");
	    lbl_ExtraPurchase =new JLabel("Extra Purchase");
		
		// Calender
		date_picker = new JXDatePicker();
		date_picker.setDate(Calendar.getInstance().getTime());
		date_picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
		add(date_picker);
		JDateChooser calender = new JDateChooser();
		add(calender);

		// Adding Labels
		add(lbl_Branch_ID);
		add(lbl_Branch_Name);
		add(lbl_Available_Stock);
		add(lbl_Date);
		add(lbl_Item_ID);
		add(lbl_Item_Name);
		add(lbl_Item_Unit);
		add(lbl_Item_Price);
		add(lbl_Sales_quantity);
		add(lbl_Sales_Amount);
		add(lbl_ExtraPurchase);

		pnl_purchase=new JPanel();
		// combo box values
		Vector<String> list = new Vector<>();
		try {
			Connection co=db_Object.getConnection();
			String query = "Select Branch_ID from Branch_Details";
			PreparedStatement pstmt = co.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("Branch_ID"));
			}
			model1 = new DefaultComboBoxModel<>(list);
			cbx_Branch_ID = new JComboBox<>(model1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Vector<String> list1 = new Vector<>();
		try {
			Connection co=db_Object.getConnection();
			String query = "Select Item_ID from Item_Entry";
			PreparedStatement pstmt = co.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list1.add(rs.getString("Item_ID"));
			}
			model2 = new DefaultComboBoxModel<>(list1);
			cbx_Item_ID = new JComboBox<>(model2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] extraItems= {"","Gasoline","Jet Fuel","Lubricants","Heavy Fuel Oil","Asphalt","Kerosene","CNG","Air Refilling"};
		cbx_ExtraItem = new JComboBox(extraItems);
		cbx_ExtraItem.setBorder(blackline);
		add(cbx_Branch_ID);
		add(cbx_Item_ID);
		add(cbx_ExtraItem);
		
		// Setting Bounds to the labels
		lbl_Branch_ID.setBounds(100, 310, 100, 100);
		// lbl_Branch_ID.setOpaque(false);
		lbl_Branch_Name.setBounds(100, 360, 100, 100);
		lbl_Available_Stock.setBounds(100, 410, 150, 100);
		lbl_Date.setBounds(100, 460, 150, 100);
		lbl_ExtraPurchase.setBounds(100,510,150,100);
		
		lbl_Item_ID.setBounds(600, 310, 100, 100);
		lbl_Item_Name.setBounds(600, 360, 100, 100);
		lbl_Item_Unit.setBounds(600, 410, 100, 100);
		lbl_Item_Price.setBounds(600, 460, 100, 100);
		lbl_Sales_quantity.setBounds(600, 510, 100, 100);
		lbl_Sales_Amount.setBounds(600, 560, 100, 100);
		
		lbl_Branch_ID.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Branch_Name.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Date.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_ExtraPurchase.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Available_Stock.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Item_ID.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Item_Name.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Item_Unit.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Item_Price.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Sales_quantity.setFont(new Font("Cambria", Font.BOLD, 16));
		lbl_Sales_Amount.setFont(new Font("Cambria", Font.BOLD, 16));

		// Setting Borders to the Branch Details
		blackline = BorderFactory.createLineBorder(Color.BLACK, 2);

		// Creating TextFields
		txt_Branch_Name = new JTextField();
		txt_Item_Name = new JTextField();
		txt_Item_Unit = new JTextField();
		txt_Item_price = new JTextField();
		txt_sales_quantity = new JTextField();
		txt_sales_amount = new JTextField();
		txt_Available_Stock = new JTextField();

		// Adding TextFields
		add(txt_Branch_Name);
		add(txt_Item_Name);
		add(txt_Item_Unit);
		add(txt_Item_price);
		add(txt_sales_quantity);
		add(txt_sales_amount);
		add(txt_Available_Stock);

		// setting Bounds to txt fields
		cbx_Branch_ID.setBounds(250, 350, 150, 30);
		cbx_Branch_ID.setBorder(blackline);
		cbx_Item_ID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Item_ID = cbx_Item_ID.getSelectedItem().toString();
				try {
					Connection co=db_Object.getConnection();

					String query = "select Branch_ID,Branch_Name,Available_Quantity,Item_Name,Unit,Sell_Price from Item_Entry where Item_ID='" + Item_ID
							+ "'";

					PreparedStatement pst = co.prepareStatement(query);
					
					ResultSet rs = pst.executeQuery();

					if (rs != null) {
						while (rs.next()) {
							cbx_Branch_ID.setSelectedItem(rs.getString("Branch_ID"));
							txt_Branch_Name.setText(rs.getString("Branch_Name"));
							txt_Item_Name.setText(rs.getString("Item_Name"));
							txt_Available_Stock.setText(rs.getString("Available_Quantity"));
							txt_Item_Unit.setText(rs.getString("Unit"));
							txt_Item_price.setText(Float.toString(rs.getFloat("Sell_Price")));
						}
					}
					co.setAutoCommit(true);
					co.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		});
		cbx_ExtraItem.setBorder(blackline);
		cbx_ExtraItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String extra_purchase=cbx_ExtraItem.getSelectedItem().toString();
				if(extra_purchase.equals("Gasoline"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+100;
					txt_sales_amount.setText(Float.toString(result));
				}
				else if(extra_purchase.equals("Jet Fuel"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+250;
					txt_sales_amount.setText(Float.toString(result));
				}
				else if(extra_purchase.equals("Lubricants"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+300;
					txt_sales_amount.setText(Float.toString(result));
				}
				else if(extra_purchase.equals("Heavy Fuel Oil"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+250;
					txt_sales_amount.setText(Float.toString(result));
				}
				else if(extra_purchase.equals("Asphalt"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+50;
					txt_sales_amount.setText(Float.toString(result));
				}
				else if(extra_purchase.equals("Kerosene"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+80;
					txt_sales_amount.setText(Float.toString(result));
				}
				else if(extra_purchase.equals("CNG"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+90;
					txt_sales_amount.setText(Float.toString(result));
				}
				else if(extra_purchase.equals("Air Refilling"))
				{
					float amount=Float.parseFloat(txt_sales_amount.getText());
					float result=amount+30;
					txt_sales_amount.setText(Float.toString(result));
				}
			}
		});
		calender.setBorder(blackline);
		txt_Branch_Name.setBounds(250, 400, 150, 30);
		txt_Branch_Name.setBorder(blackline);
		txt_Available_Stock.setBounds(250, 450, 150, 30);
		txt_Available_Stock.setBorder(blackline);
		calender.setBounds(250, 500, 150, 30);
		cbx_ExtraItem.setBounds(250,550,150,30);
		
		cbx_Item_ID.setBounds(700, 350, 150, 30);
		cbx_Item_ID.setBorder(blackline);
		txt_Item_Name.setBounds(700, 400, 150, 30);
		txt_Item_Name.setBorder(blackline);
		txt_Item_Unit.setBounds(700, 450, 150, 30);
		txt_Item_Unit.setBorder(blackline);
		txt_Item_price.setBounds(700, 500, 150, 30);
		txt_Item_price.setBorder(blackline);
		txt_sales_quantity.setBounds(700, 550, 150, 30);
		txt_sales_quantity.setBorder(blackline);
		txt_sales_amount.setBounds(700, 600, 150, 30);
		txt_sales_amount.setBorder(blackline);
		

		txt_sales_quantity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				float item_Price_Per_Liter = Float.parseFloat(txt_Item_price.getText());
				float available_Stock = Float.parseFloat(txt_Available_Stock.getText());
				float quantity = Float.parseFloat(txt_sales_quantity.getText());
				float result=available_Stock-quantity;
				float amount = quantity * item_Price_Per_Liter;
				txt_Available_Stock.setText(Float.toString(result));
				txt_sales_amount.setText(Float.toString(amount));
			}
		});
		// Creation of Button
		btn_Print = new JButton("Print");

		// Adding button
		add(btn_Print);

		// Adding Action Listener
		btn_Print.addActionListener(this);
		// Setting Bounds for Buttons
		btn_Print.setBounds(180, 625, 80, 30);
		btn_Print.setBorder(blackline);
		btn_Print.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Float Available_Stock=Float.parseFloat(txt_Available_Stock.getText());
						String Item_ID=cbx_Item_ID.getSelectedItem().toString();
						try
						{
							Connection co=db_Object.getConnection();
							String query="Update Item_Entry set Available_Quantity='"+Available_Stock+"' where Item_ID='"+Item_ID+"'";
							PreparedStatement pstmt=co.prepareStatement(query);
							pstmt.executeQuery();
							co.setAutoCommit(true);
							co.close();
						}
						catch(Exception ex)
						{
							JOptionPane.showConfirmDialog(null, ex.toString());
						}
					}
				});
		
       
        setSize(1600,830);
		setVisible(true);
	}

	public static void main(String[] args) {
		new SalesEntry();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn_source = e.getSource();
		String branch_ID = cbx_Branch_ID.getSelectedItem().toString();
		String branch_Name = txt_Branch_Name.getText();
		float available_Stock = Float.parseFloat(txt_Available_Stock.getText());
		String temp = date_picker.getDate().toString();
		String[] temp1 = temp.split(" ");
		String date = temp1[1] + "/" + temp1[2] + "/" + temp1[5];
		String item_ID = cbx_Item_ID.getSelectedItem().toString();
		String item_Name = txt_Item_Name.getText();
		String item_Unit = txt_Item_Unit.getText();
		float item_Price_Per_Liter = Float.parseFloat(txt_Item_price.getText());
		float sales_Quantity = Float.parseFloat(txt_sales_quantity.getText());
		float sales_Amount = Float.parseFloat(txt_sales_amount.getText());
		String extra_Item=cbx_ExtraItem.getSelectedItem().toString();
		
		SalesJavaBean ob = new SalesJavaBean();
		
		String regex_float_validate="([0-9]*[.])?[0-9]+";
		String regex_sell_price="([0-9]*[.])?[0-9]+";
		String regx_item_name = "^[0-9 A-Za-z]*$";
		String regex_item_unit="^[A-Za-z]*$";
		if(!Pattern.matches(regx_item_name, txt_Item_Name.getText())){
			JOptionPane.showMessageDialog(null, "Enter a valid Item Name");
			return;
		}
		if(!Pattern.matches(regex_item_unit, txt_Item_Unit.getText())){
			JOptionPane.showMessageDialog(null, "Enter a valid Item Unit");
			return;
		}

		if(!Pattern.matches(regex_float_validate,txt_Item_price.getText())){
			JOptionPane.showMessageDialog(null, "Enter a valid Item Price");
			return;
		}
		if(!Pattern.matches(regex_float_validate,txt_sales_quantity.getText())){
			JOptionPane.showMessageDialog(null, "Enter a valid Item Sales Quantity");
			return;
		}
		if(!Pattern.matches(regex_float_validate,txt_sales_amount.getText())){
			JOptionPane.showMessageDialog(null, "Enter a valid Item Sales Amount");
			return;
		}
		ob.setBranch_ID(branch_ID);
		ob.setBranch_Name(branch_Name);
		ob.setAvailable_Stock(available_Stock);
		ob.setDate(date);
		ob.setItem_ID(item_ID);
		ob.setItem_Name(item_Name);
		ob.setItem_Unit(item_Unit);
		ob.setExtraItem(extra_Item);
		ob.setItem_Price_Per_Liter(item_Price_Per_Liter);
		ob.setSales_Amount(sales_Amount);
		ob.setSales_Quantity(sales_Quantity);
		
		if (btn_source == btn_Print) 
		{
			SalesEntryConnection ob1 = new SalesEntryConnection();
			ob1.Sales_Entry_Connection1(ob);
			
			int bill_no=ob1.tbl_bill();
			
			lbl_Item_ID.setBounds(500, 350, 100, 100);
			lbl_Item_Name.setBounds(500, 400, 100, 100);
			lbl_Item_Unit.setBounds(500, 450, 100, 100);
			lbl_Item_Price.setBounds(500, 500, 100, 100);
			lbl_Sales_quantity.setBounds(500, 550, 100, 100);
			lbl_Sales_Amount.setBounds(500, 600, 100, 100);
			lbl_Branch_ID.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Branch_Name.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Date.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Available_Stock.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Item_ID.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Item_Name.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Item_Unit.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Item_Price.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Sales_quantity.setFont(new Font("Cambria", Font.BOLD, 15));
			lbl_Sales_Amount.setFont(new Font("Cambria", Font.BOLD, 15));
			cbx_Item_ID.setBounds(600, 390, 150, 25);
			cbx_Item_ID.setBorder(blackline);
			txt_Item_Name.setBounds(600, 440, 150, 25);
			txt_Item_Name.setBorder(blackline);
			txt_Item_Unit.setBounds(600, 490, 150, 25);
			txt_Item_Unit.setBorder(blackline);
			txt_Item_price.setBounds(600, 540, 150, 25);
			txt_Item_price.setBorder(blackline);
			txt_sales_quantity.setBounds(600, 590, 150, 25);
			txt_sales_quantity.setBorder(blackline);
			txt_sales_amount.setBounds(600, 640, 150, 25);
			txt_sales_amount.setBorder(blackline);
			String [] columnNames={"BillNo","Item Name","Quantity","Item Price/unit","Extra Item","Price"};
			
			model = new DefaultTableModel(columnNames,0);
			
			tbl_Purchase_products_cart =new JTable(model);
			
			tbl_Purchase_products_cart.setRowHeight(25);
			
			int[] columnsWidth = {50,100,100,100,150,100};
			String Item_Name=ob.getItem_Name();
			String sales_quantity=Float.toString(ob.getSales_Quantity());
			String extra_item=ob.getExtraItem();
			String price_per_unit=Float.toString(ob.getItem_Price_Per_Liter());
			String price=Float.toString(ob.getSales_Amount());
			model.addRow(new String[]{Integer.toString(bill_no),Item_Name,sales_quantity,price_per_unit,extra_item,price});
			
			int i = 0;
			for (int width : columnsWidth) {
				TableColumn column = tbl_Purchase_products_cart.getColumnModel().getColumn(i++);
				column.setMinWidth(width);
				column.setMaxWidth(width);
				column.setPreferredWidth(width);
			}
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			tbl_Purchase_products_cart.setDefaultRenderer(Object.class, centerRenderer);
			tbl_Purchase_products_cart.setPreferredScrollableViewportSize(new Dimension(600,300));
			tbl_Purchase_products_cart.setFillsViewportHeight(true); 
			pnl_purchase.add(new JScrollPane(tbl_Purchase_products_cart));
			pnl_purchase.setPreferredSize(new Dimension(600, 300));
			JLabel lbl_quote=new JLabel("Have a safe Journey!!!");
			lbl_quote.setFont(new Font("Cambria", Font.BOLD, 15));
			pnl_purchase.add(lbl_quote);
			add(pnl_purchase);
			pnl_purchase.setBounds(900, 380, 600, 400);
			
		}
	}

	

}