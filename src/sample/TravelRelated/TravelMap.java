package src.sample.TravelRelated;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import src.sample.PlanetRelated.Planet;
import src.sample.PlanetRelated.PlanetList;
import src.sample.ShipRelated.ShipHub;
import src.sample.StartPages.PlayerInfo;

public class TravelMap extends Application {

    public void start(Stage mainStage) {
        // Root pane
        VBox rootPane = new VBox();

        // Map pane
        GridPane map = new GridPane();
        map.setAlignment(Pos.CENTER);
        map.setGridLinesVisible(true);

        for (int i = 1; i <= 10; i++) {
            Label letter = new Label("" + (char) (i + 64));
            map.add(letter, 0, i);
            map.setMargin(letter, new Insets(16, 25, 16, 25));
        }
        for (int i = 1; i <= 10; i++) {
            Label number = new Label("" + i);
            map.add(number, i, 0);
            map.setMargin(number, new Insets(16, 25, 16, 25));
        }
        for (int i = 0; i < 10; i++) {
            Planet planet = PlanetList.getArrPlanets()[i];
            Button button = planet.getMapButton();
            if (planet == PlayerInfo.getCurrPlanet()) {
                Circle circ = new Circle(10, Color.GREEN);
                map.add(circ, planet.getColumnIndex(), planet.getRowIndex());
                map.setHalignment(circ, HPos.CENTER);
                map.setValignment(circ, VPos.CENTER);
            } else {
                double dist = Planet.distance(planet, PlayerInfo.getCurrPlanet());
                if (planet.getTravelled()) {
                    button.setStyle("-fx-background-color: greenyellow;");
                } else if (dist <= PlayerInfo.getShip().getMaxTravellable()) {
                    button.setStyle("-fx-background-color: yellow;");
                } else if (dist <= PlayerInfo.getShip().getMaxDistance()) {
                    button.setStyle("-fx-background-color: orange;");
                } else {
                    button.setStyle("-fx-background-color: orangered;");
                }
                map.add(button, planet.getColumnIndex(), planet.getRowIndex());
                map.setHalignment(button, HPos.CENTER);
                map.setValignment(button, VPos.CENTER);
            }
            button.setOnAction(e -> {
                TravelConfirmation confirmation = new TravelConfirmation(planet);
                confirmation.start(new Stage());
                mainStage.close();
            });
        }

        // Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            ShipHub hub = new ShipHub();
            hub.start(new Stage());
            mainStage.close();
        });

        // Legend
        HBox legend = new HBox();

        Rectangle here = new Rectangle(10, 10, Color.GREEN);
        Label hereLabel = new Label(" -- Current\t\t");
        Rectangle traveled = new Rectangle(10, 10, Color.GREENYELLOW);
        Label traveledLabel = new Label(" -- Visited\t\t");
        Rectangle closeEnough = new Rectangle(10, 10, Color.YELLOW);
        Label closeEnoughLabel = new Label(" -- Near\t\t");
        Rectangle far = new Rectangle(10, 10, Color.ORANGE);
        Label farLabel = new Label(" -- Not Enough Fuel\t\t");
        Rectangle oOR = new Rectangle(10, 10, Color.ORANGERED);
        Label oORLabel = new Label(" -- Out of Range");

        legend.getChildren().addAll(here, hereLabel, traveled, traveledLabel,
                closeEnough, closeEnoughLabel, far, farLabel, oOR, oORLabel);
        legend.setAlignment(Pos.CENTER);

        // Set up root pane
        rootPane.getChildren().addAll(map, legend, backButton);
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setMargin(map, new Insets(5));
        rootPane.setMargin(backButton, new Insets(10));

        // Set up scene
        mainStage.setTitle("Map");
        Scene scene = new Scene(rootPane, 650, 620);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
