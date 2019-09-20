package unsw.dungeon;



public class OpenDoor extends Door {
	
	private Dungeon dungeon;

	public OpenDoor(Dungeon dungeon, int x, int y, int doorID) {
		super(dungeon, x, y, doorID);
		
	}

}
