package src.sample.UseRelated;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.sample.ItemRelated.*;
import src.sample.ShipRelated.InventoryPage;
import src.sample.ShipRelated.Ship;
import src.sample.StartPages.PlayerInfo;

public class UseConfirm {
    private Item relevantItem;
    private Application sourcePage;
    private Ship ship = PlayerInfo.getShip();
    private Stage inventoryStage;

    public UseConfirm(Item item, Application source, Stage stage) {
        relevantItem = item;
        sourcePage = source;
        inventoryStage = stage;
    }

    public void start(Stage mainStage) {
        // Root Pane
        BorderPane root = new BorderPane();

        // Center VBox
        VBox itemData = new VBox();

        //Button HBox
        HBox buttons = new HBox();

        // Components
        Label confirmationText = new Label("Confirm Usage: ");
        confirmationText.setFont(new Font(30));
        Label nameAndCost = new Label(relevantItem.toStringUse());
        nameAndCost.setAlignment(Pos.CENTER);
        nameAndCost.setFont(new Font(20));
        Label description = new Label(" Description: " + relevantItem.getDescrptn());
        description.setFont(new Font(15));
        description.setAlignment(Pos.CENTER);
        Button confirmation = new Button("Confirm");
        Button back = new Button("Go Back");

        confirmation.setOnAction(e -> {
            if (relevantItem instanceof ToolBoxItem) {
                relevantItem.useItem();
            } else if (relevantItem instanceof PermStatItem) {
                relevantItem.useItem();
            } else if (relevantItem instanceof ShipFuelItem) {
                relevantItem.useItem();
            } else if (relevantItem instanceof WinConItem) {
                relevantItem.useItem();
            }

            ((InventoryPage) sourcePage).update();
            if (relevantItem instanceof WinConItem) {
                inventoryStage.close();
            }
            mainStage.close();
        });

        back.setOnAction(e -> {
            mainStage.close();
        });

        // Set up VBox and HBox
        itemData.getChildren().addAll(nameAndCost, description);
        itemData.setAlignment(Pos.CENTER);
        itemData.setMargin(nameAndCost, new Insets(10));
        itemData.setMargin(description, new Insets(10));

        buttons.getChildren().addAll(back, confirmation);
        buttons.setAlignment(Pos.CENTER);
        buttons.setMargin(back, new Insets(10));
        buttons.setMargin(confirmation, new Insets(10));

        // Set Up Root
        root.setTop(confirmationText);
        root.setCenter(itemData);
        root.setBottom(buttons);
        root.setAlignment(confirmationText, Pos.CENTER);
        root.setAlignment(itemData, Pos.CENTER);
        root.setAlignment(buttons, Pos.CENTER);

        // Set up edge cases
        if (relevantItem instanceof ToolBoxItem && ship.atMaxHealth()) {
            confirmation.setText("Ship Health is Full");
            confirmation.setDisable(true);
        } else if (relevantItem instanceof ShipFuelItem && ship.atFullTank()) {
            confirmation.setText("Ship Fuel Tank is Full");
            confirmation.setDisable(true);
        }

        // Set scene
        Scene scene = new Scene(root, 500, 300);
        mainStage.setTitle("Confirmation");
        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.setScene(scene);
        mainStage.show();
    }
}