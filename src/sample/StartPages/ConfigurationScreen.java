package src.sample.StartPages;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents a configuration screen GUI.
 * @author Raj Srivastava
 * @version 1.0
 */

public class ConfigurationScreen extends Application {

    private int maxSkillPoints = 12;
    private int[] skillPoints = new int[4];

    /**
     * This method describes what to do when the program is started
     * @param primaryStage is the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        BorderPane basePane = new BorderPane();

        Label instructions = new Label("Design Your Character:");
        instructions.setFont(new Font(50));

        VBox namePortion = new VBox();
        Text textName = new Text("Enter your player name:");
        TextField name = new TextField();
        name.setMaxWidth(200);
        name.setPromptText("Name");
        namePortion.getChildren().addAll(textName, name);
        namePortion.setSpacing(10);
        namePortion.setAlignment(Pos.CENTER);

        VBox difficultySelector = new VBox();
        Label difficultyLabel = new Label("Difficulty:");
        ToggleGroup difficulties = new ToggleGroup();
        RadioButton easy = new RadioButton(PlayerInfo.Difficulty.values()[0].toString());
        RadioButton normal = new RadioButton(PlayerInfo.Difficulty.values()[1].toString());
        RadioButton hard = new RadioButton(PlayerInfo.Difficulty.values()[2].toString());

        easy.setToggleGroup(difficulties);
        normal.setToggleGroup(difficulties);
        hard.setToggleGroup(difficulties);
        normal.setSelected(true);
        difficultySelector.getChildren().addAll(difficultyLabel, easy, normal, hard);
        difficultySelector.setAlignment(Pos.CENTER);


        VBox skillPointsContainer = new VBox();
        Label totalPoints = new Label("Skill Points: " + maxSkillPoints);
        HBox stats = new HBox();
        Label pilotLabel = new Label("Pilot:");
        Label fighterLabel = new Label("Fighter:");
        Label merchantLabel = new Label("Merchant:");
        Label engineerLabel = new Label("Engineer:");
        TextField pilotPoints = new TextField("0");
        TextField fighterPoints = new TextField("0");
        TextField merchantPoints = new TextField("0");
        TextField engineerPoints = new TextField("0");
        pilotPoints.setPrefColumnCount(2);
        fighterPoints.setPrefColumnCount(2);
        merchantPoints.setPrefColumnCount(2);
        engineerPoints.setPrefColumnCount(2);
        stats.getChildren().addAll(pilotLabel, pilotPoints, fighterLabel, fighterPoints,
                merchantLabel, merchantPoints, engineerLabel, engineerPoints);
        stats.setSpacing(10);
        stats.setAlignment(Pos.CENTER);
        skillPointsContainer.getChildren().addAll(totalPoints, stats);
        skillPointsContainer.setAlignment(Pos.CENTER);
        skillPointsContainer.setSpacing(5);

        difficulties.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable,
                                Toggle oldValue, Toggle newValue) {
                RadioButton newSelected = (RadioButton) difficulties.getSelectedToggle();
                PlayerInfo.Difficulty dif = PlayerInfo.Difficulty.valueOf(newSelected.getText());
                if (dif == PlayerInfo.Difficulty.EASY) {
                    maxSkillPoints = 15;
                } else if (dif == PlayerInfo.Difficulty.NORMAL) {
                    maxSkillPoints = 12;
                } else if (dif == PlayerInfo.Difficulty.HARD) {
                    maxSkillPoints = 9;
                }
                totalPoints.setText("Skill Points: " + maxSkillPoints);
            }
        });

        VBox confirm = new VBox();
        Button confirmButton = new Button("Enter");
        Label errorField = new Label();
        errorField.setTextFill(Color.RED);
        confirmButton.setOnAction(e -> {

            if (pilotPoints.getText().equals("")) {
                pilotPoints.setText("0");
            }
            if (fighterPoints.getText().equals("")) {
                fighterPoints.setText("0");
            }
            if (merchantPoints.getText().equals("")) {
                merchantPoints.setText("0");
            }
            if (engineerPoints.getText().equals("")) {
                engineerPoints.setText("0");
            }

            try {
                skillPoints[0] = Integer.valueOf(pilotPoints.getText());
                skillPoints[1] = Integer.valueOf(fighterPoints.getText());
                skillPoints[2] = Integer.valueOf(merchantPoints.getText());
                skillPoints[3] = Integer.valueOf(engineerPoints.getText());
            } catch (Exception ohno) {
                errorField.setText("Error: Invalid input for skill points!");
                return;
            }
            int totalSkillPoints = skillPoints[0]
                                + skillPoints[1]
                                + skillPoints[2]
                                + skillPoints[3];

            if (name.getText().equals("")) {
                errorField.setText("Error: Name cannot be empty!");
            } else if (totalSkillPoints > maxSkillPoints) {
                errorField.setText("Error: Cannot allocate that many skill points!");
            } else if (totalSkillPoints < maxSkillPoints) {
                errorField.setText("Error: Do not waste your skill points!");
            } else {
                errorField.setText("");

                // Acquire data from radio button
                RadioButton selectedRadioButton = (RadioButton) difficulties.getSelectedToggle();
                String difficultyText = selectedRadioButton.getText();
                PlayerInfo.Difficulty diff = PlayerInfo.Difficulty.valueOf(difficultyText);

                PlayerInfo.initialize(name.getText(), diff, skillPoints);


                // Spawn Char screen and close Config screen
                CharacterScreen characterScreen = new CharacterScreen();
                characterScreen.start(new Stage());
                primaryStage.close();
            }
        });
        confirm.getChildren().addAll(confirmButton, errorField);
        confirm.setAlignment(Pos.CENTER);

        Separator sep = new Separator(Orientation.HORIZONTAL);
        VBox mid = new VBox();
        difficultySelector.setPadding(new Insets(30, 0, 30, 0));
        skillPointsContainer.setPadding(new Insets(30, 0, 0, 0));
        mid.getChildren().addAll(namePortion, difficultySelector, sep, skillPointsContainer,
                confirm);
        mid.setAlignment(Pos.CENTER);

        basePane.setTop(instructions);
        basePane.setCenter(mid);
        basePane.setBottom(confirm);
        basePane.setAlignment(instructions, Pos.CENTER);
        basePane.setMargin(namePortion, new Insets(25, 25, 0, 25));
        basePane.setMargin(confirm, new Insets(30));

        Scene scene = new Scene(basePane, 1000, 500);
        primaryStage.setTitle("Character Configuration Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
