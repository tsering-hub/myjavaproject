package algo;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;



public class Login extends JFrame implements ActionListener{

	JLabel titlelbl;
	JLabel userlbl;
	JLabel passlbl;
	
	JTextField usertxt;
	JPasswordField passtxt;
	
	JButton loginbtn;
	JButton clearbtn;
	JButton registerbtn;
	
	
	Login(){
		 setTitle("Login");
		 setLayout(null);
		 
		 titlelbl=new JLabel("Login");
		 userlbl=new JLabel("Username");
		 passlbl=new JLabel("Password");
		 
		 usertxt=new JTextField(10);
		 passtxt=new JPasswordField(10);
		 loginbtn=new JButton("Login");
		 registerbtn=new JButton("Register");
		 clearbtn=new JButton("Clear");
		 
		 titlelbl.setBounds(110, 30, 100, 50);
		 titlelbl.setFont(new Font("Serif", Font.BOLD, 40));
		 userlbl.setBounds(50, 100, 75, 25);
		 passlbl.setBounds(50, 150, 75, 25);
		 
		 usertxt.setBounds(120, 100, 200, 25);
		 passtxt.setBounds(120, 150, 200, 25);
		 
		 loginbtn.setBounds(115, 200, 100, 25);
		 registerbtn.setBounds(225, 200, 100, 25);
		 clearbtn.setBounds(200, 250, 100, 25);
		 
		 loginbtn.addActionListener(this);
		 registerbtn.addActionListener(this);
		 clearbtn.addActionListener(this);
		 
		 add(titlelbl);
		 add(userlbl);
		 add(passlbl);
		 
		 add(usertxt);
		 add(passtxt);
		 
		 add(loginbtn);
		 add(registerbtn);
		 add(clearbtn);
		
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setSize(420,420);
		 setVisible(true);
	}
	
	public static void main(String[] args) {
		Login f = new Login();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source=e.getSource();//logibbtn
		
		if (source==loginbtn) {
			 String username = usertxt.getText();
	            String password = passtxt.getText();
	            // admin admin@123
	            if (username.isEmpty() == false && password.isEmpty() == false) {
	                try {
	                    File f = new File("C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//Userregister.txt");
	                    BufferedReader br = new BufferedReader(new FileReader(f));
	                    String line;
	                    while ((line = br.readLine()) != null) {
	                        String[] data = line.split(",");
	                        if (username.equals(data[0]) && password.equals(data[4])) {
	                            JOptionPane.showMessageDialog(null, "Login Success");
	                            AdminDashboard admin = new AdminDashboard();
	                            dispose();
	                        }
	                        else {
	                            JOptionPane.showMessageDialog(null, "Enter correct username and password");
	                        }
	                    }
	                } catch (Exception ee) {
	                    ee.printStackTrace();
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Enter all the fields");
	            }
	        } else if (source == clearbtn) {
	            usertxt.setText("");
	            passtxt.setText("");
	        }

		else if (source==registerbtn) {
			Register register = new Register();
			
		}
		
	}
}
