package algo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddContactTracing extends JFrame implements ActionListener {

	JLabel headlbl;

	JLabel covidpatientidlbl;
	JLabel contactpatientidlbl;

	JTextField covidpatientidtxt;
	JTextField contactpatientidtxt;

	JButton savebtn;
	JButton updatebtn;
	JButton backbtn;
	JButton clearbtn;

	DefaultTableModel tablemodel;
	JTable tbl;

	int vertices = 100;
	int[][] adjacency_matrix = new int[vertices][vertices];

	AddContactTracing() {
		setTitle("Add Contact Tracing Details");

		headlbl = new JLabel("Add Contact Tracing Details");
		headlbl.setFont(new Font("Serif", Font.BOLD, 30));

		covidpatientidlbl = new JLabel("Patient ID");
		contactpatientidlbl = new JLabel("Contacted patient ID");

		covidpatientidtxt = new JTextField(10);
		contactpatientidtxt = new JTextField(10);

		savebtn = new JButton("Add");
		updatebtn = new JButton("Update");
		clearbtn = new JButton("Clear");
		backbtn = new JButton("Back");

		headlbl.setBounds(200, 30, 1110, 60);

		covidpatientidlbl.setBounds(40, 150, 120, 25);
		contactpatientidlbl.setBounds(40, 200, 120, 25);

		covidpatientidtxt.setBounds(180, 150, 150, 25);
		contactpatientidtxt.setBounds(180, 200, 150, 25);

		savebtn.setBounds(100, 300, 150, 25);
		updatebtn.setBounds(100, 350, 150, 25);
		clearbtn.setBounds(100, 350, 150, 25);
		backbtn.setBounds(100, 400, 150, 25);

		savebtn.setBackground(Color.GREEN);
		savebtn.setOpaque(true);

		updatebtn.setBackground(Color.CYAN);
		updatebtn.setOpaque(true);

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

		sp.setBounds(350, 100, 500, 400);

		add(headlbl);

		add(covidpatientidlbl);
		add(contactpatientidlbl);

		add(covidpatientidtxt);
		add(contactpatientidtxt);

		add(savebtn);
		add(clearbtn);
		add(backbtn);

		add(sp);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 600);
		setVisible(true);

		savebtn.addActionListener(this);
		updatebtn.addActionListener(this);
		clearbtn.addActionListener(this);
		backbtn.addActionListener(this);

		display();
		readMatrix();

	}

	public void display() {

		try {
			File f = new File(
					"C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//covidpatientdetails.txt");
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

	public void table_remove() {
		for (int i = tablemodel.getRowCount() - 1; i >= 0; i--) {
			tablemodel.removeRow(i);
		}
	}

	public void reset() {
		covidpatientidtxt.setText("");
		contactpatientidtxt.setText("");

	}

	public void addEdge(int source, int destination) {
		adjacency_matrix[source][destination] = 1;
	}

	public void writeMatrix() {

		try {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < vertices; i++)// for each row
			{
				for (int j = 0; j < vertices; j++)// for each column
				{
					builder.append(adjacency_matrix[i][j] + "");
					if (j < adjacency_matrix.length - 1)
						builder.append(",");
				}
				builder.append("\n");
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//contactTracingMatrix.txt"));
			writer.write(builder.toString());
			writer.close();
		}

		catch (Exception a) {
			a.printStackTrace();
		}

	}

	public void readMatrix() {

		try {
			File f = new File(
					"C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//contactTracingMatrix.txt");
			BufferedReader buffer = new BufferedReader(new FileReader(f));

			String line;
			int row = 0;

			while ((line = buffer.readLine()) != null) {
				String[] vals = line.trim().split(",");

				for (int col = 0; col < vertices; col++) {
					adjacency_matrix[row][col] = Integer.parseInt(vals[col]);
				}

				row++;
			}

		} catch (Exception a) {
			a.printStackTrace();
		}

	}

	public static void main(String[] args) {
		AddContactTracing f = new AddContactTracing();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == clearbtn) {
			reset();
		}
		if (source == backbtn) {
			dispose();
		}
		if (source == savebtn) {
			int covid_id = Integer.parseInt(covidpatientidtxt.getText());
			int contact_id = Integer.parseInt(contactpatientidtxt.getText());
			int id;
			boolean covid_id_exist = false;
			boolean contact_id_exist = false;
			try {
				File f = new File(
						"C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//covidpatientdetails.txt");
				BufferedReader br = new BufferedReader(new FileReader(f));
				String line;
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					id = Integer.parseInt(data[0]);

					if (covid_id == id) {
						covid_id_exist = true;
						continue;
					}
					if (contact_id == id) {
						contact_id_exist = true;
						continue;
					}

				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			if (covid_id_exist && contact_id_exist) {
				addEdge(covid_id, contact_id);
//				printEdges();
				writeMatrix();
				reset();
				JOptionPane.showMessageDialog(null, "Contact tracing detail is added successfully");
			} else {
				JOptionPane.showMessageDialog(null, "failed to add contact tracing detail");
			}

		}

	}

}
