package unsw.dungeon;

public class GoalExit implements Goal {
	
	
	private boolean win;
	
	public GoalExit() {
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
