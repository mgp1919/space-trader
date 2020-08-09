package src.sample.PlanetRelated;

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
import src.sample.BlackMarketRelated.BlackMarketHub;
import src.sample.MarketRelated.MarketHub;
import src.sample.ShipRelated.ShipHub;
import src.sample.StartPages.PlayerInfo;

public class PlanetHub extends Application {
    private Planet planet = PlayerInfo.getCurrPlanet();
    private String blackMarketCode = "";

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
        Button marketplace = new Button("Go to Marketplace");
        Button board = new Button("Return to Ship");

        //Labels
        Label planetName = new Label("Welcome to " + planet.getName() + ",\n"
                + PlayerInfo.getName() + ".");
        planetName.setFont(new Font(45));
        planetName.setWrapText(true);
        planetName.setTextAlignment(TextAlignment.CENTER);
        Label planetTechLevel = new Label(">Technology Level: "
                + planet.getTechLevel());
        planetTechLevel.setFont(new Font(15));
        Label planetDescription = new Label(">Description: "
                + planet.getDescription());
        planetDescription.setFont(new Font(15));
        planetDescription.setWrapText(true);

        // Set up Boxes
        buttons.getChildren().addAll(board, marketplace);
        buttons.setMinWidth(500);
        infoPane.getChildren().addAll(planetTechLevel, planetDescription);
        centerInfo.getChildren().addAll(planetName, infoPane);

        // Set up root pane
        rootPane.setCenter(centerInfo);
        rootPane.setBottom(buttons);
        rootPane.setAlignment(centerInfo, Pos.CENTER);
        rootPane.setAlignment(buttons, Pos.CENTER);
        rootPane.setMargin(centerInfo, new Insets(10, 10, 0, 10));
        rootPane.setMargin(buttons, new Insets(0, 50, 50, 50));

        // Set up button action
        marketplace.setOnAction(e -> {
            MarketHub market = new MarketHub();
            market.start(new Stage());
            mainStage.close();
        });

        board.setOnAction(e -> {
            ShipHub ship = new ShipHub();
            ship.start(new Stage());
            mainStage.close();
        });

        // Set up scene
        mainStage.setTitle("Welcome to " + planet.getName() + ".");
        Scene scene = new Scene(rootPane, 1000, 550);

        scene.setOnKeyTyped(e -> {
            if (blackMarketCode.length() < 11) {
                blackMarketCode = blackMarketCode + e.getCharacter();
            } else {
                blackMarketCode = blackMarketCode.substring(1) + e.getCharacter();
            }

            if (blackMarketCode.equals("uuddlrlrbas")) {
                marketplace.setText("Go To Black Market");
                marketplace.setOnAction(b -> {
                    BlackMarketHub market = new BlackMarketHub();
                    market.start(new Stage());
                    mainStage.close();
                });
            }
        });

        mainStage.setScene(scene);
        mainStage.show();
    }
}
