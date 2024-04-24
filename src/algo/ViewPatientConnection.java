package algo;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class ViewPatientConnection extends JFrame implements MouseListener {

	JLabel headlbl;
	JLabel head1lbl;

	DefaultTableModel tablemodel;
	JTable tbl;

	int vertices = 100;
	int[][] adjacency_matrix = new int[vertices][vertices];

	int[] level1vertices;

	ViewPatientConnection() {

		setTitle("Pateint Connection Details");

		headlbl = new JLabel("Pateint Connection");
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

		head1lbl = new JLabel("**Click on table rows to display connection of that person**");
		head1lbl.setFont(new Font("Serif", Font.BOLD, 20));

		headlbl.setBounds(400, 40, 800, 50);

		sp.setBounds(50, 100, 1100, 400);

		head1lbl.setBounds(200, 510, 700, 40);

		add(headlbl);
		add(sp);

		add(head1lbl);

		tbl.addMouseListener(this);

		readMatrix();
		display();

		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 750);
		setVisible(true);

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

	public int[][] level0(int idNo) {
		int[][] points = new int[10][2];
		level1vertices = new int[10];
		int count = 0;
		for (int i = 0; i < vertices; i++) {
			if (i == idNo) {
				for (int j = 0; j < vertices; j++) {
					if (adjacency_matrix[i][j] != 0) {
						points[count][0] = idNo;
						points[count][1] = j;
						level1vertices[count] = j;
						count++;
					}
				}
			}
		}
		return points;
	}

	public int[][] level1() {
		int[][] points = new int[10][2];
		int count = 0;
		for (int a = 0; a < level1vertices.length; a++) {
			for (int i = 0; i < vertices; i++) {
				if (i == level1vertices[a]) {
					for (int j = 0; j < vertices; j++) {
						if (adjacency_matrix[i][j] != 0) {
							points[count][0] = level1vertices[a];
							points[count][1] = j;
							count++;
						}
					}
				}
			}
		}

		return points;
	}

	public void graphConnectionDisplay(int idNo) {
		DirectedSparseGraph g = new DirectedSparseGraph();
		int[][] points0 = level0(idNo);
		for (int i = 0; i < points0.length; i++) {
			if (points0[i][0] != 0 && points0[i][1] != 0) {
				g.addVertex(points0[i][0]);
				g.addVertex(points0[i][1]);
				String edgeString = points0[i][1] + " contacted to " + points0[i][0];
				g.addEdge(edgeString, points0[i][0], points0[i][1]);
			}
		}
		int[][] points1 = level1();
		for (int i = 0; i < points1.length; i++) {
			if (points1[i][0] != 0 && points1[i][1] != 0) {
				g.addVertex(points1[i][0]);
				g.addVertex(points1[i][1]);
				String edgeString = points1[i][1] + " contacted to " + points1[i][0];
				g.addEdge(edgeString, points1[i][0], points1[i][1]);
			}
		}

		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
				0.0f);
		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
			public Stroke transform(String s) {
				return edgeStroke;
			}
		};
		VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g), new Dimension(500, 500));
		vs.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vs.getRenderer().getVertexLabelRenderer().setPosition(Position.N);
		JFrame frame = new JFrame();
		frame.getContentPane().add(vs);
		frame.pack();
		frame.setSize(1200, 750);
		frame.setVisible(true);
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

	public static void main(String[] args) {
		ViewPatientConnection add_c = new ViewPatientConnection();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int row = tbl.rowAtPoint(e.getPoint());

		int id = Integer.parseInt(tablemodel.getValueAt(row, 0).toString());

//		printEdges(id);
		graphConnectionDisplay(id);

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
