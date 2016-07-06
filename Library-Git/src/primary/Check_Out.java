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
	private JTextField textField_Phone;
	private JTextField textField_State;
	private JTextField textField_City;
	private JTextField textField_Address;
	private JTextField textField_Email;
	private JTextField textField_Lname;
	private JTextField textField_Fname;
	private JTextField textField_SSN;
	private JTable table;
	JButton btnSearch = new JButton("Search");

	
	private String SSN;
	private String FName;
	private String LName;
	private String Email;
	private String Address;
	private String City;
	private String State;
	private String Phone;

	/**
	 * Create the frame.
	 */
	public Check_Out(JFrame frame1, Connection conn) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 861, 800);
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
		Main_Menu.setBounds(719, 713, 115, 38);
		contentPane.add(Main_Menu);
		
		textField_Phone = new JTextField();
		textField_Phone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_Phone.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Phone.setColumns(10);
		textField_Phone.setBounds(548, 130, 138, 20);
		contentPane.add(textField_Phone);
		
		JLabel label = new JLabel("Phone:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(469, 130, 69, 20);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("State:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(469, 105, 69, 20);
		contentPane.add(label_1);
		
		textField_State = new JTextField();
		textField_State.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_State.setHorizontalAlignment(SwingConstants.CENTER);
		textField_State.setColumns(10);
		textField_State.setBounds(548, 105, 138, 20);
		contentPane.add(textField_State);
		
		textField_City = new JTextField();
		textField_City.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_City.setHorizontalAlignment(SwingConstants.CENTER);
		textField_City.setColumns(10);
		textField_City.setBounds(548, 80, 138, 20);
		contentPane.add(textField_City);
		
		JLabel label_2 = new JLabel("City:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(469, 80, 69, 20);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Address:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(469, 55, 69, 20);
		contentPane.add(label_3);
		
		textField_Address = new JTextField();
		textField_Address.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_Address.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Address.setColumns(10);
		textField_Address.setBounds(548, 55, 138, 20);
		contentPane.add(textField_Address);
		
		textField_Email = new JTextField();
		textField_Email.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_Email.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Email.setColumns(10);
		textField_Email.setBounds(116, 130, 138, 20);
		contentPane.add(textField_Email);
		
		JLabel label_4 = new JLabel("Email:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(37, 130, 69, 20);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Last:");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(37, 105, 69, 20);
		contentPane.add(label_5);
		
		textField_Lname = new JTextField();
		textField_Lname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_Lname.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Lname.setColumns(10);
		textField_Lname.setBounds(116, 105, 138, 20);
		contentPane.add(textField_Lname);
		
		textField_Fname = new JTextField();
		textField_Fname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_Fname.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Fname.setColumns(10);
		textField_Fname.setBounds(116, 80, 138, 20);
		contentPane.add(textField_Fname);
		
		JLabel label_6 = new JLabel("First:");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(37, 80, 69, 20);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("SSN:");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(37, 55, 69, 20);
		contentPane.add(label_7);
		
		textField_SSN = new JTextField();
		textField_SSN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}
		});
		textField_SSN.setHorizontalAlignment(SwingConstants.CENTER);
		textField_SSN.setColumns(10);
		textField_SSN.setBounds(116, 55, 138, 20);
		contentPane.add(textField_SSN);
		
		JLabel lblSearchBorrower = new JLabel("Search Borrower");
		lblSearchBorrower.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchBorrower.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSearchBorrower.setBounds(10, 11, 244, 38);
		contentPane.add(lblSearchBorrower);
		
		JLabel label_9 = new JLabel("Example");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(264, 24, 138, 14);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("555-55-5555");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBounds(264, 55, 138, 20);
		contentPane.add(label_10);
		
		JLabel label_11 = new JLabel("John");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_11.setBounds(264, 80, 138, 20);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("Smith");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_12.setBounds(264, 105, 138, 20);
		contentPane.add(label_12);
		
		JLabel label_13 = new JLabel("JohnSmith@gmail.com");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_13.setBounds(264, 130, 138, 20);
		contentPane.add(label_13);
		
		JLabel label_14 = new JLabel("123 Apple Road");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_14.setBounds(696, 55, 138, 20);
		contentPane.add(label_14);
		
		JLabel label_15 = new JLabel("Apple City");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_15.setBounds(696, 80, 138, 20);
		contentPane.add(label_15);
		
		JLabel label_16 = new JLabel("TX");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_16.setBounds(696, 105, 138, 20);
		contentPane.add(label_16);
		
		JLabel label_17 = new JLabel("(555) 555-5555");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_17.setBounds(696, 130, 138, 20);
		contentPane.add(label_17);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				SSN = textField_SSN.getText();
				FName = textField_Fname.getText();
				LName = textField_Lname.getText();
				Email = textField_Email.getText();
				Address = textField_Address.getText();
				City = textField_City.getText();
				State = textField_State.getText();
				Phone = textField_Phone.getText();
				
				if(SSN.length() > 0)
					SSN = "'%" + SSN + "%'";
				else
					SSN = "''";
				if(FName.length() > 0)
					FName = "'%" + FName + "%'";
				else
					FName = "''";
				if(LName.length() > 0)
					LName = "'%" + LName + "%'";
				else
					LName = "''";
				if(Email.length() > 0)
					Email = "'%" + Email + "%'";
				else
					Email = "''";
				if(Address.length() > 0)
					Address = "'%" + Address + "%'";
				else
					Address = "''";
				if(City.length() > 0)
					City = "'%" + City + "%'";
				else
					City = "''";
				if(State.length() > 0)
					State = "'%" + State + "%'";
				else
					State = "''";
				if(Phone.length() > 0)
					Phone = "'%" + Phone + "%'";
				else
					Phone = "''";
				
				try{
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("SELECT * FROM BORROWER WHERE SSN LIKE " + SSN + " OR FName LIKE " + FName + " OR LName LIKE " + LName + " OR Email LIKE " + Email + " OR Address LIKE " + Address + " OR City LIKE " + City + "OR State LIKE " + State + "OR Phone LIKE " + Phone + ";");
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

			}
			
			catch(Exception e9)
			{
				JOptionPane.showMessageDialog(null, e9);
			}
		}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(252, 161, 138, 38);
		contentPane.add(btnSearch);
		
		JLabel label_8 = new JLabel("Example");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(696, 24, 138, 14);
		contentPane.add(label_8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 218, 824, 482);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField_Phone.setText("");
				textField_State.setText("");
				textField_City.setText("");
				textField_Address.setText("");
				textField_Email.setText("");
				textField_Lname.setText("");
				textField_Fname.setText("");
				textField_SSN.setText("");
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnClear.setBounds(400, 161, 138, 38);
		contentPane.add(btnClear);
	}

}
