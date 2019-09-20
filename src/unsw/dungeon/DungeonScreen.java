package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
	
	 private Stage stage;
	 private DungeonControllerLoader dungeonLoader;
	 private DungeonController controller;
	 private Scene scene;

	/**
	 * creating the interface, user can select the level from one to four
	 * following by the difficulty
	 * @param primaryStage
	 * @param level
	 * @throws IOException
	 */
	public DungeonScreen(Stage primaryStage, int level) throws IOException {
		primaryStage.setTitle("Dungeon");
        if (level == 1)
        	dungeonLoader = new DungeonControllerLoader("boulders.json");
        if (level == 2)
        	dungeonLoader = new DungeonControllerLoader("maze.json");
        if (level == 3)
			dungeonLoader = new DungeonControllerLoader("advanced.json");
        if (level == 4)
        	dungeonLoader = new DungeonControllerLoader("entities.json");
        
        controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
        root.requestFocus();
        stage = primaryStage;
    }
	/**
	 * part of the interface part clicking when want to start the game
	 */
	public void start() {
		stage.setScene(scene);
        stage.show();
    }

	/**
	 * @return the dungeonLoader
	 */
	public DungeonControllerLoader getDungeonLoader() {
		return dungeonLoader;
	}

	/**
	 * @param dungeonLoader the dungeonLoader to set
	 */
	public void setDungeonLoader(DungeonControllerLoader dungeonLoader) {
		this.dungeonLoader = dungeonLoader;
	}
	/**
	 * get the stage
	 * @return
	 */
	public Stage getStage() {
		return stage;
	}
	
	
	

}
