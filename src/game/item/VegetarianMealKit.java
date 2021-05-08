package game.item;

public class VegetarianMealKit extends Food {
    public static final int PRICE = 100;
    public static final int FEED_POINTS = 160;
    public VegetarianMealKit() {
        super("VegetarianMealKit", '&');
    }

    public static int getFeedPoints() {
        return FEED_POINTS;
    }

    public int getPrice(){
        return PRICE;
    }
}
