package unsw.dungeon;

public class GoalBoulder implements Goal {
	

	private boolean win;
	
	
	public GoalBoulder() {
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
