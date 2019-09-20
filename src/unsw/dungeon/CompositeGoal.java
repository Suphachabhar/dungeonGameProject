package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class CompositeGoal implements Goal {

	private List<Goal> subGoals;
	private String condition;

	public CompositeGoal(String condition) {
		this.subGoals = new ArrayList<Goal>();
		this.condition = condition;
	}

	@Override
	public boolean isWin() {
		// TODO Auto-generated method stub
		if (condition == "AND") {
			for (Goal g : subGoals) {
				if (!g.isWin())
					return false;
			}
			return true;

		} else if (condition == "OR") {
			for (Goal g : subGoals) {
				if (g.isWin())
					return true;
			}

			return false;
		}
		return false;
	}

	@Override
	public void setWin(boolean win) {
		// TODO Auto-generated method stub

	}
	/**
	 * add sub goal
	 * @param subGoal
	 */
	public void addSubGoal(Goal subGoal) {
		this.subGoals.add(subGoal);
	}
	/**
	 * check the goal
	 * @param goal
	 * @return
	 */
	public boolean hasGoal(String goal) {
		for (Goal g : subGoals) {
			if (g instanceof GoalBoulder && goal == "Boulder") {
				return true;
			} else if (g instanceof GoalEnemies && goal == "Enemy") {
				return true;
			} else if (g instanceof GoalExit && goal == "Exit") {
				return true;
			} else if (g instanceof GoalTreasure && goal == "Treasure") {
				return true;
			} else if (g instanceof CompositeGoal) {
				if (((CompositeGoal) g).hasGoal(goal)) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * check the sub goal win 
	 * @param goal
	 * @return
	 */
	public boolean isSubGoalWin(String goal) {
		for (Goal g : subGoals) {
			if (g instanceof GoalBoulder && goal == "Boulder") {
				return g.isWin();
			} else if (g instanceof GoalEnemies && goal == "Enemy") {
				return g.isWin();
			} else if (g instanceof GoalExit && goal == "Exit") {
				return g.isWin();
			} else if (g instanceof GoalTreasure && goal == "Treasure") {
				return g.isWin();
			} else if (g instanceof CompositeGoal) {
				if (((CompositeGoal) g).hasGoal(goal)) {
					return ((CompositeGoal) g).isSubGoalWin(goal);
				}
			}
		}
		return false;
	}
	/**
	 * finish the boulder goal
	 */
	public void finishBoulderGoal() {
		for (Goal g : subGoals) {
			if (g instanceof GoalBoulder) {
				g.setWin(true);
			} else if (g instanceof CompositeGoal) {
				((CompositeGoal) g).finishBoulderGoal();
			}
		}
	}
	/**
	 * finish the enemy goal
	 */
	public void finishEnemyGoal() {
		for (Goal g : subGoals) {
			if (g instanceof GoalEnemies) {
				g.setWin(true);
			} else if (g instanceof CompositeGoal) {
				((CompositeGoal) g).finishEnemyGoal();
			}
		}
	}
	/**
	 * finish the exit goal
	 */
	public void finishExitGoal() {
		for (Goal g : subGoals) {
			if (g instanceof GoalExit) {
				g.setWin(true);
			} else if (g instanceof CompositeGoal) {
				((CompositeGoal) g).finishExitGoal();
			}
		}
	}
	/**
	 * finish the exit goal
	 */
	public void unFinishExitGoal() {
		for (Goal g : subGoals) {
			if (g instanceof GoalExit) {
				g.setWin(false);
			} else if (g instanceof CompositeGoal) {
				((CompositeGoal) g).unFinishExitGoal();
			}
		}
	}
	/**
	 * finish the exit goal
	 */
	public void unFinishBoulderGoal() {
		for (Goal g : subGoals) {
			if (g instanceof GoalBoulder) {
				g.setWin(false);
			} else if (g instanceof CompositeGoal) {
				((CompositeGoal) g).unFinishBoulderGoal();
			}
		}
	}
	
	
	/**
	 * finish the treasure goal
	 */
	public void finishTreasureGoal() {
		for (Goal g : subGoals) {
			if (g instanceof GoalTreasure) {
				g.setWin(true);
			} else if (g instanceof CompositeGoal) {
				((CompositeGoal) g).finishTreasureGoal();
			}
		}
	}

}