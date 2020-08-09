package src.sample.MarketRelated;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.sample.StartPages.PlayerInfo;

public class ThanksForBusiness extends Application {

    public void start(Stage mainStage) {
        // Root pane
        BorderPane root = new BorderPane();

        // Labels
        Label thanks = new Label("Thank you for doing business with us, "
                + PlayerInfo.getName() + ".");
        thanks.setFont(new Font(20));
        Label creditsRemaining = new Label("You now have " + PlayerInfo.getCredits() + " credits.");
        creditsRemaining.setFont(new Font(15));

        // Label HBox
        VBox labels = new VBox(thanks, creditsRemaining);
        labels.setAlignment(Pos.CENTER);
        labels.setMargin(thanks, new Insets(5));
        labels.setMargin(creditsRemaining, new Insets(5));

        // Button
        Button ok = new Button("Ok");
        ok.setOnAction(e -> {
            mainStage.close();
        });

        // Set up root
        root.setCenter(labels);
        root.setBottom(ok);
        root.setAlignment(labels, Pos.CENTER);
        root.setAlignment(ok, Pos.CENTER);
        root.setPadding(new Insets(10));

        // Set scene
        Scene scene = new Scene(root, 500, 300);
        mainStage.setScene(scene);
        mainStage.setTitle("Thank You!");
        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.show();
    }
}
