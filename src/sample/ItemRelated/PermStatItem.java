package src.sample.ItemRelated;

import src.sample.StartPages.PlayerInfo;

public class PermStatItem extends Item {
    private int[] arrBoost; //pilot, fighter, merchant, engineer

    public PermStatItem(String name, String descriptn, int baseCost, int minTechLevel,
                        String type, int[] arrBoost) {
        super(name, descriptn, baseCost, minTechLevel, type);
        this.arrBoost = arrBoost;
    }

    public int[] getArrBoost() {
        return arrBoost;
    }

    public void useItem() {
        super.useItem();
        for (int i = 0; i < 4; i++) {
            PlayerInfo.getSkillPoints()[i] += arrBoost[i];
        }
    }
}
