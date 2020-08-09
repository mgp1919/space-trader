package src.sample.PlanetRelated;

import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Planet {
    private String name;
    private String description;
    private int techLevel;
    private int rowIndex;
    private int columnIndex;
    private Button mapButton = new Button();
    private boolean travelled;
    private boolean winCon = false;
    private boolean deliveryBlacklist = false;

    public Planet() {
        name = "Planet-";
        Random rand = new Random();
        while (name.length() < 13) {
            Boolean num = rand.nextBoolean();
            if (num) {
                name = name + ((int) (Math.random() * 10));
            } else {
                name = name + (char) ((int) (Math.random() * 26 + 65));
            }
        }
        techLevel = (int) (Math.random() * 10 + 1);
        mapButton.setShape(new Circle(1));
    }

    public Planet(boolean winCon) {
        this();
        this.winCon = winCon;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getTechLevel() {
        return techLevel;
    }
    public int getRowIndex() {
        return rowIndex;
    }
    public int getColumnIndex() {
        return columnIndex;
    }
    public Button getMapButton() {
        return mapButton;
    }
    public boolean getTravelled() {
        return travelled;
    }
    public boolean getWinCon() {
        return winCon;
    }
    public boolean isDeliveryBlacklist() {
        return deliveryBlacklist;
    }
    public void setDeliveryBlacklist(boolean deliveryBlacklist) {
        this.deliveryBlacklist = deliveryBlacklist;
    }

    public void setRowIndex(int i) {
        rowIndex = i;
    }
    public void setColumnIndex(int i) {
        columnIndex = i;
    }
    public void setTravelled(boolean bool) {
        travelled = bool;
    }
    public void setDescription() {
        description = "One of the ten planets in this galaxy.\nCoordinates: "
                + (char) (rowIndex + 64) + columnIndex;
    }

    public static double distance(Planet planet1, Planet planet2) {
        double columnDistance = Math.abs(planet1.getColumnIndex() - planet2.getColumnIndex());
        double rowDistance = Math.abs(planet1.getRowIndex() - planet2.getRowIndex());
        return Math.sqrt(columnDistance * columnDistance + rowDistance * rowDistance);
    }
}
