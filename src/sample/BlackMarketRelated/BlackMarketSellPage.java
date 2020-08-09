package src.sample.BlackMarketRelated;

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
import src.sample.ItemRelated.ItemSortHelper;
import src.sample.MarketRelated.MarketHub;
import src.sample.StartPages.PlayerInfo;

import java.util.ArrayList;

public class BlackMarketSellPage extends Application {
    private ArrayList<HBox> items = new ArrayList<>();
    private ListView<HBox> itemsToSell;
    private Label greeting;
    private Label currCredits;
    private HBox sorting;
    private Button back;
    private VBox root;
    private ItemSortHelper sorter = new ItemSortHelper(items, "Sell",
            PlayerInfo.getShip().getInventory(), this);

    public void start(Stage mainStage) {
        // Root pane
        root = new VBox();

        // Root Levels
        greeting = new Label("What would you like to sell?");
        greeting.setFont(new Font(20));
        greeting.setTextAlignment(TextAlignment.CENTER);
        currCredits = new Label("Current Credits: " + PlayerInfo.getCredits());
        currCredits.setFont(new Font(15));
        currCredits.setTextAlignment(TextAlignment.CENTER);
        sorting = new HBox();
        itemsToSell = new ListView<>();
        back = new Button("Back");
        back.setOnAction(e -> {
            MarketHub mHub = new MarketHub();
            mHub.start(new Stage());
            mainStage.close();
        });

        // ListView
        itemsToSell = sorter.setBasicLV();

        // Set up Sorting
        Label sortPrompt = new Label("Sort: ");
        Button eTool = new Button("Equippables");
        Button toolBox = new Button("Tool Boxes");
        Button permStat = new Button("Scrolls");
        Button shipFuel = new Button("Fuel");
        Button clear = new Button("Clear Filter");

        eTool.setOnAction(e -> {
            itemsToSell = sorter.setEToolItemLV();
            rootSetUp();
        });
        toolBox.setOnAction(e -> {
            itemsToSell = sorter.setToolBoxItemLV();
            rootSetUp();
        });
        permStat.setOnAction(e -> {
            itemsToSell = sorter.setPermStatItemLV();
            rootSetUp();
        });
        shipFuel.setOnAction(e -> {
            itemsToSell = sorter.setShipFuelItemLV();
            rootSetUp();
        });
        clear.setOnAction(e -> {
            itemsToSell = sorter.setBasicLV();
            rootSetUp();
        });

        sorting.getChildren().addAll(sortPrompt, clear, toolBox, permStat, shipFuel, eTool);
        sorting.setAlignment(Pos.CENTER);
        sorting.setMargin(sortPrompt, new Insets(5));
        sorting.setMargin(eTool, new Insets(5));
        sorting.setMargin(toolBox, new Insets(5));
        sorting.setMargin(permStat, new Insets(5));
        sorting.setMargin(shipFuel, new Insets(5));
        sorting.setMargin(clear, new Insets(5));

        // Set up Root
        rootSetUp();

        // Set up scene
        mainStage.setTitle("Selling");
        Scene scene = new Scene(root, 500, 600);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void rootSetUp() {
        root.getChildren().clear();
        currCredits = new Label("Current Credits: " + PlayerInfo.getCredits());
        root.getChildren().addAll(greeting, currCredits, sorting, itemsToSell, back);
        root.setAlignment(Pos.CENTER);
        root.setMargin(greeting, new Insets(10));
        root.setMargin(sorting, new Insets(10));
        root.setMargin(itemsToSell, new Insets(10));
        root.setMargin(back, new Insets(10));
    }

    public void update() {
        itemsToSell = sorter.setBasicLV();
        rootSetUp();
    }
}
