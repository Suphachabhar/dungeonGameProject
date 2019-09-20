package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

	@FXML
	private GridPane squares;

	private List<ImageView> initialEntities;

	private Player player;

	private Dungeon dungeon;

	private Text text;
	
	private BooleanProperty finish;

	public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
		this.dungeon = dungeon;
		this.player = dungeon.getPlayer();
		this.player.addObserver(this.dungeon);
		this.initialEntities = new ArrayList<>(initialEntities);
		this.text = new Text();
		this.text.setText("0");
		this.text.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		this.text.setFill(Color.WHITE);
	}	

	@FXML
	public void initialize() {
		Image ground = new Image("/dirt_0_new.png");

		// Add the ground first so it is below all other entities
		for (int x = 0; x < dungeon.getWidth(); x++) {
			for (int y = 0; y < dungeon.getHeight(); y++) {
				squares.add(new ImageView(ground), x, y);
			}
		}

		for (ImageView entity : initialEntities)
			squares.getChildren().add(entity);

		squares.add(this.text, dungeon.getWidth() - 1, dungeon.getHeight() - 1);

		this.dungeon.getScoreProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//System.out.println("oldValue:" + oldValue + ", newValue = " + newValue);
				text.setText(Integer.toString((int) newValue));
			}
						
		});
		
		
		this.dungeon.getFinish().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					changeStart();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});
		
		this.dungeon.getEntities().addListener(new ListChangeListener<Entity>() {
			@Override
			public void onChanged(Change<? extends Entity> c) {
				while (c.next()) {
					for (Entity removedItem : c.getRemoved()) {
						System.out.println("Removed: " + removedItem);
						squares.getChildren().remove(removedItem.getImageView());
					}

					for (Entity addedItem : c.getAddedSubList()) {
						if (addedItem instanceof OpenDoor) {
//        	                    
							System.out.println("Added: " + addedItem);
							Image doorImage2 = new Image("/open_door.png");
							ImageView view = new ImageView(doorImage2);
							GridPane.setColumnIndex(view, addedItem.getX());
							GridPane.setRowIndex(view, addedItem.getY());
							addedItem.setImageView(view);

							squares.getChildren().add(addedItem.getImageView());
//        	                    DungeonControllerLoader.onLoad(addedItem);
						}
						if (addedItem instanceof BombDropped) {
//    	                    
							System.out.println("Added: " + addedItem);
							Image bombCount1 = new Image("/bomb_lit_1.png");
							ImageView view = new ImageView(bombCount1);
							GridPane.setColumnIndex(view, addedItem.getX());
							GridPane.setRowIndex(view, addedItem.getY());
							addedItem.setImageView(view);

							squares.getChildren().add(addedItem.getImageView());

						}
					}
				}
			}
		});

	}
	
	public void changeStart() throws IOException{
		Stage stage = (Stage) squares.getScene().getWindow();
		WinScreen startScreen = new WinScreen(stage);
        DungeonScreen DungeonScreen = new DungeonScreen(stage, 4);
               
        startScreen.getController().setDungeonScreen(DungeonScreen);
        startScreen.start();
	}
	

	@FXML
	public void handleKeyPress(KeyEvent event) throws IOException {
		switch (event.getCode()) {
		case UP:
			player.moveUp();
			break;
		case DOWN:
			player.moveDown();
			break;
		case LEFT:
			player.moveLeft();
			break;
		case RIGHT:
			player.moveRight();
			break;
		case E:
			player.dropBomb();
			break;
		case F:
			player.dropMegaBomb();
			break;
		case P:
			System.out.println("woof!");
			Image dog = new Image("/hound.png");
			ImageView view = new ImageView(dog);
			squares.add(view, player.getX(), player.getY());
			break;
		case R:
			Stage stage = (Stage) squares.getScene().getWindow();
			StartScreen startScreen = new StartScreen(stage);
	        DungeonScreen DungeonScreen = new DungeonScreen(stage, 4);
	               
	        startScreen.getController().setDungeonScreen(DungeonScreen);
	        startScreen.start();
			break;
		case M:
			ImageView screen = new ImageView(new Image("/mrmufasi.png")); 
			squares.add(screen, player.getX(), player.getY());      
			break;	
		default:
			break;
		}
	}

}
