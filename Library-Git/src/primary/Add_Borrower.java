package primary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.sql.*;

public class Add_Borrower extends JFrame {

	private JPanel contentPane;
	private JTextField SSN_Field;
	private JTextField First_Field;
	private JTextField Email_Field;
	private JTextField Last_Field;
	private JTextField Phone_Field;
	private JTextField State_Field;
	private JTextField City_Field;
	private JTextField Address_Field;
	
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
	public Add_Borrower(JFrame frame1, Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddborrower = new JLabel("Add Borrower");
		lblAddborrower.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddborrower.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddborrower.setBounds(92, 11, 101, 38);
		contentPane.add(lblAddborrower);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(true);
				dispose();
			}
		});
		btnMainMenu.setBounds(248, 268, 138, 38);
		contentPane.add(btnMainMenu);
		
		JLabel lblNewLabel = new JLabel("SSN:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(31, 55, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(31, 80, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblLast = new JLabel("Last:");
		lblLast.setHorizontalAlignment(SwingConstants.CENTER);
		lblLast.setBounds(31, 105, 46, 14);
		contentPane.add(lblLast);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(31, 130, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(31, 155, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setBounds(31, 180, 46, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setHorizontalAlignment(SwingConstants.CENTER);
		lblState.setBounds(31, 205, 46, 14);
		contentPane.add(lblState);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setBounds(31, 230, 46, 14);
		contentPane.add(lblPhone);
		
		SSN_Field = new JTextField();
		SSN_Field.setHorizontalAlignment(SwingConstants.CENTER);
		SSN_Field.setBounds(100, 55, 138, 14);
		contentPane.add(SSN_Field);
		SSN_Field.setColumns(10);
		
		First_Field = new JTextField();
		First_Field.setHorizontalAlignment(SwingConstants.CENTER);
		First_Field.setColumns(10);
		First_Field.setBounds(100, 80, 138, 14);
		contentPane.add(First_Field);
		
		Email_Field = new JTextField();
		Email_Field.setHorizontalAlignment(SwingConstants.CENTER);
		Email_Field.setColumns(10);
		Email_Field.setBounds(100, 130, 138, 14);
		contentPane.add(Email_Field);
		
		Last_Field = new JTextField();
		Last_Field.setHorizontalAlignment(SwingConstants.CENTER);
		Last_Field.setColumns(10);
		Last_Field.setBounds(100, 105, 138, 14);
		contentPane.add(Last_Field);
		
		Phone_Field = new JTextField();
		Phone_Field.setHorizontalAlignment(SwingConstants.CENTER);
		Phone_Field.setColumns(10);
		Phone_Field.setBounds(100, 230, 138, 14);
		contentPane.add(Phone_Field);
		
		State_Field = new JTextField();
		State_Field.setHorizontalAlignment(SwingConstants.CENTER);
		State_Field.setColumns(10);
		State_Field.setBounds(100, 205, 138, 14);
		contentPane.add(State_Field);
		
		City_Field = new JTextField();
		City_Field.setHorizontalAlignment(SwingConstants.CENTER);
		City_Field.setColumns(10);
		City_Field.setBounds(100, 180, 138, 14);
		contentPane.add(City_Field);
		
		Address_Field = new JTextField();
		Address_Field.setHorizontalAlignment(SwingConstants.CENTER);
		Address_Field.setColumns(10);
		Address_Field.setBounds(100, 155, 138, 14);
		contentPane.add(Address_Field);
		
		JButton Submit_Button = new JButton("Submit");
		Submit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SSN 	= "'" + SSN_Field.getText() + "'";
				FName 	= "'" + First_Field.getText() + "'";
				LName 	= "'" + Last_Field.getText() + "'";
				Email 	= "'" + Email_Field.getText() + "',";
				Address = "'" + Address_Field.getText() + "',";
				City 	= "'" + City_Field.getText() + "',";
				State 	= "'" + State_Field.getText() + "',";
				Phone 	= "'" + Phone_Field.getText() + "'";
				
				if(SSN.length() == 13)
				{
					if(FName.length() > 2 && LName.length() > 2)
					{
						if(Address.length() > 3 && City.length() > 3 && State.length() > 3)
						{
								try{				
									ResultSet rs2;
									
									Statement stmt = conn.createStatement();
									
									stmt.executeUpdate("INSERT INTO BORROWER (SSN, Fname, LName, Email, Address, City, State, Phone) VALUES (" + SSN + "," + FName + "," + LName+ ","  + Email + Address + City + State + Phone + ");");
									
									rs2 = stmt.executeQuery("SELECT Card_No FROM BORROWER WHERE SSN like " + SSN + ";");
									
									rs2.next();
									
									JOptionPane.showMessageDialog(null, FName + " " + LName + " has been added. Card Number: " + rs2.getString("Card_No"));
									
									SSN_Field.setText("");
									First_Field.setText("");
									Last_Field.setText("");
									Email_Field.setText("");
									Address_Field.setText("");
									City_Field.setText("");
									State_Field.setText("");
									Phone_Field.setText("");
									
									}
									catch(Exception e2)
									{
										JOptionPane.showMessageDialog(null, e2);
									}
						}
						else
							JOptionPane.showMessageDialog(null, "Must have a Complete Address");
					}
					else
						JOptionPane.showMessageDialog(null, "Must have a First and Last Name");
				}
				else
					JOptionPane.showMessageDialog(null, "SSN is not correct. Enter as xxx-xx-xxxx");
			}
		});
		Submit_Button.setFont(new Font("Tahoma", Font.BOLD, 11));
		Submit_Button.setBounds(100, 268, 138, 38);
		contentPane.add(Submit_Button);
		
		JLabel lblExample = new JLabel("Example");
		lblExample.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExample.setHorizontalAlignment(SwingConstants.CENTER);
		lblExample.setBounds(248, 24, 138, 14);
		contentPane.add(lblExample);
		
		JLabel lblXxxxxxxxx = new JLabel("555-55-5555");
		lblXxxxxxxxx.setHorizontalAlignment(SwingConstants.CENTER);
		lblXxxxxxxxx.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblXxxxxxxxx.setBounds(248, 55, 138, 14);
		contentPane.add(lblXxxxxxxxx);
		
		JLabel lblJohn = new JLabel("John");
		lblJohn.setHorizontalAlignment(SwingConstants.CENTER);
		lblJohn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJohn.setBounds(248, 80, 138, 14);
		contentPane.add(lblJohn);
		
		JLabel lblJohnsmithgmailcom = new JLabel("JohnSmith@gmail.com");
		lblJohnsmithgmailcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblJohnsmithgmailcom.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJohnsmithgmailcom.setBounds(248, 130, 138, 14);
		contentPane.add(lblJohnsmithgmailcom);
		
		JLabel lblSmith = new JLabel("Smith");
		lblSmith.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmith.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSmith.setBounds(248, 105, 138, 14);
		contentPane.add(lblSmith);
		
		JLabel label_4 = new JLabel("(555) 555-5555");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setBounds(248, 230, 138, 14);
		contentPane.add(label_4);
		
		JLabel lblTx = new JLabel("TX");
		lblTx.setHorizontalAlignment(SwingConstants.CENTER);
		lblTx.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTx.setBounds(248, 205, 138, 14);
		contentPane.add(lblTx);
		
		JLabel lblAppleCity = new JLabel("Apple City");
		lblAppleCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppleCity.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAppleCity.setBounds(248, 180, 138, 14);
		contentPane.add(lblAppleCity);
		
		JLabel lblAppleRoad = new JLabel("123 Apple Road");
		lblAppleRoad.setHorizontalAlignment(SwingConstants.CENTER);
		lblAppleRoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAppleRoad.setBounds(248, 155, 138, 14);
		contentPane.add(lblAppleRoad);
	}
}
