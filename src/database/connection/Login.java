package database.connection;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener 
{

	JButton jb_login;
	JPasswordField pf_pass;
	JTextField tf_user;
	JLabel jl_user, jl_pass, jl_title;
	String user = "admin";
	String pass = "admin@4120";

	public Login() 
	{
		setLayout(null);

		ImageIcon background = new ImageIcon(getClass().getResource("station1.jpg"));
		JLabel fr = new JLabel(background);
		fr.setBounds(0,-110,600,600);

		jl_title = new JLabel("PETRO SOFT STATION");
		jl_title.setBounds(100, 120, 450, 50);
		jl_title.setFont(new Font("TimesRoman", Font.BOLD, 35));
		//jl_title.setForeground(Color.gray);
		fr.add(jl_title);

		jl_user = new JLabel("Username");
		jl_user.setBounds(100, 230, 130, 30);
		jl_user.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 25));
		jl_user.setForeground(Color.white);
		fr.add(jl_user);

		jl_pass = new JLabel("Password");
		jl_pass.setBounds(100, 300, 130, 30);
		jl_pass.setFont(new Font("TimesRoman", Font.BOLD | Font.ITALIC, 25));
		jl_pass.setForeground(Color.white);
		fr.add(jl_pass);

		tf_user = new JTextField();
		tf_user.setBounds(280, 230, 130, 30);
		fr.add(tf_user);
		
		pf_pass = new JPasswordField();		
		pf_pass.setBounds(280, 300, 130, 30);
		fr.add(pf_pass);

		jb_login = new JButton("Login");
		jb_login.setBounds(100, 400, 100, 30);
		fr.add(jb_login);
		jb_login.addActionListener(this);

		add(fr);
		setVisible(true);
		setBounds(150,50,600, 420);

	}

	public void actionPerformed(ActionEvent ae) 
	{
		Object obj_source = ae.getSource();

		if (obj_source == jb_login) 
		{

			try 
			{
				String Username = tf_user.getText();
				String Password = pf_pass.getText();
				if(user.equals(Username) && pass.equals(Password))
				{
					JOptionPane.showMessageDialog(null, "Logged-in Successfully!!");
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

	public static void main(String[] args) 
	{
		new Login();
	}

}

