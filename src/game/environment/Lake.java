package game.environment;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.item.Fish;

import java.util.ArrayList;

public class Lake extends Ground {
    private ArrayList<Fish> fishPopulation = new ArrayList<>();
    private int sips;
    static final double NEW_FISH_PROBABILITY = 0.6;
    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        sips = 25;
        fishPopulation.add(new Fish());
        fishPopulation.add(new Fish());
        fishPopulation.add(new Fish());
        fishPopulation.add(new Fish());
        fishPopulation.add(new Fish());

    }

    public void reduceSip(){
        sips -= 1;
    }

    public void tick(Location location){
        super.tick(location);
        double fishProbability = Math.random();
        if (fishProbability <= NEW_FISH_PROBABILITY){
            fishPopulation.add(new Fish());
        }
    }
}
