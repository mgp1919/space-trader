package src.sample.PoliceRelated;

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

import src.sample.ItemRelated.Item;
import src.sample.StartPages.PlayerInfo;

import java.util.ArrayList;
import java.util.Random;

public class PoliceHub extends Application {
    private int[] fineValues = {150, 200, 250};
    private int fine = fineValues[PlayerInfo.getDifficultyEnum().ordinal()];

    private double[] damagePercentageVals = {0.05, 0.1, 0.15};
    private double damagePercentage
            = damagePercentageVals[PlayerInfo.getDifficultyEnum().ordinal()];

    private double[] successModifiers = {1, 0.75, 0.5};
    private double successModifier = successModifiers[PlayerInfo.getDifficultyEnum().ordinal()];

    private double successFleeChance = 20 + PlayerInfo.getPilot() * successModifier;
    private double successFightChance = 20 + PlayerInfo.getFighter() * successModifier;

    private String situation;
    private double damage = PlayerInfo.getShip().getMaxHealth() * damagePercentage;

    public void start(Stage mainStage) {
        // Root pane
        BorderPane rootPane = new BorderPane();

        // Encounter Details
        Label infoText = new Label("You have been stopped by the police.");
        infoText.setFont(new Font(20));
        infoText.setTextAlignment(TextAlignment.CENTER);

        // Label
        Label encounterText = new Label("They claim to have found black market and stolen goods.");
        encounterText.setWrapText(true);
        encounterText.setFont(new Font(20));
        encounterText.setTextAlignment(TextAlignment.CENTER);

        int size = PlayerInfo.getShip().calculateStolenGoodsLoad();
        Label stolenStatus = new Label("Stolen Goods: " + size + " Item(s)");
        stolenStatus.setTextAlignment(TextAlignment.CENTER);
        stolenStatus.setFont(new Font(15));


        VBox info = new VBox(encounterText, stolenStatus);
        info.setMargin(encounterText, new Insets(5));
        info.setMargin(stolenStatus, new Insets(5));
        info.setAlignment(Pos.CENTER);

        // Pane and Buttons for options
        HBox options = new HBox();
        options.setSpacing(50);
        options.setAlignment(Pos.CENTER);
        Button forfeit = new Button("Forfeit Items");
        Button flee = new Button("Flee");
        Button fight = new Button("Fight");
        options.getChildren().addAll(forfeit, flee, fight);


        // Button functionality
        forfeit.setOnAction(e -> {
            forfeit();
            PoliceResultPage prp = new PoliceResultPage(situation);
            prp.start(new Stage());
            mainStage.close();
        });

        flee.setOnAction(e -> {
            flee();
            PoliceResultPage prp = new PoliceResultPage(situation);
            prp.start(new Stage());
            mainStage.close();
        });

        fight.setOnAction(e -> {
            fight();
            PoliceResultPage prp = new PoliceResultPage(situation);
            prp.start(new Stage());
            mainStage.close();
        });


        // Setting up root pane
        rootPane.setTop(infoText);
        rootPane.setMargin(infoText, new Insets(20));
        rootPane.setAlignment(infoText, Pos.CENTER);

        rootPane.setCenter(info);
        rootPane.setMargin(info, new Insets(20));
        rootPane.setAlignment(info, Pos.CENTER);

        rootPane.setBottom(options);
        rootPane.setMargin(options, new Insets(20));
        rootPane.setAlignment(options, Pos.CENTER);

        // Set up scene
        mainStage.setTitle("Police Encounter");
        Scene scene = new Scene(rootPane, 400, 300);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void forfeit() {
        situation = "You forfeited some of your items, illegal or not,"
                + " and proceeded on your journey.";
        ArrayList<Item> stolenGoods = PlayerInfo.getShip().getStolenGoods();
        ArrayList<Item> bMarketGoods = PlayerInfo.getShip().getBlackMarketGoods();
        ArrayList<Item> inventory = PlayerInfo.getShip().getInventory();
        if (stolenGoods.size() != 0 || bMarketGoods.size() != 0) {
            for (Item i: stolenGoods) {
                if (i.getQuantityStolen() == i.getQuantity()) {
                    i.setQuantity(0);
                    i.setQuantityStolen(0);
                    stolenGoods.remove(i);
                    inventory.remove(i);
                } else {
                    i.setQuantity(i.getQuantity() - i.getQuantityStolen());
                    stolenGoods.remove(i);
                }
            }

            for (Item i: bMarketGoods) {
                i.setQuantity(0);
                stolenGoods.remove(i);
                inventory.remove(i);
            }
        } else {
            boolean cont = true;
            while (cont && inventory.size() > 0) {
                int randItemIndex = (int) (Math.random() * inventory.size());
                Item randItem = inventory.get(randItemIndex);
                int randQuantity = (int) (Math.random() * randItem.getQuantity()) + 1;
                int newQuantity = randItem.getQuantity() - randQuantity;
                randItem.setQuantity(newQuantity);
                if (newQuantity == 0) {
                    inventory.remove(randItem);
                }
                Random rand = new Random();
                cont = rand.nextBoolean();
            }
        }
    }

    private void flee() {
        int randomVal = (int) (Math.random() * 100) + 1;
        if (randomVal <= successFleeChance) {
            situation = "You successfully fled the police but had to make a U-turn.";
            PlayerInfo.setCurrPlanet(PlayerInfo.getPrevPlanet());
        } else {
            situation = "You failed in fleeing the police. They have confiscated your stolen goods,"
                    + " damaged your ship by " + (int) damage + " points, and gave you a "
                    + fine + " credit fine. You still continued on.";
            PlayerInfo.getShip().damage((int) damage);
            PlayerInfo.setCredits(PlayerInfo.getCredits() - fine);
        }
    }

    private void fight() {
        int randomVal = (int) (Math.random() * 100) + 1;
        if (randomVal <= successFightChance) {
            situation = "You fought off the police and proceeded on your journey.";
        } else {
            PlayerInfo.getShip().damage((int) (damage * 2));
            PlayerInfo.setCredits(PlayerInfo.getCredits() - fine * 2);
            situation = "You failed in fighting the police. They have "
                    + "confiscated your stolen goods," + " damaged your ship by "
                    + (int) (damage * 2) + " points, and gave you a "
                    + fine * 2 + " credit fine. You still continued on.";
        }
    }
}
