package game.environment;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.item.Fruit;

import java.util.ArrayList;

public class Bush extends Ground {
    private ArrayList<Fruit> fruits = new ArrayList<>();
    /**
     * Constructor
     */
    public Bush(){
        super('w');
    }

    public void tick(Location location){
        super.tick(location);
        double fruitPossibility = 0.1;

        double possibility = Math.random();
        if (possibility<=fruitPossibility && fruits.size() <= 5){
            fruits.add(new Fruit());
        }
    }
}
