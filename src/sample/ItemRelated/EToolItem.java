package src.sample.ItemRelated;


import javafx.scene.control.Button;
import src.sample.StartPages.PlayerInfo;

public class EToolItem extends Item {
    private int[] arrBoost; //pilot, fighter, merchant, engineer
    private Button equipable;
    private Button unequipable;

    public EToolItem(String name, String descriptn, int baseCost, int minTechLevel,
                     String type, int[] arrBoost) {
        super(name, descriptn, baseCost, minTechLevel, type);
        this.arrBoost = arrBoost;
        equipable = new Button("Equip");
        unequipable = new Button("Unequip");
    }

    public void equip() {
        PlayerInfo.setEquipped(this);
        for (int i = 0; i < 4; i++) {
            PlayerInfo.getSkillPoints()[i] += arrBoost[i];
        }
        equipable.setDisable(true);
        unequipable = new Button("Unequip");
    }
    public void unequip() {
        if (PlayerInfo.getEquipped() != null) {
            PlayerInfo.setEquipped(null);
            for (int i = 0; i < 4; i++) {
                PlayerInfo.getSkillPoints()[i] -= arrBoost[i];
            }
        }
        unequipable.setDisable(true);
        equipable = new Button("Equip");
    }

    public int getPilotBoost() {
        return arrBoost[0];
    }
    public int getFighterBoost() {
        return arrBoost[1];
    }
    public int getMerchantBoost() {
        return arrBoost[2];
    }
    public int getEngineerBoost() {
        return arrBoost[3];
    }

    public Button getEquipable() {
        return equipable;
    }
    public Button getUnequipable() {
        return unequipable;
    }
}
