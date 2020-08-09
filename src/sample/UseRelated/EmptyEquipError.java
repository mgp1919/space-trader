package src.sample.UseRelated;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmptyEquipError extends Application {

    public void start(Stage mainStage) {
        // Root pane
        BorderPane root = new BorderPane();

        // Labels
        Label errorMessage = new Label("There is nothing in your cargo bay to equip.");
        errorMessage.setFont(new Font(15));
        errorMessage.setAlignment(Pos.CENTER);
        errorMessage.setTextAlignment(TextAlignment.CENTER);

        // Button
        Button ok = new Button("Ok");
        ok.setOnAction(e -> {
            mainStage.close();
        });

        // Set up root
        root.setCenter(errorMessage);
        root.setBottom(ok);
        root.setAlignment(errorMessage, Pos.CENTER);
        root.setAlignment(ok, Pos.CENTER);
        root.setPadding(new Insets(10));

        // Set scene
        Scene scene = new Scene(root, 400, 200);
        mainStage.setScene(scene);
        mainStage.setTitle("Error");
        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.show();
    }
}
