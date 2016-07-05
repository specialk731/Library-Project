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
import java.awt.Font;
import javax.swing.JTextField;

public class Check_Out extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public Check_Out(JFrame frame1, Connection conn) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
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
		Main_Menu.setBounds(659, 513, 115, 38);
		contentPane.add(Main_Menu);
		
		JLabel lblCheckout = new JLabel("Check In");
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCheckout.setBounds(10, 11, 79, 27);
		contentPane.add(lblCheckout);
		
		JLabel lblNewLabel = new JLabel("Book Id:");
		lblNewLabel.setBounds(99, 18, 85, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(152, 18, 79, 14);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCheckIn = new JButton("Check In");
		btnCheckIn.setBounds(251, 11, 105, 27);
		contentPane.add(btnCheckIn);
	}

}
