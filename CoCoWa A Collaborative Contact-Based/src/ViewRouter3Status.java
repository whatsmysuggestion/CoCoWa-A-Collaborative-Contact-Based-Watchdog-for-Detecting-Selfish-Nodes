import java.sql.*;
import java.awt.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewRouter3Status extends JFrame

{

	private static final long serialVersionUID = 2L;

	JScrollPane pane;
	JTable table;
	Container c;

	ViewRouter3Status() {

		setTitle("Router3 Node and Channel Status");
		c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.ORANGE);
		setSize(300, 100);
		setVisible(true);

		try {

			Vector heading = new Vector();

			heading.addElement("Node Name");
			heading.addElement("Channel Name");
			heading.addElement("Availability");
			

			Vector data = new Vector();

			String query = "SELECT * FROM Router3";

			ResultSet rs = DBConnection.getConnection().createStatement()
					.executeQuery(query);

			ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();

			while (rs.next()) {

				Vector row = new Vector();

				for (int i = 1; i <= col; i++) {

					row.addElement(rs.getObject(i));

				}

				data.addElement(row);
			}

			JTable table = new JTable(data, heading);

			pane = new JScrollPane(table);

			pane.setBounds(20, 20, 400, 250);
			c.add(pane);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}