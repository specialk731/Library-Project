package primary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.sql.*;

public class Check_Out extends JFrame {

	private JPanel contentPane;
	
	private int Branch;

	/**
	 * Create the frame.
	 */
	public Check_Out(JFrame frame1, Connection conn, int branch) {
		
		Branch = branch;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Main_Menu = new JButton("Main Menu");
		Main_Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame1.setVisible(true);
				dispose();
			}
		});
		Main_Menu.setBounds(309, 213, 115, 38);
		contentPane.add(Main_Menu);
		
		JLabel lblCheckout = new JLabel("Check_Out");
		lblCheckout.setBounds(10, 11, 79, 27);
		contentPane.add(lblCheckout);
		
		JLabel label = new JLabel("Branch: " + Branch);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 49, 97, 14);
		contentPane.add(label);
	}

}
