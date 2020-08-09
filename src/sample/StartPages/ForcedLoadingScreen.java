package src.sample.StartPages;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.sample.ItemRelated.ItemList;
import src.sample.PlanetRelated.Planet;
import src.sample.PlanetRelated.PlanetList;
import src.sample.ShipRelated.ShipHub;
import src.sample.ShipRelated.ShipList;

public class ForcedLoadingScreen extends Application {
    public void start(Stage mainStage) {
        PlayerInfo.setShip(ShipList.STARTER);

        ItemList.initTotal();

        PlanetList.initialize();
        int currPlanetIndex = (int) (Math.random() * 10);
        Planet[] listOfPlanets = PlanetList.getArrPlanets();
        PlayerInfo.setCurrPlanet(listOfPlanets[currPlanetIndex]);

        PlanetList.setMap();

        // Root pane
        VBox rootPane = new VBox();

        // Labels
        Label startGame = new Label("Starting Game...");
        Label location = new Label("Location: " + PlayerInfo.getCurrPlanet().getName());

        startGame.setFont(new Font(45));
        location.setFont(new Font(15));

        // Root pane setup
        rootPane.getChildren().addAll(startGame, location);
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setSpacing(30);

        // Scene setup
        mainStage.setTitle("Loading...");
        Scene scene = new Scene(rootPane, 1000, 500);
        mainStage.setScene(scene);
        mainStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            ShipHub shipHub = new ShipHub();
            shipHub.start(new Stage());
            mainStage.close();
        });
        delay.play();
    }
}
