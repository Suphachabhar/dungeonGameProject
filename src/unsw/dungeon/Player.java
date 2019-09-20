package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Sword playerSword;
    private int playerBombs;
    private int playerInvincible;
    private int playerTreasure;
    private List<Key> playerKeys = new ArrayList<Key>();   
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
    /**
     * add conditions when player moves upward
     */
    public void moveUp() {
    	Wall wall = new Wall(0,0);
    	Boulder boulder = new Boulder(dungeon,0,0);
    	Door door = new Door(dungeon,0,0,0);
    	//If boulder, pass to boulder function
    	//move if boulder returns true
    	
        if (getY() > 0 && dungeon.checkEntity(wall, getX(), getY() - 1) == false)	{
        	
        	//door check, create new door kill old door        	
        	if (dungeon.checkEntity(door, getX(), getY() - 1))	{
        		Door playerDoor = (Door) dungeon.getEntity(door, getX(), getY() - 1);
        		if (openDoor(playerDoor)) {
        			dungeon.killEntity(playerDoor, getX(), getY() - 1);
        			System.out.println("Door Opened");
        			createDoor(getX(), getY() - 1, playerDoor.getDoorID());
        		}
        		else {
        			return;
        		}
        	}
        	
        	if (dungeon.checkEntity(boulder, getX(), getY() - 1))	{
        		boulder = (Boulder) dungeon.getEntity(boulder, getX(), getY() - 1);
        		if (boulder.moveUp() == false) {
        			return;
        		}
        	}
        	//move
            y().set(getY() - 1);
            dungeon.checkEvent();
            setChanged();
            notifyObservers();
        }    
    }
    /**
     * add conditions when player moves downward
     */
    public void moveDown() {
    	Wall wall = new Wall(0,0);
    	Boulder boulder = new Boulder(dungeon,0,0);
    	Door door = new Door(dungeon,0,0,0);
    	
    	if (dungeon.checkEntity(door, getX(), getY() + 1))	{
    		Door playerDoor = (Door) dungeon.getEntity(door, getX(), getY() + 1);
    		if (openDoor(playerDoor)) {
    			dungeon.killEntity(playerDoor, getX(), getY() + 1);
    			System.out.println("Door Opened");
    			createDoor(getX(), getY() + 1, playerDoor.getDoorID());
    		}
    		else {
    			return;
    		}
    	}
    	
        if (getY() < dungeon.getHeight() - 1 && dungeon.checkEntity(wall, getX(), getY() +1) == false)	{
        	if (dungeon.checkEntity(boulder, getX(), getY() + 1))	{
        		boulder = (Boulder) dungeon.getEntity(boulder, getX(), getY() + 1);
        		if (boulder.moveDown() == false) {
        			return;
        		}
        	}
	    	//move
	        y().set(getY() + 1);
	        dungeon.checkEvent();
	        setChanged();
            notifyObservers();
        }    
    }
    /**
     * add conditions when player moves to the left direction
     */
    public void moveLeft() {
    	Wall wall = new Wall(0,0);
    	Boulder boulder = new Boulder(dungeon,0,0);
    	Door door = new Door(dungeon,0,0,0);
    	
    	if (dungeon.checkEntity(door, getX() - 1, getY()))	{
    		Door playerDoor = (Door) dungeon.getEntity(door, getX()- 1, getY());
    		if (openDoor(playerDoor)) {
    			dungeon.killEntity(playerDoor, getX()- 1, getY());
    			System.out.println("Door Opened");
    			createDoor(getX()- 1, getY(), playerDoor.getDoorID());
    		}
    		else {
    			return;
    		}
    	}
    	
        if (getX() > 0 && dungeon.checkEntity(wall, getX() - 1, getY()) == false)	{
        	
        	if (dungeon.checkEntity(boulder, getX() - 1, getY())) {
        		boulder = (Boulder) dungeon.getEntity(boulder, getX() - 1, getY());
        		if (boulder.moveLeft() == false) {
        			return;
        		}
        	}
	    	//move
	        x().set(getX() - 1);
	        dungeon.checkEvent();
	        setChanged();
            notifyObservers();
        }
    }
    /**
     * add conditions when player moves to the right direction
     */
    public void moveRight() {
    	Wall wall = new Wall(0,0);
    	Boulder boulder = new Boulder(dungeon,0,0);
    	Door door = new Door(dungeon,0,0,0);
    	
    	if (dungeon.checkEntity(door, getX() + 1, getY()))	{
    		Door playerDoor = (Door) dungeon.getEntity(door, getX() + 1, getY());
    		if (openDoor(playerDoor)) {
    			dungeon.killEntity(playerDoor, getX() + 1, getY());
    			System.out.println("Door Opened");
    			createDoor(getX() + 1, getY(), playerDoor.getDoorID());
    		}
    		else {
    			return;
    		}
    	}
    	
        if (getX() < dungeon.getWidth() - 1 && dungeon.checkEntity(wall, getX() + 1, getY()) == false) {
        	if (dungeon.checkEntity(boulder, getX() + 1, getY())) {
        		boulder = (Boulder) dungeon.getEntity(boulder, getX() + 1, getY());
        		if (boulder.moveRight() == false) {
        			return;
        		}
        	}
	    	//move
	        x().set(getX() + 1);
	        dungeon.checkEvent();
	        setChanged();
            notifyObservers();
        }    
    }

    /**
     * get the dungeon
     * @return the dungeon
     */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/**
	 * get the sword
	 * @return the number of playerSword
	 */
	public Sword getPlayerSword() {
		return playerSword;
	}	
	
	/**
	 * get the bomb
	 * @return the number of playerBombs
	 */
	public int getPlayerBombs() {
		return playerBombs;
	}	
	
	/**
	 * get the key
	 * @return the playerKeys
	 */
	public List<Key> getPlayerKeys() {
		return playerKeys;
	}
	
	/**
	 * get the invincible potion
	 * @return the playerInvincible
	 */
	public int getPlayerInvincible() {
		return playerInvincible;
	}

	/**
	 * set the invincible potion
	 * @param change player invincible
	 */
	public void setPlayerInvincible(int playerInvincible) {
		this.playerInvincible = playerInvincible;
	}
	/**
	 * pick up the invincible potion
	 */
	public void pickUpPotion() {
		setPlayerInvincible(7);
	}
	/**
	 * get the list of keys
	 * @return
	 */
	public List<Key> getKeys() {
		return playerKeys;
	}
	/**
	 * pick up the treasure
	 */
	public int getPlayerTreasure() {
		return playerTreasure;
	}
	/**
	 * after pick up the treasure, the number of treasure will be increased by 1
	 */
	public void pickUpTreasure() {
		this.playerTreasure += 1;
		
	}
	/**
	 * pick up key
	 * @param id
	 */
	public void pickUpKey(int id) {
		Key key = new Key(dungeon, this.getX(), this.getY(), id);
		playerKeys.add(key);
	}
	
	/**
	 * @playerBombs change the value of playerBombs
	 */
	public void setPlayerBombs(int set) {
		playerBombs = set;
	}
	/**
	 * pick up sword
	 */
	public void pickUpSword() {
		Sword sword = new Sword(dungeon, 0, 0);
		this.playerSword = sword;
	}
	/**
	 * after pick up bomb, the number of bomb will be increased by 1
	 */
	public void pickUpBomb() {
		setPlayerBombs(playerBombs + 1);
	}
	
	/**
	 * after drop bomb, the number of bomb will be decreased by 1
	 */
	public void dropBomb() {
		if (getPlayerBombs() > 0) {
			setPlayerBombs(playerBombs - 1);
			BombDropped bombDrop = new BombDropped(dungeon, this.getX(), this.getY());
			dungeon.addEntity(bombDrop);
			System.out.println("Bomb Dropped");
		}
	}
	/**
	 * after drop bomb, the number of bomb will be decreased by 1
	 */
	public void dropMegaBomb() {
		if (getPlayerBombs() > 1) {
			setPlayerBombs(playerBombs - 2);
			MegaBomb bombDrop = new MegaBomb(dungeon, this.getX(), this.getY());
			dungeon.addEntity(bombDrop);
			System.out.println("Quick Bomb Dropped");
		}
	}
	/**
	 * check the number of swing to know how many times the sword can hit the enemies
	 * @return
	 */
	public boolean checkSwing() {
		Sword playerSword = getPlayerSword();
		if (playerSword == null) {
		}
		
		else if (playerSword.getHitsLeft() > 0)	{
			playerSword.hit();
			return true;
		}
		
		return false;
		
	}
	/**
	 * check both id of key and door, if the id is matched, the door can be opened
	 * @param open
	 * @return
	 */
	public boolean openDoor(Door open) {
		for (Key key : getPlayerKeys()) {
			if(key.getKeyID() == open.getDoorID()) {
				getPlayerKeys().remove(key);
				return true;
			}
		}
		return false;
	}
	/**
	 * create the new door
	 * @param x
	 * @param y
	 * @param id
	 */
	public void createDoor(int x, int y, int id) {
		OpenDoor door = new OpenDoor(dungeon, x, y, id);
		dungeon.addEntity(door);
	}

	
	
}
