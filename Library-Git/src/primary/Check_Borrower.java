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
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Check_Borrower extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Card_No;
	private JTextField textField_SSN;
	private JTable table;
	private JTable table_1;
	JComboBox comboBox = new JComboBox();
	private JTextField textField;
	private JTable table_2;
	private JTable table_3;
	JButton btnSearch = new JButton("Search");
	JButton btnPay = new JButton("Pay");
	private JTextField textField_BookId;
	JButton btnCheckIn = new JButton("Check In");

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
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSsn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSsn.setBounds(464, 11, 32, 27);
		contentPane.add(lblSsn);
		
		JLabel lblCardNumber = new JLabel("Card Number:");
		lblCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardNumber.setBounds(145, 11, 85, 27);
		contentPane.add(lblCardNumber);
		
		textField_Card_No = new JTextField();
		textField_Card_No.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_Card_No.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Card_No.setBounds(240, 11, 149, 27);
		contentPane.add(textField_Card_No);
		textField_Card_No.setColumns(10);
		
		textField_SSN = new JTextField();
		textField_SSN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_SSN.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_SSN.setColumns(10);
		textField_SSN.setBounds(501, 11, 149, 27);
		contentPane.add(textField_SSN);
		
		JLabel lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOr.setHorizontalAlignment(SwingConstants.CENTER);
		lblOr.setBounds(422, 11, 32, 27);
		contentPane.add(lblOr);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String str = (String) comboBox.getSelectedItem();
				
				
			if(textField_SSN.getText().length() == 11 || textField_SSN.getText().length() == 0)
			{
				try{
					Statement stmt = conn.createStatement();
					ResultSet rs3;
					String CardNo = textField_Card_No.getText();
					
					if(CardNo.length() == 0)
					{
						rs3 = stmt.executeQuery("SELECT Card_No FROM BORROWER WHERE SSN LIKE '" + textField_SSN.getText() + "';");
						if(rs3.next())
							textField_Card_No.setText(rs3.getString("Card_No"));
						CardNo = textField_Card_No.getText();
					}
					
					rs3 = stmt.executeQuery("SELECT Card_No FROM BORROWER WHERE Card_No Like '" + CardNo + "';");
					
					if(rs3.next())
					{
					
					if(textField_Card_No.getText().length() > 0)
					{
					rs3 = stmt.executeQuery("SELECT BOOK_LOANS.Loan_Id, BOOK_LOANS.Book_Id, BOOK_LOANS.Date_Out, BOOK_LOANS.Date_Due, BOOK_LOANS.Date_In FROM BOOK_LOANS JOIN BORROWER ON BOOK_LOANS.Card_No=BORROWER.Card_No WHERE BOOK_LOANS.Date_IN IS NOT NULL AND BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() +"';");
					
					table.setModel(DbUtils.resultSetToTableModel(rs3));
					
					rs3 = stmt.executeQuery("SELECT BOOK_LOANS.Loan_Id, BOOK_LOANS.Book_Id, BOOK_LOANS.Date_Out, BOOK_LOANS.Date_Due, BOOK_LOANS.Date_In FROM BOOK_LOANS JOIN BORROWER ON BOOK_LOANS.Card_No=BORROWER.Card_No WHERE (BOOK_LOANS.Date_Due < CURDATE() AND BOOK_LOANS.Date_In IS NULL) AND BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() +"';");
					
					table_2.setModel(DbUtils.resultSetToTableModel(rs3));
					
					rs3 = stmt.executeQuery("SELECT BOOK_LOANS.Loan_Id, BOOK_LOANS.Book_Id, BOOK_LOANS.Date_Out, BOOK_LOANS.Date_Due, BOOK_LOANS.Date_In FROM BOOK_LOANS JOIN BORROWER ON BOOK_LOANS.Card_No=BORROWER.Card_No WHERE (BOOK_LOANS.Date_Due >= CURDATE() AND BOOK_LOANS.Date_IN IS NULL) AND BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() +"';");
					
					table_3.setModel(DbUtils.resultSetToTableModel(rs3));
					
					if(str.equals("Unpaid"))
						rs3 = stmt.executeQuery("SELECT FINES.Loan_Id, FINES.Fine_Amt, FINES.Paid FROM FINES JOIN BOOK_LOANS ON FINES.Loan_Id=BOOK_LOANS.Loan_Id WHERE BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() +"' AND FINES.Paid = FALSE;");
					else if (str.equals("Paid"))
						rs3 = stmt.executeQuery("SELECT FINES.Loan_Id, FINES.Fine_Amt, FINES.Paid FROM FINES JOIN BOOK_LOANS ON FINES.Loan_Id=BOOK_LOANS.Loan_Id WHERE BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() +"' AND FINES.Paid = TRUE;");
					else
						rs3 = stmt.executeQuery("SELECT FINES.Loan_Id, FINES.Fine_Amt, FINES.Paid FROM FINES JOIN BOOK_LOANS ON FINES.Loan_Id=BOOK_LOANS.Loan_Id WHERE BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() +"';");
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs3));
					}
					else
						JOptionPane.showMessageDialog(null, "No matches for SSN given.");
					}
					else
						JOptionPane.showMessageDialog(null, "No matches for Card_No given.");

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
		btnSearch.setBounds(660, 11, 125, 27);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 85, 792, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 85, 306, 409);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Both", "Paid", "Unpaid"}));
		comboBox.setBounds(240, 49, 80, 25);
		contentPane.add(comboBox);
		
		JLabel lblFines = new JLabel("Fines");
		lblFines.setHorizontalAlignment(SwingConstants.CENTER);
		lblFines.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFines.setBounds(91, 49, 125, 25);
		contentPane.add(lblFines);
		
		JLabel lblPayLoanId = new JLabel("Pay Loan Id:");
		lblPayLoanId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPayLoanId.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayLoanId.setBounds(10, 505, 85, 28);
		contentPane.add(lblPayLoanId);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnPay.doClick();
			}
		});
		textField.setColumns(10);
		textField.setBounds(105, 505, 111, 28);
		contentPane.add(textField);
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				int Id = 0;
				
				Id = Integer.parseInt(textField.getText());
				
				Statement stmt = conn.createStatement();
				
				ResultSet rs;
				
				rs = stmt.executeQuery("SELECT FINES.Paid FROM FINES JOIN BOOK_LOANS ON FINES.Loan_Id = BOOK_LOANS.Loan_Id WHERE FINES.Loan_Id = " + Id + " AND BOOK_LOANS.Card_No LIKE '" + textField_Card_No.getText() + "';");
				
				if(!rs.next())
					JOptionPane.showMessageDialog(null, "The selected Loan Id doesn't exist or does not belong to the displayed Card No");
				else if(rs.getBoolean("Paid"))
					JOptionPane.showMessageDialog(null, "The selected Loan Id has already been paid");
				else
					{
					stmt.execute("UPDATE FINES SET FINES.Paid=TRUE WHERE Loan_Id = " + Id + ";");
					JOptionPane.showMessageDialog(null, "Loan " + Id + " Paid");
					}
					
				
				}
				catch(Exception e4)
				{
					JOptionPane.showMessageDialog(null, e4);
				}
				
				btnSearch.doClick();
				
			}
		});
		btnPay.setBounds(231, 503, 85, 30);
		contentPane.add(btnPay);
		
		JLabel lblCurrentlyCheckedOut = new JLabel("Previously Checked Out");
		lblCurrentlyCheckedOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentlyCheckedOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurrentlyCheckedOut.setBounds(591, 55, 255, 25);
		contentPane.add(lblCurrentlyCheckedOut);
		
		JLabel lblPastDue = new JLabel("Past Due");
		lblPastDue.setHorizontalAlignment(SwingConstants.CENTER);
		lblPastDue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPastDue.setBounds(591, 277, 255, 25);
		contentPane.add(lblPastDue);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(325, 301, 792, 80);
		contentPane.add(scrollPane_2);
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(325, 414, 792, 80);
		contentPane.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		
		JLabel lblCurrentlyCheckedOut_1 = new JLabel("Currently Checked Out");
		lblCurrentlyCheckedOut_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentlyCheckedOut_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCurrentlyCheckedOut_1.setBounds(591, 386, 255, 25);
		contentPane.add(lblCurrentlyCheckedOut_1);
		
		textField_BookId = new JTextField();
		textField_BookId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_BookId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnCheckIn.doClick();
			}
		});
		textField_BookId.setBounds(549, 505, 214, 28);
		contentPane.add(textField_BookId);
		textField_BookId.setColumns(10);
		btnCheckIn.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					
				ResultSet rs = null;
					
				String BookId = textField_BookId.getText();
				
				if(BookId.length() != 0)
				{
				
				Statement stmt = conn.createStatement();
				
				rs = stmt.executeQuery("SELECT * FROM BOOK_LOANS WHERE Book_Id = " + BookId + " AND Date_In IS NULL");
				
				if(rs.next())
				{
					
					Date Due_Date, Date_In;
					
					long Diff = 0;
					
					double Fine = 0.00;
					
					Due_Date = new Date(Calendar.getInstance().getTimeInMillis());
					
					Date_In = new Date(Calendar.getInstance().getTimeInMillis());
					
					rs = stmt.executeQuery("SELECT CURDATE();");
					
					rs.next();
					
					Date_In.setTime(rs.getDate("CURDATE()").getTime());
					
					rs = stmt.executeQuery("SELECT * FROM BOOK_LOANS WHERE Book_Id = " + BookId + " AND Date_In IS NULL;");
					
					rs.next();
					
					Due_Date.setTime(rs.getDate("Date_Due").getTime());
					
					if(Date_In.after(Due_Date))
					{
						Diff = Date_In.getTime() - Due_Date.getTime();
						
						Fine = .25 * TimeUnit.DAYS.convert(Diff, TimeUnit.MILLISECONDS); 
						
						DecimalFormat decimalFormat = new DecimalFormat("#.00");
						
						JOptionPane.showMessageDialog(null, TimeUnit.DAYS.convert(Diff, TimeUnit.MILLISECONDS) + " Days Late. Fine: $" + decimalFormat.format(Fine));
						
						rs.first();
						
						stmt.executeUpdate("INSERT INTO FINES VALUES('" + rs.getString("Loan_Id") + "', '" + Fine + "', FALSE);");
											
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Book Returned on time. No Fine.");
						textField_BookId.setText("");
					}
					
					stmt.executeUpdate("UPDATE BOOK_LOANS SET Date_in = CURDATE() WHERE Book_Id = " + BookId + " AND Date_In IS NULL;");
					stmt.executeUpdate("UPDATE BOOK_COPIES SET Checked_Out = FALSE WHERE Book_Id = " + BookId + ";");
					btnSearch.doClick();
										
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Book Id Not Found.");
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please Enter a Book Id");

				}
			}
			catch(Exception e9)
			{
				JOptionPane.showMessageDialog(null, e9);
			}
		}
	});
		btnCheckIn.setBounds(783, 505, 100, 28);
		contentPane.add(btnCheckIn);
		
		JLabel lblCheckInBook = new JLabel("Check in Book Id:");
		lblCheckInBook.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCheckInBook.setBounds(414, 512, 125, 14);
		contentPane.add(lblCheckInBook);

	}
}
