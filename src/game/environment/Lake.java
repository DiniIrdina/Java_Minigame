package game.environment;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actor.Pterodactyl;
import game.item.Fish;

import java.util.ArrayList;

public class Lake extends Ground {
    private ArrayList<Fish> fishPopulation = new ArrayList<>();
    private int sips;
    static final double NEW_FISH_PROBABILITY = 0.6;
    static final double RAIN_PROBABILITY = 0.2;
    static final double RAINFALL_LOW = 0.1;
    static final double RAINFALL_HIGH = 0.6;
    private int currentTurn;
    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        currentTurn = 1;
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

    @Override
    public boolean canActorEnter(Actor actor) {
        boolean result = false;
        if (actor instanceof Pterodactyl){
            result = true;
        }
        return result;
    }

    public void increaseSip(int moreSip){
        sips += moreSip;
    }

    public void tick(Location location){
        currentTurn += 1;
        super.tick(location);
        double fishProbability = Math.random();
        if (fishProbability <= NEW_FISH_PROBABILITY && fishPopulation.size() < 25){
            fishPopulation.add(new Fish());
        }
        if (currentTurn%10 == 0){
            double rainChance = Math.random();
            if (rainChance <= RAIN_PROBABILITY){
                double rainfall = Math.random();
                if (rainfall >= RAINFALL_LOW && rainfall <= RAINFALL_HIGH){
                    int sipAmount = (int)(rainfall * 20);
                    increaseSip(sipAmount);
                }

            }
        }
    }
}
