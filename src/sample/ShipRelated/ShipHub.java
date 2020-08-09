package src.sample.ShipRelated;

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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import src.sample.MusicRelated.Music;
import src.sample.MusicRelated.MusicList;
import src.sample.PlanetRelated.PlanetHub;
import src.sample.PlanetRelated.TravelledPlanets;
import src.sample.StartPages.PlayerInfo;
import src.sample.TravelRelated.TravelMap;

public class ShipHub extends Application {
    private Ship ship = PlayerInfo.getShip();

    @Override
    public void start(Stage mainStage) {
        // Root pane
        BorderPane rootPane = new BorderPane();

        // Button pane
        HBox buttonPane = new HBox();
        buttonPane.setMinWidth(300);
        buttonPane.setMaxWidth(300);
        buttonPane.setSpacing(100);
        buttonPane.setAlignment(Pos.CENTER);

        // Fuel Information Pane
        VBox fuelPane = new VBox();


        // Buttons
        Button mapBtn = new Button("Travel");
        Button inventoryBtn = new Button("Inventory");
        Button disembarkBtn = new Button("Disembark");

        // Set Edge Cases for Map Button
        if (ship.getCurrentHealth() == 0) {
            mapBtn.setText("Ship Health at 0");
            mapBtn.setDisable(true);
        }

        // Radio Parts
        Label radio = new Label("\n\n\nRadio:");
        HBox radioControls = new HBox();
        Button nextTrack = new Button(">");
        Button prevTrack = new Button("<");
        Label trackName = new Label(MusicList.getFileNameWithoutTag());

        nextTrack.setOnAction(e -> {
            Music.clear();
            MusicList.moveTrackForward();
            trackName.setText(MusicList.getFileNameWithoutTag());
            Music music = new Music(MusicList.getFileName());
            music.start(new Stage());
        });
        prevTrack.setOnAction(e -> {
            Music.clear();
            MusicList.moveTrackBackward();
            trackName.setText(MusicList.getFileNameWithoutTag());
            Music music = new Music(MusicList.getFileName());
            music.start(new Stage());
        });

        radioControls.getChildren().addAll(prevTrack, trackName, nextTrack);
        radioControls.setAlignment(Pos.CENTER);
        radioControls.setMargin(trackName, new Insets(5));

        // Labels
        Label shipName = new Label("Welcome aboard the " + ship.getName()
                + ",\nCaptatin " + PlayerInfo.getName() + ".");
        shipName.setFont(new Font(35));
        shipName.setTextAlignment(TextAlignment.CENTER);

        Label fuelLeft = new Label("The ship has " + ship.getCurrentFuel() + " units of fuel and "
                + ship.getCurrentHealth() + " point(s) of health left.");

        Label travelable = new Label("\nYou can travel up to " + ship.getMaxTravellable()
                + " tile(s) in distance.");
        fuelLeft.setFont(new Font(15));

        Label fullCapacity = new Label("\nYou are at " + ship.getPercentage() + "% fuel capacity");
        fullCapacity.setFont(new Font(13));

        Label currPlanetName = new Label("\n\nYou are currently on " 
                                         + PlayerInfo.getCurrPlanet().getName() + ".");
        currPlanetName.setFont(new Font(15));

        // Set up Fuel Pane
        fuelPane.getChildren().addAll(fuelLeft, travelable, fullCapacity, currPlanetName,
                radio, radioControls);
        fuelPane.setAlignment(Pos.TOP_CENTER);
        fuelPane.setMargin(radioControls, new Insets(10));

        // Set up Button Pane
        buttonPane.getChildren().addAll(disembarkBtn, inventoryBtn, mapBtn);
        buttonPane.setMinWidth(500);

        // Set up Root pane
        rootPane.setTop(shipName);
        rootPane.setAlignment(shipName, Pos.CENTER);
        rootPane.setMargin(shipName, new Insets(50));

        rootPane.setCenter(fuelPane);
        rootPane.setAlignment(fuelPane, Pos.CENTER);

        rootPane.setBottom(buttonPane);
        rootPane.setAlignment(buttonPane, Pos.CENTER);
        rootPane.setMargin(buttonPane, new Insets(0, 50, 50, 50));

        // Button to Map
        mapBtn.setOnAction(e -> {
            TravelMap map = new TravelMap();
            map.start(new Stage());
            mainStage.close();
        });

        inventoryBtn.setOnAction(e -> {
            InventoryPage inventoryPage = new InventoryPage();
            inventoryPage.start(new Stage());
            mainStage.close();
        });

        disembarkBtn.setOnAction(e -> {
            PlanetHub planetHub = new PlanetHub();
            planetHub.start(new Stage());
            mainStage.close();
        });

        // Set Up Planet Info
        PlayerInfo.getCurrPlanet().setTravelled(true);
        TravelledPlanets.getTravelled().add(PlayerInfo.getCurrPlanet());

        // Set main scene
        mainStage.setTitle("Aboard on " + ship.getName());
        Scene scene = new Scene(rootPane, 1000, 550);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
