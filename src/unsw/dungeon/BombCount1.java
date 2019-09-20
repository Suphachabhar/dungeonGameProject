package unsw.dungeon;

public class BombCount1 implements BombState {
	
	public String getStatus() {
		return "1";
	}
	
	public void tick(BombDropped bomb) {
		bomb.setBombState(new BombExplode());
	}
}
