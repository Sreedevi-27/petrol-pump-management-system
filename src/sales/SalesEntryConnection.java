package sales;

import java.sql.*;
import javax.swing.*;

import database.connection.*;

public class SalesEntryConnection 
{
	Connection con;
	DatabaseConnection conn=new DatabaseConnection();
	int max;
	
	public void Sales_Entry_Connection1(SalesJavaBean ob) 
	{
		// TODO Auto-generated method stub
		String branch_ID=ob.getBranch_ID();
		String branch_Name=ob.getBranch_Name();
		float available_Stock=ob.getAvailable_Stock();
		String sales_Date=ob.getDate();
		String item_ID=ob.getItem_ID();
		String item_Unit=ob.getItem_Unit();
		String item_Name=ob.getItem_Name();
		String extra_item=ob.getExtraItem();
		float item_Price=ob.getItem_Price_Per_Liter();
		float item_sales_Quantity=ob.getSales_Quantity();
		float item_sales_Amount=ob.getSales_Amount();
		try
		{
			con=conn.getConnection();
            String query="insert into Sales_Details(Branch_ID,Branch_Name,Available_Stock,Sales_Date,Item_ID,Item_Name,Extra_Item,Unit,sell_Price,Sales_Quantity,Sales_Amount) values('"+branch_ID+"','"+branch_Name+"','"+available_Stock+"','"+sales_Date+"','"+item_ID+"','"+item_Name+"','"+extra_item+"','"+item_Unit+"','"+item_Price+"','"+item_sales_Quantity+"','"+item_sales_Amount+"')";
            PreparedStatement psmt=con.prepareStatement(query);
            psmt.executeUpdate();
            con.setAutoCommit(true);
            con.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showConfirmDialog(null, ex.toString());
		}
	}
	
	public int tbl_bill() {
		try {
			con=conn.getConnection();
			String query1="select max(bill_no) as max_bill from sales_Details";
	        PreparedStatement psmt1=con.prepareStatement(query1);
	        ResultSet rs=psmt1.executeQuery();
	        while(rs.next())
	        {
	        	max=rs.getInt("max_bill");
	        }
			}
			catch(Exception ex)
			{
				JOptionPane.showConfirmDialog(null, ex.toString());
			}
			return max;
	}
}
