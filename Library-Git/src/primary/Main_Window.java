package primary;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/*import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;*/

public class Main_Window {

	private JFrame frame;
	private JTextField textField;
	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Window window = new Main_Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Window() {
		initialize();
		try{
			conn = DriverManager.getConnection("jdbc:mysql://10.10.10.124:3306/", "Kevin", "");
			
			if(conn.isValid(0))
			{
				JLabel lblNewLabel = new JLabel("Good");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(355, 204, 69, 14);
				frame.getContentPane().add(lblNewLabel);
			}
			
			Statement stmt = conn.createStatement();
			
			stmt.execute("USE LIBRARY");
			
			stmt.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(SystemColor.desktop);
		frame.setBounds(100, 100, 470, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton TopLeft = new JButton("Add Borrower");
		TopLeft.setFont(new Font("Tahoma", Font.BOLD, 13));
		TopLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Borrower bor = new Add_Borrower(frame, conn);
				frame.setVisible(false);
				bor.setVisible(true);
			}
		});
		TopLeft.setBounds(45, 112, 145, 58);
		frame.getContentPane().add(TopLeft);
		
		JButton BottomLeft = new JButton("Check Borrower");
		BottomLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check_Borrower bor = new Check_Borrower(frame, conn);
				frame.setVisible(false);
				bor.setVisible(true);
			}
		});
		BottomLeft.setFont(new Font("Tahoma", Font.BOLD, 13));
		BottomLeft.setBounds(45, 181, 145, 58);
		frame.getContentPane().add(BottomLeft);
		
		JButton TopRight = new JButton("Search");
		TopRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				int Branch = Integer.parseInt(textField.getText());
				
				Statement stmt = conn.createStatement();
				
				if(Check_Branch(conn, stmt, Branch))
						{
						Search sea = new Search(frame, conn, Branch);
						frame.setVisible(false);
						sea.setVisible(true);
						}
				else
					JOptionPane.showMessageDialog(null, "Branch: " + Branch + " does not exist. Possible Branches: " + Get_Branches(conn,stmt));
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		TopRight.setFont(new Font("Tahoma", Font.BOLD, 13));
		TopRight.setBounds(200, 112, 145, 58);
		frame.getContentPane().add(TopRight);
		
		JButton BottomRight = new JButton("Check In");
		BottomRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Check_Out che = new Check_Out(frame, conn);
				frame.setVisible(false);
				che.setVisible(true);
				
			}
		});
		BottomRight.setFont(new Font("Tahoma", Font.BOLD, 13));
		BottomRight.setBounds(200, 181, 145, 58);
		frame.getContentPane().add(BottomRight);
		
		JLabel lblLibraryProject = new JLabel("Library Project");
		lblLibraryProject.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLibraryProject.setHorizontalAlignment(SwingConstants.CENTER);
		lblLibraryProject.setBounds(45, 11, 300, 37);
		frame.getContentPane().add(lblLibraryProject);
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMainMenu.setBounds(46, 45, 299, 37);
		frame.getContentPane().add(lblMainMenu);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(355, 137, 69, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSetBranch = new JLabel("Set Branch:");
		lblSetBranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetBranch.setBounds(355, 112, 69, 14);
		frame.getContentPane().add(lblSetBranch);
		
		JLabel lblConnectionStatus = new JLabel("Connection:");
		lblConnectionStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnectionStatus.setBounds(355, 181, 69, 14);
		frame.getContentPane().add(lblConnectionStatus);
		
	}
	
	static public boolean Check_Branch(Connection conn, Statement stmt, int Branch) throws SQLException
	{
		ResultSet rs1;
		Boolean ret = false;
		
		rs1 = stmt.executeQuery("Select * FROM LIBRARY_BRANCH WHERE Branch_Id = " + Branch + ";");
		ret = rs1.first();
		rs1.close();
		
		return ret;
	}
	
	static public String Get_Branches(Connection conn, Statement stmt) throws SQLException
	{
		ResultSet rs1;
		String s1 = "";
		
		rs1 = stmt.executeQuery("SELECT Branch_Id FROM LIBRARY_BRANCH;");
		
		while(rs1.next())
			if(s1 == "")
				s1 = s1 + rs1.getString("Branch_Id");
			else
				s1 = s1 + ", " + rs1.getShort("Branch_Id");
		
		return s1;
	}
}
