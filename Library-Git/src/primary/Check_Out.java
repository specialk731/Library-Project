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
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Check_Out extends JFrame {

	private JPanel contentPane;
	private JTextField textField_BookId;
	JButton btnCheckIn = new JButton("Check In");

	/**
	 * Create the frame.
	 */
	public Check_Out(JFrame frame1, Connection conn) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 162);
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
		Main_Menu.setBounds(241, 66, 115, 38);
		contentPane.add(Main_Menu);
		
		JLabel lblCheckout = new JLabel("Check In");
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCheckout.setBounds(10, 11, 79, 27);
		contentPane.add(lblCheckout);
		
		JLabel lblNewLabel = new JLabel("Book Id:");
		lblNewLabel.setBounds(99, 18, 85, 14);
		contentPane.add(lblNewLabel);
		
		textField_BookId = new JTextField();
		textField_BookId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnCheckIn.doClick();
			}
		});
		textField_BookId.setBounds(152, 18, 79, 14);
		contentPane.add(textField_BookId);
		textField_BookId.setColumns(10);
		
	
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
		btnCheckIn.setBounds(251, 11, 105, 27);
		contentPane.add(btnCheckIn);
	}

}
