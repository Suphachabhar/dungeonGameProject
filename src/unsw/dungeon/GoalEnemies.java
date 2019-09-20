package unsw.dungeon;

public class GoalEnemies implements Goal {
	
	
	private boolean win;
	
	public GoalEnemies() {
		super();
		this.win = false;
	}
	/**
	 * @return the win
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * @param set win
	 */
	public void setWin(boolean win) {
		this.win = win;
	}

}
