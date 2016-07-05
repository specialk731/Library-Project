package primary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.sql.*;

public class Check_Borrower extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Card_No;
	private JTextField textField_SSN;
	private JTable table;
	private JTable table_1;


	/**
	 * Create the frame.
	 */
	public Check_Borrower(JFrame frame1, Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 646);
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
		Main_Menu.setBounds(1014, 559, 115, 38);
		contentPane.add(Main_Menu);
		
		JLabel lblCheckBorrower = new JLabel("Check Borrower");
		lblCheckBorrower.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckBorrower.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCheckBorrower.setBounds(10, 11, 125, 38);
		contentPane.add(lblCheckBorrower);
		
		JLabel lblSsn = new JLabel("SSN");
		lblSsn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSsn.setBounds(352, 24, 32, 14);
		contentPane.add(lblSsn);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardNumber.setBounds(145, 24, 85, 14);
		contentPane.add(lblCardNumber);
		
		textField_Card_No = new JTextField();
		textField_Card_No.setBounds(240, 24, 80, 14);
		contentPane.add(textField_Card_No);
		textField_Card_No.setColumns(10);
		
		textField_SSN = new JTextField();
		textField_SSN.setColumns(10);
		textField_SSN.setBounds(389, 24, 80, 14);
		contentPane.add(textField_SSN);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setBounds(325, 24, 32, 14);
		contentPane.add(lblOr);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			if(textField_SSN.getText().length() == 11 || textField_SSN.getText().length() == 0)
			{
				try{
					Statement stmt = conn.createStatement();
					ResultSet rs3;
					
					rs3 = stmt.executeQuery("SELECT BOOK_LOANS.Loan_Id, BOOK_LOANS.Book_Id, BOOK_LOANS.Date_Out, BOOK_LOANS.Date_Due, BOOK_LOANS.Date_In FROM BOOK_LOANS JOIN BORROWER ON BOOK_LOANS.Card_No=BORROWER.Card_No WHERE BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() +"' OR BORROWER.SSN LIKE '" + textField_SSN.getText() + "';");
					
					table.setModel(DbUtils.resultSetToTableModel(rs3));
				}
				catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null, e3);
				}
			}
			else
				JOptionPane.showMessageDialog(null, "SSN is incorrect");
				
				
				
			}
		});
		btnSearch.setBounds(490, 19, 98, 25);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(573, 85, 556, 448);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 85, 556, 448);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
	}
}
