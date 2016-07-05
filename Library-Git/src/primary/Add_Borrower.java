package primary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.sql.*;

public class Add_Borrower extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Add_Borrower(JFrame frame1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddborrower = new JLabel("Add_Borrower");
		lblAddborrower.setBounds(31, 11, 101, 38);
		contentPane.add(lblAddborrower);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(true);
				dispose();
			}
		});
		btnMainMenu.setBounds(309, 213, 115, 38);
		contentPane.add(btnMainMenu);
	}

}
