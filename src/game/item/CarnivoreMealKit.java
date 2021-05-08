package game.item;

public class CarnivoreMealKit extends Food {
    public static final int PRICE = 500;
    public static final int FEED_POINTS = 100;
    public CarnivoreMealKit() {
        super("CarnivoreMealKit", '^');
    }

    public static int getFeedPoints() {
        return FEED_POINTS;
    }

    public int getPrice(){
        return PRICE;
    }
}
