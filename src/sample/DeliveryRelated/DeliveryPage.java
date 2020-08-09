package src.sample.DeliveryRelated;

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
import src.sample.ItemRelated.Item;
import src.sample.ItemRelated.ItemList;
import src.sample.MarketRelated.MarketHub;
import src.sample.PlanetRelated.Planet;
import src.sample.PlanetRelated.TravelledPlanets;
import src.sample.StartPages.PlayerInfo;

import java.util.Random;

public class DeliveryPage extends Application {
    public void start(Stage mainStage) {
        // Root pane
        BorderPane rootPane = new BorderPane();

        // Picking the Planet
        int randomVal;
        double distance = 0;
        Planet deliveryPlanet = PlayerInfo.getCurrPlanet();
        while (PlayerInfo.getCurrPlanet() == deliveryPlanet) {
            randomVal = (int) (Math.random() * TravelledPlanets.getTravelled().size());
            deliveryPlanet = TravelledPlanets.getTravelled().get(randomVal);
            distance = Planet.distance(PlayerInfo.getCurrPlanet(), deliveryPlanet);
        }
        double distanceForLambda = distance;

        // Picking the Item
        randomVal = (int) (Math.random() * ItemList.getBuyableList().size());
        Item deliveryItem = ItemList.getBuyableList().get(randomVal);

        // Payment
        int[] payments = {500, 400, 300};
        int payment = payments[PlayerInfo.getDifficultyEnum().ordinal()];

        // Description
        Label description = new Label("Could you deliver this " + deliveryItem.getName()
                + " to " + deliveryPlanet.getName() + "? I'll pay you " + payment + " credits.");
        description.setWrapText(true);
        description.setFont(new Font(20));
        description.setTextAlignment(TextAlignment.CENTER);


        // Buttons
        Button refuseButton = new Button("Refuse");
        Button stealButton = new Button("Agree with Intent to Steal");
        Button agreeButton = new Button("Agree");
        HBox refuseStealAgree = new HBox(refuseButton, stealButton, agreeButton);
        refuseStealAgree.setAlignment(Pos.CENTER);
        refuseStealAgree.setMargin(refuseButton, new Insets(10));
        refuseStealAgree.setMargin(stealButton, new Insets(10));
        refuseStealAgree.setMargin(agreeButton, new Insets(10));

        // Set up root pane
        rootPane.setCenter(description);
        rootPane.setBottom(refuseStealAgree);
        rootPane.setAlignment(refuseButton, Pos.CENTER);
        rootPane.setAlignment(refuseStealAgree, Pos.CENTER);

        // Set up buttons
        refuseButton.setOnAction(e -> {
            MarketHub marketHub = new MarketHub();
            marketHub.start(new Stage());
            mainStage.close();
        });

        stealButton.setOnAction(e -> {
            Random random = new Random();
            String result;
            if (random.nextBoolean()) {
                result = "You agree to deliver it, but but you pocket it "
                        + "instead and no one is none the wiser. You return some "
                        + "time later to collect your payment.";
                PlayerInfo.setCredits(PlayerInfo.getCredits() + payment);
                deliveryItem.steal();
            } else {
                result = "You thought no one was watching but lo and behold, "
                        + "the shopkeeper saw you. You get no payment and "
                        + "he will now refuse to let you do any more deliveries.";
                PlayerInfo.getCurrPlanet().setDeliveryBlacklist(true);
            }

            DeliveryResultPage resultPage = new DeliveryResultPage(result);
            resultPage.start(new Stage());
            mainStage.close();
        });

        agreeButton.setOnAction(e -> {
            String result = "You deliver the item, then return to pick up "
                    + "your payment.";
            PlayerInfo.setCredits(PlayerInfo.getCredits() + payment);
            PlayerInfo.getShip().travel(distanceForLambda);
            PlayerInfo.getShip().travel(distanceForLambda); // twice since round trip

            DeliveryResultPage resultPage = new DeliveryResultPage(result);
            resultPage.start(new Stage());
            mainStage.close();
        });

        // Check
        if (PlayerInfo.getShip().getMaxTravellable() < 2 * distance) {
            agreeButton.setDisable(true);
            agreeButton.setText("Not enough fuel");
        }

        // Set up scene
        mainStage.setTitle("Delivery");
        Scene scene = new Scene(rootPane, 450, 300);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
