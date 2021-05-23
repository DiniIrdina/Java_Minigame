package game.environment;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actor.Pterodactyl;
import game.item.Fish;

import java.util.ArrayList;

/**
 * Class creation of the Lake object. The lake object will contain instances of fishes as an available food source
 * within the world.
 */
public class Lake extends Ground {
    private ArrayList<Fish> fishPopulation = new ArrayList<>();
    private int sips;
    static final double NEW_FISH_PROBABILITY = 0.6;
    static final double RAIN_PROBABILITY = 0.2;
    static final double RAINFALL_LOW = 0.1;
    static final double RAINFALL_HIGH = 0.6;
    final int RAIN_TURN = 10;
    private int currentTurn;
    /**
     * Overloading constructor of the Lake class. Lake will be represented by the char '~'.
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

    /**
     * Setter method that reduces the sips.
     */
    public void reduceSip(){
        sips -= 1;
    }

    /**
     * If the class allows the actors to pass through.
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        boolean result = false;
        if (actor instanceof Pterodactyl){
            result = true;
        }
        return result;
    }

    /**
     * Setter method that increases the sips.
     */
    public void increaseSip(int moreSip){
        sips += moreSip;
    }

    /**
     * Getter method that returns the amount of sips.
     * @return the current amount of sips
     */
    public int getSips(){
        return sips;
    }

    /**
     * Tick updates the current turn of game for the class object
     * @param location The location of the Ground
     */
    public void tick(Location location){
        currentTurn += 1;
        super.tick(location);
        double fishProbability = Math.random();
        if (fishProbability <= NEW_FISH_PROBABILITY && fishPopulation.size() < 25){
            fishPopulation.add(new Fish());
        }
        if (currentTurn%RAIN_TURN == 0){
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

    /**
     * Getter method that returns the total amount of fishes within the Lake block.
     * @return the total amount of fishes.
     */
    public ArrayList<Fish> getFishPopulation() {
        return fishPopulation;
    }

    /**
     * Setter method that reduces the total amount of fishes within the Lake block.
     * @param number the number of fishes to be reduced by.
     */
    public void reduceFishPopulation(int number){
        for (int i = 0; i < number; i++) {
            fishPopulation.remove(fishPopulation.size() - 1);
        }
    }
}
