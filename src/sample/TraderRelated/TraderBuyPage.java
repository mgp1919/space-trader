package src.sample.TraderRelated;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import src.sample.ItemRelated.*;
import src.sample.StartPages.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

public class TraderBuyPage extends Application {
    private List<HBox> items = new ArrayList<>();
    private ListView<HBox> itemsToBuy;
    private Label greeting;
    private Label currCredits;
    private HBox sorting;
    private HBox options;
    private Button back;
    private Button negotiate;
    private VBox root;
    private Trader trader;
    private ItemSortHelper sorter;

    public TraderBuyPage(Trader trader) {
        this.trader = trader;
        sorter = new ItemSortHelper(items, "Buy",
                trader.getBuyableList(), this);
    }

    public void start(Stage mainStage) {
        // Root pane
        root = new VBox();

        // Root Levels
        greeting = new Label("What would you like to buy?");
        greeting.setFont(new Font(20));
        greeting.setTextAlignment(TextAlignment.CENTER);
        currCredits = new Label("Current Credits: " + PlayerInfo.getCredits());
        currCredits.setFont(new Font(15));
        currCredits.setTextAlignment(TextAlignment.CENTER);
        sorting = new HBox();
        options = new HBox();
        itemsToBuy = new ListView<>();
        back = new Button("Back");
        negotiate = new Button("Negotiate");

        if (trader.hasNegotiated()) {
            negotiate.setDisable(true);
        }

        negotiate.setOnAction(e -> {
            negotiate.setDisable(true);
            trader.setNegotiated(true);
            TraderResultPage traderResultPage = new TraderResultPage(trader.negotiate(), this);
            this.update();
            traderResultPage.start(new Stage());
        });

        back.setOnAction(e -> {
            TraderHub tHub = new TraderHub(true, trader);
            tHub.start(new Stage());
            mainStage.close();
        });

        // ListView
        itemsToBuy = sorter.setBasicLV();

        // Set up Sorting
        Label sortPrompt = new Label("Sort: ");
        Button eTool = new Button("Equippables");
        Button toolBox = new Button("Tool Boxes");
        Button permStat = new Button("Scrolls");
        Button shipFuel = new Button("Fuel");
        Button clear = new Button("Clear Filter");

        eTool.setOnAction(e -> {
            itemsToBuy = sorter.setEToolItemLV();
            rootSetUp();
        });
        toolBox.setOnAction(e -> {
            itemsToBuy = sorter.setToolBoxItemLV();
            rootSetUp();
        });
        permStat.setOnAction(e -> {
            itemsToBuy = sorter.setPermStatItemLV();
            rootSetUp();
        });
        shipFuel.setOnAction(e -> {
            itemsToBuy = sorter.setShipFuelItemLV();
            rootSetUp();
        });
        clear.setOnAction(e -> {
            itemsToBuy = sorter.setBasicLV();
            rootSetUp();
        });

        // Set up Sorting
        sorting.getChildren().addAll(sortPrompt, clear, toolBox, permStat, shipFuel, eTool);
        sorting.setAlignment(Pos.CENTER);
        sorting.setMargin(sortPrompt, new Insets(5));
        sorting.setMargin(eTool, new Insets(5));
        sorting.setMargin(toolBox, new Insets(5));
        sorting.setMargin(permStat, new Insets(5));
        sorting.setMargin(shipFuel, new Insets(5));
        sorting.setMargin(clear, new Insets(5));

        // Set up options
        options.getChildren().addAll(back, negotiate);
        options.setAlignment(Pos.CENTER);
        options.setMargin(back, new Insets(5, 10, 5, 10));
        options.setMargin(negotiate, new Insets(5, 10, 5, 10));

        // Set up Root
        rootSetUp();

        // Set up scene
        mainStage.setTitle("Buying");
        Scene scene = new Scene(root, 500, 600);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void rootSetUp() {
        root.getChildren().clear();
        currCredits = new Label("Current Credits: " + PlayerInfo.getCredits());
        root.getChildren().addAll(greeting, currCredits, sorting, itemsToBuy, options);
        root.setAlignment(Pos.CENTER);
        root.setMargin(greeting, new Insets(10));
        root.setMargin(sorting, new Insets(10));
        root.setMargin(itemsToBuy, new Insets(10));
        root.setMargin(options, new Insets(10));
    }

    public void update() {
        itemsToBuy = sorter.setBasicLV();
        rootSetUp();
    }
}
