package unsw.dungeon;

public class BombCount2 implements BombState{
	
	public String getStatus() {
		return "2";
	}
	
	public void tick(BombDropped bomb) {
		bomb.setBombState(new BombCount1());
	}
}
