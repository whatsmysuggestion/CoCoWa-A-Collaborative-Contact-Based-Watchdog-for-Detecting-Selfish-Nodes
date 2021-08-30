import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateStatus extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2L;

	JPanel p = new JPanel();

	JComboBox cb = new JComboBox();
	JComboBox cb1 = new JComboBox();

	JLabel l1 = new JLabel("Node"), l2 = new JLabel("Status"), l3 = new JLabel(
			"");
	JTextField t1 = new JTextField();

	JButton b1 = new JButton("Update"), b2 = new JButton("Cancel");

	UpdateStatus() {

		p.setLayout(null);
		p.setBackground(Color.lightGray);

		cb.addItem("Select Node");
		cb.addItem("LSU1");
		cb.addItem("LSU2");

		cb1.addItem("Default");
		cb1.addItem("Block");

		l1.setBounds(50, 100, 100, 25);
		l2.setBounds(50, 150, 100, 25);

		cb.setBounds(150, 100, 150, 25);
		cb1.setBounds(150, 150, 150, 25);

		b1.setBounds(55, 225, 125, 30);
		b2.setBounds(200, 225, 125, 30);

		p.add(l1);
		p.add(l2);
		p.add(cb);
		p.add(cb1);
		p.add(b1);
		p.add(b2);

		add(p);

		b1.addActionListener(this);
		b2.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {

			try {

				String status = (String) cb1.getSelectedItem();
				String node = (String) cb.getSelectedItem();

				DBConnection.getConnection().createStatement().executeUpdate(
						"update Router2 set Status = '" + status
								+ "' where Node = '" + node + "'   ");

				JOptionPane.showMessageDialog(null, "Status Updated");

			} catch (Exception ex) {
				System.out.println(ex);
			}

		}

		if (e.getSource() == b2) {

			dispose();

		}

	}

}
