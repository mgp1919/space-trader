package src.sample.TraderRelated;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import src.sample.ShipRelated.ShipHub;
import src.sample.StartPages.PlayerInfo;

public class TraderHub  extends Application {
    private boolean sawGoods;
    private Trader trader;

    public TraderHub() {
        sawGoods = false;
        trader = new Trader();
    }

    public TraderHub(boolean sawGoods) {
        this.sawGoods = sawGoods;
        trader = new Trader();
    }

    public TraderHub(boolean sawGoods, Trader trader) {
        this.sawGoods = sawGoods;
        this.trader = trader;
    }

    public void start(Stage mainStage) {

        // Root pane
        BorderPane rootPane = new BorderPane();

        // Context
        Label context = new Label("You encountered a trader.");
        context.setWrapText(true);
        context.setFont(new Font(30));
        context.setTextAlignment(TextAlignment.CENTER);

        // Greeting
        Label greeting1 = new Label("Hello, " + playerName()
                + "! What would you like to do today?");
        greeting1.setWrapText(true);
        greeting1.setFont(new Font(20));
        greeting1.setTextAlignment(TextAlignment.CENTER);


        // Buttons
        Button buyButton = new Button("Buy");
        Button ignoreContinueButton;
        if (sawGoods) {
            ignoreContinueButton = new Button("Continue Travelling");
        } else {
            ignoreContinueButton = new Button("Ignore");
        }
        Button robButton = new Button("Rob");
        HBox buyIgnoreRob = new HBox(buyButton, ignoreContinueButton, robButton);
        buyIgnoreRob.setAlignment(Pos.CENTER);
        buyIgnoreRob.setMargin(buyButton, new Insets(10));
        buyIgnoreRob.setMargin(ignoreContinueButton, new Insets(10));
        buyIgnoreRob.setMargin(robButton, new Insets(10));

        // Set up root pane
        rootPane.setTop(context);
        rootPane.setMargin(context, new Insets(15));
        rootPane.setAlignment(context, Pos.CENTER);

        rootPane.setBottom(buyIgnoreRob);
        rootPane.setMargin(buyIgnoreRob, new Insets(15));
        rootPane.setAlignment(buyIgnoreRob, Pos.CENTER);

        rootPane.setCenter(greeting1);
        rootPane.setMargin(greeting1, new Insets(15));
        rootPane.setAlignment(greeting1, Pos.CENTER);

        // Set up buttons
        buyButton.setOnAction(e -> {
            TraderBuyPage traderBuyPage = new TraderBuyPage(trader);
            traderBuyPage.start(new Stage());
            mainStage.close();
        });

        ignoreContinueButton.setOnAction(e -> {
            trader.resetPrices();
            ShipHub hub = new ShipHub();
            hub.start(new Stage());
            mainStage.close();
        });

        robButton.setOnAction(e -> {
            TraderResultPage traderResultPage = new TraderResultPage(trader.rob(), this);
            traderResultPage.start(new Stage());
            mainStage.close();
        });

        // Set up scene
        mainStage.setTitle("Marketplace");
        Scene scene = new Scene(rootPane, 450, 300);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private String playerName() {
        return PlayerInfo.getName();
    }
}
