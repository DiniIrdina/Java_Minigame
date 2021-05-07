package game.item;

import edu.monash.fit2099.interfaces.VendingMachineItemsInterface;

public class VegetarianMealKit extends Food implements VendingMachineItemsInterface {
    public static final int price = 100;
    public VegetarianMealKit() {
        super("Vegetarian Meal Kit", '&');
    }
}
