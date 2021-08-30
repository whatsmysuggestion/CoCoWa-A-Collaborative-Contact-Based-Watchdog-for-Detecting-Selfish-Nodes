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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Router3 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JPanel p = new JPanel();

	ImageIcon cld, phone, gphone, rphone, ch, list, glist, im;

	JLabel cl = new JLabel(), n1 = new JLabel(), n2 = new JLabel(),
			n3 = new JLabel(), n4 = new JLabel(), ch1 = new JLabel(),
			ch2 = new JLabel(), ch3 = new JLabel(), ch4 = new JLabel(),
			cn1 = new JLabel(), cn2 = new JLabel(), cn3 = new JLabel(),
			cn4 = new JLabel(), cn5 = new JLabel(), cn6 = new JLabel(),
			cn7 = new JLabel(), cn8 = new JLabel(), cn9 = new JLabel(),
			cn10 = new JLabel(), cn11 = new JLabel(), cn12 = new JLabel(),
			cn13 = new JLabel(), cn14 = new JLabel(), cn15 = new JLabel(),
			cn16 = new JLabel(), cn17 = new JLabel(), cn18 = new JLabel(),
			cn19 = new JLabel(), cn20 = new JLabel();

	JLabel l1 = new JLabel("Node LSU1"), l2 = new JLabel("Node LSU2"), l3 = new JLabel(
			"Node SSU1"), l4 = new JLabel("Node SSU2");

	JMenuBar mbr = new JMenuBar();
	JMenu file = new JMenu("File");

	JMenuItem item1 = new JMenuItem("Assign Channels");
	JMenuItem item3 = new JMenuItem("View Attackers");
	JMenuItem item4 = new JMenuItem("View Router3 Nodes Details");
	JMenuItem item2 = new JMenuItem("Exit");

	Router3() {

		p.setLayout(null);
		p.setBackground(Color.PINK);

		setJMenuBar(mbr);

		mbr.add(file);
		file.add(item1);
		file.add(item3);
		file.add(item4);
		file.add(item2);

		cld = new ImageIcon("image/area.png");
		phone = new ImageIcon("image/phone.png");
		rphone = new ImageIcon("image/rphone.png");
		gphone = new ImageIcon("image/gphone.png");
		ch = new ImageIcon("image/channel.png");

		list = new ImageIcon("image/ch.png");
		glist = new ImageIcon("image/glist.png");
		
		
		ImageIcon bann1 = new ImageIcon(this.getClass().getResource("Router.PNG"));
		JLabel bp1 = new JLabel();
		bp1.setIcon(bann1);
		bp1.setBounds(0, 0, 1000, 100);

		cl.setIcon(cld);

		n1.setIcon(phone);
		n2.setIcon(phone);
		n3.setIcon(phone);
		n4.setIcon(phone);

		ch1.setIcon(ch);
		ch2.setIcon(ch);
		ch3.setIcon(ch);
		ch4.setIcon(ch);

		cn1.setBounds(356, 220, 100, 100);
		cn2.setBounds(356, 238, 100, 100);
		cn3.setBounds(356, 256, 100, 100);
		cn4.setBounds(356, 274, 100, 100);
		cn5.setBounds(356, 291, 100, 100);

		cn6.setBounds(516, 220, 100, 100);
		cn7.setBounds(516, 238, 100, 100);
		cn8.setBounds(516, 256, 100, 100);
		cn9.setBounds(516, 274, 100, 100);
		cn10.setBounds(516, 291, 100, 100);

		cn11.setBounds(356, 340, 100, 100);
		cn12.setBounds(356, 358, 100, 100);
		cn13.setBounds(356, 375, 100, 100);
		cn14.setBounds(356, 395, 100, 100);
		cn15.setBounds(356, 410, 100, 100);

		cn16.setBounds(516, 340, 100, 100);
		cn17.setBounds(516, 358, 100, 100);
		cn18.setBounds(516, 375, 100, 100);
		cn19.setBounds(516, 395, 100, 100);
		cn20.setBounds(516, 410, 100, 100);

		n1.setBounds(280, 200, 50, 100);
		n2.setBounds(580, 200, 50, 100);
		n3.setBounds(430, 350, 50, 100);
		n4.setBounds(630, 350, 50, 100);

		l1.setBounds(325, 240, 150, 50);
		l2.setBounds(625, 240, 150, 50);
		l3.setBounds(475, 400, 150, 50);
		l4.setBounds(675, 375, 150, 50);

		ch1.setBounds(355, 230, 35, 150);
		ch2.setBounds(355, 350, 35, 150);

		ch3.setBounds(515, 230, 35, 150);
		ch4.setBounds(515, 350, 35, 150);
		cl.setBounds(150, 50, 800, 600);

		p.add(n1);
		p.add(n2);
		p.add(n3);
		p.add(n4);

		p.add(l1);
		p.add(l2);
		p.add(l3);
		p.add(l4);

		p.add(cn1);
		p.add(cn2);
		p.add(cn3);
		p.add(cn4);
		p.add(cn5);
		p.add(cn6);
		p.add(cn7);
		p.add(cn8);
		p.add(cn9);
		p.add(cn10);

		p.add(cn11);
		p.add(cn12);
		p.add(cn13);
		p.add(cn14);
		p.add(cn15);

		p.add(cn16);
		p.add(cn17);
		p.add(cn18);
		p.add(cn19);
		p.add(cn20);

		p.add(ch1);
		p.add(ch2);
		p.add(ch3);
		p.add(ch4);

		p.add(cl);
		p.add(bp1);
		add(p);

		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);

		int[] ports = new int[] { 5002 };

		for (int i = 0; i < 1; i++) {

			Thread t = new Thread(new PortListener(ports[i]));
			t.start();

		}

	}

	public static void main(String[] args) {

		Router3 r1 = new Router3();
		r1.setTitle("Watch Dog Router3-CoCoWa: A Collaborative Contact-Based Watchdog for Detecting Selfish Nodes");
		r1.setBounds(10, 10, 880, 650);
		r1.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == item1) {

			AssignChannels up = new AssignChannels();
			up.setTitle("Assign Channel");
			up.setSize(400, 450);
			up.setVisible(true);
			up.setResizable(false);

		}
		if (e.getSource() == item2) {
		
			System.exit(0);
		}
		if (e.getSource() == item3) {

			ViewAttacker v = new ViewAttacker("Pre-Occupied");
			v.setSize(450, 330);
			v.setResizable(false);
			v.setVisible(true);

		}
		
		if (e.getSource() == item4) {

			ViewRouter3Status v1 = new ViewRouter3Status();
			v1.setSize(450, 330);
			v1.setResizable(false);
			v1.setVisible(true);

		}

	}

	public void refresh(long seconds) {

		try {

			sleep(seconds);

			cl.setIcon(cld);

			n1.setIcon(phone);
			n2.setIcon(phone);
			n3.setIcon(phone);
			n4.setIcon(phone);

			ch1.setIcon(ch);
			ch2.setIcon(ch);
			ch3.setIcon(ch);
			ch4.setIcon(ch);

			cn1.setIcon(im);
			cn2.setIcon(im);
			cn3.setIcon(im);
			cn4.setIcon(im);
			cn5.setIcon(im);
			cn6.setIcon(im);
			cn7.setIcon(im);
			cn8.setIcon(im);
			cn9.setIcon(im);
			cn10.setIcon(im);
			cn11.setIcon(im);
			cn12.setIcon(im);
			cn13.setIcon(im);
			cn14.setIcon(im);
			cn15.setIcon(im);
			cn16.setIcon(im);
			cn17.setIcon(im);
			cn18.setIcon(im);
			cn19.setIcon(im);
			cn20.setIcon(im);

		} catch (Exception ex) {

			System.out.println(ex.getLocalizedMessage());

		}

	}

	class PortListener implements Runnable {

		int port;

		PortListener(int port) {
			this.port = port;
		}

		public void run() {

			if (this.port == 5002) {

				try {

					ServerSocket server = new ServerSocket(5002);
					Socket socket;

					while (true) {

						socket = server.accept();

						DataInputStream dis = new DataInputStream(socket
								.getInputStream());

						String node = dis.readUTF();
						String fname = dis.readUTF();
						String ct = dis.readUTF();

						if (node.equals("LSU1")) {

							String sql = "select * from Router3 where Node = '"
									+ node + "'  ";

							int count = 0;

							ResultSet rs = DBConnection.getConnection()
									.createStatement().executeQuery(sql);

							while (rs.next() == true) {

								count++;

							}

							if (count == 0) {

								JOptionPane
										.showConfirmDialog(null,
												"There is No Channel Occuppied for this Node ");

							}
							if (count == 1) {

								ResultSet set = DBConnection.getConnection()
										.createStatement().executeQuery(
												"select * from Router3 where Node = '"
														+ node + "' ");

								String chan = "";

								if (set.next() == true) {

									chan = set.getString(2);

								}

								if (chan.equals("C1")) {

									sleep(1000);

									cn1.setIcon(glist);

									Thread.sleep(1000);

									n1.setIcon(gphone);

								}

								if (chan.equals("C2")) {

									sleep(1000);

									cn2.setIcon(glist);

									Thread.sleep(1000);

									n1.setIcon(gphone);

								}

								if (chan.equals("C3")) {

									sleep(1000);

									cn3.setIcon(glist);

									Thread.sleep(1000);

									n1.setIcon(gphone);

								}

								if (chan.equals("C4")) {

									sleep(1000);

									cn4.setIcon(glist);

									Thread.sleep(1000);

									n1.setIcon(gphone);

								}

								if (chan.equals("C5")) {

									sleep(1000);

									cn5.setIcon(glist);

									Thread.sleep(1000);

									n1.setIcon(gphone);

								}

								Socket skt = new Socket("localhost", 6000);
								DataOutputStream dos = new DataOutputStream(skt
										.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

							}

							else {

								java.util.Date td = new java.util.Date();
								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");

								String dt = sdf.format(td).toString();

								String attack = "Pre-Occupied";

								DBConnection.getConnection().createStatement()
										.executeUpdate(
												"insert into Attacker values ('"
														+ attack + "','"
														+ fname + "','" + node
														+ "','" + dt + "') ");

								sleep(1000);

								n1.setIcon(rphone);

								JOptionPane.showMessageDialog(null,
										"Channel pre-occupation selfish attacker Found in Node '"
												+ node + "'  ");

							}

							refresh(5000);

						}

						if (node.equals("LSU2")) {

							String sql = "select * from Router3 where Node = '"
									+ node + "'  ";

							int count = 0;

							ResultSet rs = DBConnection.getConnection()
									.createStatement().executeQuery(sql);

							while (rs.next() == true) {

								count++;

							}

							System.out.println(count);

							if (count == 0) {

								JOptionPane
										.showConfirmDialog(null,
												"There is No Channel Occuppied for this Node ");

							}
							if (count == 1) {

								ResultSet set = DBConnection.getConnection()
										.createStatement().executeQuery(
												"select * from Router3 where Node = '"
														+ node + "' ");

								String chan = "";

								if (set.next() == true) {

									chan = set.getString(2);

								}

								if (chan.equals("C1")) {

									sleep(1000);

									cn6.setIcon(glist);

									Thread.sleep(1000);

									n2.setIcon(gphone);

								}

								if (chan.equals("C2")) {

									sleep(1000);

									cn7.setIcon(glist);

									Thread.sleep(1000);

									n2.setIcon(gphone);

								}

								if (chan.equals("C3")) {

									sleep(1000);

									cn8.setIcon(glist);

									Thread.sleep(1000);

									n2.setIcon(gphone);

								}

								if (chan.equals("C4")) {

									sleep(1000);

									cn9.setIcon(glist);

									Thread.sleep(1000);

									n2.setIcon(gphone);

								}

								if (chan.equals("C5")) {

									sleep(1000);

									cn10.setIcon(glist);

									Thread.sleep(1000);

									n2.setIcon(gphone);

								}

								Socket skt = new Socket("localhost", 6003);
								DataOutputStream dos = new DataOutputStream(skt
										.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

							}

							else {

								java.util.Date td = new java.util.Date();
								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");

								String dt = sdf.format(td).toString();

								String attack = "Pre-Occupied";

								DBConnection.getConnection().createStatement()
										.executeUpdate(
												"insert into Attacker values ('"
														+ attack + "','"
														+ fname + "','" + node
														+ "','" + dt + "') ");

								sleep(1000);

								n2.setIcon(rphone);

								JOptionPane.showMessageDialog(null,
										"Channel pre-occupation selfish attacker Found in Node '"
												+ node + "'  ");

							}

							refresh(5000);

						}

						if (node.equals("SSU1")) {

							String sql = "select * from Router3 where Node = '"
									+ node + "'  ";

							int count = 0;

							ResultSet rs = DBConnection.getConnection()
									.createStatement().executeQuery(sql);

							while (rs.next() == true) {

								count++;

							}

							System.out.println(count);

							if (count == 0) {

								JOptionPane
										.showConfirmDialog(null,
												"There is No Channel Occuppied for this Node ");

							}
							if (count == 1) {

								ResultSet set = DBConnection.getConnection()
										.createStatement().executeQuery(
												"select * from Router3 where Node = '"
														+ node + "' ");

								String chan = "";

								if (set.next() == true) {

									chan = set.getString(2);

								}

								if (chan.equals("C1")) {

									sleep(1000);

									cn11.setIcon(glist);

									Thread.sleep(1000);

									n3.setIcon(gphone);

								}

								if (chan.equals("C2")) {

									sleep(1000);

									cn12.setIcon(glist);

									Thread.sleep(1000);

									n3.setIcon(gphone);

								}

								if (chan.equals("C3")) {

									sleep(1000);

									cn13.setIcon(glist);

									Thread.sleep(1000);

									n3.setIcon(gphone);

								}

								if (chan.equals("C4")) {

									sleep(1000);

									cn14.setIcon(glist);

									Thread.sleep(1000);

									n3.setIcon(gphone);

								}

								if (chan.equals("C5")) {

									sleep(1000);

									cn15.setIcon(glist);

									Thread.sleep(1000);

									n3.setIcon(gphone);

								}

								Socket skt = new Socket("localhost", 6001);
								DataOutputStream dos = new DataOutputStream(skt
										.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

							}

							else {

								java.util.Date td = new java.util.Date();
								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");

								String dt = sdf.format(td).toString();

								String attack = "Pre-Occupied";

								DBConnection.getConnection().createStatement()
										.executeUpdate(
												"insert into Attacker values ('"
														+ attack + "','"
														+ fname + "','" + node
														+ "','" + dt + "') ");

								sleep(1000);

								n3.setIcon(rphone);

								JOptionPane.showMessageDialog(null,
										"Channel pre-occupation selfish attacker Found in Node '"
												+ node + "'  ");

							}

							refresh(5000);

						}

						if (node.equals("SSU2")) {

							String sql = "select * from Router3 where Node = '"
									+ node + "'  ";

							int count = 0;

							ResultSet rs = DBConnection.getConnection()
									.createStatement().executeQuery(sql);

							while (rs.next() == true) {

								count++;

							}

							System.out.println(count);

							if (count == 0) {

								JOptionPane
										.showConfirmDialog(null,
												"There is No Channel Occuppied for this Node ");

							}

							if (count == 1) {

								ResultSet set = DBConnection.getConnection()
										.createStatement().executeQuery(
												"select * from Router3 where Node = '"
														+ node + "' ");

								String chan = "";

								if (set.next() == true) {

									chan = set.getString(2);

								}

								if (chan.equals("C1")) {

									sleep(1000);

									cn16.setIcon(glist);

									Thread.sleep(1000);

									n4.setIcon(gphone);

								}

								if (chan.equals("C2")) {

									sleep(1000);

									cn17.setIcon(glist);

									Thread.sleep(1000);

									n4.setIcon(gphone);

								}

								if (chan.equals("C3")) {

									sleep(1000);

									cn18.setIcon(glist);

									Thread.sleep(1000);

									n4.setIcon(gphone);

								}

								if (chan.equals("C4")) {

									sleep(1000);

									cn19.setIcon(glist);

									Thread.sleep(1000);

									n4.setIcon(gphone);

								}

								if (chan.equals("C5")) {

									sleep(1000);

									cn20.setIcon(glist);

									Thread.sleep(1000);

									n4.setIcon(gphone);

								}

								Socket skt = new Socket("localhost", 6002);
								DataOutputStream dos = new DataOutputStream(skt
										.getOutputStream());

								dos.writeUTF(fname);
								dos.writeUTF(ct);

							}

							else {

								java.util.Date td = new java.util.Date();
								SimpleDateFormat sdf = new SimpleDateFormat(
										"dd/MM/yyyy");

								String dt = sdf.format(td).toString();

								String attack = "Pre-Occupied";

								DBConnection.getConnection().createStatement()
										.executeUpdate(
												"insert into Attacker values ('"
														+ attack + "','"
														+ fname + "','" + node
														+ "','" + dt + "') ");

								sleep(1000);

								n4.setIcon(rphone);

								JOptionPane.showMessageDialog(null,
										"Channel pre-occupation selfish attacker Found in Node '"
												+ node + "'  ");

							}

						}
						refresh(5000);
					}

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

		}
	}

	public void sleep(long seconds) {

		try {

			Thread.sleep(seconds);

		} catch (Exception ex) {

			System.out.println(ex);

		}

	}

	class AssignChannels extends JFrame implements ActionListener {

		private static final long serialVersionUID = 2L;

		public static final boolean running = true;

		JPanel p = new JPanel();

		JComboBox cb = new JComboBox();
		JComboBox cb1 = new JComboBox();

		JLabel l1 = new JLabel("Node"), l2 = new JLabel("Status"),
				l3 = new JLabel("");
		JTextField t1 = new JTextField();

		JButton b1 = new JButton("Update"), b2 = new JButton("Cancel");

		AssignChannels() {

			p.setLayout(null);
			p.setBackground(Color.lightGray);

			cb.addItem("Select Node");
			cb.addItem("SSU1");
			cb.addItem("SSU2");
			cb.addItem("LSU1");
			cb.addItem("LSU2");

			cb1.addItem("C1");
			cb1.addItem("C2");
			cb1.addItem("C3");
			cb1.addItem("C4");
			cb1.addItem("C5");

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

					String avail = "Yes";

					String sql = "insert into Router3 values('" + node + "','"
							+ status + "','" + avail + "' )";

					DBConnection.getConnection().createStatement()
							.executeUpdate(sql);

					if (node.equals("LSU1")) {

						if (status.equals("C1")) {

							cn1.setIcon(list);

						}

						if (status.equals("C2")) {

							cn2.setIcon(list);

						}

						if (status.equals("C3")) {
							cn3.setIcon(list);

						}

						if (status.equals("C4")) {

							cn4.setIcon(list);

						}

						if (status.equals("C5")) {

							cn5.setIcon(list);

						}

					}

					if (node.equals("LSU2")) {

						if (status.equals("C1")) {
							cn6.setIcon(list);
						}

						if (status.equals("C2")) {
							cn7.setIcon(list);
						}

						if (status.equals("C3")) {
							cn8.setIcon(list);
						}

						if (status.equals("C4")) {
							cn9.setIcon(list);
						}

						if (status.equals("C5")) {
							cn10.setIcon(list);
						}

					}

					if (node.equals("SSU1")) {

						if (status.equals("C1")) {
							cn11.setIcon(list);
						}

						if (status.equals("C2")) {
							cn12.setIcon(list);
						}

						if (status.equals("C3")) {
							cn13.setIcon(list);
						}

						if (status.equals("C4")) {
							cn14.setIcon(list);
						}

						if (status.equals("C5")) {
							cn15.setIcon(list);
						}

					}

					if (node.equals("SSU2")) {

						if (status.equals("C1")) {
							cn16.setIcon(list);
						}

						if (status.equals("C2")) {
							cn17.setIcon(list);
						}

						if (status.equals("C3")) {
							cn18.setIcon(list);
						}

						if (status.equals("C4")) {
							cn19.setIcon(list);
						}

						if (status.equals("C5")) {
							cn20.setIcon(list);
						}

					}

					JOptionPane.showMessageDialog(null, "Channels Assigned");

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

			if (e.getSource() == b2) {

				dispose();

			}

		}

	}
}
