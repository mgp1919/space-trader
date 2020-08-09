package src.sample.ShipRelated;

import src.sample.ItemRelated.Item;
import src.sample.StartPages.PlayerInfo;

import java.util.ArrayList;

public class Ship /*extends Item*/ {

    private final String name;

    private ArrayList<Item> inventory;
    private ArrayList<Item> stolenGoods;
    private ArrayList<Item> blackMarketGoods;
    private final int maxHealth;
    private final int inventoryCapacity;
    private final int fuelCapacity;

    private int health;
    private int currentFuel;

    private final int shipEfficiency = 20;   // The rate of fuel consumption per tile travelled

    public Ship(String name,
                int baseCost, int minCost, int minTechLevel,
                int health, int inventoryCapacity, int fuelCapacity) {

        this.name = name;
        //super(name, "A ship", baseCost, minTechLevel); // Worry about ship transactions later

        this.maxHealth = health;
        this.inventoryCapacity = inventoryCapacity;
        this.fuelCapacity = fuelCapacity;

        this.health = maxHealth;
        this.currentFuel = fuelCapacity;

        inventory = new ArrayList<>();
        stolenGoods = new ArrayList<>();
        blackMarketGoods = new ArrayList<>();
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public ArrayList<Item> getStolenGoods() {
        return stolenGoods;
    }
    public ArrayList<Item> getBlackMarketGoods() {
        return blackMarketGoods;
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }
    public void setInventory(ArrayList<Item> arr) {
        inventory = arr;
    }

    public String getName() {
        return name;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }
    public int getShipEfficiency() {
        return shipEfficiency;
    }
    public int getCurrentFuel() {
        return currentFuel;
    }
    public void setCurrentFuel(int currentFuel) {
        this.currentFuel = currentFuel;
    }
    public void refuel(int refill) {
        currentFuel += refill;
        if (currentFuel > fuelCapacity) {
            currentFuel = fuelCapacity;
        }
    }

    public int getCurrentHealth() {
        return health;
    }
    public void damage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
    public void heal(int heal) {
        health += heal;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public int calculateInventoryLoad() {
        int load = 0;
        for (Item i: inventory) {
            load += i.getQuantity();
        }

        return load;
    }
    public int calculateStolenGoodsLoad() {
        int load = 0;
        for (Item i: stolenGoods) {
            load += i.getQuantityStolen();
        }

        return load;
    }

    public double getMaxDistance() {
        return ((double) fuelCapacity) / shipEfficiency;
    }
    public double getMaxTravellable() {
        return ((double) currentFuel) / shipEfficiency;
    }
    public double getPercentage() {
        return ((double) currentFuel) * 100 / fuelCapacity;
    }
    public void travel(double distance) {
        currentFuel -= (distance * (shipEfficiency - PlayerInfo.getPilot() * 0.3));
    }

    public boolean atMaxHealth() {
        return health == maxHealth;
    }
    public boolean atFullTank() {
        return currentFuel == fuelCapacity;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
