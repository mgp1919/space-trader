package src.sample.ShipRelated;

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

public class InventoryPage extends Application {
    private ArrayList<HBox> items = new ArrayList<>();
    private ListView<HBox> currentItems;
    private Label greeting;
    private Label currCredits;
    private HBox sorting;
    private Button back;
    private VBox root;
    private ItemSortHelper sorter;

    public void start(Stage mainStage) {
        // Root pane
        root = new VBox();

        // Root Levels
        greeting = new Label("Here are the items you currently own: ");
        greeting.setFont(new Font(20));
        greeting.setTextAlignment(TextAlignment.CENTER);
        currCredits = new Label("Current Credits: " + PlayerInfo.getCredits());
        currCredits.setFont(new Font(15));
        currCredits.setTextAlignment(TextAlignment.CENTER);
        sorting = new HBox();
        currentItems = new ListView<>();
        back = new Button("Back");
        back.setOnAction(e -> {
            ShipHub ship = new ShipHub();
            ship.start(new Stage());
            mainStage.close();
        });

        // Sorter
        sorter = new ItemSortHelper(items, "Use",
                PlayerInfo.getShip().getInventory(), this, mainStage);

        // ListView
        currentItems = sorter.setBasicLV();

        // Set up Sorting
        Label sortPrompt = new Label("Sort: ");
        Button eTool = new Button("Equippables");
        Button toolBox = new Button("Tool Boxes");
        Button permStat = new Button("Scrolls");
        Button shipFuel = new Button("Fuel");
        Button clear = new Button("Clear Filter");

        eTool.setOnAction(e -> {
            currentItems = sorter.setEToolItemLV();
            rootSetUp();
        });
        toolBox.setOnAction(e -> {
            currentItems = sorter.setToolBoxItemLV();
            rootSetUp();
        });
        permStat.setOnAction(e -> {
            currentItems = sorter.setPermStatItemLV();
            rootSetUp();
        });
        shipFuel.setOnAction(e -> {
            currentItems = sorter.setShipFuelItemLV();
            rootSetUp();
        });
        clear.setOnAction(e -> {
            currentItems = sorter.setBasicLV();
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
        mainStage.setTitle("Inventory");
        Scene scene = new Scene(root, 500, 600);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void rootSetUp() {
        root.getChildren().clear();
        root.getChildren().addAll(greeting, currCredits, sorting, currentItems, back);
        root.setAlignment(Pos.CENTER);
        root.setMargin(greeting, new Insets(10));
        root.setMargin(sorting, new Insets(10));
        root.setMargin(currentItems, new Insets(10));
        root.setMargin(back, new Insets(10));
    }

    public void update() {
        currentItems = sorter.setBasicLV();
        rootSetUp();
    }
}

