package src.sample.PoliceRelated;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import src.sample.EndGameRelated.GameOverPage;
import src.sample.ShipRelated.ShipHub;
import src.sample.StartPages.PlayerInfo;

public class PoliceResultPage extends Application {
    private Label textLabel;

    public PoliceResultPage(String situation) {
        textLabel = new Label(situation);
        textLabel.setTextAlignment(TextAlignment.CENTER);
        textLabel.setWrapText(true);
    }

    public void start(Stage mainStage) {
        // Root pane
        BorderPane rootPane = new BorderPane();
        textLabel.setFont(new Font(20));

        // Button setup
        Button okBtn = new Button("Ok");

        // Root pane setup
        rootPane.setCenter(textLabel);
        rootPane.setAlignment(textLabel, Pos.CENTER);
        rootPane.setMargin(textLabel, new Insets(20));
        rootPane.setBottom(okBtn);
        rootPane.setAlignment(okBtn, Pos.CENTER);
        rootPane.setMargin(okBtn, new Insets(10));


        // Scene setup
        mainStage.setTitle("Outcome");
        Scene scene = new Scene(rootPane, 400, 250);
        mainStage.setScene(scene);
        mainStage.show();

        okBtn.setOnAction(e -> {
            if (PlayerInfo.getShip().getCurrentHealth() <= 0) {
                GameOverPage gOPage = new GameOverPage();
                gOPage.start(new Stage());
            } else {
                ShipHub hub = new ShipHub();
                hub.start(new Stage());
            }
            mainStage.close();
        });
    }
}
