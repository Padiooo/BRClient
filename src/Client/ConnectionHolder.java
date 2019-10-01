package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ConnectionHolder extends Thread {

	private Socket socket;
	private static BufferedWriter os;
	private BufferedReader is;

	private static Player player;
	private Panel pan;
	private String delims = "[ ]+";

	private static boolean isGameStarted = false;

	public ConnectionHolder(Socket socket) {
		try {
			this.socket = socket;
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Window win = new Window(0, 50, 70);
		ReadyPanel rp = new ReadyPanel(win);
		win.setContentPane(rp);
	}

	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("o");
				String data = is.readLine();
				if (data.compareTo("") != 0) {
					updates(data);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updates(String data) {
		System.out.println(data);
		String[] command = data.split(delims);
		switch (command[0]) {
		case "SETUP":
			createPlayer(command);
			break;
		case "START":
			start();
			break;
		case "DIE":
			killPlayer();
			break;
		default:
			//updatePanel(command);
			break;
		}
	}

	public void killPlayer() {
		player.die();
	}

	public void start() {
		isGameStarted = true;
	}

	public void createPlayer(String[] data) {
		int id = Integer.valueOf(data[1]);
		int rate = Integer.valueOf(data[2]);

		int windowX = Integer.valueOf(data[3]);
		int windowY = Integer.valueOf(data[4]);

		int size_player = Integer.valueOf(data[5]);
		int size_ball = Integer.valueOf(data[6]);

		player = new Player();
		new Clock(rate, player).start();

		Window win = new Window(id, windowX, windowY);
		pan = new Panel(id, size_player, size_ball, windowX, windowY);
		win.setContentPane(pan);
		win.addKeyListener(new KeyboardListener(player));
		win.addMouseListener(new MousePlayerListener(player));
	}

	public void updatePanel(String[] data) {
		// System.out.println("|" + data + "|");
		int rows = data.length / 9;
		int[] players_id = new int[rows];
		int[][][] coord_data = new int[rows][4][2];

		int shift = 0;
		int k = 0;

		for (int i = 0; i < rows; i++) {
			players_id[i] = Integer.valueOf(data[shift]);
			for (int j = 1; j < 9; j += 2) {
				coord_data[i][k][0] = Integer.valueOf(data[j + shift]);
				coord_data[i][k][1] = Integer.valueOf(data[j + 1 + shift]);
				k++;
			}
			k = 0;
			shift += 9;
		}

		pan.setId(players_id);
		pan.setData(coord_data);
		pan.repaint();
	}

	public static void send(String s) {
		if (player.isAlive() && isGameStarted) {
			try {
				os.write(s);
				os.newLine();
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
