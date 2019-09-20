package unsw.dungeon;

public class BombCount3 implements BombState{
	
	public String getStatus() {
		return "3";
	}
	
	public void tick(BombDropped bomb) {
		bomb.setBombState(new BombCount2());
	}

}
