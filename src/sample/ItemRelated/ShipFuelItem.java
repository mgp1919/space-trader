package src.sample.ItemRelated;

import src.sample.StartPages.PlayerInfo;

public class ShipFuelItem extends Item {
    private int fillingAmount;

    public ShipFuelItem(String name, String descriptn, int baseCost,
                        int minTechLevel, String type, int fillingAmount) {
        super(name, descriptn, baseCost, minTechLevel, type);
        this.fillingAmount = fillingAmount;
    }

    public int getFillingAmount() {
        return fillingAmount;
    }

    public void useItem() {
        super.useItem();
        PlayerInfo.getShip().refuel(fillingAmount);
    }
}
