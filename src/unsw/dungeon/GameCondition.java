package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

//composite pattern
public class GameCondition implements Goal {
	
	private List<String> condition;
	private List<Goal> goals;
	private List<Goal> orGoals;
	private boolean win;
	
	public GameCondition() {
		super();
		this.condition = new ArrayList<String>();
		this.goals = new ArrayList<Goal>();
		this.orGoals = new ArrayList<Goal>();
		this.win = false;
	}
	/**
	 * add condition to goal
	 * @param goal
	 */
	public void addCondition(String goal)	{
		condition.add(goal);
	}
	
	/**
	 * add condition to goal
	 * @param goal
	 */
	public void addGoal(Goal goal)	{
		goals.add(goal);
	}

	/**
	 * @return the win
	 */
	public boolean isWin() {						
		return win;
	}
	
	/**
	 * change win status
	 */
	public void checkWin() {
		for (Goal i : goals) {
			if (i.isWin()) {				
			}
			else {
				return;
			}
		}
		for (Goal i : orGoals) {
			if (i.isWin()) {
				setWin(true);
			}
		}
	}

	/**
	 * @param set win
	 */
	public void setWin(boolean win) {
		this.win = win;
	}
	
	/**
	 * find subgoal through string comparison
	 * @param goal
	 * @return
	 */
	public boolean searchSubGoal(String goal) {
		for (String i : condition) {
			if (i.equals(goal)) {
				return true;
			}
		}
		return false;
	}
}
