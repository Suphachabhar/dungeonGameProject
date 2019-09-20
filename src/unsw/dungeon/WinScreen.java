package unsw.dungeon;
	
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class WinScreen {

    private Stage stage;
    private String title;
    private StartController controller;
    private Scene scene;
    
    /**
     * the background of start screen
     * @param stage
     * @throws IOException
     */
    public WinScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Dungeon";

        controller = new StartController();        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameWin.fxml"));
        loader.setController(controller);        
        
        // load into a Parent node called root
        Parent root = loader.load();
        
        scene = new Scene(root);
    }

    
    /**
     * start the game
     */
    public void start() {       
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * 
     * @return
     */
    public StartController getController() {
        return controller;
    }
}


