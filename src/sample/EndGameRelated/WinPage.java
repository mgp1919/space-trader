package src.sample.EndGameRelated;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class WinPage extends Application {

    public void start(Stage mainStage) {
        // Root Pane
        BorderPane root = new BorderPane();

        // Labels
        Label finallyFinished = new Label("Congratulations! You finally gave up on "
                + "mindlessly bouncing around planets and \"trading\", and"
                + " gave your life some actual meaning!");
        finallyFinished.setFont(new Font(25));
        finallyFinished.setTextAlignment(TextAlignment.CENTER);
        finallyFinished.setWrapText(true);
        Label longEnough = new Label("Took you long enough...");
        longEnough.setFont(new Font(20));
        longEnough.setTextAlignment(TextAlignment.CENTER);
        VBox labels = new VBox(finallyFinished, longEnough);
        labels.setMargin(finallyFinished, new Insets(10));
        labels.setMargin(longEnough, new Insets(5));
        labels.setAlignment(Pos.CENTER);

        // Buttons
        Button betterThings = new Button("On to Bigger Things!");
        betterThings.setMinWidth(50);
        betterThings.setOnAction(e -> {
            EndCredits credits = new EndCredits();
            credits.start(new Stage());
            mainStage.close();
        });

        // Set Up Root
        root.setCenter(labels);
        root.setBottom(betterThings);
        root.setPadding(new Insets(20));
        root.setAlignment(labels, Pos.CENTER);
        root.setAlignment(betterThings, Pos.CENTER);

        // Set Up Scene
        mainStage.setTitle("You did it!");
        Scene scene = new Scene(root, 700, 400);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
