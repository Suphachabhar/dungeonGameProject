package unsw.dungeon;

public interface BombState {
	
	void tick(BombDropped bomb);
	
	String getStatus();
}
