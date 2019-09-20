package unsw.dungeon;

import java.util.Observable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.scene.image.ImageView;


/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity extends Observable {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    protected ImageView imageView;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    /**
     * set the ImageView using for deleting picked up entities
     * @param imageView
     */
    public void setImageView(ImageView imageView) {
     this.imageView = imageView;
    }
    /**
     * get the ImageView using for deleting picked up entities
     * @return
     */
    public ImageView getImageView() {
     return this.imageView;
    }
    

}