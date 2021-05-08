package game.item;

import game.interfaces.VendingMachineItemsInterface;

public class VegetarianMealKit extends Food implements VendingMachineItemsInterface {
    public static final int PRICE = 100;
    public static final int FEED_POINTS = 160;
    public VegetarianMealKit() {
        super("Vegetarian Meal Kit", '&');
    }

    public static int getFeedPoints() {
        return FEED_POINTS;
    }
}
