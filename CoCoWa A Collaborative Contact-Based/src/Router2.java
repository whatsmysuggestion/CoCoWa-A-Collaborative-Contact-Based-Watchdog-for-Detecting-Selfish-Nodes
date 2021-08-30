import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Router2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JPanel p = new JPanel();

	ImageIcon cld, c1, c2, c3, node, phone, line, str2, str3, rstr2, rstr3,
			rline, rphone, nul;

	JLabel cl = new JLabel(), ch1 = new JLabel(), ch2 = new JLabel(),
			ch3 = new JLabel(), ls1 = new JLabel(), ls2 = new JLabel(),
			ss1 = new JLabel();

	JLabel l1 = new JLabel("Node SSU"), l2 = new JLabel("Node LSU1"), l3 = new JLabel(
			"Node LSU2"), line1 = new JLabel(), line2 = new JLabel(),
			line3 = new JLabel();

	JMenuBar mbr = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem item1 = new JMenuItem("Update Status"), item2 = new JMenuItem(
			"Exit"), item3 = new JMenuItem("View Attackers"),item4 = new JMenuItem("View Router2 Nodes Details");

	Router2() {

		p.setLayout(null);
		p.setBackground(Color.YELLOW);

		setJMenuBar(mbr);
		mbr.add(file);
		file.add(item1);
		file.add(item3);
		file.add(item4);
		file.add(item2);

		cld = new ImageIcon("image/area.png");
		c1 = new ImageIcon("image/c1.png");
		c2 = new ImageIcon("image/c2.png");
		c3 = new ImageIcon("image/c3.png");
		
		ImageIcon bann1 = new ImageIcon(this.getClass().getResource("Router.PNG"));
		JLabel bp1 = new JLabel();
		bp1.setIcon(bann1);
		bp1.setBounds(0, 0, 1000, 100);
		

		str2 = new ImageIcon("image/strlin2.png");
		str3 = new ImageIcon("image/strlin3.png");

		rstr2 = new ImageIcon("image/rstrlin2.png");
		rstr3 = new ImageIcon("image/rstrlin3.png");

		node = new ImageIcon("image/gphone.png");
		phone = new ImageIcon("image/phone.png");
		rphone = new ImageIcon("image/rphone.png");

		line = new ImageIcon("image/stline1.png");
		rline = new ImageIcon("image/stline2.png");

		ss1.setIcon(phone);
		ls1.setIcon(phone);
		ls2.setIcon(phone);

		cl.setIcon(cld);
		ch1.setIcon(c1);
		ch2.setIcon(c2);
		ch3.setIcon(c3);

		// line1.setIcon(line);
		// line2.setIcon(str2);
		// line3.setIcon(str3);

		line1.setBounds(305, 190, 50, 150);
		line2.setBounds(455, 180, 50, 250);
		line3.setBounds(575, 230, 50, 200);

		ss1.setBounds(320, 150, 50, 100);

		ls1.setBounds(450, 150, 50, 100);
		ls2.setBounds(580, 150, 50, 100);

		l1.setBounds(360, 180, 150, 50);
		l2.setBounds(500, 180, 150, 50);
		l3.setBounds(625, 180, 150, 50);

		cl.setBounds(150, 50, 700, 500);
		ch1.setBounds(250, 320, 410, 40);
		ch2.setBounds(250, 362, 410, 40);
		ch3.setBounds(250, 405, 410, 40);

		p.add(l1);
		p.add(l2);
		p.add(l3);

		p.add(line1);
		p.add(line2);
		p.add(line3);

		p.add(ss1);
		p.add(ls1);
		p.add(ls2);

		p.add(ch1);
		p.add(ch2);
		p.add(ch3);
		p.add(cl);
		p.add(bp1);
		add(p);

		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);

		int[] ports = new int[] { 5001 };

		for (int i = 0; i < 1; i++) {

			Thread t = new Thread(new PortListener(ports[i]));
			t.start();

		}

	}

	public static void main(String[] args) {

		Router2 r1 = new Router2();
		r1.setTitle("Watch Dog Router2-CoCoWa: A Collaborative Contact-Based Watchdog for Detecting Selfish Nodes");
		r1.setBounds(10, 10, 850, 600);
		r1.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == item2) {
			System.exit(0);
		}
		
		if (e.getSource() == item1) {

			UpdateStatus up = new UpdateStatus();
			up.setTitle("Update Node Status");
			up.setSize(400, 450);
			up.setVisible(true);
			up.setResizable(false);

		}

		if (e.getSource() == item3) {

			ViewAttacker v = new ViewAttacker("FakeSignalAttack");
			v.setSize(450, 330);
			v.setResizable(false);
			v.setVisible(true);

		}
		
		if (e.getSource() == item4) {

			ViewRouter2Status v1 = new ViewRouter2Status();
			v1.setSize(450, 330);
			v1.setResizable(false);
			v1.setVisible(true);

		}
		

	}

	class PortListener implements Runnable {

		int port;

		PortListener(int port) {

			this.port = port;

		}

		public void run() {

			if (this.port == 5001) {

				try {

					ServerSocket server = new ServerSocket(5001);
					Socket socket;

					while (true) {

						socket = server.accept();

						DataInputStream dis = new DataInputStream(socket
								.getInputStream());

						String node = dis.readUTF();
						String fname = dis.readUTF();
						String ct = dis.readUTF();

						java.util.Date td = new java.util.Date();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd/MM/yyyy");
						String dt = sdf.format(td).toString();

						if (node.equals("LSU1")) {

							String sql = "select * from Router2 where Node = '"
									+ node + "' ";
							ResultSet rs = DBConnection.getConnection()
									.createStatement().executeQuery(sql);
							String status = "";

							if (rs.next() == true) {

								status = rs.getString(3);

							}

							if (status.equals("Default")) {

								Thread.sleep(1000);

								line2.setIcon(str2);

								Thread.sleep(1000);

								ls1.setIcon(Router2.this.node);

								Socket skt = new Socket("localhost", 6000);

								DataOutputStream dos = new DataOutputStream(skt
										.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

							} else {

								Thread.sleep(1000);

								line2.setIcon(rstr2);

								Thread.sleep(1000);

								ls1.setIcon(Router2.this.rphone);

								line1.setBounds(305, 210, 50, 200);

								line1.setIcon(str2);

								String attack = "FakeSignalAttack";

								DBConnection.getConnection().createStatement()
										.executeUpdate(
												"insert into Attacker values ('"
														+ attack + "','"
														+ fname + "','" + node
														+ "','" + dt + "') ");

								JOptionPane.showMessageDialog(null,
										"Selfish Atatcker found");

							}

							refresh(5000);

						}

						if (node.equals("LSU2")) {

							String sql = "select * from Router2 where Node = '"
									+ node + "' ";
							ResultSet rs = DBConnection.getConnection()
									.createStatement().executeQuery(sql);
							String status = "";

							if (rs.next() == true) {

								status = rs.getString(3);

							}

							if (status.equals("Default")) {

								Thread.sleep(1000);

								line3.setIcon(str3);

								Thread.sleep(1000);

								ls2.setIcon(Router2.this.node);

								Socket skt = new Socket("localhost", 6003);

								DataOutputStream dos = new DataOutputStream(skt
										.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

							} else {

								Thread.sleep(1000);

								line3.setIcon(rstr3);

								Thread.sleep(1000);

								ls2.setIcon(Router2.this.rphone);

								line1.setBounds(305, 230, 50, 200);

								line1.setIcon(str3);

								String attack = "FakeSignalAttack";

								DBConnection.getConnection().createStatement()
										.executeUpdate(
												"insert into Attacker values ('"
														+ attack + "','"
														+ fname + "','" + node
														+ "','" + dt + "') ");

								JOptionPane.showMessageDialog(null,
										"Selfish Atatcker found");

							}

							refresh(5000);

						}

						if (node.equals("SSU")) {

							Thread.sleep(1000);

							line1.setIcon(line);

							Thread.sleep(1000);

							ss1.setIcon(Router2.this.node);

							Socket skt = new Socket("localhost", 6001);

							DataOutputStream dos = new DataOutputStream(skt
									.getOutputStream());

							dos.writeUTF(fname);
							dos.writeUTF(ct);

							refresh(5000);

						}

					}

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

		}
	}

	public void refresh(long seconds) {

		try {

			Thread.sleep(seconds);

			ss1.setIcon(phone);
			ls1.setIcon(phone);
			ls2.setIcon(phone);

			cl.setIcon(cld);
			ch1.setIcon(c1);
			ch2.setIcon(c2);
			ch3.setIcon(c3);

			line1.setIcon(nul);
			line2.setIcon(nul);
			line3.setIcon(nul);

			line1.setBounds(305, 190, 50, 150);
			line2.setBounds(455, 180, 50, 250);
			line3.setBounds(575, 230, 50, 200);

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
