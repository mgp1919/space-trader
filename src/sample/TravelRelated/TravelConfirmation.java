package src.sample.TravelRelated;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import src.sample.BanditRelated.BanditHub;
import src.sample.PlanetRelated.Planet;
import src.sample.PoliceRelated.PoliceHub;
import src.sample.ShipRelated.ShipHub;
import src.sample.StartPages.PlayerInfo;
import src.sample.TraderRelated.TraderHub;

public class TravelConfirmation extends Application {
    private Planet planet;
    private String planetName;
    private int planetTechLevel;
    private String planetDescription;

    public TravelConfirmation(Planet planet) {
        this.planet = planet;
        planetName = planet.getName();
        planetTechLevel = planet.getTechLevel();
        planetDescription = planet.getDescription();
    }

    public void start(Stage mainStage) {
        // Root pane
        BorderPane rootPane = new BorderPane();

        // Info pane
        VBox centerInfo = new VBox();
        VBox infoPane = new VBox();
        infoPane.setMaxWidth(200);

        // Button pane
        HBox buttons = new HBox();
        buttons.setMinWidth(300);
        buttons.setMaxWidth(300);

        // Box padding and alignment
        centerInfo.setSpacing(10);
        centerInfo.setAlignment(Pos.CENTER);
        infoPane.setSpacing(10);
        infoPane.setAlignment(Pos.TOP_LEFT);
        buttons.setSpacing(100);
        buttons.setAlignment(Pos.CENTER);

        // Buttons
        Button map = new Button("Go back to Map");
        Button travel = new Button("Travel");

        // Labels
        Label planetNameInfo;
        Label planetTechLevelInfo;
        Label planetDescriptionInfo;


        if (planet.getTravelled()) {
            // Label instantiations
            planetNameInfo = new Label(planet.getName());
            planetTechLevelInfo = new Label(">Technology Level: " + planet.getTechLevel());
            planetDescriptionInfo = new Label(">Description: " + planet.getDescription());
        } else {
            // Label instantiations
            planetNameInfo = new Label("Planet Name: ???");
            planetTechLevelInfo = new Label(">Technology Level: ???");
            planetDescriptionInfo = new Label(">Description: ???");
        }
        planetNameInfo.setFont(new Font(45));
        planetNameInfo.setWrapText(true);
        planetTechLevelInfo.setFont(new Font(15));
        planetDescriptionInfo.setFont(new Font(15));
        planetDescriptionInfo.setWrapText(true);

        // Travel button disabling
        double dist = Planet.distance(planet, PlayerInfo.getCurrPlanet());
        if (dist > PlayerInfo.getShip().getMaxTravellable()) {
            if (dist < PlayerInfo.getShip().getMaxDistance()) {
                travel.setText("Too Little Fuel");
            } else {
                travel.setText("Too Far Away");
            }

            travel.setDisable(true);
        }

        // Set up Boxes
        buttons.getChildren().addAll(map, travel);
        infoPane.getChildren().addAll(planetTechLevelInfo, planetDescriptionInfo);
        centerInfo.getChildren().addAll(planetNameInfo, infoPane);

        // Set up root pane
        rootPane.setCenter(centerInfo);
        rootPane.setBottom(buttons);
        rootPane.setAlignment(centerInfo, Pos.CENTER);
        rootPane.setAlignment(buttons, Pos.CENTER);
        rootPane.setMargin(centerInfo, new Insets(10, 10, 0, 10));
        rootPane.setMargin(buttons, new Insets(0, 50, 50, 50));

        // Set up button action
        map.setOnAction(e -> {
            TravelMap travelMap = new TravelMap();
            travelMap.start(new Stage());
            mainStage.close();
        });

        travel.setOnAction(e -> {
            PlayerInfo.getShip().travel(Planet.distance(planet, PlayerInfo.getCurrPlanet()));
            PlayerInfo.setPrevPlanet(PlayerInfo.getCurrPlanet());
            PlayerInfo.setCurrPlanet(planet);

            // Randomly spawns trader or police when traveling
            // Adjusts spawn rates based on difficulty
            int[][] spawnRateThresholds = {{30, 50, 60}, {25, 50, 75}, {20, 45, 85}};
            int[] threshold = spawnRateThresholds[PlayerInfo.getDifficultyEnum().ordinal()];

            int randomVal = (int) (Math.random() * 100) + 1;
            if (0 < randomVal && randomVal <= threshold[0]) {
                TraderHub tHub = new TraderHub();
                tHub.start(new Stage());
            } else if (!(PlayerInfo.getShip().getInventory().isEmpty())
                    && threshold[0] < randomVal && randomVal <= threshold[1]) {
                PoliceHub pHub = new PoliceHub();
                pHub.start(new Stage());
            } else if (threshold[1] < randomVal && randomVal <= threshold[2]) {
                BanditHub bHub = new BanditHub();
                bHub.start(new Stage());
            } else {
                ShipHub hub = new ShipHub();
                hub.start(new Stage());
            }
            mainStage.close();
        });

        // Set up scene
        mainStage.setTitle("Travel to " + PlayerInfo.getCurrPlanet().getName() + "?");
        Scene scene = new Scene(rootPane, 600, 500);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
