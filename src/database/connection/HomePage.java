package database.connection;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class HomePage extends JPanel implements ActionListener
{
	JButton btn_login;
	JPasswordField pf_pass;
	JTextField tf_user;
	JLabel jl_user, jl_pass, jl_title, jl_quote1, jl_quote2, jl_quote3,jl_quote4;
	String user = "admin";
	String pass = "admin@123";
	JLabel lbl_heading;
	
	JTabbedPane tp;
	
	HomePage(JTabbedPane tabbedPane)
	{
		setLayout(null);
		setSize(1600, 830);
		setVisible(true);
		
		this.tp = tabbedPane;
		
	//	ImageIcon background = new ImageIcon(getClass().getResource("light.jpeg"));
		// JLabel fr = new JLabel( background );
		JLabel fr = new JLabel();
		fr.setBounds(0, 0, 1400, 800);
		
	//	ImageIcon logo1 = new ImageIcon(getClass().getResource("logo_violet1.jpeg"));
		JLabel log1 = new JLabel();
		log1.setBounds(230,-5,100,130);
		fr.add(log1);
		
	//	ImageIcon logo2 = new ImageIcon(getClass().getResource("logo_violet2.jpeg"));
		JLabel log2 = new JLabel();
		log2.setBounds(300,-10,700,130);
		fr.add(log2);
		
//		ImageIcon bg = new ImageIcon(getClass().getResource("light.jpeg"));
//		JLabel fr_bg = new JLabel(bg);
//		fr_bg.setBounds(0, 350, 1450, 400);
//		fr.add(fr_bg);
//		
//		ImageIcon image = new ImageIcon(getClass().getResource("home2.jpeg"));
//		JLabel fr1 = new JLabel(image);
//		fr1.setBounds(410, 350, 600, 550);
//		fr.add(fr1);
//		
		jl_quote1 = new JLabel("'Sometimes it takes a ");
		jl_quote1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		jl_quote1.setBounds(1000, 400, 300, 35);
		fr.add(jl_quote1);
		
		jl_quote2 = new JLabel(" Whole tankful of Fuel ");
		jl_quote2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		jl_quote2.setBounds(1000, 470, 300, 35);
		fr.add(jl_quote2);
		
		jl_quote3 = new JLabel(" Before you can ");
		jl_quote3.setFont(new Font("Times New Roman", Font.BOLD, 25));
		jl_quote3.setBounds(1000, 540, 300, 35);
		fr.add(jl_quote3);
		
		jl_quote4 = new JLabel(" Think Straight'");
		jl_quote4.setFont(new Font("Times New Roman", Font.BOLD, 25));
		jl_quote4.setBounds(1000, 610, 300, 35);
		fr.add(jl_quote4);

		jl_user = new JLabel("Username");
		jl_user.setFont(new Font("Cambria", Font.BOLD, 25));
		jl_user.setBounds(100, 400, 150, 30);
		fr.add(jl_user);

		jl_pass = new JLabel("Password");
		jl_pass.setFont(new Font("Cambria", Font.BOLD, 25));
		jl_pass.setBounds(100, 450, 150, 30);
		fr.add(jl_pass);

		tf_user = new JTextField();
		tf_user.setBounds(320, 400, 150, 30);
		fr.add(tf_user);
		
		pf_pass = new JPasswordField();
		pf_pass.setEchoChar('*');
		pf_pass.setBounds(320, 450, 150, 30);
		fr.add(pf_pass);

		btn_login = new JButton("Login");
		btn_login.setBounds(100, 550, 100, 30);
		fr.add(btn_login);
		
		btn_login.addActionListener(this);
		
		
//		lbl_heading=new JLabel("PETRO SOFT STATION");
//		lbl_heading.setBounds(550, 50, 700, 30);
//		lbl_heading.setFont(new Font("Times_Roman", Font.BOLD, 35));
//		add(lbl_heading);
		
		
		add(fr);
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		Object obj_source = ae.getSource();

		if (obj_source == btn_login) 
		{
			try 
			{
				String Username = tf_user.getText();
				String Password = pf_pass.getText();
				if(user.equals(Username) && pass.equals(Password))
				{
					JOptionPane.showMessageDialog(null, "Logged-in Successfully!!");
					tp.setEnabledAt(1,true);
					tp.setEnabledAt(2,true);
					tp.setEnabledAt(3,true);
					tp.setEnabledAt(4,true);
					tp.setEnabledAt(5,true);
					tp.setEnabledAt(6,true);					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "OOPs!! Wrong Username or Password");
				}
			}
			 
			 catch (Exception e) 
			{ 
				 JOptionPane.showMessageDialog(this, e.toString()); 
			}		 
		}
	}	
}