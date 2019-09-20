/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements Observer {

	private int width, height;
	private ObservableList<Entity> entities;
	private List<Switch> switches;
	private Player player;
	private List<BombDropped> bombsDropped;
	private List<Enemy> enemies;
	private IntegerProperty scores;
	private CompositeGoal mainGoal;
	private BooleanProperty finish;

	@FXML
	public GridPane squares;

	public Dungeon(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = FXCollections.observableArrayList();
		this.player = null;
		this.switches = new ArrayList<Switch>();
		this.bombsDropped = new ArrayList<BombDropped>();
		this.enemies = new ArrayList<Enemy>();
		this.scores = new SimpleIntegerProperty(0);
		this.mainGoal = new CompositeGoal("AND");
		this.finish = new SimpleBooleanProperty(false);
	}

	/**
	 * check the available position for the movement of enemy
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canMove(int x, int y) {
		if (x < 0 || y < 0 || x >= this.width || y >= this.height)
			return false;
		for (Entity entity : getEntities()) {
			if ((entity instanceof Boulder || entity instanceof Wall || entity instanceof Door) && entity.getX() == x
					&& entity.getY() == y) {
				return false;
			}
		}
		return true;
	}

	/**
	 * get width of the dungeon
	 * 
	 * @return the number of width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * get height of the dungeon
	 * 
	 * @return the number of height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * get the player
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
	

	/**
	 * set the player
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * add entity
	 * 
	 * @param entity
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * get the list of entities
	 * 
	 * @return the list of entities
	 */
	public ObservableList<Entity> getEntities() {
		return entities;
	}

	/**
	 * get the floor switches
	 * 
	 * @return the list of floor switched
	 */
	public List<Switch> getSwitches() {
		return switches;
	}

	/**
	 * get enemies
	 * 
	 * @return the list of enemies
	 */
	public List<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * add the bomb dropped
	 * 
	 * @param bomb
	 */
	public void addBombsDropped(BombDropped bomb) {
		bombsDropped.add(bomb);
	}

	/**
	 * add enemies
	 * 
	 * @param enemy
	 */
	public void addEnemies(Enemy enemy) {
		enemies.add(enemy);
	}

	/**
	 * get the bomb dropped
	 * 
	 * @return bombs dropped
	 */
	public List<BombDropped> getBombsDropped() {
		return bombsDropped;
	}

	/**
	 * add all floor switches and check the conditions
	 */
	public void initSwitches() {
		for (Entity entity : getEntities()) {// add all switches
			if (entity instanceof Switch && getSwitches().contains(entity) == false) {
				switches.add((Switch) entity);
			}
		}
	}

	/**
	 * add enemies to enemy list and check the conditions
	 */
	public void initEnemies() {
		for (Entity entity : getEntities()) {
			if (entity instanceof Enemy && getEnemies().contains(entity) == false) {
				addEnemies((Enemy) entity);
			}
		}
	}

	/**
	 * add bombs to bomb dropped list and check the conditions
	 */
	public void initBombsDropped() {
		for (Entity entity : getEntities()) {
			if (entity instanceof BombDropped && getBombsDropped().contains(entity) == false) {
				addBombsDropped((BombDropped) entity);
			}
		}
	}

	/**
	 * updated by checking the goal conditions later
	 * 
	 * @return
	 */
	public boolean checkGameWin() {

		// for (GameCondition i : getGameCondition()) {
		// if (i.isWin()) {
		// return true;
		// }
		// }
		// return false;

		return this.mainGoal.isWin();
	}

	/**
	 * victory screen the system will print "you win" and exit the game
	 */
	public void victory() {
		this.finish.set(true);
		System.out.println("you win");
		// Stage primaryStage = get;
		// StartScreen startScreen = new StartScreen(primaryStage );
		// startScreen.start();
		// System.exit(0);
	}

	/**
	 * loose screen the system will print "you lose" and exit the game
	 */
	public void defeat() {
		System.out.println("you lose");
		System.exit(0);
	}

	/**
	 * shallow comparison to Entity
	 * 
	 * @param check
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkEntity(Entity check, int x, int y) {

		for (Entity entity : getEntities()) {
			if (entity.getX() == x && entity.getY() == y && (entity.getClass().equals(check.getClass()))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * remove an entity from the dungeon. Does not remove from view but update
	 * controller function
	 * 
	 * @param remove
	 * @param x
	 * @param y
	 */
	public void killEntity(Entity remove, int x, int y) {
		Iterator<Entity> itr = getEntities().iterator();
		while (itr.hasNext()) {
			Entity entity = itr.next();
			if (entity.getX() == x && entity.getY() == y && (entity.getClass().equals(remove.getClass()))) {
				System.out.println(entity + " removed");
				itr.remove();
			}
		}
	}

	/**
	 * get an entities from the dungeon
	 * 
	 * @param retrieve
	 * @param x
	 * @param y
	 * @return
	 */
	public Entity getEntity(Entity retrieve, int x, int y) {
		for (Entity entity : getEntities()) {
			if (entity.getX() == x && entity.getY() == y && (entity.getClass().equals(retrieve.getClass()))) {
				return entity;
			}
		}

		return null;
	}

	/**
	 * check if player/boulder is standing on game condion
	 */
	// move somewhere else later
	// add subgoals later
	public void checkEvent() {
		// init player and all switches
		Player player = this.getPlayer();
		initSwitches();
		initEnemies();
		initBombsDropped();
		Entity targetEntity = null;

		// Trigger conditions when player steps on something
		for (Entity entity : getEntities()) {

			// picking up treasure
			if (entity.getX() == player.getX() && entity.getY() == player.getY() && entity instanceof Treasure) {
				targetEntity = entity;

				player.pickUpTreasure();

				System.out.println("Treasure Picked up");

				this.scores.set(this.scores.get() + 100);
			}
			// Picking up a sword
			if (entity.getX() == player.getX() && entity.getY() == player.getY() && entity instanceof Sword) {
				if (player.getPlayerSword() == null) {
					targetEntity = entity;
					player.pickUpSword();
					System.out.println("Sword Picked up");
				}

			}
			// Picking up a key
			if (entity.getX() == player.getX() && entity.getY() == player.getY() && entity instanceof Key) {
				if (player.getKeys().isEmpty()) {
					targetEntity = new Key(this, 0, 0, 0);
					targetEntity = entity;
					int id = ((Key) entity).getKeyID();
					player.pickUpKey(id);				
					System.out.println("Key picked up, id: " + id);
				}
			}
			// Pick up bombs
			if (entity.getX() == player.getX() && entity.getY() == player.getY() && entity instanceof Bomb) {
				targetEntity = entity;
				player.pickUpBomb();

				System.out.println("Bomb picked up, Player has : " + player.getPlayerBombs());
			}

			// Potion
			if (entity.getX() == player.getX() && entity.getY() == player.getY() && entity instanceof Potion) {
				targetEntity = entity;
				player.pickUpPotion();

				System.out.println("Potion picked up");
			}

			// enemy interaction
			if (entity.getX() == player.getX() && entity.getY() == player.getY() && entity instanceof Enemy) {

				if (player.getPlayerInvincible() > 0) {
					targetEntity = entity;

					System.out.println("Enemy killed with invincibility");
					this.scores.set(this.scores.get() + 300);
				} else if (player.checkSwing() == true) {
					targetEntity = entity;

					// double class getter only for testing purposes, does not break o-o principles

					System.out.println(
							"Enemy killed with sword, sword swings left :" + player.getPlayerSword().getHitsLeft());

					this.scores.set(this.scores.get() + 300);
				} else {
					defeat();
				}
			}

		}

		// remove entity
		if (targetEntity != null) {
			killEntity(targetEntity, player.getX(), player.getY());
		}

		// check for finishing goals
		checkGoals();

		if (checkGameWin()) {
			victory();
		}
	}
	/**
	 * time left for bomb
	 */
	public void tickBombs() {
		// tick bombs
		for (BombDropped bomb : getBombsDropped()) {
			((BombDropped) bomb).tick();
		}
	}
	/**
	 * time left for invincible potion
	 */
	public void tickPotion() {
		// tick potion
		if (player.getPlayerInvincible() > 0) {
			player.setPlayerInvincible(player.getPlayerInvincible() - 1);
			System.out.println("Seconds invuln left: " + player.getPlayerInvincible());
		}
	}
	/**
	 * check the goal
	 */
	public void checkGoals() {
		// trigger victory for treasure
		// note for subgoals you may want to edit this so it doesn't return completely
		if (mainGoal.hasGoal("Treasure") && !mainGoal.isSubGoalWin("Treasure")) {
			boolean hasTreasure = false;
			for (Entity entity : getEntities()) {
				if (entity instanceof Treasure) {
					hasTreasure = true;
				}
			}
			if (!hasTreasure) {
				this.mainGoal.finishTreasureGoal();
				System.out.println("Treasure goal finished");
			}
		}

		// enemies all killed
		if (mainGoal.hasGoal("Enemy") && !mainGoal.isSubGoalWin("Enemy")) {
			boolean hasEnemy = false;
			for (Entity entity : getEntities()) {
				if (entity instanceof Enemy) {
					hasEnemy = true;
				}
			}
			if (!hasEnemy) {
				this.mainGoal.finishEnemyGoal();
				System.out.println("Enemy goal finished");
			}

		}

		// Trigger for boulders on switches
		if (mainGoal.hasGoal("Boulder") && !mainGoal.isSubGoalWin("Boulder")) {
			
			this.mainGoal.unFinishBoulderGoal();
			
			boolean allActivated = true;
			for (Switch floorSwitch : getSwitches()) {
				boolean activated = false;
				for (Entity entity : getEntities()) {
					// if not all switches are triggered, return
					if (entity.getX() == floorSwitch.getX() && entity.getY() == floorSwitch.getY()
							&& entity instanceof Boulder) {
						activated = true;
					}
				}
				if (activated == false) {
					allActivated = false;
				}
			}
			// else victory
			if (allActivated) {
				this.mainGoal.finishBoulderGoal();
				System.out.println("Boulder goal finished");
			}
		}
		
		this.mainGoal.unFinishExitGoal();
		
		for (Entity entity : getEntities()) {
			// player steps on exit
			if (entity.getX() == player.getX() && entity.getY() == player.getY() && entity instanceof Exit) {
				this.mainGoal.finishExitGoal();
				System.out.println("Exit Goal finished");
			}
		}

	}

	@Override
	public void update(Observable o, Object arg) {

		// tick bombs
		tickBombs();

		// Tick potion
		tickPotion();

		for (Enemy enemy : getEnemies()) {
			if (player.getPlayerInvincible() > 0) {
				enemy.moveAwayPlayer();
			} else {
				enemy.moveTowardsPlayer();
			}
		}
		checkEvent();

	}
	/**
	 * get the score
	 * @return
	 */
	public int getScores() {
		return this.scores.get();
	}
	/**
	 * get the scoreProperty
	 * @return
	 */
	public IntegerProperty getScoreProperty() {
		return this.scores;

	}
	
	/**
	 * get the scoreProperty
	 * @return
	 */
	public BooleanProperty getFinish() {
		return this.finish;

	}
	
	/**
	 * get the main goal
	 * @return
	 */
	public CompositeGoal getMainGoal() {
		return mainGoal;
	}
	/**
	 * set the main goal
	 * @param mainGoal
	 */
	public void setMainGoal(CompositeGoal mainGoal) {
		this.mainGoal = mainGoal;
	}

}
