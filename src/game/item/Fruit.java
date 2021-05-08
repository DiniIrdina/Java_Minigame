package game.item;

import edu.monash.fit2099.engine.Location;
import game.interfaces.VendingMachineItemsInterface;

public class Fruit extends Food implements VendingMachineItemsInterface {
    public static final int PRICE = 30;
    public static final int FEED_POINTS = 20;
    private int age;
    public Fruit(){
        super("Fruit", '*');
    }


    public static int getFeedPoints() {
        return FEED_POINTS;
    }

    public void tick(Location location){
        age++;
        Location fruitLocation = getLocation(location.map(), this);
        if (age>=15){
            location.removeItem(this);
        }
    }
}
