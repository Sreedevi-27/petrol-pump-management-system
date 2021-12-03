package report;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewReport extends JPanel 
{
	JButton bt_product, bt_employee, bt_stock, bt_expense, bt_sales;
	public ViewReport() {
		setLayout(null);
		setSize(1600,830);
		setVisible(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		JPanel  panel1, panel2, panel3, panel4, panel5, panel6, panel7;
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
	
	    panel1.setBounds(getVisibleRect());
	   
	   
		tabbedPane.add("",new ProductReport());
		tabbedPane.add("",new SalesReport());
		tabbedPane.add("",new EmployeeReport());
		tabbedPane.add("",new StockReport());
		tabbedPane.add("",new EmployeeExpenseReport());
		tabbedPane.add("",new PurchaseExpenseReport());
//		tabbedPane.add("",new Charts());
		
		
		
		JLabel p1 = new JLabel("Product Report");
		p1.setFont(new Font("Cambria",Font.BOLD,20));
		p1.setPreferredSize(new Dimension(180, 50));
		p1.setHorizontalAlignment(0);
		tabbedPane.setTabComponentAt(0,p1);
		
		
		JLabel p2 = new JLabel("Sales Report");
		p2.setFont(new Font("Cambria",Font.BOLD,20));
		p2.setPreferredSize(new Dimension(180, 50));
		p2.setHorizontalAlignment(0);
		tabbedPane.setTabComponentAt(1,p2);
		
		JLabel p3 = new JLabel("Employee Report");
		p3.setFont(new Font("Cambria",Font.BOLD,20));
		p3.setPreferredSize(new Dimension(180, 50));
		p3.setHorizontalAlignment(0);
		tabbedPane.setTabComponentAt(2,p3);
		
		JLabel p4 = new JLabel("Stock Report");
		p4.setFont(new Font("Cambria",Font.BOLD,20));
		p4.setPreferredSize(new Dimension(180, 50));
		p4.setHorizontalAlignment(0);
		tabbedPane.setTabComponentAt(3,p4);
		
		JLabel p5 = new JLabel("Employee Expense");
		p5.setFont(new Font("Cambria",Font.BOLD,20));
		p5.setPreferredSize(new Dimension(180, 50));
		p5.setHorizontalAlignment(0);
		tabbedPane.setTabComponentAt(4,p5);
		
		
		JLabel p6 = new JLabel("Purchase Expense");
		p6.setFont(new Font("Cambria",Font.BOLD,20));
		p6.setPreferredSize(new Dimension(180, 50));
		p6.setHorizontalAlignment(0);
		tabbedPane.setTabComponentAt(5,p6);

//		JLabel p7 = new JLabel("Charts");
//		p7.setFont(new Font("Cambria",Font.BOLD,20));
//		p7.setPreferredSize(new Dimension(180, 50));
//		p7.setHorizontalAlignment(0);
//		tabbedPane.setTabComponentAt(6,p7);
//		
		add(tabbedPane);
		tabbedPane.setBounds(0,0,1360,1000);
		
		
	}
}