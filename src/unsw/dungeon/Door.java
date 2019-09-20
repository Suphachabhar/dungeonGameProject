package unsw.dungeon;

public class Door extends Entity {
	
	private Dungeon dungeon;
	private int doorID;

	public Door(Dungeon dungeon, int x, int y, int doorID) {
        super(x, y);
        this.dungeon = dungeon;
        this.doorID = doorID;
    }
	/**
	 * get the ID of the door, each door has different ID
	 * @return
	 */
	public int getDoorID() {
		return doorID;
	}
}
