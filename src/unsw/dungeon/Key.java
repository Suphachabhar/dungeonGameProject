package unsw.dungeon;

public class Key extends Entity {
	
	private Dungeon dungeon;
	private int keyID;

	public Key(Dungeon dungeon, int x, int y, int keyID) {
        super(x, y);
        this.dungeon = dungeon;
        this.keyID = keyID;
        
    }
	/**
	 * get the ID of the key, each key has different ID
	 * @return
	 */
	public int getKeyID() {
		return keyID;
	}

}
