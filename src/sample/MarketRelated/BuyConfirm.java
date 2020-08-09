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
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.sample.BlackMarketRelated.BlackMarketBuyPage;
import src.sample.ItemRelated.Item;
import src.sample.StartPages.PlayerInfo;
import src.sample.TraderRelated.TraderBuyPage;

public class BuyConfirm extends Application {
    private Item relevantItem;
    private boolean haveEnough;
    private Application sourcePage;

    public BuyConfirm(Item item, boolean haveEnough, Application source) {
        relevantItem = item;
        this.haveEnough = haveEnough;
        sourcePage = source;
    }

    public void start(Stage mainStage) {
        // Root Pane
        BorderPane root = new BorderPane();
        // Center VBox
        VBox itemData = new VBox();
        //Button HBox
        HBox buttons = new HBox();

        // Components
        Label confirmationText = new Label("Confirm Purchase:");
        confirmationText.setFont(new Font(30));
        Label nameAndCost = new Label(relevantItem.toStringBuy());

        nameAndCost.setAlignment(Pos.CENTER);
        nameAndCost.setFont(new Font(20));
        Label description = new Label(" Description: " + relevantItem.getDescrptn());
        description.setFont(new Font(15));
        description.setAlignment(Pos.CENTER);
        Button confirmation = new Button("Confirm");
        Button back = new Button("Go Back");


        if (PlayerInfo.getShip().calculateInventoryLoad()
                == PlayerInfo.getShip().getInventoryCapacity()) {
            confirmation.setText("Cargo Bay is Full");
            confirmation.setDisable(true);
        } else if (!haveEnough) {
            confirmation.setText("Not Enough Credits");
            confirmation.setDisable(true);
        }

        confirmation.setOnAction(e -> {
            relevantItem.buy(relevantItem.getFinalCost());
            if (sourcePage instanceof BuyPage) {
                ((BuyPage) sourcePage).update();
            } else if (sourcePage instanceof TraderBuyPage) {
                ((TraderBuyPage) sourcePage).update();
            } else if (sourcePage instanceof BlackMarketBuyPage) {
                ((BlackMarketBuyPage) sourcePage).update();
            }
            ThanksForBusiness thanks = new ThanksForBusiness();
            thanks.start(new Stage());
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

        // Set scene
        Scene scene = new Scene(root, 500, 300);
        mainStage.setTitle("Confirmation");
        mainStage.initModality(Modality.APPLICATION_MODAL);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
