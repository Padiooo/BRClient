package Client;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Color blank = Color.GRAY;
	private Color color_player = Color.WHITE;
	private Color color_ennemy = Color.BLACK;

	private int[][][] data;
	private int[] players_id;

	private int id_player;
	private int size_player;
	private int size_ball;
	
	private int windowX;
	private int windowY;

	public Panel(int id_player, int size_player, int size_ball, int x, int y) {
		this.id_player = id_player;
		this.size_player = size_player;
		this.size_ball = size_ball;
		
		this.windowX = x;
		this.windowY = y;
		
		JButton ready = new JButton();
		ready.setText("Ready !");
		this.setLayout(null);
		this.add(ready);
	}

	public void paintComponent(Graphics g) {

		g.clearRect(0, 0, windowX, windowY);
		g.setColor(blank);
		g.fillRect(0, 0, windowX, windowY);

		if (data != null) {
			for (int id_player = 0; id_player < data.length; id_player++) {
				if (this.id_player != players_id[id_player]) {
					g.setColor(color_ennemy);
				} else {
					g.setColor(color_player);
				}
				for (int id_player_ball = 0; id_player_ball < data[0].length; id_player_ball++) {
					if(data[id_player][id_player_ball][0] != -99) {
						if (id_player_ball != 0) {
							//@formatter:off
							g.fillOval(data[id_player][id_player_ball][0] - size_ball / 2, data[id_player][id_player_ball][1] - size_ball / 2, size_ball, size_ball);
							//@formatter:on
						} else {
							//@formatter:off
							g.fillOval(data[id_player][id_player_ball][0] - size_player / 2, data[id_player][id_player_ball][1] - size_player / 2, size_player, size_player);
							//@formatter:on
						}
					}
				}
			}
		}
	}

	public void setData(int[][][] data) {
		this.data = data;
	}
	public void setId(int[] players_id) {
		this.players_id = players_id;
	}

}
