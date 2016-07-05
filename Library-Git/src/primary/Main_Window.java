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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "");
			
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
		frame.setBounds(100, 100, 450, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton TopLeft = new JButton("Add Borrower");
		TopLeft.setFont(new Font("Tahoma", Font.BOLD, 13));
		TopLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Borrower bor = new Add_Borrower(frame);
				frame.setVisible(false);
				bor.setVisible(true);
			}
		});
		TopLeft.setBounds(45, 112, 145, 58);
		frame.getContentPane().add(TopLeft);
		
		JButton BottomLeft = new JButton("Check Borrower");
		BottomLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check_Borrower bor = new Check_Borrower(frame);
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
				Search sea = new Search(frame);
				frame.setVisible(false);
				sea.setVisible(true);
			}
		});
		TopRight.setFont(new Font("Tahoma", Font.BOLD, 13));
		TopRight.setBounds(200, 112, 145, 58);
		frame.getContentPane().add(TopRight);
		
		JButton BottomRight = new JButton("Check Out");
		BottomRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check_Out che = new Check_Out(frame);
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
		textField.setBounds(355, 181, 69, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblSetBranch = new JLabel("Set Branch:");
		lblSetBranch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetBranch.setBounds(355, 156, 69, 14);
		frame.getContentPane().add(lblSetBranch);
	}
}
