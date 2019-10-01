package Client;

import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	public Window(int id, int x, int y) {

		setTitle("Client " + id);

		setSize(x, y);

		setLocationRelativeTo(null);

		setResizable(false);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setBackground(Color.GRAY);

		setVisible(true);
	}

	public void close() {
		setVisible(false);
		dispose();
	}
}
