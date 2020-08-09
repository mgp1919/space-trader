package src.sample.StartPages;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class CharacterScreen extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox allButNameAndButton = new VBox();
        VBox allThings = new VBox();

        Label name = new Label(PlayerInfo.getName());
        name.setFont(new Font("Arial", 45));
        name.setPadding(new Insets(15));
        name.setWrapText(true);
        name.setTextAlignment(TextAlignment.CENTER);

        Label difficulty = new Label("Difficulty: " + PlayerInfo.getDifficulty());

        VBox skillContainer = new VBox();
        Label skillPoints = new Label("Skill Points:");
        Label pilotPoints = new Label("\tPilot - " + PlayerInfo.getPilot());
        Label fighterPoints = new Label("\tFighter - " + PlayerInfo.getFighter());
        Label merchantPoints = new Label("\tMerchant - " + PlayerInfo.getMerchant());
        Label engineerPoints = new Label("\tEngineer - " + PlayerInfo.getEngineer());
        skillContainer.getChildren().addAll(skillPoints, pilotPoints, fighterPoints,
                merchantPoints, engineerPoints);

        Label credits = new Label("Credits: " + PlayerInfo.getCredits());

        HBox buttons = new HBox();
        Button back = new Button("Go Back and Edit");
        Button cont = new Button("Confirm and Continue");
        buttons.getChildren().addAll(back, cont);
        buttons.setSpacing(30);
        buttons.setAlignment(Pos.CENTER);
        back.setOnAction(e -> {
            ConfigurationScreen configScreen = new ConfigurationScreen();
            configScreen.start(new Stage());
            primaryStage.close();
        });
        cont.setOnAction(e -> {
            ForcedLoadingScreen loadingScreen = new ForcedLoadingScreen();
            loadingScreen.start(new Stage());
            primaryStage.close();
        });

        allButNameAndButton.getChildren().addAll(difficulty, skillContainer, credits);
        allButNameAndButton.setAlignment(Pos.TOP_LEFT);
        allThings.getChildren().addAll(name, allButNameAndButton, buttons);
        allThings.setAlignment(Pos.CENTER);
        difficulty.setPadding(new Insets(20));
        skillContainer.setPadding(new Insets(20));
        credits.setPadding((new Insets(20)));

        primaryStage.setTitle("Character View");
        Scene scene = new Scene(allThings, 350, 475);
        primaryStage.setScene(scene);

        name.setWrapText(true);

        primaryStage.show();
    }
}
