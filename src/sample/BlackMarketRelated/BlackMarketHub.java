package src.sample.BlackMarketRelated;

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
import src.sample.ItemRelated.ItemList;
import src.sample.PlanetRelated.Planet;
import src.sample.PlanetRelated.PlanetHub;
import src.sample.StartPages.PlayerInfo;

public class BlackMarketHub extends Application {

    public void start(Stage mainStage) {
        // Root pane
        BorderPane rootPane = new BorderPane();

        // Market name
        Label marketName = new Label(planet().getName() + "\'s Black Market");
        marketName.setWrapText(true);
        marketName.setFont(new Font(30));
        marketName.setTextAlignment(TextAlignment.CENTER);

        // Greeting
        Label greeting1 = new Label("Welcome, friend! What can I get for you? "
                + "I got things you can only imagine.");
        greeting1.setWrapText(true);
        greeting1.setFont(new Font(20));
        greeting1.setTextAlignment(TextAlignment.CENTER);


        // Buttons
        Button backButton = new Button("Back");
        Button buyButton = new Button("Buy");
        Button sellButton = new Button("Sell");
        HBox buySell = new HBox(buyButton, sellButton);
        buySell.setAlignment(Pos.CENTER);
        buySell.setMargin(buyButton, new Insets(10));
        buySell.setMargin(sellButton, new Insets(10));

        // Actions
        VBox options = new VBox(greeting1, buySell);
        options.setAlignment(Pos.BOTTOM_CENTER);
        options.setMargin(buySell, new Insets(15));

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
            ItemList.sortBuyableBMarket();

            BlackMarketBuyPage buyPage = new BlackMarketBuyPage();
            buyPage.start(new Stage());
            mainStage.close();
        });

        sellButton.setOnAction(e -> {
            BlackMarketSellPage sellPage = new BlackMarketSellPage();
            sellPage.start(new Stage());
            mainStage.close();
        });

        // Set up scene
        mainStage.setTitle("Black Market");
        Scene scene = new Scene(rootPane, 450, 300);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private Planet planet() {
        return PlayerInfo.getCurrPlanet();
    }
}
