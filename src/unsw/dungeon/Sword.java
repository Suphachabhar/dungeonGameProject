package unsw.dungeon;

public class Sword extends Entity {

	private Dungeon dungeon;
	private int hitsLeft;
	

	public Sword(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.hitsLeft = 5;
    }
	/**
	 * the number of hits will reduced by 1
	 * after faced the enemy
	 */
	public void hit() {
		setHitsLeft(getHitsLeft() - 1);
	}
	
	/**
	 * get a number of hits of the sword
	 * @return a number of the hitsLeft
	 */
	public int getHitsLeft() {
		return hitsLeft;
	}

	/**
	 * set a number of hits of the sword
	 * @param hitsLeft the number of hitsLeft to set
	 */
	public void setHitsLeft(int hitsLeft) {
		this.hitsLeft = hitsLeft;
	}
	
	
	
}
