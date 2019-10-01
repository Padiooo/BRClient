package Client;

import java.util.Observable;

public class Player extends Observable {

	private boolean isAlive = true;

	private int directionX = 0;
	private int directionY = 0;
	@SuppressWarnings("unused")
	private int shoot = 3;

	private int[] ballId = { 1, 1, 1 };

	public void sendChanges(String s) {
		ConnectionHolder.send(s);
	}

	public void move() {
		sendChanges("move " + directionX + " " + directionY);
	}

	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}

	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}

	public void shoot(int x, int y) {
		int id = ballId();
		if (id != -1) {
			BallShoot bs = new BallShoot(this, id);
			bs.start();
			shoot--;

			sendChanges("shoot " + id + " " + x + " " + y);
		}
	}

	public int ballId() {
		int i = 0;
		for (i = 0; i < ballId.length; i++) {
			if (ballId[i] > 0) {
				ballId[i] = -1;
				break;
			}
		}
		if (i == 3) {
			return -1;
		}
		return i;
	}

	public void die() {
		this.isAlive = false;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void reload(int id) {
		shoot++;
		ballId[id] = 1;
		sendChanges("reload " + id);
	}

}
