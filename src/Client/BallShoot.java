package Client;

public class BallShoot extends Thread {

	private Player player = null;
	private int id;

	public BallShoot(Player player, int id) {
		this.player = player;
		this.id = id;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		player.reload(id);
	}

}
