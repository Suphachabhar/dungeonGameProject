package unsw.dungeon;

import javafx.scene.image.Image;

public class BombDropped extends Entity {
	
	private Dungeon dungeon;
	private BombState bombState;
	
	protected Image bombCount2 = new Image("/bomb_lit_2.png");
	protected Image bombCount3 = new Image("/bomb_lit_3.png");
	protected Image bombExplode = new Image("/bomb_lit_4.png");
	
	public BombDropped(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.bombState = new BombCount3();
    }
	/**
	 * The tick algorithm is based on the status of bomb state from 0-3
	 * having time from 1-3 before the bomb explode (0).
	 * the bomb still lives on in bombDropped list
	 */
	public void tick() {
				
		if (bombState.getStatus() == "0") {			
			System.out.println("Bomb Explode");
			//this.imageView.setImage(bombExplode);
			bombState.tick(this);
		}	
		if (bombState.getStatus() == "1") {
			bombState.tick(this);
			this.imageView.setImage(bombExplode);
			
			System.out.println("Bomb Tick 1");
			
		}	
		if (bombState.getStatus() == "2") {
			bombState.tick(this);
			this.imageView.setImage(bombCount3);
			
			System.out.println("Bomb Tick 2");
		}	
		if (bombState.getStatus() == "3") {
			bombState.tick(this);
			
			this.imageView.setImage(bombCount2);
			System.out.println("Bomb Tick 3");
		}	
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
