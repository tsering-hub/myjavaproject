package algo;

import java.util.Arrays;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewHighRisk extends JFrame {

	JLabel headlbl;

	DefaultTableModel tablemodel;
	JTable tbl;

	int vertices = 100;
	int[][] adjacency_matrix = new int[vertices][vertices];

	int[] contacted_id = new int[30];

	ViewHighRisk() {

		setTitle("High Risk Pateint Details");

		headlbl = new JLabel("High Risk Pateint Details");
		headlbl.setFont(new Font("Serif", Font.BOLD, 30));

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

		headlbl.setBounds(400, 40, 800, 50);

		sp.setBounds(50, 100, 1100, 500);

		add(headlbl);
		add(sp);

		readMatrix();
		highRiskID();
		display();

		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 650);
		setVisible(true);

	}

	public void readMatrix() {

		try {
			File f = new File("C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//contactTracingMatrix.txt");
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

	public int[] highRiskID() {

		for (int row = 0; row < vertices; row++) {

			for (int col = 0; col < vertices; col++) {
				if (adjacency_matrix[row][col] == 1) {

					try {
						File f = new File(
								"C://Users//lenovo//eclipse-workspace//assignmentfile//Week8to11//covidpatientdetails.txt");
						BufferedReader br = new BufferedReader(new FileReader(f));
						String line;
						while ((line = br.readLine()) != null) {
							String[] data = line.split(",");

							if (row == Integer.parseInt(data[0])) {
								if (!data[3].equals("null")) {
									contacted_id[col] = col;


								}

							}

						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

			}

		}
		return contacted_id;


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

				int[] texting = highRiskID();

				for (int i = 0; i < texting.length; i++) {
					if (texting[i] == Integer.parseInt(data[0])) {
						tablemodel.addRow(new Object[] { id, name, address, covid_date, contacted_date, created_date });
					}
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ViewHighRisk add_c = new ViewHighRisk();
		System.out.println(Arrays.toString(add_c.highRiskID()));
	}

}
