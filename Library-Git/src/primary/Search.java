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
import javax.swing.SwingConstants;

import java.sql.*;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Search extends JFrame {

	private JPanel contentPane;
	JCheckBox chckbxAll = new JCheckBox("General");
	
	private int Branch;
	private JTextField textField;
	private JTable table;
	private JTextField textField_Book_Id;
	private JTextField textField_Card_No;
	JButton btnSearch = new JButton("Search");
	JButton btnCheckOut = new JButton("Check Out");

	/**
	 * Create the frame.
	 */
	public Search(JFrame frame1, Connection conn, int branch) {
		
		Branch = branch;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
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
		Main_Menu.setBounds(959, 563, 115, 38);
		contentPane.add(Main_Menu);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSearch.setBounds(10, 11, 58, 24);
		contentPane.add(lblSearch);
		
		JLabel lblNewLabel = new JLabel("Branch: " + Branch);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(972, 17, 102, 14);
		contentPane.add(lblNewLabel);
		
		JCheckBox chckbxIsbn = new JCheckBox("ISBN");
		chckbxIsbn.setSelected(true);
		chckbxIsbn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAll.isSelected())
					chckbxAll.setSelected(false);
			}
		});
		chckbxIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxIsbn.setBounds(150, 13, 74, 23);
		contentPane.add(chckbxIsbn);
		
		JCheckBox chckbxTitle = new JCheckBox("Title");
		chckbxTitle.setSelected(true);
		chckbxTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAll.isSelected())
					chckbxAll.setSelected(false);
			}
		});
		chckbxTitle.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxTitle.setBounds(226, 13, 74, 23);
		contentPane.add(chckbxTitle);
		
		JCheckBox chckbxAuthors = new JCheckBox("Authors");
		chckbxAuthors.setSelected(true);
		chckbxAuthors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxAll.isSelected())
					chckbxAll.setSelected(false);
			}
		});
		chckbxAuthors.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxAuthors.setBounds(302, 13, 74, 23);
		contentPane.add(chckbxAuthors);
		chckbxAll.setSelected(true);
		
		chckbxAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxAll.isSelected())
				{
				chckbxAuthors.setSelected(true);
				chckbxIsbn.setSelected(true);
				chckbxTitle.setSelected(true);
				}
				else
				{				
					chckbxAuthors.setSelected(false);
					chckbxIsbn.setSelected(false);
					chckbxTitle.setSelected(false);
				}
			}
		});
		chckbxAll.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxAll.setBounds(74, 12, 74, 23);
		contentPane.add(chckbxAll);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField.setBounds(402, 14, 491, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try{
				
				String str = textField.getText();
				
				str = str.replaceAll(" ", "|");
				
				Statement stmt = conn.createStatement();
				
				ResultSet rs6 = null;
				
				if(chckbxIsbn.isSelected() && !chckbxAuthors.isSelected() && !chckbxTitle.isSelected())	// ISBN SEARCH
					{
						rs6 = stmt.executeQuery("SELECT book_Id, isbn, title, author_names FROM GENERAL_SEARCH4 WHERE ISBN REGEXP '" + str + "' AND Branch_Id = " + Branch +" AND Checked_Out = FALSE ORDER BY ISBN;");
						
						table.setModel(DbUtils.resultSetToTableModel(rs6));	
					}
				else;
				
				if(!chckbxIsbn.isSelected() && chckbxAuthors.isSelected() && !chckbxTitle.isSelected())	// Authors SEARCH
					{
						rs6 = stmt.executeQuery("SELECT book_Id, isbn, title, author_names FROM GENERAL_SEARCH4 WHERE author_names REGEXP '" + str + "' AND Branch_Id = " + Branch +" AND Checked_Out = FALSE ORDER BY author_names;");

						table.setModel(DbUtils.resultSetToTableModel(rs6));
					}
				else;

				
				if(!chckbxIsbn.isSelected() && !chckbxAuthors.isSelected() && chckbxTitle.isSelected())	// Title SEARCH
					{
						rs6 = stmt.executeQuery("SELECT book_Id, isbn, title, author_names FROM GENERAL_SEARCH4 WHERE Title REGEXP '" + str + "' AND Branch_Id = " + Branch +" AND Checked_Out = FALSE ORDER BY Title;");

						table.setModel(DbUtils.resultSetToTableModel(rs6));
					}
				else;

				
				if(chckbxIsbn.isSelected() && chckbxAuthors.isSelected() && !chckbxTitle.isSelected())	// ISBN AND Author SEARCH
					{
						rs6 = stmt.executeQuery("SELECT book_Id, isbn, title, author_names FROM GENERAL_SEARCH4 WHERE (ISBN REGEXP '" + str + "' OR author_names REGEXP '" + str + "') AND Branch_Id = " + Branch +" AND Checked_Out = FALSE ORDER BY ISBN;");

						table.setModel(DbUtils.resultSetToTableModel(rs6));
						
					}
				else;

				
				if(chckbxIsbn.isSelected() && !chckbxAuthors.isSelected() && chckbxTitle.isSelected())	// ISBN AND Title SEARCH
					{
						rs6 = stmt.executeQuery("SELECT book_Id, isbn, title, author_names FROM GENERAL_SEARCH4 WHERE (ISBN REGEXP '" + str + "' OR Title REGEXP '" + str + "') AND Branch_Id = " + Branch +" AND Checked_Out = FALSE ORDER BY Title;");
	
						table.setModel(DbUtils.resultSetToTableModel(rs6));
					}
				else;

				
				if(!chckbxIsbn.isSelected() && chckbxAuthors.isSelected() && chckbxTitle.isSelected())	// Authors AND Title SEARCH
					{
						rs6 = stmt.executeQuery("SELECT book_Id, isbn, title, author_names FROM GENERAL_SEARCH4 WHERE (author_names REGEXP '" + str + "' OR Title REGEXP '" + str + "') AND Branch_Id = " + Branch +" AND Checked_Out = FALSE ORDER BY author_names;");
	
						table.setModel(DbUtils.resultSetToTableModel(rs6));
					}
				else;

				if(chckbxAll.isSelected() || (chckbxIsbn.isSelected() && chckbxAuthors.isSelected() && chckbxTitle.isSelected()))	//General SEARCH
					{
						rs6 = stmt.executeQuery("SELECT book_Id, isbn, title, author_names FROM GENERAL_SEARCH4 WHERE (author_names REGEXP '" + str + "' OR ISBN REGEXP '" + str + "' OR Title REGEXP '" + str + "') AND Branch_Id = " + Branch +" AND Checked_Out = FALSE ORDER BY book_Id;");
					//rs6 = stmt.executeQuery("select book_Id, isbn, title, author_names from GENERAL_SEARCH4 where (author_names like '%Thomas%' OR ISBN like '%green%') AND Branch_ID = " + Branch + " ORDER BY book_id;");
						table.setModel(DbUtils.resultSetToTableModel(rs6));
					}
				else;
				
				if(!chckbxIsbn.isSelected() && !chckbxAuthors.isSelected() && !chckbxTitle.isSelected())	// NONE SEARCH
					JOptionPane.showMessageDialog(null, "Must have at least one Search parameter checked");
				else;
				
			}
			catch(Exception e5)
			{
				JOptionPane.showMessageDialog(null, e5);
			}


		}
	});
		btnSearch.setBounds(903, 13, 83, 24);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 1064, 414);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckOut.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCheckOut.setBounds(10, 474, 102, 24);
		contentPane.add(lblCheckOut);
		
		JLabel lblNewLabel_1 = new JLabel("Book Id");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 509, 102, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardNumber.setBounds(10, 534, 102, 14);
		contentPane.add(lblCardNumber);
		
		textField_Book_Id = new JTextField();
		textField_Book_Id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				btnCheckOut.doClick();
			}
		});
		textField_Book_Id.setBounds(106, 509, 118, 14);
		contentPane.add(textField_Book_Id);
		textField_Book_Id.setColumns(10);
		
		textField_Card_No = new JTextField();
		textField_Card_No.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnCheckOut.doClick();
			}
		});
		textField_Card_No.setColumns(10);
		textField_Card_No.setBounds(106, 534, 118, 14);
		contentPane.add(textField_Card_No);
		
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				String CardNum = "";
				String BookId = "";
				Statement stmt = conn.createStatement();
				ResultSet rs = null;
				
				CardNum = textField_Card_No.getText();
				BookId = textField_Book_Id.getText();
				
				rs = stmt.executeQuery("SELECT * FROM BORROWER WHERE Card_No = " + CardNum + ";");
				
				if(!rs.next())
					JOptionPane.showMessageDialog(null, "Card Number does not exist.");
				else
				{		
					rs = stmt.executeQuery("SELECT Checked_Out FROM BOOK_COPIES WHERE Book_Id = " + BookId + ";");
					
					if(!rs.next() || rs.getBoolean("Checked_Out") == true)
					{
						JOptionPane.showMessageDialog(null, "Book ID is not valid.");
					}
					else
					{
						rs = stmt.executeQuery("SELECT COUNT(*) FROM BOOK_LOANS WHERE Card_No = " + CardNum + " AND Date_In IS NULL;");
						
						rs.next();
						
						if(rs.getInt("COUNT(*)") > 2)
						{
							JOptionPane.showMessageDialog(null, "Card Number has to many books checked out.");
						}
						else
						{
							rs = stmt.executeQuery("SELECT COUNT(*) FROM BOOK_LOANS WHERE Date_Due < CURDATE() AND Date_In IS NULL AND Card_No = " + CardNum + ";");
							
							rs.next();
							
							if(rs.getInt("COUNT(*)") == 0)
							{
								rs = stmt.executeQuery("SELECT COUNT(*) FROM FINES JOIN BOOK_LOANS ON FINES.Loan_Id=BOOK_LOANS.Loan_Id AND Paid = FALSE AND Card_No = " + CardNum + ";");
								
								rs.next();
								
								if(rs.getInt("COUNT(*)") == 0)
								{
									stmt.executeUpdate("INSERT INTO BOOK_LOANS (Book_Id, Card_No, Date_Out, Date_Due) VALUES (" + BookId + ", " + CardNum + ", CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY));");
									stmt.executeUpdate("UPDATE BOOK_COPIES SET Checked_Out = TRUE WHERE Book_Id = " + BookId + ";");
									
									rs = stmt.executeQuery("SELECT Loan_Id FROM BOOK_LOANS WHERE Book_Id = " + BookId + " AND Card_No = " + CardNum + " AND Date_In IS NULL;");
									
									rs.next();
									
									JOptionPane.showMessageDialog(null, "The Book Id: " + BookId + " is checked out to Card Number:" + CardNum + ". The Loan ID is " + rs.getString("Loan_Id"));
									
									btnSearch.doClick();
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Card Number has an unpaid Fine.");
								}
							}
							else
								JOptionPane.showMessageDialog(null, "Card Number has an overdue book.");
						}
					}
				}
				
					
				
				}
				catch(Exception e7){
					JOptionPane.showMessageDialog(null, e7);
				}
				
				
				
			}
		});
		btnCheckOut.setBounds(261, 509, 115, 39);
		contentPane.add(btnCheckOut);
	}
}
