package unsw.dungeon;

public class Treasure extends Entity {
	
	private Dungeon dungeon;

	public Treasure(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
}
