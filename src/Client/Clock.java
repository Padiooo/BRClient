package Client;

public class Clock extends Thread {

	private int millis = 0;
	private Player player;

	private boolean exit = false;

	public Clock(int millis, Player player) {
		this.millis = millis;
		this.player = player;
	}

	@Override
	public void run() {
		while (player.isAlive()) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			player.move();
		}
	}
	
}
