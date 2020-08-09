package src.sample.BanditRelated;

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

public class BanditHub extends Application {
    private int[] demandValues = {200, 250, 300};
    private int demand = demandValues[PlayerInfo.getDifficultyEnum().ordinal()];

    private double[] damagePercentageVals = {0.15, 0.2, 0.25};
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

        // Encounter Description
        Label encounterText = new Label("You have been stopped by a bandit.");
        encounterText.setFont(new Font(20));
        encounterText.setTextAlignment(TextAlignment.CENTER);

        // Demands
        Label demands = new Label("The bandit demands " + demand + " credits.");
        demands.setFont(new Font(20));

        Label currCredit = new Label("Current Credits: " + PlayerInfo.getCredits());
        currCredit.setFont(new Font(15));

        int size = PlayerInfo.getShip().calculateInventoryLoad();
        Label currInventoryStatus = new Label("Inventory: " + size + " Item(s)");
        currInventoryStatus.setTextAlignment(TextAlignment.CENTER);
        currInventoryStatus.setFont(new Font(15));


        VBox info = new VBox(demands, currCredit, currInventoryStatus);
        info.setMargin(demands, new Insets(5));
        info.setMargin(currCredit, new Insets(5));
        info.setMargin(currInventoryStatus, new Insets(5));
        info.setAlignment(Pos.CENTER);

        // Pane and Buttons for options
        HBox options = new HBox();
        options.setSpacing(50);
        options.setAlignment(Pos.CENTER);
        Button comply = new Button("Comply");
        Button flee = new Button("Flee");
        Button fight = new Button("Fight");
        options.getChildren().addAll(comply, flee, fight);


        // Button functionality
        comply.setOnAction(e -> {
            comply();
            BanditResultPage brp = new BanditResultPage(situation);
            brp.start(new Stage());
            mainStage.close();
        });

        flee.setOnAction(e -> {
            flee();
            BanditResultPage brp = new BanditResultPage(situation);
            brp.start(new Stage());
            mainStage.close();
        });

        fight.setOnAction(e -> {
            fight();
            BanditResultPage brp = new BanditResultPage(situation);
            brp.start(new Stage());
            mainStage.close();
        });


        // Setting up root pane
        rootPane.setTop(encounterText);
        rootPane.setAlignment(encounterText, Pos.CENTER);
        rootPane.setMargin(encounterText, new Insets(10));

        rootPane.setCenter(info);
        rootPane.setAlignment(info, Pos.CENTER);
        rootPane.setMargin(info, new Insets(20));

        rootPane.setBottom(options);
        rootPane.setAlignment(options, Pos.CENTER);
        rootPane.setMargin(options, new Insets(20));


        // Set up scene
        mainStage.setTitle("Bandit Encounter");
        Scene scene = new Scene(rootPane, 400, 300);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private void comply() {
        if (PlayerInfo.getCredits() >= demand) {
            PlayerInfo.setCredits(PlayerInfo.getCredits() - demand);

            situation = "You gave the bandit " + demand + " credits and continued on.";

        } else if (PlayerInfo.getShip().getInventory().size() == 0) {
            PlayerInfo.getShip().damage((int) damage);

            situation = "Your ship took " + (int) damage + " point(s) of damage but continued on.";
        } else {
            PlayerInfo.getShip().setInventory(new ArrayList<Item>());

            situation = "You lost all your items but continued on.";
        }
    }

    private void flee() {
        int randomVal = (int) (Math.random() * 100) + 1;
        if (randomVal <= successFleeChance) {
            PlayerInfo.setCurrPlanet(PlayerInfo.getPrevPlanet());

            situation = "You successfully fled the bandit but made a U-turn.";
        } else {
            PlayerInfo.getShip().damage((int) (damage));
            PlayerInfo.setCredits(0);

            situation = "You could not get away and the bandit stole all your credits and "
                    + "damaged your ship by " + (int) damage + " points."
                    + " But you continued on.";
        }
    }

    private void fight() {
        int randomVal = (int) (Math.random() * 100) + 1;
        if (randomVal <= successFightChance) {
            PlayerInfo.setCredits(PlayerInfo.getCredits() + (demand / 2));

            situation = "You fought off the bandit, took some of "
                    + "his credits and continued on.";
        } else {
            double damage = PlayerInfo.getShip().getMaxHealth() * damagePercentage;
            PlayerInfo.getShip().damage((int) (damage));
            PlayerInfo.setCredits(0);

            situation = "You couldn't fight off the bandit, and he took"
                    + " all your credits and damaged your ship by " + (int) damage + " points."
                    + " But you continued on.";
        }
    }
}
