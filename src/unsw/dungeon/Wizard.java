package unsw.dungeon;

import java.util.Random;

public class Wizard extends Enemy {

	private int max = 4;
	private int min = -4;

	public Wizard(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
	}
	/**
	 * make the witzard move toward player
	 */
	public void moveTowardsPlayer() {
		Random random = new Random();
		int new_x = random.nextInt(max - min) + min + getX();
		int new_y = random.nextInt(max - min) + min + getY();

		while (!dungeon.canMove(new_x, new_y)) {
			new_x = random.nextInt(max - min) + min + getX();
			new_y = random.nextInt(max - min) + min + getY();
		}
		y().set(new_y);
		x().set(new_x);
	}

}