package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

	private JSONObject json;

	public DungeonLoader(String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}

	/**
	 * Parses the JSON to create a dungeon.
	 * 
	 * @return
	 */
	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");

		Dungeon dungeon = new Dungeon(width, height);

		JSONArray jsonEntities = json.getJSONArray("entities");

		// implement subgoals later
		JSONObject condition = json.getJSONObject("goal-condition");
		loadGoal(dungeon, condition);

		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}
		return dungeon;
	}

	/**
	 * using switch function to divide the different type of entities
	 * 
	 * @param dungeon
	 * @param json
	 */
	private void loadEntity(Dungeon dungeon, JSONObject json) {
		String type = json.getString("type");
		int x = json.getInt("x");
		int y = json.getInt("y");

		Entity entity = null;
		switch (type) {
		case "player":
			Player player = new Player(dungeon, x, y);
			dungeon.setPlayer(player);
			onLoad(player);
			entity = player;
			break;
		case "wall":
			Wall wall = new Wall(x, y);
			onLoad(wall);
			entity = wall;
			break;
		case "exit":
			Exit exit = new Exit(x, y);
			onLoad(exit);
			entity = exit;
			break;
		case "treasure":
			Treasure treasure = new Treasure(dungeon, x, y);
			onLoad(treasure);
			entity = treasure;
			break;
		case "enemy":
			Enemy enemy = new Enemy(dungeon, x, y);
			onLoad(enemy);
			entity = enemy;
			break;
		case "boulder":
			Boulder boulder = new Boulder(dungeon, x, y);
			onLoad(boulder);
			entity = boulder;
			break;
		case "switch":
			Switch floorswitch = new Switch(dungeon, x, y);
			onLoad(floorswitch);
			entity = floorswitch;
			break;
		case "key":
			int id = json.getInt("id");
			Key key = new Key(dungeon, x, y, id);
			onLoad(key);
			entity = key;
			break;
		case "door":
			id = json.getInt("id");
			Door door = new Door(dungeon, x, y, id);
			onLoad(door);
			entity = door;
			break;
		case "invincibility":
			Potion potion = new Potion(dungeon, x, y);
			onLoad(potion);
			entity = potion;
			break;
		case "sword":
			Sword sword = new Sword(dungeon, x, y);
			onLoad(sword);
			entity = sword;
			break;
		case "bomb":
			Bomb bomb = new Bomb(dungeon, x, y);
			onLoad(bomb);
			entity = bomb;
			break;
		case "wizard":
			Wizard wizard = new Wizard(dungeon, x, y);
			onLoad(wizard);
			entity = wizard;
			break;

		}
		dungeon.addEntity(entity);
	}

	public abstract void onLoad(Entity player);

	public abstract void onLoad(Exit exit);

	public abstract void onLoad(Treasure treasure);

	public abstract void onLoad(Enemy enemy);

	public abstract void onLoad(Boulder boulder);

	public abstract void onLoad(Switch floorswitch);

	public abstract void onLoad(Key key);

	public abstract void onLoad(Door door);

	public abstract void onLoad(Potion potion);

	public abstract void onLoad(Sword sword);

	public abstract void onLoad(Wall wall);

	public abstract void onLoad(Bomb bomb);

	public abstract void onLoad(OpenDoor door);

	public abstract void onLoad(Wizard wizard);
//    public abstract void onLoad(BombCount1 bomb);
//    public abstract void onLoad(BombCount2 bomb);
//    public abstract void onLoad(BombCount3 bomb);
//    public abstract void onLoad(BombExplode bomb);

//GOAL ADDING    
	private void loadGoal(Dungeon dungeon, JSONObject json) {
		String type = json.getString("goal");

		CompositeGoal mainGoal = new CompositeGoal("AND");
		switch (type) {
		case "exit":
			GoalExit exit = new GoalExit();
			mainGoal.addSubGoal(exit);
			dungeon.setMainGoal(mainGoal);
			break;
		case "treasure":
			GoalTreasure treasure = new GoalTreasure();
			mainGoal.addSubGoal(treasure);
			dungeon.setMainGoal(mainGoal);
			break;
		case "boulders":
			GoalBoulder boulders = new GoalBoulder();
			mainGoal.addSubGoal(boulders);
			dungeon.setMainGoal(mainGoal);
			break;
		case "enemies":
			GoalEnemies enemies = new GoalEnemies();
			mainGoal.addSubGoal(enemies);
			dungeon.setMainGoal(mainGoal);
			break;
		case "AND":
			CompositeGoal andGoal = new CompositeGoal("AND");
			addCompositeGoal(json, andGoal);
			mainGoal.addSubGoal(andGoal);
			dungeon.setMainGoal(mainGoal);
			break;
		case "OR":
			CompositeGoal orGoal = new CompositeGoal("OR");
			addCompositeGoal(json, orGoal);
			mainGoal.addSubGoal(orGoal);
			dungeon.setMainGoal(mainGoal);
			break;
		}
	}

	private void loadSubGoal(JSONObject json, CompositeGoal compositeGoal) {
		String type = json.getString("goal");
		switch (type) {
		case "exit":
			GoalExit exit = new GoalExit();
			compositeGoal.addSubGoal(exit);
			break;
		case "treasure":
			GoalTreasure treasure = new GoalTreasure();
			compositeGoal.addSubGoal(treasure);
			break;
		case "boulders":
			GoalBoulder boulders = new GoalBoulder();
			compositeGoal.addSubGoal(boulders);
			break;
		case "enemies":
			GoalEnemies enemies = new GoalEnemies();
			compositeGoal.addSubGoal(enemies);
			break;
		case "AND":
			CompositeGoal andGoal = new CompositeGoal("AND");
			addCompositeGoal(json, andGoal);
			compositeGoal.addSubGoal(andGoal);
			break;
		case "OR":
			CompositeGoal orGoal = new CompositeGoal("OR");
			addCompositeGoal(json, orGoal);
			compositeGoal.addSubGoal(orGoal);
			break;
		}
	}

	/**
	 * detect andGoal in json files
	 * 
	 * @param dungeon
	 * @param json
	 * @param goal
	 */
	private void addCompositeGoal(JSONObject json, CompositeGoal compositeGoal) {
		JSONArray subarray = json.getJSONArray("subgoals");
		for (int i = 0; i < subarray.length(); i++) {
			JSONObject subgoal = subarray.getJSONObject(i);
			loadSubGoal(subgoal, compositeGoal);
		}
	}

}