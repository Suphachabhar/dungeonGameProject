package unsw.dungeon;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class test {

	@Test
	public void movingThroughWall() {
		Dungeon dungeon = new Dungeon(5, 5);
		Wall wall = new Wall(0, 1);
		dungeon.addEntity(wall);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		// check
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}

	@Test
	public void movingThroughBoulder() {
		Dungeon dungeon = new Dungeon(5, 5);
		Boulder boulder = new Boulder(dungeon, 0, 1);
		dungeon.addEntity(boulder);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}

	@Test
	public void movingTwoBoulder() {
		Dungeon dungeon = new Dungeon(5, 5);
		Boulder boulder1 = new Boulder(dungeon, 2, 1);
		dungeon.addEntity(boulder1);

		Boulder boulder2 = new Boulder(dungeon, 1, 1);
		dungeon.addEntity(boulder2);

		Player player = new Player(dungeon, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveRight();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
	}

	@Test
	public void pushBoulder() {
		Dungeon dungeon = new Dungeon(5, 5);
		Boulder boulder = new Boulder(dungeon, 2, 1);
		dungeon.addEntity(boulder);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);
	}

	@Test
	public void movingThroughClosedDoor() {
		Dungeon dungeon = new Dungeon(5, 5);
		Door door = new Door(dungeon, 0, 1, 0);
		dungeon.addEntity(door);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		// without key, can't exit the door, stay at same position
		player.moveLeft();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}

	@Test
	public void movingThroughExit() {
		Dungeon dungeon = new Dungeon(5, 5);
		Exit exit = new Exit(0, 1);
		dungeon.addEntity(exit);

		GoalExit exitGoal = new GoalExit();
		CompositeGoal mainGoal = new CompositeGoal("AND");
		mainGoal.addSubGoal(exitGoal);
		dungeon.setMainGoal(mainGoal);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertEquals(dungeon.checkGameWin(), true);
	}

	@Test
	public void pickUpSword() {
		Dungeon dungeon = new Dungeon(5, 5);
		Sword sword = new Sword(dungeon, 0, 1);
		dungeon.addEntity(sword);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		// check player's movement
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertNotNull(player.getPlayerSword());
	}

	@Test
	public void pickUpBomb() {
		Dungeon dungeon = new Dungeon(5, 5);
		Bomb bomb = new Bomb(dungeon, 0, 1);
		dungeon.addEntity(bomb);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertEquals(player.getPlayerBombs(), 1);
		// assertNotNull(player.getPlayerBombs());
	}

	@Test
	public void pickUpPotion() {
		Dungeon dungeon = new Dungeon(5, 5);
		Potion potion = new Potion(dungeon, 0, 1);
		dungeon.addEntity(potion);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertNotNull(player.getPlayerInvincible());
	}

	@Test
	public void pickUpTreasure() {
		Dungeon dungeon = new Dungeon(5, 5);
		Treasure treasure = new Treasure(dungeon, 0, 1);
		dungeon.addEntity(treasure);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertEquals(player.getPlayerTreasure(), 1);
		// assertNotNull(player.getPlayerTreasure());
	}

	@Test
	public void pickUpKey() {
		Dungeon dungeon = new Dungeon(5, 5);
		Key key1 = new Key(dungeon, 0, 1, 0);
		dungeon.addEntity(key1);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertNotNull(player.getPlayerKeys());
	}

	@Test
	public void movingThroughDoor() {
		Dungeon dungeon = new Dungeon(5, 5);
		Door door = new Door(dungeon, 0, 2, 0);
		dungeon.addEntity(door);

		Key key1 = new Key(dungeon, 0, 1, 0);
		dungeon.addEntity(key1);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertNotNull(player.getPlayerKeys());

		player.moveDown();
		assertEquals(player.getX(), 0); // win
		assertEquals(player.getY(), 2);
		// assertEquals(dungeon.checkGameWin(),true);
	}

	@Test
	public void movingThroungEnemies() {
		Dungeon dungeon = new Dungeon(3, 1);
		Enemy enemy1 = new Enemy(dungeon, 0, 1);
		dungeon.addEnemies(enemy1);
		dungeon.addEntity(enemy1);

		Player player = new Player(dungeon, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveLeft();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
	}

	@Test
	public void killEnemiesBySword() {
		Dungeon dungeon = new Dungeon(4, 1);
		Enemy enemy = new Enemy(dungeon, 0, 1);
		dungeon.addEnemies(enemy);
		dungeon.addEntity(enemy);

		Sword sword = new Sword(dungeon, 2, 1);
		dungeon.addEntity(sword);

		Player player = new Player(dungeon, 3, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		int begin_size = dungeon.getEntities().size();
		player.moveLeft(); // pick up sword
		assertEquals(dungeon.getEntities().size(), begin_size - 1);
		player.moveLeft();
		player.moveLeft();

		// check win
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertEquals(player.getPlayerSword().getHitsLeft(), 4);
		assertEquals(dungeon.getEntities().size(), begin_size - 2);
	}

	@Test
	public void killEnemiesByPotion() {
		Dungeon dungeon = new Dungeon(4, 1);
		Enemy enemy = new Enemy(dungeon, 0, 1);
		dungeon.addEnemies(enemy);
		dungeon.addEntity(enemy);

		Potion potion = new Potion(dungeon, 2, 1);
		dungeon.addEntity(potion);

		Player player = new Player(dungeon, 3, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		int begin_size = dungeon.getEntities().size();

		player.moveLeft(); // pick up potion
		assertEquals(dungeon.getEntities().size(), begin_size - 1);
		player.moveLeft();
		player.moveLeft();

		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertEquals(dungeon.getEntities().size(), begin_size - 2);
	}

//	@Test
//	public void dropBomb() {
//		Dungeon dungeon = new Dungeon(3, 5);
//		Bomb bomb = new Bomb(dungeon, 2, 1);
//		dungeon.addEntity(bomb);
//
//		Player player = new Player(dungeon, 1, 1);
//		dungeon.setPlayer(player);
//		dungeon.addEntity(player);
//
//		player.moveRight();
//		assertEquals(player.getX(), 2);
//		assertEquals(player.getY(), 1);
//
//		assertEquals(player.getPlayerBombs(), 1);
//		player.dropBomb(); // press E
//		assertEquals(player.getPlayerBombs(), 0);
//	}

//	@Test
//	public void killEnemiesByBomb() {
//		Dungeon dungeon = new Dungeon(10, 1);
//		Enemy enemy = new Enemy(dungeon, 9, 1);
//		dungeon.addEnemies(enemy);
//		dungeon.addEntity(enemy);
//
//		Bomb bomb = new Bomb(dungeon, 4, 1);
//		dungeon.addEntity(bomb);
//
//		Player player = new Player(dungeon, 5, 1);
//
//		dungeon.setPlayer(player);
//		dungeon.addEntity(player);
//
//		player.moveLeft(); // pick up bomb
//		player.dropBomb();
//
//		// System.out.println(player.getX() + "," + player.getY() + " : " + enemy.getX()
//		// + "," + enemy.getY());
//		player.moveLeft();
//		// System.out.println(player.getX() + "," + player.getY() + " : " + enemy.getX()
//		// + "," + enemy.getY());
//
//		int begin_size = dungeon.getEntities().size();
//		player.moveLeft();
//		// System.out.println(player.getX() + "," + player.getY() + " : " + enemy.getX()
//		// + "," + enemy.getY());
//
//		// Bomb explode
//		player.moveLeft();
//		player.moveLeft();
//		assertEquals(dungeon.getEntities().size(), begin_size - 1);
//		// System.out.println(player.getX() + "," + player.getY() + " : " + enemy.getX()
//		// + "," + enemy.getY());
//
//		// check win
//	}

	@Test
	public void untriggerFloorSwitch() {
		Dungeon dungeon = new Dungeon(5, 5);
		Boulder boulder = new Boulder(dungeon, 2, 1);
		dungeon.addEntity(boulder);

		GoalBoulder boulders = new GoalBoulder();
		CompositeGoal mainGoal = new CompositeGoal("AND");
		mainGoal.addSubGoal(boulders);
		dungeon.setMainGoal(mainGoal);

		Switch floorSwitch = new Switch(dungeon, 3, 1);
		dungeon.addEntity(floorSwitch);

		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);

		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);

		assertEquals(boulder.getX(), 3);
		assertEquals(boulder.getY(), 1);

		assertEquals(dungeon.checkGameWin(), true);
	}

}
