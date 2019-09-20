package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

	
	 @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        DungeonScreen DungeonScreen = new DungeonScreen(primaryStage, 4);
               
        startScreen.getController().setDungeonScreen(DungeonScreen);
        startScreen.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
