package src.sample.TraderRelated;

import src.sample.ItemRelated.Item;
import src.sample.ItemRelated.ItemList;
import src.sample.StartPages.PlayerInfo;

import java.util.ArrayList;
import java.util.Random;

public class Trader {
    private int randomVal;
    private boolean negotiated = false;
    private ArrayList<Item> buyableList;

    private double[] damagePercentageVals = {0.1, 0.2, 0.3};
    private double damagePercentage
            = damagePercentageVals[PlayerInfo.getDifficultyEnum().ordinal()];

    private double[] successModifiers = {1, 0.75, 0.5};
    private double successModifier = successModifiers[PlayerInfo.getDifficultyEnum().ordinal()];

    private double successNegChance = 40 + PlayerInfo.getMerchant() * successModifier;
    private double successRobChance = 15 + PlayerInfo.getFighter() * successModifier;

    private double damage = PlayerInfo.getShip().getMaxHealth() * damagePercentage;

    public Trader() {
        ItemList.sortBuyableTrader();
        this.buyableList = ItemList.getBuyableList();
    }

    public String negotiate() {
        negotiated = true;
        randomVal = (int) (Math.random() * 100) + 1;
        if (randomVal <= successNegChance) {
            ItemList.decreasePrices();
            return "You succeeded in negotiating with the trader! The trader lowered his prices.";
        } else {
            ItemList.increasePrices();
            return "You failed in negotiating. The upset trader increased all the prices.";
        }
    }
    public String rob() {
        randomVal = (int) (Math.random() * 100) + 1;
        if (randomVal <= successRobChance) {
            boolean cont = true;
            int times = 0;
            while (cont) {
                int randItemIndex = (int) (Math.random() * buyableList.size());
                buyableList.get(randItemIndex).steal();

                times++;

                Random rand = new Random();
                cont = rand.nextBoolean();
            }

            return "You successfully robbed the trader, stealing " + times + " items, "
                    + "and proceeded to continue on.";
        } else {
            PlayerInfo.getShip().damage((int) damage);
            return "You failed to rob the trader, and he damaged your ship by "
                    + (int) damage + " points, but you still proceeded to your destination.";
        }
    }

    public void setNegotiated(boolean negotiated) {
        this.negotiated = negotiated;
    }
    public boolean hasNegotiated() {
        return negotiated;
    }

    public void resetPrices() {
        for (Item i: buyableList) {
            i.setCost(i.getBaseCostOriginal());
        }
    }

    public ArrayList<Item> getBuyableList() {
        return buyableList;
    }
}
