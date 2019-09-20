package unsw.dungeon;

import javafx.scene.image.Image;

public class MegaBomb extends BombDropped {
	
	private Dungeon dungeon;
	private BombState bombState;
	
	protected Image bombExplode = new Image("/bomb_lit_4.png");
	
	public MegaBomb(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.dungeon = dungeon;
        this.bombState = new BombCount1();
    }
	/**
	 * The tick algorithm is based on the status of bomb state from 0-3
	 * having time from 1-3 before the bomb explode (0)
	 */
	//Bomb still lives on in bombDropped list
	public void tick() {
				
		bombState.tick(this);			
		this.imageView.setImage(bombExplode);
	}
	/**
	 * @param bombState the bombState to set
	 */
	public void setBombState(BombState bombState) {
		this.bombState = bombState;
	}
	/**
	 * @return the Dungeon
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}
}
