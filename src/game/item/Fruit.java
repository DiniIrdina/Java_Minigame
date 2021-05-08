package game.item;

import game.interfaces.VendingMachineItemsInterface;

public class Fruit extends Food implements VendingMachineItemsInterface {
    public static final int PRICE = 30;
    public static final int FEED_POINTS = 20;
    public Fruit(){
        super("Fruit", '*');
    }
    private int age;

    public static int getFeedPoints() {
        return FEED_POINTS;
    }

    public int getPrice(){
        return PRICE;
    }
}
