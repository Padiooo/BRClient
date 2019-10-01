package Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private Player player;

	public KeyboardListener(Player player) {
		this.player = player;
	}

	//@formatter:off
	@Override
	public void keyTyped(KeyEvent e) {}
	//@formatter:on

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'z':
			player.setDirectionY(-1);
			break;
		case 'q':
			player.setDirectionX(-1);
			break;
		case 's':
			player.setDirectionY(1);
			break;
		case 'd':
			player.setDirectionX(1);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		char c = e.getKeyChar();

		if (c == 'z' || c == 's') {
			player.setDirectionY(0);
		}
		if (c == 'q' || c == 'd') {
			player.setDirectionX(0);
		}

	}

}
