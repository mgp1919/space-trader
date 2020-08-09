package src.sample.ItemRelated;

import src.sample.StartPages.PlayerInfo;
import java.util.ArrayList;
import java.util.Random;

public class ItemList {
    private static ArrayList<Item> totalListMarket = new ArrayList<>();
    private static ArrayList<Item> totalListTrader = new ArrayList<>();
    private static ArrayList<Item> totalListBMarket = new ArrayList<>();
    private static ArrayList<Item> buyableList = new ArrayList<>();

    // Tool Boxes
    private static ToolBoxItem basicTB = new ToolBoxItem("Basic Tool Box",
            "Fixes your ship by 50 points.",
            30, 1, "Market", 50);
    private static ToolBoxItem stdTB = new ToolBoxItem("Standard Tool Box",
            "Fixes your ship by 100 points.",
            120, 3, "Market", 100);
    private static ToolBoxItem advTB = new ToolBoxItem("Advanced Tool Box",
            "Fixes your ship by 150 points.",
            210, 6, "Market", 150);

    private static ToolBoxItem traderTB = new ToolBoxItem("Trader Tool Box",
            "Fixes your ship by 225 points.",
            350, 10, "Trader", 225);

    private static ToolBoxItem blackMarketTB = new ToolBoxItem("Black Market Tool Box",
            "Fully fixes your ship.", 650, 10,
            "Black Market", PlayerInfo.getShip().getMaxHealth());

    // Equippable Tools
    private static EToolItem aviGoggles = new EToolItem("Aviator Goggles",
            "Boosts your pilot skills by 3 points.",
            150, 5, "Market", new int[]{3, 0, 0, 0});
    private static EToolItem bxGloves = new EToolItem("Boxing Gloves",
            "Boosts your fighter skills by 3 points.",
            150, 5, "Market", new int[]{0, 3, 0, 0});
    private static EToolItem coinPurse = new EToolItem("Coin Purse",
            "Boosts your merchant skills by 3 points.",
            150, 5, "Market", new int[]{0, 0, 3, 0});
    private static EToolItem wrench = new EToolItem("Wrench",
            "Boosts your engineer skills by 3 points.",
            150, 5, "Market", new int[]{0, 0, 0, 3});

    private static EToolItem traderAvi = new EToolItem("Aviator Headset",
            "Boosts your pilot skills by 5 points.",
            275, 10, "Trader", new int[]{5, 0, 0, 0});
    private static EToolItem traderGun = new EToolItem("Gun",
            "Boosts your fighter skills by 5 points.",
            275, 10, "Trader", new int[]{0, 5, 0, 0});
    private static EToolItem traderChest = new EToolItem("Treasure Chest",
            "Boosts your merchant skills by 5 points.",
            275, 10, "Trader", new int[]{0, 0, 5, 0});
    private static EToolItem traderWelding = new EToolItem("Welding Tool",
            "Boosts your engineer skills by 5 points.",
            275, 10, "Trader", new int[]{0, 0, 5, 0});

    private static EToolItem blackMarketTool = new EToolItem("Black Market Multi-Tool",
            "Boosts all your skills by 2 points.",
            550, 10, "Black Market", new int[]{2, 2, 2, 2});

    // Permanent Stat Items
    private static PermStatItem scrollFlight = new PermStatItem("Scroll of Flight",
            "Permanently boosts your pilot skills by 1 point.",
            525, 7, "Market", new int[]{1, 0, 0, 0});
    private static PermStatItem scrollAggro = new PermStatItem("Scroll of Aggression",
            "Permanently boosts your fighter skills by 1 point.",
            525, 7, "Market", new int[]{0, 1, 0, 0});
    private static PermStatItem scrollSavings = new PermStatItem("Scroll of Savings",
            "Permanently boosts your merchant skills by 1 point.",
            525, 7, "Market", new int[]{0, 0, 1, 0});
    private static PermStatItem scrollFix = new PermStatItem("Scroll of Fixing",
            "Permanently boosts your engineer skills by 1 point.",
            525, 7, "Market", new int[]{0, 0, 0, 1});

    private static PermStatItem traderPScroll = new PermStatItem("Advanced Scroll of Flight",
            "Permanently boosts your merchant skills by 2 points.",
            745, 10, "Trader", new int[]{2, 0, 0, 0});
    private static PermStatItem traderFScroll = new PermStatItem("Advanced Scroll of Aggression",
            "Permanently boosts your fighter skills by 2 point.",
            745, 10, "Trader", new int[]{0, 2, 0, 0});
    private static PermStatItem traderMScroll = new PermStatItem("Advanced Scroll of Savings",
            "Permanently boosts your merchant skills by 2 points.",
            745, 10, "Trader", new int[]{0, 0, 2, 0});
    private static PermStatItem traderEScroll = new PermStatItem("Advanced Scroll of Fixing",
            "Permanently boosts your engineer skills by 2 point.",
            745, 10, "Trader", new int[]{0, 0, 0, 2});

