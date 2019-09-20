package unsw.dungeon;

public class Boulder extends Entity {
	
	private Dungeon dungeon;

	public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
	/**
     * check conditions when boulder is moved upward
     */
	public boolean moveUp() {
		Wall wall = new Wall(0,0);
		Boulder boulder = new Boulder(dungeon,0,0);
		
        if (getY() > 0 
        			&& dungeon.checkEntity(wall, getX(), getY() - 1) == false
        			&& dungeon.checkEntity(boulder, getX(), getY() - 1) == false) {
           
        	//move boulder			
        	y().set(getY() - 1);
            return true;
        }
        return false;
    }
	/**
     * check conditions when boulder is moved downward
     */
    public boolean moveDown() {
    	Wall wall = new Wall(0,0);
    	Boulder boulder = new Boulder(dungeon,0,0);
    	
        if (getY() < dungeon.getHeight() - 1 
        			&& dungeon.checkEntity(wall, getX(), getY() +1) == false 
        			&& dungeon.checkEntity(boulder, getX(), getY() + 1) == false) {
            
        	//move boulder
        	y().set(getY() + 1);
            return true;
        }
        return false;
    }
    /**
     * check conditions when boulder is moved to the left direction
     */
    public boolean moveLeft() {
    	Wall wall = new Wall(0,0);
    	Boulder boulder = new Boulder(dungeon,0,0);
    	
        if (getX() > 0 
        			&& dungeon.checkEntity(wall, getX() - 1, getY()) == false
        			&& dungeon.checkEntity(boulder, getX() - 1, getY()) == false) {
            
        	//move Boulder			
        	x().set(getX() - 1);
        	return true;
        }
        return false;
    }
    /**
     * check conditions when boulder is moved to the right direction
     */
    public boolean moveRight() {
    	Wall wall = new Wall(0,0);
    	Boulder boulder = new Boulder(dungeon,0,0);
    	
        if (getX() < dungeon.getWidth() - 1 
        			&& dungeon.checkEntity(wall, getX() + 1, getY()) == false
        			&& dungeon.checkEntity(boulder, getX() + 1, getY()) == false) {
            
        	//move boulder
        	x().set(getX() + 1);
    		return true;
        }
        return false;
    }

}
