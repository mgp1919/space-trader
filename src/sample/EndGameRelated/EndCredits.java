package src.sample.EndGameRelated;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import src.sample.MusicRelated.Music;
import src.sample.MusicRelated.MusicList;
import src.sample.StartPages.ConfigurationScreen;

public class EndCredits extends Application {

    public void start(Stage mainStage) {
        // Root Pane
        VBox root = new VBox();

        // Label
        Label thanks = new Label("Thank You for Playing!");
        thanks.setFont(new Font(50));
        thanks.setPadding(new Insets(15));

        // Credits
        VBox credits = new VBox();
        Label spacer1 = new Label(" ");
        spacer1.setFont(new Font(300));
        Label youngho = new Label("Youngho Lim as............Youngho");
        youngho.setFont(new Font(20));
        Label justin = new Label("Justin DeSimpliciis as............Justin");
        justin.setFont(new Font(20));
        Label curie = new Label("Curie Kim as............Curie");
        curie.setFont(new Font(20));
        Label mihir = new Label("Mihir Paramesh as............Mihir");
        mihir.setFont(new Font(20));
        Label raj = new Label("Raj Srivastava as............Raj");
        raj.setFont(new Font(20));
        Label spacer2 = new Label(" ");
        spacer2.setFont(new Font(75));
        Label merchant = new Label("Justin DeSimpliciis as............Market Merchant");
        merchant.setFont(new Font(20));
        Label trader = new Label("Mihir Paramesh as............Trader");
        trader.setFont(new Font(20));
        Label police = new Label("Curie Kim as............Police");
        police.setFont(new Font(20));
        Label bandit = new Label("Raj Srivastava as............Bandit");
        bandit.setFont(new Font(20));
        Label ship = new Label("Youngho Lim as............Ship");
        ship.setFont(new Font(20));
        Label spacer3 = new Label(" ");
        spacer3.setFont(new Font(150));
        Label spacer4 = new Label(" ");
        spacer4.setFont(new Font(120));
        Button playAgain = new Button("Play Again?");
        playAgain.setOnAction(e -> {
            Music.clear();
            MusicList.initialize();
            Music backgroundMusic = new Music(MusicList.getFileName());
            backgroundMusic.start(new Stage());
            ConfigurationScreen configScreen = new ConfigurationScreen();
            configScreen.start(new Stage());
            mainStage.close();
        });

        //Sets up credits VBox
        credits.getChildren().addAll(spacer1, youngho, justin, curie, mihir,
                raj, spacer2, merchant, trader, police, bandit, ship, spacer3,
                playAgain, spacer4);
        credits.setAlignment(Pos.CENTER);
        credits.setPadding(new Insets(7.5));

        // Scroll Pane
        ScrollPane creditsPane = new ScrollPane(credits);
        creditsPane.setFitToWidth(true);
        creditsPane.setPannable(false);
        creditsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        creditsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Set Up Root
        root.getChildren().addAll(thanks, creditsPane);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Set Up Scene
        Scene scene = new Scene(root, 1000, 550);
        mainStage.setScene(scene);
        mainStage.show();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        KeyValue kv = new KeyValue(creditsPane.vvalueProperty(), creditsPane.getVmax());
        KeyFrame kf = new KeyFrame(Duration.millis(10000), kv);
        timeline.getKeyFrames().addAll(kf);
        timeline.play();
    }
}
