package game.item;

import edu.monash.fit2099.interfaces.VendingMachineItemsInterface;

public class CarnivoreMealKit extends Food implements VendingMachineItemsInterface {
    public static final int price = 500;
    public CarnivoreMealKit() {
        super("Carnivore Meal Kit", '^');
    }
}
