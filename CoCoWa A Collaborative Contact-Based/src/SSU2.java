import java.awt.Color;
import java.awt.Font;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SSU2 extends JFrame {

	private static final long serialVersionUID = 1L;

	JPanel p = new JPanel();

	JTextArea ta = new JTextArea();
	JScrollPane pane = new JScrollPane();

	SSU2() {

		p.setLayout(null);
		p.setBackground(Color.CYAN);

		ta.setRows(10);
		ta.setColumns(10);
		ta.setFont(new Font("times new roman", Font.BOLD, 15));
		pane.setViewportView(ta);

		pane.setBounds(50, 250, 400, 300);
		
		ImageIcon bann1 = new ImageIcon(this.getClass().getResource("EndUser.PNG"));
		JLabel bp1 = new JLabel();
		bp1.setIcon(bann1);
		bp1.setBounds(0, 20, 1000, 200);

		p.add(pane);
		p.add(bp1);
		
		add(p);

		int[] ports = new int[] { 6002 };

		for (int i = 0; i < 1; i++) {

			Thread t = new Thread(new PortListener(ports[i]));
			t.start();

		}
	}

	public static void main(String[] args) {

		SSU2 l = new SSU2();
		l.setSize(500, 650);
		l.setTitle("Node SSU-2::CoCoWa: A Collaborative Contact-Based Watchdog for Detecting Selfish Nodes");
		l.setVisible(true);

	}

	class PortListener implements Runnable {

		int port;

		PortListener(int port) {
			this.port = port;
		}

		public void run() {

			if (this.port == 6002) {

				try {

					ServerSocket server = new ServerSocket(6002);
					Socket socket;

					while (true) {

						socket = server.accept();
						DataInputStream dis = new DataInputStream(socket
								.getInputStream());
						@SuppressWarnings("unused")
						String fname = dis.readUTF();
						String ct = dis.readUTF();

						ta.setText(ct);

					}

				} catch (Exception ex) {
					System.out.println(ex);
				}

			}

		}

	}

}
