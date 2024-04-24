package algo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

public class AdminDashboard extends JFrame implements ActionListener {

	JLabel headlbl;
	JButton addcovidpatientbtn;
	JButton addcontactractingbtn;
	JButton viewpeoplehighriskbtn;
	JButton viewpeoplemoderateriskbtn;
	JButton viewpeopleconnectionbtn;
	JButton logoutbtn;

	AdminDashboard() {
		setTitle("Admin Dashboard");
		setLayout(null);

		headlbl = new JLabel("Admin Dashboard");

		headlbl.setFont(new Font("Serif", Font.BOLD, 40));

		addcovidpatientbtn = new JButton("Add Covid Patient Details");
		addcontactractingbtn = new JButton("Add Contant Tracing Details");
		viewpeoplehighriskbtn = new JButton("View Patient who are at high risk");
		viewpeoplemoderateriskbtn = new JButton("View Patient who are at moderate risk");
		viewpeopleconnectionbtn = new JButton("View Patient Connections");
		logoutbtn = new JButton("Logout");

		addcovidpatientbtn.addActionListener(this);
		addcontactractingbtn.addActionListener(this);
		viewpeoplehighriskbtn.addActionListener(this);
		viewpeoplemoderateriskbtn.addActionListener(this);
		viewpeopleconnectionbtn.addActionListener(this);
		logoutbtn.addActionListener(this);

		headlbl.setBounds(50, 30, 350, 60);
		addcovidpatientbtn.setBounds(50, 100, 380, 70);
		addcontactractingbtn.setBounds(50, 190, 380, 70);
		viewpeoplehighriskbtn.setBounds(50, 280, 380, 70);
		viewpeoplemoderateriskbtn.setBounds(50, 370, 380, 70);
		viewpeopleconnectionbtn.setBounds(50, 460, 380, 70);
		logoutbtn.setBounds(150, 570, 100, 50);
		logoutbtn.setBackground(Color.RED);
		logoutbtn.setOpaque(true);

		add(headlbl);
		add(addcovidpatientbtn);
		add(addcontactractingbtn);
		add(viewpeoplehighriskbtn);
		add(viewpeoplemoderateriskbtn);
		add(viewpeopleconnectionbtn);
		add(logoutbtn);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 720);
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		AdminDashboard f = new AdminDashboard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == addcovidpatientbtn) {
			AddCovidPatientDetail add_p = new AddCovidPatientDetail();
		}
		if (source == addcontactractingbtn) {
			AddContactTracing add_c = new AddContactTracing();
		}
		if (source == viewpeoplehighriskbtn) {
			ViewHighRisk add_c = new ViewHighRisk();
		}
		if (source == viewpeoplemoderateriskbtn) {
			ViewLowRisk add_c = new ViewLowRisk();
		}
		if (source == viewpeopleconnectionbtn) {
			ViewPatientConnection add_c = new ViewPatientConnection();
		}
		if (source == logoutbtn) {
			dispose();
		}

	}

}
