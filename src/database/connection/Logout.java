package database.connection;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Logout extends JPanel implements ActionListener {

	JTabbedPane tp;
	JButton btn_logout;
	Logout(JTabbedPane tabbedPane){
		
		ImageIcon background = new ImageIcon(getClass().getResource("light.jpeg"));
		JLabel fr = new JLabel(background);
		fr.setBounds(-50, 0, 1500, 700);
		
		setLayout(null);
		setSize(1600, 830);
		setVisible(true);
		this.tp=tabbedPane;
		
		btn_logout = new JButton("Logout");
		btn_logout.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		btn_logout.setBounds(600, 70, 300, 50);
		//btn_logout.setFont(new Font("Cambria", Font.BOLD, 18));
		btn_logout.setOpaque(false);
		btn_logout.setContentAreaFilled(false);
		btn_logout.setBorderPainted(false);
		//btn_logout.setBackground(new Color(165, 137, 193));
		fr.add(btn_logout);
		add(fr);
		btn_logout.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj_source = e.getSource();
		if (obj_source == btn_logout) {
			//new Home_Page(tp);
			System.exit(0);
//			tp.setEnabledAt(1,false);
//			tp.setEnabledAt(2,false);
//			tp.setEnabledAt(3,false);
//			tp.setEnabledAt(4,false);
//			tp.setEnabledAt(5,false);
//			tp.setEnabledAt(6,false);
//			tp.disable();
		}
	}
	
}