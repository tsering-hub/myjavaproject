package algo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class AddCovidPatientDetail extends JFrame implements MouseListener, ActionListener {

	JLabel headlbl;

	JLabel idlbl;
	JLabel namelbl;
	JLabel addresslbl;
	JLabel covid_datelbl;
	JLabel contacted_datelbl;

	JTextField idtxt;
	JTextField nametxt;
	JTextField addresstxt;
	JTextField covid_datetxt;
	JTextField contacted_datetxt;

	JButton savebtn;
	JButton updatebtn;
	JButton deletebtn;
	JButton clearbtn;
	JButton backbtn;

	Border border;
	DefaultTableModel tablemodel;
	JTable tbl;

	AddCovidPatientDetail() {
		setTitle("Add Covid Pateint Details");

		headlbl = new JLabel("Add Patient Details");
		headlbl.setFont(new Font("Serif", Font.BOLD, 30));
		border = BorderFactory.createLineBorder(Color.BLUE, 10);
		headlbl.setBorder(border);

		idlbl = new JLabel("ID");
		namelbl = new JLabel("Name");
		addresslbl = new JLabel("Address");
		covid_datelbl = new JLabel("Covid Date");
		contacted_datelbl = new JLabel("Contact Date");

		idtxt = new JTextField(10);
		idtxt.setEditable(false);
		nametxt = new JTextField(10);
		addresstxt = new JTextField(10);
		covid_datetxt = new JTextField(10);
		contacted_datetxt = new JTextField(10);

		savebtn = new JButton("Add");
		updatebtn = new JButton("Update");
		deletebtn = new JButton("Delete");
		clearbtn = new JButton("Clear");
		backbtn = new JButton("Back");

		headlbl.setBounds(50, 30, 1110, 60);

		idlbl.setBounds(50, 100, 90, 25);
		namelbl.setBounds(50, 150, 90, 25);
		addresslbl.setBounds(50, 200, 90, 25);
		covid_datelbl.setBounds(50, 250, 90, 25);
		contacted_datelbl.setBounds(50, 300, 90, 25);

		idtxt.setBounds(150, 100, 150, 25);
		nametxt.setBounds(150, 150, 150, 25);
		addresstxt.setBounds(150, 200, 150, 25);
		covid_datetxt.setBounds(150, 250, 150, 25);
		contacted_datetxt.setBounds(150, 300, 150, 25);

		savebtn.setBounds(100, 350, 150, 25);
		updatebtn.setBounds(100, 400, 150, 25);
		deletebtn.setBounds(100, 450, 150, 25);
		clearbtn.setBounds(100, 500, 150, 25);
		backbtn.setBounds(100, 550, 150, 25);

		savebtn.setBackground(Color.GREEN);
		savebtn.setOpaque(true);

		updatebtn.setBackground(Color.CYAN);
		updatebtn.setOpaque(true);

		deletebtn.setBackground(Color.RED);
		deletebtn.setOpaque(true);

		clearbtn.setBackground(Color.YELLOW);
		clearbtn.setOpaque(true);

		backbtn.setBackground(Color.RED);
		backbtn.setOpaque(true);

		String[] cols = { "ID", "Name", "Address", "Covid Date", "Contacted Date", "Created Date" };

		tablemodel = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};

		tbl = new JTable(tablemodel);

		// add jtable to scroll pane
		JScrollPane sp = new JScrollPane(tbl);

		sp.setBounds(350, 100, 800, 500);

		add(headlbl);

		add(idlbl);
		add(namelbl);
		add(addresslbl);
		add(covid_datelbl);
		add(contacted_datelbl);

		add(idtxt);
		add(nametxt);
		add(addresstxt);
		add(covid_datetxt);
		add(contacted_datetxt);

		add(savebtn);
		add(updatebtn);
		add(deletebtn);
		add(clearbtn);
		add(backbtn);

		add(sp);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 650);
		setVisible(true);

		display();

		savebtn.addActionListener(this);
		updatebtn.addActionListener(this);
		deletebtn.addActionListener(this);
		clearbtn.addActionListener(this);
		backbtn.addActionListener(this);

		tbl.addMouseListener(this);
	}

	public void table_remove() {
		for (int i = tablemodel.getRowCount() - 1; i >= 0; i--) {
			tablemodel.removeRow(i);
		}
	}

	public void reset() {
		idtxt.setText("");
		nametxt.setText("");
		addresstxt.setText("");
		covid_datetxt.setText("");
		contacted_datetxt.setText("");
	}

	public void display() {

		try {
			File f = new File("C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//covidpatientdetails.txt");
			
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");

				String id = data[0];
				String name = data[1];
				String address = data[2];
				String covid_date = data[3];
				String contacted_date = data[4];
				String created_date = data[5];
				tablemodel.addRow(new Object[] { id, name, address, covid_date, contacted_date, created_date });

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AddCovidPatientDetail f = new AddCovidPatientDetail();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == savebtn) {

			if (idtxt.getText().isEmpty()) {
				int max = 0;

				String name = nametxt.getText();
				String address = addresstxt.getText();
				String covid_date = covid_datetxt.getText();
				String contacted_date = contacted_datetxt.getText();

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDateTime now = LocalDateTime.now();

				if (name.isEmpty() == false && address.isEmpty() == false) {

					if (contacted_date.isEmpty() && covid_date.isEmpty()) {
						contacted_date = null;
						covid_date = null;
					} else if (covid_date.isEmpty()) {
						covid_date = null;
					} else if (contacted_date.isEmpty()) {
						contacted_date = null;
					}

					try {
						File f = new File(
								"C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//covidpatientdetails.txt");
						if (!f.exists()) {
							f.createNewFile();
						}
						BufferedReader br = new BufferedReader(new FileReader(f));
						String line;
						while ((line = br.readLine()) != null) {
							String[] data = line.split(",");
							max = Integer.parseInt(data[0]);
						}

						String userdata = ++max + "," + name + "," + address + "," + covid_date + "," + contacted_date
								+ "," + dtf.format(now);
						FileWriter fw = new FileWriter(f, true);
						fw.write(userdata);
						fw.write("\n");
						fw.flush();
						fw.close();

					} catch (Exception a) {
						a.printStackTrace();
					}

					JOptionPane.showMessageDialog(null, "Patient details added Success");
					table_remove();
					display();
					reset();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to Add patient details");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Failed to Add patient details");
			}

		}

		if (source == clearbtn) {
			reset();
		}

		if (source == backbtn) {
			dispose();
		}

		if (source == updatebtn) {

			String id = idtxt.getText();
			String name = nametxt.getText();
			String address = addresstxt.getText();
			String covid_date = covid_datetxt.getText();
			String contacted_date = contacted_datetxt.getText();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();

			if (name.isEmpty() == false && address.isEmpty() == false) {
				if (contacted_date.isEmpty() && covid_date.isEmpty()) {
					contacted_date = null;
					covid_date = null;
				} else if (covid_date.isEmpty()) {
					covid_date = null;
				} else if (contacted_date.isEmpty()) {
					contacted_date = null;
				}

				try {
					String coviddata;
					String oldid;
					boolean found;

					File f = new File("C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//covidpatientdetails.txt");
					if (!f.exists()) {
						f.createNewFile();
					}

					// Opening file in reading and write mode.
					RandomAccessFile raf = new RandomAccessFile(f, "rw");
					found = false;

					// Checking whether the name
					// of contact already exists.
					// getFilePointer() give the current offset
					// value from start of the file.
					while (raf.getFilePointer() < raf.length()) {

						// reading line from the file.
						coviddata = raf.readLine();

						// splitting the string to get name and
						// number
						String[] data = coviddata.split(",");

						oldid = data[0];

						// if condition to find existence of record.
						if (oldid.equals(id)) {
							found = true;
							break;
						}
					}

					// Update the contact if record exists.
					if (found == true) {

						// Creating a temporary file
						// with file pointer as tmpFile.
						File tmpFile = new File("temp.txt");

						// Opening this temporary file
						// in ReadWrite Mode
						RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

						// Set file pointer to start
						raf.seek(0);

						// Traversing the friendsContact.txt file
						while (raf.getFilePointer() < raf.length()) {

							// Reading the contact from the file
							coviddata = raf.readLine();

							String[] data = coviddata.split(",");

							oldid = data[0];

							// Check if the fetched contact
							// is the one to be updated
							if (oldid.equals(id)) {

								// Update the number of this contact
								coviddata = id + "," + name + "," + address + "," + covid_date + "," + contacted_date
										+ "," + dtf.format(now);
							}

							// Add this contact in the temporary
							// file
							tmpraf.writeBytes(coviddata);

							// Add the line separator in the
							// temporary file
							tmpraf.writeBytes(System.lineSeparator());
						}

						// The contact has been updated now
						// So copy the updated content from
						// the temporary file to original file.

						// Set both files pointers to start
						raf.seek(0);
						tmpraf.seek(0);

						// Copy the contents from
						// the temporary file to original file.
						while (tmpraf.getFilePointer() < tmpraf.length()) {
							raf.writeBytes(tmpraf.readLine());
							raf.writeBytes(System.lineSeparator());
						}

						// Set the length of the original file
						// to that of temporary.
						raf.setLength(tmpraf.length());

						// Closing the resources.
						tmpraf.close();
						raf.close();

						// Deleting the temporary file
						tmpFile.delete();

						JOptionPane.showMessageDialog(null, "Patient details Updated Success");
						table_remove();
						display();
						reset();
					}

					// The contact to be updated
					// could not be found
					else {

						// Closing the resources.
						raf.close();

						// Print the message
						JOptionPane.showMessageDialog(null, "Failed to Update patient details");
					}

				} catch (Exception a) {
					a.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(null, "Failed to Update patient details");
			}

		}

		if (source == deletebtn) {

			String id = idtxt.getText();

			try {
				String coviddata;
				String oldid;
				boolean found;

				File f = new File("C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//covidpatientdetails.txt");
				if (!f.exists()) {
					f.createNewFile();
				}

				// Opening file in reading and write mode.
				RandomAccessFile raf = new RandomAccessFile(f, "rw");
				found = false;

				// Checking whether the name
				// of contact already exists.
				// getFilePointer() give the current offset
				// value from start of the file.
				while (raf.getFilePointer() < raf.length()) {

					// reading line from the file.
					coviddata = raf.readLine();

					// splitting the string to get name and
					// number
					String[] data = coviddata.split(",");

					oldid = data[0];

					// if condition to find existence of record.
					if (oldid.equals(id)) {
						found = true;
						break;
					}
				}

				// Update the contact if record exists.
				if (found == true) {

					// Creating a temporary file
					// with file pointer as tmpFile.
					File tmpFile = new File("temp.txt");

					// Opening this temporary file
					// in ReadWrite Mode
					RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

					// Set file pointer to start
					raf.seek(0);

					// Traversing the friendsContact.txt file
					while (raf.getFilePointer() < raf.length()) {

						// Reading the contact from the file
						coviddata = raf.readLine();

						String[] data = coviddata.split(",");

						oldid = data[0];

						// Check if the fetched contact
						// is the one to be updated
						if (oldid.equals(id)) {
							// Skip inserting this contact
							// into the temporary file
							continue;
						}

						// Add this contact in the temporary
						// file
						tmpraf.writeBytes(coviddata);

						// Add the line separator in the
						// temporary file
						tmpraf.writeBytes(System.lineSeparator());
					}

					// The contact has been deleted now
					// So copy the updated content from
					// the temporary file to original file.

					// Set both files pointers to start
					raf.seek(0);
					tmpraf.seek(0);

					// Copy the contents from
					// the temporary file to original file.
					while (tmpraf.getFilePointer() < tmpraf.length()) {
						raf.writeBytes(tmpraf.readLine());
						raf.writeBytes(System.lineSeparator());
					}

					// Set the length of the original file
					// to that of temporary.
					raf.setLength(tmpraf.length());

					// Closing the resources.
					tmpraf.close();
					raf.close();

					// Deleting the temporary file
					tmpFile.delete();

					JOptionPane.showMessageDialog(null, "Patient details deleted Success");
					table_remove();
					display();
					reset();
				}

				// The contact to be updated
				// could not be found
				else {

					// Closing the resources.
					raf.close();

					JOptionPane.showMessageDialog(null, "Unable to delete Patient details");
				}

			} catch (Exception a) {
				a.printStackTrace();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tbl.rowAtPoint(e.getPoint());

		idtxt.setText(tablemodel.getValueAt(row, 0).toString());
		nametxt.setText(tablemodel.getValueAt(row, 1).toString());
		addresstxt.setText(tablemodel.getValueAt(row, 2).toString());
		covid_datetxt.setText(tablemodel.getValueAt(row, 3).toString());
		contacted_datetxt.setText(tablemodel.getValueAt(row, 4).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
