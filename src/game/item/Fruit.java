package game.item;

import edu.monash.fit2099.engine.Location;

public class Fruit extends Food {
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

    public void tick(Location location){
        age++;
        if (age>=15){
            location.removeItem(this);
        }
    }
}
