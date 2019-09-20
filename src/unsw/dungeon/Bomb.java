package unsw.dungeon;

public class Bomb extends Entity {
	
	private Dungeon dungeon;
	
	public Bomb(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

}
