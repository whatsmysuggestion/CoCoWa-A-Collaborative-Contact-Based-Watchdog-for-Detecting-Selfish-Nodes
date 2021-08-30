import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Router1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final boolean running = true;

	JPanel p = new JPanel();

	ImageIcon cld, ch, rnode, gnode, node, line, rline, nul;

	JLabel cl = new JLabel(), ch1 = new JLabel(), ls1 = new JLabel(),
			ss2 = new JLabel(), ss1 = new JLabel();

	JLabel l1 = new JLabel("Node LSU"), l2 = new JLabel("Node SSU1"), l3 = new JLabel(
			"Node SSU2"), line1 = new JLabel(), line2 = new JLabel(),
			line3 = new JLabel();

	JButton b1 = new JButton("View Attackers");

	int x;	
	
	Router1() {

		p.setLayout(null);
		p.setBackground(Color.ORANGE);

		cld = new ImageIcon("image/area.png");
		ch = new ImageIcon("image/cc1.png");
		node = new ImageIcon("image/phone.png");
		rnode = new ImageIcon("image/rphone.png");
		gnode = new ImageIcon("image/gphone.png");

		line = new ImageIcon("image/stline1.png");
		rline = new ImageIcon("image/stline2.png");
		
		
		ImageIcon bann1 = new ImageIcon(this.getClass().getResource("Router.PNG"));
		JLabel bp1 = new JLabel();
		bp1.setIcon(bann1);
		bp1.setBounds(0, 0, 1000, 100);

		ls1.setIcon(node);
		ss1.setIcon(node);
		ss2.setIcon(node);
		ch1.setIcon(ch);
		cl.setIcon(cld);

		b1.setFont(new Font("times new roman", Font.BOLD, 15));

		// line1.setIcon(line);
		// line2.setIcon(line);
		// line3.setIcon(line);

		line1.setBounds(295, 230, 50, 150);
		line2.setBounds(435, 230, 50, 150);
		line3.setBounds(555, 230, 50, 150);

		ls1.setBounds(285, 170, 50, 100);
		ss1.setBounds(425, 170, 50, 100);
		ss2.setBounds(575, 170, 50, 100);

		l1.setBounds(340, 200, 150, 50);
		l2.setBounds(480, 200, 150, 50);
		l3.setBounds(620, 200, 150, 50);

		b1.setBounds(350, 520, 150, 30);

		cl.setBounds(150, 50, 700, 500);
		ch1.setBounds(250, 340, 402, 100);

		p.add(b1);

		p.add(l1);
		p.add(l2);
		p.add(l3);

		p.add(line1);
		p.add(line2);
		p.add(line3);

		p.add(ls1);
		p.add(ss1);
		p.add(ss2);

		p.add(ch1);
		p.add(cl);
		p.add(bp1);
		add(p);

		setBounds(10, 10, 860, 600);
		setTitle("WatchDog Router1-CoCoWa: A Collaborative Contact-Based Watchdog for Detecting Selfish Nodes");
		setVisible(true);

		b1.addActionListener(this);

		int[] ports = new int[] { 5000 };

		for (int i = 0; i < 1; i++) {

			Thread t = new Thread(new PortListener(ports[i]));
			t.start();
		}
		try {

			while (running) {

				Thread t1 = new Thread();
				Thread t2 = new Thread();
				Thread t3 = new Thread();
				Thread t4 = new Thread();

				x = 0;
				t1.start();
				t2.suspend();
				t3.suspend();
				t4.suspend();

				Thread.sleep(1000);
				x = 1;

				t2.start();
				t1.suspend();
				t3.suspend();
				t4.suspend();

				Thread.sleep(1000);
				x = 2;

				t3.start();
				t1.suspend();
				t2.suspend();
				t4.suspend();

				Thread.sleep(1000);

				x = 3;
				t4.start();
				t1.suspend();
				t2.suspend();
				t3.suspend();

				Thread.sleep(1000);

			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void main(String[] args) {

		new Router1();

	}

	public void refresh(long seconds) {

		try {

			Thread.sleep(seconds);

			ls1.setIcon(node);
			ss1.setIcon(node);
			ss2.setIcon(node);
			ch1.setIcon(ch);
			cl.setIcon(cld);

			line1.setIcon(nul);
			line2.setIcon(nul);
			line3.setIcon(nul);

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {

			ViewAttacker v = new ViewAttacker("SelfishAttack");
			v.setSize(450, 330);
			v.setResizable(false);
			v.setVisible(true);

		}
	}

	class PortListener implements Runnable {

		int port;

		Connection con;

		PortListener(int port) {

			con = DBConnection.getConnection();

			this.port = port;

		}

		@SuppressWarnings("static-access")
		public void run() {

			JOptionPane op = new JOptionPane();

			if (this.port == 5000) {

				try {

					ServerSocket server = new ServerSocket(5000);
					Socket socket;

					while (running) {

						socket = server.accept();
						DataInputStream dis = new DataInputStream(socket
								.getInputStream());

						String node = dis.readUTF();
						String fname = dis.readUTF();
						String ct = dis.readUTF();

						if (x == 0) {

							if (node.equals("LSU")) {

								Thread.sleep(1000);

								line1.setIcon(line);

								Thread.sleep(1000);

								ls1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6000);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								refresh(5000);

							}

							if (node.equals("SSU1")) {

								Thread.sleep(1000);

								line2.setIcon(line);

								Thread.sleep(1000);

								ss1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6001);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								refresh(5000);

							}

							if (node.equals("SSU2")) {

								Thread.sleep(1000);

								line3.setIcon(line);

								Thread.sleep(1000);

								ss2.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6002);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								refresh(5000);

							}

						}

						if (x == 1) {

							if (node.equals("LSU")) {

								Thread.sleep(1000);

								line1.setIcon(line);

								Thread.sleep(1000);

								ls1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6000);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								Thread.sleep(1000);
								line2.setIcon(rline);

								java.util.Date td = new java.util.Date();

								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String dt = sdf.format(td).toString();

								String attack = "SelfishAttack";

								con.createStatement().executeUpdate(
										"insert into Attacker values ('"
												+ attack + "','" + fname
												+ "','" + node + "','" + dt
												+ "') ");

								op.showMessageDialog(null,
										"Selfish Attacker found in Netowrk");

								refresh(5000);

							}

							if (node.equals("SSU1")) {

								Thread.sleep(1000);

								line2.setIcon(line);

								Thread.sleep(1000);

								ss1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6001);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								refresh(5000);

							}

							if (node.equals("SSU2")) {

								Thread.sleep(1000);

								line3.setIcon(line);

								Thread.sleep(1000);

								ss2.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6002);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								line1.setIcon(rline);
								Thread.sleep(1000);

								java.util.Date td = new java.util.Date();

								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String dt = sdf.format(td).toString();

								String attack = "SelfishAttack";

								con.createStatement().executeUpdate(
										"insert into Attacker values ('"
												+ attack + "','" + fname
												+ "','" + node + "','" + dt
												+ "') ");

								op.showMessageDialog(null,
										"Selfish Attacker found in Netowrk");

								refresh(5000);
							}
						}
						if (x == 2) {

							if (node.equals("LSU")) {

								Thread.sleep(1000);

								line1.setIcon(line);

								Thread.sleep(1000);

								ls1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6000);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								refresh(5000);

							}

							if (node.equals("SSU1")) {

								Thread.sleep(1000);

								line2.setIcon(line);

								Thread.sleep(1000);

								ss1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6001);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								line3.setIcon(rline);
								Thread.sleep(1000);

								java.util.Date td = new java.util.Date();
								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String dt = sdf.format(td).toString();

								String attack = "SelfishAttack";

								con.createStatement().executeUpdate(
										"insert into Attacker values ('"
												+ attack + "','" + fname
												+ "','" + node + "','" + dt
												+ "') ");

								op.showMessageDialog(null,
										"Selfish Attacker found in Netowrk");

								refresh(5000);

							}

							if (node.equals("SSU2")) {

								Thread.sleep(1000);

								line3.setIcon(line);

								Thread.sleep(1000);

								ss2.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6002);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								line2.setIcon(rline);
								Thread.sleep(1000);

								java.util.Date td = new java.util.Date();

								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String dt = sdf.format(td).toString();

								String attack = "SelfishAttack";

								con.createStatement().executeUpdate(
										"insert into Attacker values ('"
												+ attack + "','" + fname
												+ "','" + node + "','" + dt
												+ "') ");

								op.showMessageDialog(null,
										"Selfish Attacker found in Netowrk");

								refresh(5000);

							}

						}

						if (x == 3) {

							if (node.equals("LSU")) {

								Thread.sleep(1000);

								line1.setIcon(line);

								Thread.sleep(1000);

								ls1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6000);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								line3.setIcon(rline);
								Thread.sleep(1000);

								java.util.Date td = new java.util.Date();

								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String dt = sdf.format(td).toString();

								String attack = "SelfishAttack";

								con.createStatement().executeUpdate(
										"insert into Attacker values ('"
												+ attack + "','" + fname
												+ "','" + node + "','" + dt
												+ "') ");

								op.showMessageDialog(null,
										"Selfish Attacker found in Netowrk");

								refresh(5000);

							}

							if (node.equals("SSU1")) {

								Thread.sleep(1000);

								line2.setIcon(line);

								Thread.sleep(1000);

								ss1.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6001);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								refresh(5000);

							}

							if (node.equals("SSU2")) {

								Thread.sleep(1000);

								line3.setIcon(line);

								Thread.sleep(1000);

								ss2.setIcon(gnode);

								Socket socket2 = new Socket("localhost", 6002);

								DataOutputStream dos = new DataOutputStream(
										socket2.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

								line1.setIcon(rline);
								Thread.sleep(1000);

								java.util.Date td = new java.util.Date();

								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");
								String dt = sdf.format(td).toString();

								String attack = "SelfishAttack";

								con.createStatement().executeUpdate(
										"insert into Attacker values ('"
												+ attack + "','" + fname
												+ "','" + node + "','" + dt
												+ "') ");

								op.showMessageDialog(null,
										"Selfish Attacker found in Netowrk");

								refresh(5000);

							}

						}

					}

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

		}
	}

}
