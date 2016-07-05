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
import javax.swing.SwingConstants;

public class Search extends JFrame {

	private JPanel contentPane;
	
	private int Branch;

	/**
	 * Create the frame.
	 */
	public Search(JFrame frame1, int branch) {
		
		Branch = branch;
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
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(10, 11, 58, 24);
		contentPane.add(lblSearch);
		
		JLabel lblNewLabel = new JLabel("Branch: " + Branch);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 46, 102, 14);
		contentPane.add(lblNewLabel);
	}

}
