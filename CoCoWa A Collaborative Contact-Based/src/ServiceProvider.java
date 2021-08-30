import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServiceProvider extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2L;

	JPanel p = new JPanel();

	Font f = new Font("times new roman", Font.BOLD, 15);
	Font f1 = new Font("times new roman", Font.BOLD, 18);

	JButton b1 = new JButton("Browse"), b2 = new JButton("Upload");

	JTextArea ta = new JTextArea();
	JScrollPane pane = new JScrollPane();

	JTextField tf = new JTextField();

	ServiceProvider() {

		p.setLayout(null);
		p.setBackground(Color.LIGHT_GRAY);
		
		
		ImageIcon bann1 = new ImageIcon(this.getClass().getResource("ServiceProvider.PNG"));
		JLabel bp1 = new JLabel();
		bp1.setIcon(bann1);
		bp1.setBounds(20, 0, 1000, 100);
		
		
		ImageIcon bann11 = new ImageIcon(this.getClass().getResource("SelfishAttacks.PNG"));
		JLabel bp11 = new JLabel();
		bp11.setIcon(bann11);
		bp11.setBounds(460, 60, 490, 620);
		
		

		tf.setFont(f1);
		ta.setRows(10);
		ta.setColumns(10);
		ta.setFont(f);

		pane.setViewportView(ta);

		tf.setBounds(50, 100, 150, 30);
		pane.setBounds(50, 135, 400, 350);

		b1.setBounds(75, 520, 130, 30);
		b2.setBounds(235, 520, 130, 30);

		b1.setFont(f1);
		b2.setFont(f1);

		p.add(tf);
		p.add(pane);
		p.add(b1);
		p.add(b2);
		p.add(bp11);
		p.add(bp1);
		add(p);

		b1.addActionListener(this);
		b2.addActionListener(this);

	}

	public static void main(String[] args) {

		ServiceProvider sp = new ServiceProvider();
		sp.setSize(960, 600);
		sp.setTitle("Service-Provider::CoCoWa: A Collaborative Contact-Based Watchdog for Detecting Selfish Nodes");
		sp.setVisible(true);

	}


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {

			JFileChooser chooser = new JFileChooser("Database");
			int check = chooser.showOpenDialog(this);

			try {
				if (check == JFileChooser.APPROVE_OPTION) {

					File ff = chooser.getSelectedFile();

					tf.setText(ff.getName());

					FileInputStream fis = new FileInputStream(ff);
					DataInputStream dis = new DataInputStream(fis);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(dis));

					StringBuffer bf = new StringBuffer();
					String s;
					while ((s = br.readLine()) != null) {
						bf.append(s + "\n");
					}

					ta.setText(bf.toString());

					ta.setEditable(false);
					tf.setEditable(false);

				}

			} catch (Exception ez) {

				ez.printStackTrace();

			}

		}

		if (e.getSource() == b2) {

			JOptionPane op = new JOptionPane();
			String node = null;
			int port = 0;

			String[] s = { "Select Router", "Router1", "Router2", "Router3" };

			String x = (String) op.showInputDialog(this,
					"Choose project to open", "Open Project", op.PLAIN_MESSAGE,
					null, s, s[0]);

			if (x.equals("Router1")) {

				port = 5000;

				String[] n = { "Select Router", "LSU", "SSU1", "SSU2" };

				node = (String) op.showInputDialog(this,
						"Choose project to open", "Open Project",
						op.PLAIN_MESSAGE, null, n, n[0]);

			}

			if (x.equals("Router2")) {

				port = 5001;

				String[] n = { "Select Router", "SSU", "LSU1", "LSU2" };

				node = (String) op.showInputDialog(this,
						"Choose project to open", "Open Project",
						op.PLAIN_MESSAGE, null, n, n[0]);

			}

			if (x.equals("Router3")) {

				port = 5002;

				String[] n = { "Select Router", "LSU1", "LSU2", "SSU1", "SSU2" };

				node = (String) op.showInputDialog(this,
						"Choose project to open", "Open Project",
						op.PLAIN_MESSAGE, null, n, n[0]);

			}

			try {

				Socket socket = new Socket(op.showInputDialog(null,
						"Enter IPAddress"), port);

				DataOutputStream dos = new DataOutputStream(socket
						.getOutputStream());

				dos.writeUTF(node);
				dos.writeUTF(tf.getText());
				dos.writeUTF(ta.getText());

			} catch (Exception ex) {

				System.out.println(ex);

			}

		}

	}
}
