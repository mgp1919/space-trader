package src.sample.MarketRelated;

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
import src.sample.DeliveryRelated.DeliveryPage;
import src.sample.ItemRelated.ItemList;
import src.sample.PlanetRelated.Planet;
import src.sample.PlanetRelated.PlanetHub;
import src.sample.PlanetRelated.TravelledPlanets;
import src.sample.StartPages.PlayerInfo;

public class MarketHub extends Application {

    public void start(Stage mainStage) {
        // Root pane
        BorderPane rootPane = new BorderPane();

        // Market name
        Label marketName = new Label(planet().getName() + "\'s Marketplace");
        marketName.setWrapText(true);
        marketName.setFont(new Font(30));
        marketName.setTextAlignment(TextAlignment.CENTER);

        // Greeting
        Label greeting1 = new Label("Welcome, " + playerName()
                + "! What would you\nlike to do today?");
        greeting1.setWrapText(true);
        greeting1.setFont(new Font(20));
        greeting1.setTextAlignment(TextAlignment.CENTER);


        // Buttons
        Button backButton = new Button("Back");
        Button buyButton = new Button("Buy");
        Button sellButton = new Button("Sell");
        Button deliverButton = new Button("Deliver a Parcel");
        HBox buySellDeliver = new HBox(buyButton, sellButton, deliverButton);
        buySellDeliver.setAlignment(Pos.CENTER);
        buySellDeliver.setMargin(buyButton, new Insets(10));
        buySellDeliver.setMargin(sellButton, new Insets(10));
        buySellDeliver.setMargin(deliverButton, new Insets(10));

        // Actions
        VBox options = new VBox(greeting1, buySellDeliver);
        options.setAlignment(Pos.BOTTOM_CENTER);
        options.setMargin(buySellDeliver, new Insets(15));

        // Set up root pane
        rootPane.setTop(marketName);
        rootPane.setBottom(backButton);
        rootPane.setCenter(options);
        rootPane.setMargin(marketName, new Insets(15));
        rootPane.setMargin(backButton, new Insets(15));
        rootPane.setAlignment(marketName, Pos.CENTER);
        rootPane.setAlignment(backButton, Pos.CENTER);
        rootPane.setAlignment(options, Pos.CENTER);

        // Set up buttons
        backButton.setOnAction(e -> {
            PlanetHub planetHub = new PlanetHub();
            planetHub.start(new Stage());
            mainStage.close();
        });

        buyButton.setOnAction(e -> {
            ItemList.sortBuyableMarket();

            BuyPage buyPage = new BuyPage();
            buyPage.start(new Stage());
            mainStage.close();
        });

        sellButton.setOnAction(e -> {
            SellPage sellPage = new SellPage();
            sellPage.start(new Stage());
            mainStage.close();
        });

        deliverButton.setOnAction(e -> {
            ItemList.sortBuyableMarket();

            DeliveryPage deliveryPage = new DeliveryPage();
            deliveryPage.start(new Stage());
            mainStage.close();
        });
        if (PlayerInfo.getCurrPlanet().isDeliveryBlacklist()) {
            deliverButton.setDisable(true);
        } else if (TravelledPlanets.getTravelled().size() == 1) {
            deliverButton.setDisable(true);
            deliverButton.setText("Travel Some More");
        }

        // Set up scene
        mainStage.setTitle("Marketplace");
        Scene scene = new Scene(rootPane, 450, 300);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private Planet planet() {
        return PlayerInfo.getCurrPlanet();
    }

    private String playerName() {
        return PlayerInfo.getName();
    }
}
