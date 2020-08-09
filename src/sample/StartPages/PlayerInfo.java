package src.sample.StartPages;

import src.sample.ItemRelated.EToolItem;
import src.sample.PlanetRelated.Planet;
import src.sample.ShipRelated.Ship;

public class PlayerInfo {

    public enum Difficulty { EASY, NORMAL, HARD }

    private static final int[] DIFF_CREDITS = {1500, 1000, 500};

    private static String name;
    private static Difficulty difficulty;
    private static int[] skillPoints;
    private static int credits;
    private static Planet currPlanet;
    private static Planet prevPlanet;
    private static Ship ship;
    private static EToolItem equipped;

    public static void initialize(String n, Difficulty d, int[] sp) {
        name = n;
        difficulty = d;
        skillPoints = sp;

        credits = DIFF_CREDITS[d.ordinal()];
    }

    public static void setCredits(int c) {
        credits = c;
    }

    public static String getName() {
        return name;
    }
    public static String getDifficulty() {
        return difficulty.toString().charAt(0) + difficulty.toString().substring(1).toLowerCase();
    }
    public static Difficulty getDifficultyEnum() {
        return difficulty;
    }

    public static int[] getSkillPoints() {
        return skillPoints;
    }
    public static int getPilot() {
        return skillPoints[0];
    }
    public static int getFighter() {
        return skillPoints[1];
    }
    public static int getMerchant() {
        return skillPoints[2];
    }
    public static int getEngineer() {
        return skillPoints[3];
    }

    public static int getCredits() {
        return credits;
    }
    public static Planet getCurrPlanet() {
        return currPlanet;
    }
    public static void setCurrPlanet(Planet planet) {
        currPlanet = planet;
    }
    public static Planet getPrevPlanet() {
        return prevPlanet;
    }
    public static void setPrevPlanet(Planet planet) {
        prevPlanet = planet;
    }

    public static Ship getShip() {
        return ship;
    }
    public static void setShip(Ship ship) {
        PlayerInfo.ship = ship;
    }

    public static EToolItem getEquipped() {
        return equipped; 
    }
    public static void setEquipped(EToolItem equipped) {
        PlayerInfo.equipped = equipped;
    }
}
