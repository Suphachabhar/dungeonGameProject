package unsw.dungeon;

import java.util.ArrayList;

public class BombExplode implements BombState{
	
	public String getStatus() {
		return "0";
	}
	
	public void tick(BombDropped bomb) {
		Dungeon dungeon = bomb.getDungeon();
		bomb.setBombState(new BombGone());
		explode(dungeon, bomb.getX(), bomb.getY());
		dungeon.killEntity(bomb, bomb.getX(), bomb.getY());
		
	}
	
	/**
	 * the bomb explode all up, down, left and right
	 * @param dungeon
	 * @param x
	 * @param y
	 */
	public void explode(Dungeon dungeon, int x, int y)	{
		ArrayList<Entity> targetEntities = new ArrayList<Entity>();
		for (Entity entity : dungeon.getEntities()) {
			//check one space right
    		if (entity.getX()+1 == x && entity.getY() == y 
    				&& (entity instanceof Player || entity instanceof Boulder || entity instanceof Enemy))	{
    			targetEntities.add(entity);
    		}
    		if (entity.getX()-1 == x && entity.getY() == y 
    				&& (entity instanceof Player || entity instanceof Boulder || entity instanceof Enemy))	{
    			targetEntities.add(entity);
    		}
    		if (entity.getX() == x && entity.getY()+1 == y 
    				&& (entity instanceof Player || entity instanceof Boulder || entity instanceof Enemy))	{
    			targetEntities.add(entity);
    		}
    		if (entity.getX() == x && entity.getY()-1 == y 
    				&& (entity instanceof Player || entity instanceof Boulder || entity instanceof Enemy))	{
    			targetEntities.add(entity);
    		}
    		if (entity.getX() == x && entity.getY() == y 
    				&& (entity instanceof Player || entity instanceof Boulder || entity instanceof Enemy))	{
    			targetEntities.add(entity);
    		}
    		
		}
		
		for (Entity i : targetEntities)	{
			if (i instanceof Player) {
				dungeon.defeat();
			}			
			dungeon.killEntity(i, i.getX(), i.getY());
		}
		
	}	

}
