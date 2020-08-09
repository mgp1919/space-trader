package src.sample.UseRelated;

import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.sample.ItemRelated.EToolItem;
import src.sample.ItemRelated.Item;
import src.sample.StartPages.PlayerInfo;

import java.util.ArrayList;
import java.util.List;

public class EquipPage extends Application {
    private List<HBox> items = new ArrayList<>();
    private ListView<HBox> itemsToEquip;
    private Label greeting;
    private HBox equippablesAndStats;
    private Button back;
    private VBox root;
    private VBox stats;

    public void start(Stage mainStage) {
        // Root pane
        root = new VBox();

        // Root Levels
        greeting = new Label("What would you like to equip?");
        greeting.setFont(new Font(20));
        greeting.setTextAlignment(TextAlignment.CENTER);
        equippablesAndStats = new HBox();
        back = new Button("Back");
        back.setOnAction(e -> {
            mainStage.close();
        });

        // Set up stats
        setStats();

        // ListView
        setBasicLV();

        // Set up equippableAndStats
        setEquippableAndStats();

        // Set up Root
        rootSetUp();

        // Set up scene
        mainStage.setTitle("Equipping");
        Scene scene = new Scene(root, 450, 500);
        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void rootSetUp() {
        root.getChildren().clear();
        root.getChildren().addAll(greeting, equippablesAndStats, back);
        root.setAlignment(Pos.CENTER);
        root.setMargin(greeting, new Insets(10));
        root.setMargin(equippablesAndStats, new Insets(10));
        root.setMargin(back, new Insets(10));
    }

    private void setBasicLV() {
        items.clear();
        for (Item i: PlayerInfo.getShip().getInventory()) {
            if (i instanceof EToolItem) {
                Label itemLabel = new Label(i.getName());

                Button equip = ((EToolItem) i).getEquipable();
                equip.setOnAction(e -> {
                    if (PlayerInfo.getEquipped() != null) {
                        PlayerInfo.getEquipped().unequip();
                    }
                    ((EToolItem) i).equip();

                    update();
                });

                HBox item = new HBox(itemLabel, equip);
                item.setAlignment(Pos.CENTER);
                item.setMargin(itemLabel, new Insets(5));
                item.setMargin(equip, new Insets(5));
                items.add(item);
            }
        }
        itemsToEquip = new ListView<HBox>(FXCollections.observableArrayList(items));
    }

    private void setStats() {
        Label equipped;
        Button unequip;
        if (PlayerInfo.getEquipped() == null) {
            equipped = new Label("[Empty]");
            unequip = new Button("Nothing to Unequip.");
            unequip.setDisable(true);
        } else {
            equipped = new Label(PlayerInfo.getEquipped().getName());
            unequip = PlayerInfo.getEquipped().getUnequipable();
            unequip.setOnAction(e -> {
                PlayerInfo.getEquipped().unequip();
                update();
            });
        }

        stats = new VBox(equipped, unequip);
        Label pilotPoints = new Label("Pilot: " + PlayerInfo.getPilot());
        Label fighterPoints = new Label("Fighter: " + PlayerInfo.getFighter());
        Label merchantPoints = new Label("Merchant: " + PlayerInfo.getMerchant());
        Label engineerPoints = new Label("Engineer: " + PlayerInfo.getEngineer());
        stats.getChildren().addAll(pilotPoints, fighterPoints, merchantPoints, engineerPoints);
        stats.setAlignment(Pos.CENTER);
        stats.setMargin(equipped, new Insets(5));
        stats.setMargin(unequip, new Insets(5));
        stats.setMargin(pilotPoints, new Insets(5));
        stats.setMargin(fighterPoints, new Insets(5));
        stats.setMargin(merchantPoints, new Insets(5));
        stats.setMargin(engineerPoints, new Insets(5));
    }

    private void setEquippableAndStats() {
        equippablesAndStats.getChildren().clear();
        equippablesAndStats.getChildren().addAll(itemsToEquip, stats);
        equippablesAndStats.setMargin(itemsToEquip, new Insets(5));
        equippablesAndStats.setMargin(stats, new Insets(5));
    }

    private void update() {
        setBasicLV();
        setStats();
        setEquippableAndStats();
        rootSetUp();
    }
}
