package unsw.dungeon;

public class Enemy extends Entity {

	protected Dungeon dungeon;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	/**
	 * check the distance between x1y1 and x2y2
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private int manhattanDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	/**
	 * the enemy moving toward player
	 */
	public void moveTowardsPlayer() {
		Player player = dungeon.getPlayer();

		int[] diffMoves = new int[5];
		diffMoves[0] = manhattanDistance(player.getX(), player.getY(), this.getX(), this.getY() - 1);
		diffMoves[1] = manhattanDistance(player.getX(), player.getY(), this.getX(), this.getY() + 1);
		diffMoves[2] = manhattanDistance(player.getX(), player.getY(), this.getX() - 1, this.getY());
		diffMoves[3] = manhattanDistance(player.getX(), player.getY(), this.getX() + 1, this.getY());
		diffMoves[4] = manhattanDistance(player.getX(), player.getY(), this.getX(), this.getY());

		boolean[] canMoves = new boolean[5];
		canMoves[0] = dungeon.canMove(this.getX(), this.getY() - 1);
		canMoves[1] = dungeon.canMove(this.getX(), this.getY() + 1);
		canMoves[2] = dungeon.canMove(this.getX() - 1, this.getY());
		canMoves[3] = dungeon.canMove(this.getX() + 1, this.getY());
		canMoves[4] = true;

		for (int i = 0; i < 4; i++) {
			int minMove = diffMoves[0];
			int idxMove = 0;
			for (int j = 0; j < 4; j++) {
				if (diffMoves[j] < minMove) {
					minMove = diffMoves[j];
					idxMove = j;
				}
			}
			if (canMoves[idxMove]) {
				switch (idxMove) {
				case 0:
					moveUp();
					break;
				case 1:
					moveDown();
					break;
				case 2:
					moveLeft();
					break;
				case 3:
					moveRight();

					break;
				}
				return;
			} else {
				diffMoves[idxMove] = Integer.MAX_VALUE;
			}
		}
	}

	/**
	 * the enemy moving away from player when the player gets the invincible potion
	 */
	public void moveAwayPlayer() {
		Player player = dungeon.getPlayer();

		int[] diffMoves = new int[5];
		diffMoves[0] = manhattanDistance(player.getX(), player.getY(), this.getX(), this.getY() - 1);
		diffMoves[1] = manhattanDistance(player.getX(), player.getY(), this.getX(), this.getY() + 1);
		diffMoves[2] = manhattanDistance(player.getX(), player.getY(), this.getX() - 1, this.getY());
		diffMoves[3] = manhattanDistance(player.getX(), player.getY(), this.getX() + 1, this.getY());
		diffMoves[4] = manhattanDistance(player.getX(), player.getY(), this.getX(), this.getY());

		boolean[] canMoves = new boolean[5];
		canMoves[0] = dungeon.canMove(this.getX(), this.getY() - 1);
		canMoves[1] = dungeon.canMove(this.getX(), this.getY() + 1);
		canMoves[2] = dungeon.canMove(this.getX() - 1, this.getY());
		canMoves[3] = dungeon.canMove(this.getX() + 1, this.getY());
		canMoves[4] = true;

		for (int i = 0; i < 4; i++) {
			int maxMove = diffMoves[0];
			int idxMove = 0;
			for (int j = 0; j < 4; j++) {
				if (diffMoves[j] > maxMove) {
					maxMove = diffMoves[j];
					idxMove = j;
				}
			}
			if (canMoves[idxMove]) {
				switch (idxMove) {
				case 0:
					moveUp();
					break;
				case 1:

					moveDown();
					break;
				case 2:
					moveLeft();
					break;
				case 3:
					moveRight();

					break;
				}
				return;
			} else {
				diffMoves[idxMove] = Integer.MIN_VALUE;
			}
		}
	}

	/**
	 * the enemy moves upward
	 */
	public void moveUp() {
		Wall wall = new Wall(0, 0);
		Boulder boulder = new Boulder(dungeon, 0, 0);
		Door door = new Door(dungeon, 0, 0, 0);

		if (dungeon.checkEntity(door, getX(), getY() - 1)) {
			return;
		}
		if (getY() > 0 && dungeon.checkEntity(wall, getX(), getY() - 1) == false) {
			if (dungeon.checkEntity(boulder, getX(), getY() - 1)) {
				return;
			}
			// move
			y().set(getY() - 1);
		}
	}

	/**
	 * the enemy moves downward
	 */
	public void moveDown() {
		Wall wall = new Wall(0, 0);
		Boulder boulder = new Boulder(dungeon, 0, 0);
		Door door = new Door(dungeon, 0, 0, 0);

		if (dungeon.checkEntity(door, getX(), getY() + 1)) {
			return;
		}

		if (getY() < dungeon.getHeight() - 1 && dungeon.checkEntity(wall, getX(), getY() + 1) == false) {
			if (dungeon.checkEntity(boulder, getX(), getY() + 1)) {
				return;
			}
			// move
			y().set(getY() + 1);
		}
	}

	/**
	 * the enemy moves to the left
	 */
	public void moveLeft() {
		Wall wall = new Wall(0, 0);
		Boulder boulder = new Boulder(dungeon, 0, 0);
		Door door = new Door(dungeon, 0, 0, 0);

		if (dungeon.checkEntity(door, getX() - 1, getY())) {
			return;
		}

		if (getX() > 0 && dungeon.checkEntity(wall, getX() - 1, getY()) == false) {

			if (dungeon.checkEntity(boulder, getX() - 1, getY())) {
				return;
			}
			// move
			x().set(getX() - 1);
		}
	}

	/**
	 * the enemy moves to the right
	 */
	public void moveRight() {
		Wall wall = new Wall(0, 0);
		Boulder boulder = new Boulder(dungeon, 0, 0);
		Door door = new Door(dungeon, 0, 0, 0);

		if (dungeon.checkEntity(door, getX() + 1, getY())) {
			return;
		}

		if (getX() < dungeon.getWidth() - 1 && dungeon.checkEntity(wall, getX() + 1, getY()) == false) {
			if (dungeon.checkEntity(boulder, getX() + 1, getY())) {
				return;
			}
			// move
			x().set(getX() + 1);
		}
	}
}
