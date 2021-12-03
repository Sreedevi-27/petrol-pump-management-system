package item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ItemConnection {

	public void connectWithOracle(ItemJavaBean jbean) {
		String branch_id=jbean.getBranch_id();
		String branch_name=jbean.getBranch_name();
		String item_id=jbean.getItem_id();
		String item_name=jbean.getItem_name();
		String unit=jbean.getUnit();
		float buy_price=jbean.getBuy_price();
		float sell_price=jbean.getSell_price();
		float purchase_quantity=jbean.getPurchase_quantity();
		float total_price=jbean.getTotal_price();
		float available_quantity=jbean.getAvailable_quantity() + jbean.getPurchase_quantity();
		String date=jbean.getDate();
		String month=jbean.getMonth();
		float extra_charges=jbean.getExtra_charges();
		int no_of_items=0;
		
		try{               
			
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE","project1","ppms");
            String query="insert into Item_Entry values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,branch_id );
            ps.setString(2,branch_name );
            ps.setString(3,item_id );
            ps.setString(4,item_name);
            ps.setString(5,unit );          
            ps.setFloat(6,buy_price);
            ps.setFloat(7,sell_price);
            ps.setFloat(8,purchase_quantity);
            ps.setFloat(9,total_price);
            ps.setFloat(10,available_quantity);
            ps.setString(11,date );
            ps.setString(12,month);
            ps.setFloat(13,extra_charges);
            ps.executeUpdate();
            
            String items="select no_of_items from Branch_Details";
            PreparedStatement pst=con.prepareStatement(items);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
            	no_of_items=rs.getInt("no_of_items")+1;
            }
            
            
            String q="update Branch_Details set no_of_items=? where branch_id=?";
            PreparedStatement pt=con.prepareStatement(q);
            
            pt.setInt(1, no_of_items);
            pt.setString(2, branch_id);
            pt.executeUpdate();
            
            con.setAutoCommit(true);
            con.close();
            JOptionPane.showMessageDialog(null, "Product Added Successfully!");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
	}
}
