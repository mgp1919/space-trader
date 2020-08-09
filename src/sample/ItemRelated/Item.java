package src.sample.ItemRelated;

import javafx.scene.control.Button;
import src.sample.StartPages.PlayerInfo;

public class Item {
    private String name;
    private String descrptn;
    private int baseCost;
    private int baseCostOriginal;
    private int minCost = 5;
    private int minTechLevel;
    private int quantity;
    private int quantityStolen;
    private Button buyButton;
    private Button sellButton;
    private Button useButton;
    private String type;

    public Item() {
        this("Unnamed Item", "None", 1000, 100,
                "Market");
    }

    public Item(String name, String descrptn, int baseCost, int minTechLevel,
                String type) {
        this.name = name;
        this.descrptn = descrptn;
        this.baseCost = baseCost;
        this.baseCostOriginal = baseCost;
        this.minTechLevel = minTechLevel;
        this.type = type;

        quantity = 0;
        quantityStolen = 0;
        buyButton = new Button("Buy");
        sellButton = new Button("Sell");
        useButton = new Button("Use");
    }

    public String getName() {
        return name;
    }
    public String getDescrptn() {
        return descrptn;
    }
    public int getMinTechLevel() {
        return minTechLevel;
    }
    public String getType() {
        return type;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantityStolen() {
        return quantityStolen;
    }
    public void setQuantityStolen(int quantityStolen) {
        this.quantityStolen = quantityStolen;
    }

    public int getFinalCost() {
        double alterFactor;
        if (type.equals("Market") || type.equals("Black Market")) {
            alterFactor = 1 - 0.01 * (PlayerInfo.getCurrPlanet().getTechLevel()
                    - this.minTechLevel) - 0.003 * (PlayerInfo.getMerchant());
        } else {
            alterFactor = 1 - 0.003 * (PlayerInfo.getMerchant());
        }
        int finalCost = (int) (baseCost * alterFactor);

        if (finalCost < minCost) {
            return minCost;
        } else {
            return finalCost;
        }
    }
    public int getBaseCost() {
        return baseCost;
    }
    public int getBaseCostOriginal() {
        return baseCostOriginal;
    }
    public int getMinCost() {
        return minCost;
    }
    public int getSellPrice() {
        return (int) (getFinalCost() * 0.8);
    }

    public Button getBuyButton() {
        return buyButton;
    }
    public Button getSellButton() {
        return sellButton;
    }
    public Button getUseButton() {
        return useButton; 
    }

    public void buy(int cost) {
        if (!PlayerInfo.getShip().getInventory().contains(this)) {
            PlayerInfo.getShip().getInventory().add(this);

            if (getType().equals("Black Market")) {
                PlayerInfo.getShip().getBlackMarketGoods().add(this);
            }
        }

        quantity += 1;
        PlayerInfo.setCredits(PlayerInfo.getCredits() - cost);
    }
    public void sell() {
        quantity -= 1;
        if (quantity == 0) {
            PlayerInfo.getShip().getInventory().remove(this);
        }

        PlayerInfo.setCredits(PlayerInfo.getCredits() + this.getSellPrice());
    }
    public void steal() {
        if (!PlayerInfo.getShip().getInventory().contains(this)) {
            PlayerInfo.getShip().getInventory().add(this);
        }
        if (!PlayerInfo.getShip().getStolenGoods().contains(this)) {
            PlayerInfo.getShip().getStolenGoods().add(this);
        }
        quantity += 1;
        quantityStolen += 1;
    }
    public void setCost(int newCost) {
        baseCost = newCost;
    }
    public void useItem() {
        quantity -= 1;
        if (quantity == 0) {
            PlayerInfo.getShip().getInventory().remove(this);
        }
    }

    public String toStringBuy() {
        return name + "    Cost: " + this.getFinalCost() + "    Quantity: " + this.getQuantity();
    }
    public String toStringSell() {
        return name + "    Price: " + this.getSellPrice() + "    Quantity: " + this.getQuantity();
    }
    public String toStringUse() {
        return name + "    Quantity: " + this.getQuantity()
                + "    Stolen Amount: " + this.getQuantityStolen();
    }
}
