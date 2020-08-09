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

public class GameOverPage extends Application {

    public void start(Stage mainStage) {
        // Root Pane
        BorderPane root = new BorderPane();

        // Labels
        Label gameOver = new Label("Game Over");
        gameOver.setFont(new Font(25));
        gameOver.setTextAlignment(TextAlignment.CENTER);
        Label sadEnding = new Label("As you were on your way, you felt that "
                + "something wasn't quite right with the ship. It then dawned on you "
                + "that it had no health left. You scrambled to see "
                + "of you had any tool boxes left, but to no avail. Before you could take even "
                + "one step towards the cargo hold, the ship exploded.");
        sadEnding.setFont(new Font(17.5));
        sadEnding.setTextAlignment(TextAlignment.CENTER);
        sadEnding.setWrapText(true);
        Label legend = new Label("Legend has it, you can still find debris from the "
                + "explosion drifting between planets...");
        legend.setFont(new Font(12.5));
        legend.setTextAlignment(TextAlignment.CENTER);
        VBox labels = new VBox(sadEnding, legend);
        labels.setMargin(sadEnding, new Insets(10));
        labels.setMargin(legend, new Insets(5));
        labels.setAlignment(Pos.CENTER);

        // Buttons
        Button excessive = new Button("Uhm... That was excessive...");
        excessive.setMinWidth(50);
        excessive.setOnAction(e -> {
            EndCredits credits = new EndCredits();
            credits.start(new Stage());
            mainStage.close();
        });

        // Set Up Root
        root.setTop(gameOver);
        root.setCenter(labels);
        root.setBottom(excessive);
        root.setPadding(new Insets(20));
        root.setAlignment(gameOver, Pos.CENTER);
        root.setAlignment(labels, Pos.CENTER);
        root.setAlignment(excessive, Pos.CENTER);

        // Set Up Scene
        mainStage.setTitle("You didn't do it!");
        Scene scene = new Scene(root, 700, 400);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
