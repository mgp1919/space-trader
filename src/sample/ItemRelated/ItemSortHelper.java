package src.sample.ItemRelated;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import src.sample.MarketRelated.BuyConfirm;
import src.sample.MarketRelated.SellConfirm;
import src.sample.StartPages.PlayerInfo;
import src.sample.UseRelated.EmptyEquipError;
import src.sample.UseRelated.EquipPage;
import src.sample.UseRelated.LastEquipError;
import src.sample.UseRelated.UseConfirm;

import java.util.ArrayList;
import java.util.List;

public class ItemSortHelper {
    private List<HBox> items;
    private String action;
    private ArrayList<Item> itemsToBeSorted;
    private Application sourcePage;
    private Stage stage;


    public ItemSortHelper(List<HBox> items, String action,
                          ArrayList<Item> itemsToBeSorted, Application sourcePage) {
        this.items = items;
        this.action = action;
        this.itemsToBeSorted = itemsToBeSorted;
        this.sourcePage = sourcePage;
    }

    public ItemSortHelper(List<HBox> items, String action,
                          ArrayList<Item> itemsToBeSorted, Application sourcePage,
                          Stage stage) {
        this(items, action, itemsToBeSorted, sourcePage);
        this.stage = stage;
    }

    public ListView<HBox> setBasicLV() {
        if (action.equals("Buy")) {
            return basicBuy();
        } else if (action.equals("Sell")) {
            return basicSell();
        } else {
            return basicUse();
        }
    }
    public ListView<HBox> setEToolItemLV() {
        if (action.equals("Buy")) {
            return eToolItemBuy();
        } else if (action.equals("Sell")) {
            return eToolItemSell();
        } else {
            return eToolItemUse();
        }
    }
    public ListView<HBox> setToolBoxItemLV() {
        if (action.equals("Buy")) {
            return toolBoxItemBuy();
        } else if (action.equals("Sell")) {
            return toolBoxItemSell();
        } else {
            return toolBoxItemUse();
        }
    }
    public ListView<HBox> setPermStatItemLV() {
        if (action.equals("Buy")) {
            return permStatItemBuy();
        } else if (action.equals("Sell")) {
            return permStatItemSell();
        } else {
            return permStatItemUse();
        }
    }
    public ListView<HBox> setShipFuelItemLV() {
        if (action.equals("Buy")) {
            return shipFuelItemBuy();
        } else if (action.equals("Sell")) {
            return shipFuelItemSell();
        } else {
            return shipFuelItemUse();
        }
    }

    private ListView<HBox> basicBuy() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            setUpItemBuy(i);
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> eToolItemBuy() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof EToolItem) {
                setUpItemBuy(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> toolBoxItemBuy() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof ToolBoxItem) {
                setUpItemBuy(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> permStatItemBuy() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof PermStatItem) {
                setUpItemBuy(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> shipFuelItemBuy() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof ShipFuelItem) {
                setUpItemBuy(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }

    private ListView<HBox> basicSell() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            setUpItemSell(i);
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> eToolItemSell() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof EToolItem) {
                setUpItemSell(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> toolBoxItemSell() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof ToolBoxItem) {
                setUpItemSell(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> permStatItemSell() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof PermStatItem) {
                setUpItemSell(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> shipFuelItemSell() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof ShipFuelItem) {
                setUpItemSell(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }

    private ListView<HBox> basicUse() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            setUpItemUse(i);
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> eToolItemUse() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof EToolItem) {
                setUpEItemUse(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> toolBoxItemUse() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof ToolBoxItem) {
                setUpItemUse(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> permStatItemUse() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof PermStatItem) {
                setUpItemUse(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }
    private ListView<HBox> shipFuelItemUse() {
        items.clear();
        for (Item i: itemsToBeSorted) {
            if (i instanceof ShipFuelItem) {
                setUpItemUse(i);
            }
        }
        return new ListView<HBox>(FXCollections.observableArrayList(items));
    }

    private void setUpItemBuy(Item i) {
        Label itemLabel = new Label(i.toStringBuy());

        Button itemBuyButton = i.getBuyButton();
        itemBuyButton.setOnAction(e -> {
            boolean haveEnough = PlayerInfo.getCredits() >= i.getFinalCost();
            BuyConfirm confirmation = new BuyConfirm(i, haveEnough, sourcePage);
            confirmation.start(new Stage());
        });

        HBox item = new HBox(itemLabel, itemBuyButton);
        item.setAlignment(Pos.CENTER);
        item.setMargin(itemLabel, new Insets(5));
        item.setMargin(itemBuyButton, new Insets(5));
        items.add(item);
    }
    private void setUpItemSell(Item i) {
        Label itemLabel = new Label(i.toStringSell());

        Button itemSellButton = i.getSellButton();
        itemSellButton.setOnAction(e -> {
            if (i instanceof EToolItem && i.getQuantity() == 1
                    && i == PlayerInfo.getEquipped()) {
                LastEquipError error = new LastEquipError();
                error.start(new Stage());
            } else {
                SellConfirm confirmation = new SellConfirm(i, sourcePage);
                confirmation.start(new Stage());
            }
        });

        HBox item = new HBox(itemLabel, itemSellButton);
        item.setAlignment(Pos.CENTER);
        item.setMargin(itemLabel, new Insets(5));
        item.setMargin(itemSellButton, new Insets(5));
        items.add(item);
    }
    private void setUpItemUse(Item i) {
        Label itemLabel = new Label(i.toStringUse());

        Button itemUseButton = i.getUseButton();
        itemUseButton.setOnAction(e -> {
            UseConfirm confirm = new UseConfirm(i, sourcePage, stage);
            confirm.start(new Stage());
        });

        HBox item = new HBox(itemLabel, itemUseButton);
        item.setAlignment(Pos.CENTER);
        item.setMargin(itemLabel, new Insets(5));
        item.setMargin(itemUseButton, new Insets(5));
        items.add(item);
    }
    private void setUpEItemUse(Item i) {
        Label itemLabel = new Label(i.toStringUse());

        Button itemUseButton = i.getUseButton();
        itemUseButton.setText("Go to Equip");
        itemUseButton.setOnAction(e -> {
            boolean eToolPresent = false;
            for (Item j: PlayerInfo.getShip().getInventory()) {
                if (j instanceof EToolItem) {
                    eToolPresent = true;
                }
            }

            if (eToolPresent) {
                EquipPage page = new EquipPage();
                page.start(new Stage());
            } else {
                EmptyEquipError page = new EmptyEquipError();
                page.start(new Stage());
            }
        });

        HBox item = new HBox(itemLabel, itemUseButton);
        item.setAlignment(Pos.CENTER);
        item.setMargin(itemLabel, new Insets(5));
        items.add(item);
    }
}
