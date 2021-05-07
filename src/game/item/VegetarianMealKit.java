package game.item;

import edu.monash.fit2099.interfaces.VendingMachineItemsInterface;

public class VegetarianMealKit extends Food implements VendingMachineItemsInterface {
    public static final int price = 100;
    public static final int FEED_POINTS = 160;
    public VegetarianMealKit() {
        super("Vegetarian Meal Kit", '&');
    }

    public static int getFeedPoints() {
        return FEED_POINTS;
    }
}