    private static EToolItem blackMarketScroll = new EToolItem("Black Market Scroll",
            "Permanently boosts all your skills by 1 point.",
            900, 10, "Black Market", new int[]{1, 1, 1, 1});

    // Ship Fuel
    private static ShipFuelItem smallFuel = new ShipFuelItem("Small Fuel Canister",
            "Refills your ship's fuel by 50 points.",
            30, 1, "Market", 50);
    private static ShipFuelItem normFuel = new ShipFuelItem("Normal Fuel Canister",
            "Refills your ship's fuel by 100 points.",
            120, 3, "Market", 100);
    private static ShipFuelItem lrgFuel = new ShipFuelItem("Large Fuel Canister",
            "Refills your ship's fuel by 150 points.",
            210, 6, "Market", 150);

    private static ShipFuelItem traderFuel = new ShipFuelItem("Trader Fuel Canister",
            "Refills your ship's fuel by 200 points",
            325, 10, "Trader", 200);

    private static ShipFuelItem blackMarketFuel = new ShipFuelItem("Black Market Fuel Canister",
            "Refills your ship's fuel by 100 percent",
            450, 10, "Black Market", PlayerInfo.getShip().getFuelCapacity());

    // Win Con
    private static WinConItem winCon = new WinConItem("The Hitchhiker's Guide",
            "Guides you on what not to do in space, like whatever the hell you've "
                    + "been doing. It's time to stop.", 4000, 1, "Market");

    public static void initTotal() {
        totalListMarket.add(basicTB);
        totalListMarket.add(stdTB);
        totalListMarket.add(advTB);
        totalListMarket.add(aviGoggles);
        totalListMarket.add(bxGloves);
        totalListMarket.add(coinPurse);
        totalListMarket.add(wrench);
        totalListMarket.add(scrollFlight);
        totalListMarket.add(scrollAggro);
        totalListMarket.add(scrollSavings);
        totalListMarket.add(scrollFix);
        totalListMarket.add(smallFuel);
        totalListMarket.add(normFuel);
        totalListMarket.add(lrgFuel);

        totalListTrader.add(traderTB);
        totalListTrader.add(traderFuel);
        totalListTrader.add(traderAvi);
        totalListTrader.add(traderGun);
        totalListTrader.add(traderChest);
        totalListTrader.add(traderWelding);
        totalListTrader.add(traderPScroll);
        totalListTrader.add(traderFScroll);
        totalListTrader.add(traderMScroll);
        totalListTrader.add(traderEScroll);

        totalListBMarket.add(blackMarketTB);
        totalListBMarket.add(blackMarketFuel);
        totalListBMarket.add(blackMarketTool);
        totalListBMarket.add(blackMarketScroll);
    }

    public static void sortBuyableMarket() {
        buyableList.clear();
        for (Item i: totalListMarket) {
            if (i.getMinTechLevel() <= PlayerInfo.getCurrPlanet().getTechLevel()) {
                if (!buyableList.contains(i)) {
                    buyableList.add(i);
                }
            }
        }
        if (PlayerInfo.getCurrPlanet().getWinCon()) {
            buyableList.add(winCon);
        }
    }
    public static void sortBuyableTrader() {
        buyableList.clear();
        for (Item i: totalListTrader) {
            Random rand = new Random();
            boolean add = rand.nextBoolean();

            if (add && !buyableList.contains(i)) {
                buyableList.add(i);
            }
        }
    }
    public static void sortBuyableBMarket() {
        buyableList.clear();
        for (Item i: totalListBMarket) {
            if (!buyableList.contains(i)) {
                buyableList.add(i);
            }
        }
    }

    public static ArrayList<Item> getBuyableList() {
        return buyableList;
    }
    public static ArrayList<Item> getTotalListMarket() {
        return totalListMarket;
    }
    public static ArrayList<Item> getTotalListTrader() {
        return totalListTrader;
    }
    public static ArrayList<Item> getTotalListBMarket() {
        return totalListBMarket;
    }

    public static void increasePrices() {
        for (Item i: buyableList) {
            i.setCost(i.getFinalCost() * 2);
        }
    }
    public static void decreasePrices() {
        for (Item i: buyableList) {
            i.setCost((int) (i.getFinalCost() * 0.5));
        }
    }
}
