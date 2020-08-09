package src.sample.StartPages;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import src.sample.MusicRelated.Music;
import src.sample.MusicRelated.MusicList;

public class Main extends Application {
    public void start(Stage mainStage) {
        // Panes
        BorderPane basePane = new BorderPane();
        VBox names = new VBox();

        // Start button
        Button startGame = new Button("Start Game");
        startGame.setOnAction(e -> {
            MusicList.initialize();
            Music backgroundMusic = new Music(MusicList.getFileName());
            backgroundMusic.start(new Stage());
            ConfigurationScreen configScreen = new ConfigurationScreen();
            configScreen.start(new Stage());
            mainStage.close();
        });

        // Title and names
        Label title = new Label("Space Traders");
        title.setFont(new Font(70));

        Font nameFont = new Font("Times New Roman", 12.5);
        Label nameY = new Label("Youngho Lim");
        nameY.setFont(nameFont);
        Label nameR = new Label("Raj Srivastava");
        nameR.setFont(nameFont);
        Label nameJ = new Label("Justin DeSimpliciis");
        nameJ.setFont(nameFont);
        Label nameM = new Label("Mihir Govind Paramesh");
        nameM.setFont(nameFont);
        Label nameC = new Label("Curie Kim");
        nameC.setFont(nameFont);

        // Setting up names
        names.getChildren().addAll(nameY, nameR, nameJ, nameM, nameC);
        names.setAlignment(Pos.TOP_CENTER);

        // Setting up root pane
        basePane.setTop(title);
        basePane.setCenter(names);
        basePane.setBottom(startGame);
        basePane.setAlignment(title, Pos.CENTER);
        basePane.setAlignment(names, Pos.CENTER);
        basePane.setAlignment(startGame, Pos.CENTER);
        basePane.setMargin(startGame, new Insets(25, 25, 50, 25));
        basePane.setMargin(title, new Insets(50));

        // Scene setup
        mainStage.setTitle("Welcome to Space Traders");
        Scene scene = new Scene(basePane, 1000, 550);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
