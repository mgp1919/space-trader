package src.sample.ItemRelated;

import src.sample.StartPages.PlayerInfo;

public class ToolBoxItem extends Item {
    private int fixingAmount;

    public ToolBoxItem(String name, String descriptn, int baseCost,
                       int minTechLevel, String type, int fixingAmount) {
        super(name, descriptn, baseCost, minTechLevel, type);
        this.fixingAmount = fixingAmount;
    }

    public int getFixingAmount() {
        return fixingAmount;
    }

    public void useItem() {
        super.useItem();
        PlayerInfo.getShip().heal(fixingAmount);
    }

    public int getFinalCost() {
        int initCost = super.getFinalCost();
        double alterFactor = 1 - (0.05 * PlayerInfo.getEngineer());
        int finalCost = (int) (initCost * alterFactor);

        if (finalCost < getMinCost()) {
            return getMinCost();
        } else {
            return finalCost;
        }
    }
    public int getSellPrice() {
        return (int) (getFinalCost() * 0.8);
    }
}
