package src.sample.ItemRelated;

import javafx.stage.Stage;
import src.sample.EndGameRelated.WinPage;

public class WinConItem extends Item {
    public WinConItem(String name, String descriptn, int baseCost,
                       int minTechLevel, String type) {
        super(name, descriptn, baseCost, minTechLevel, type);
    }

    public void useItem() {
        super.useItem();
        WinPage win = new WinPage();
        win.start(new Stage());
    }
}
