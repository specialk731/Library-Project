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

public class Check_Borrower extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public Check_Borrower(JFrame frame1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		Main_Menu.setBounds(309, 213, 115, 38);
		contentPane.add(Main_Menu);
		
		JLabel lblCheckBorrower = new JLabel("Check Borrower");
		lblCheckBorrower.setBounds(10, 11, 144, 38);
		contentPane.add(lblCheckBorrower);
	}

}
