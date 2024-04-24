package algo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame implements ActionListener {

	JLabel usernamelbl;
	JLabel addresslbl;
	JLabel phonelbl;
	JLabel emaillbl;
	JLabel passwordlbl;
	JLabel cpasswordlbl;

	JTextField txtname;
	JTextField txtaddress;
	JTextField txtphone;
	JTextField txtemail;
	JPasswordField txtpassword;
	JPasswordField txtcpassword;
	
	JButton registerbtn;
	JButton loginbtn;
	JButton clearbtn;
	
	Register() {
		 	setTitle("Register");
		 	setLayout(null);

			
			usernamelbl=new JLabel("Username");
			addresslbl=new JLabel("Address");
			phonelbl=new JLabel("Phone");
			emaillbl=new JLabel("Email");
			passwordlbl=new JLabel("Password");
			cpasswordlbl=new JLabel("Confirm Password");
			
			txtname=new JTextField(10);
			txtaddress=new JTextField(10);
			txtphone=new JTextField(10);
			txtemail=new JTextField(10);
			txtpassword=new JPasswordField(10);
			txtcpassword=new JPasswordField(10);
			
			registerbtn=new JButton("Register");
			loginbtn=new JButton("Back to Login");
			clearbtn=new JButton("Clear");
			
			registerbtn.addActionListener(this);
			loginbtn.addActionListener(this);
			clearbtn.addActionListener(this);
			
			 usernamelbl.setBounds(50, 50, 75, 25);
			 addresslbl.setBounds(50, 100, 75, 25);
			 phonelbl.setBounds(50, 150, 75, 25);
			 emaillbl.setBounds(50, 200, 75, 25);
			 passwordlbl.setBounds(50, 250, 75, 25);
			 cpasswordlbl.setBounds(50, 300, 75, 25);
			 
			 txtname.setBounds(120, 50, 200, 25);
			 txtaddress.setBounds(120, 100, 200, 25);
			 txtphone.setBounds(120, 150, 200, 25);
			 txtemail.setBounds(120, 200, 200, 25);
			 txtpassword.setBounds(120, 250, 200, 25);
			 txtcpassword.setBounds(120, 300, 200, 25);
			 
			 registerbtn.setBounds(115, 350, 100, 25);
			 clearbtn.setBounds(225, 350, 100, 25);
			 loginbtn.setBounds(110, 400, 200, 25);
			 

			 add(usernamelbl);
			 add(txtname);
			 add(addresslbl);
			 add(txtaddress);
			 add(phonelbl);
			 add(txtphone);
			 add(emaillbl);
			 add(txtemail);
			 add(passwordlbl);
			 add(txtpassword);

			 add(cpasswordlbl);
			 add(txtcpassword);
			 
			 add(registerbtn);
			 add(loginbtn);
			 add(clearbtn);
			 
			 
		 	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 	setSize(420,520);
		 	setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();
		
		if (source == registerbtn) {
			String username = txtname.getText();
			String address = txtaddress.getText();
			String phone = txtphone.getText();
			String email = txtemail.getText();
			String password = txtpassword.getText();
			String cpassword = txtcpassword.getText();

			if (username.isEmpty() == false && address.isEmpty() == false && phone.isEmpty() == false && email.isEmpty() == false && password.equals(cpassword)) {

				String userdata = username + "," + address + "," + phone + "," + email + "," + password;
				try {
					File f = new File("C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//Userregister.txt");
					if (!f.exists()) {
						f.createNewFile();
					}
					FileWriter fw = new FileWriter(f, true);
					fw.write(userdata);
					fw.write("\n");
					fw.flush();
					fw.close();
				} catch (Exception a) {
					a.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Register Success");
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Register Failed");
			}

		} else if (source == clearbtn) {
			txtname.setText("");
			txtaddress.setText("");
			txtphone.setText("");
			txtemail.setText("");
			txtpassword.setText("");
			txtcpassword.setText("");
		}
		else if (source == loginbtn) {
			dispose();
		}

		
		
	}

}
