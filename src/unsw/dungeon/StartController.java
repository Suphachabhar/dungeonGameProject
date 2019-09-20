package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartController {

    @FXML
    private Button startButton;
    
    @FXML
    private SplitMenuButton levels;
    @FXML
    private GridPane image1;
    @FXML
    private GridPane image2;
    @FXML
    private Pane pane1;
    @FXML
    private ImageView background;
    
    private Stage stage;	 	 

    private DungeonScreen dungeon;
    
    @FXML
    public void handleStartButton(ActionEvent event) {   	
    	dungeon.start();        
    }
    
    @FXML
    public void initialize() {
        //Image screen = new Image("/human_new.png");
        //Image screen2 = new Image("/deep_elf_master_archer.png");
        //Image screen3 = new Image("/gold_pile.png");
        Image mufasi = new Image("/backgroundDungeon.png");
        //Image screen = new Image("/mrmufasi.png");
        //image2.getChildren().add(new ImageView(screen2));
        //image1.add(new ImageView(screen),0 ,1);
//        image1.add(new ImageView(screen2),1 ,1);
//        image1.add(new ImageView(screen),0 ,2);
//        image1.add(new ImageView(screen2),1 ,3);
//        image1.add(new ImageView(screen3),0 ,3);
//        image1.add(new ImageView(screen3),1 ,2);
        background.setImage(mufasi);
        
    }  
    
    
    
    @FXML
    public void handleLevel1Button(ActionEvent event) throws IOException { 
    	levels.setText("Level 1");
    	stage = dungeon.getStage();
    	DungeonScreen dungeonChange =  new DungeonScreen(stage, 1);
    	setDungeonScreen(dungeonChange);
    }
    @FXML
    public void handleLevel2Button(ActionEvent event) throws IOException{
    	levels.setText("Level 2");
    	stage = dungeon.getStage();
    	DungeonScreen dungeonChange =  new DungeonScreen(stage, 2); 
    	setDungeonScreen(dungeonChange);
    }
    @FXML
    public void handleLevel3Button(ActionEvent event) throws IOException{   	
    	levels.setText("Level 3");
    	stage = dungeon.getStage();
    	DungeonScreen dungeonChange =  new DungeonScreen(stage, 3); 
    	setDungeonScreen(dungeonChange);
    }
    
    @FXML
    public void handleLevel4Button(ActionEvent event) throws IOException{   	
    	levels.setText("Level 4");
    	stage = dungeon.getStage();
    	DungeonScreen dungeonChange =  new DungeonScreen(stage, 4);
    	setDungeonScreen(dungeonChange);
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
        this.dungeon = dungeonScreen;
    }   

}
