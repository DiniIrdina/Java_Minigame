package game.environment;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.item.Fruit;

import java.util.ArrayList;

/**
 * Class creation for the Bush objects found in the game. Bushes will generate fruits based upon
 * a set probability.
 */
public class Bush extends Ground {
    /**
     * Array list to store the fruit objects
     */
    private ArrayList<Fruit> fruits = new ArrayList<>();
    private int currentTurn;
    private final double RAIN_PROBABILITY = 0.2;
    private final double FRUIT_GROWTH_PROBABILITY = 0.1;
    private final int RAIN_TURN = 10;
    private boolean isRaining;
    /**
     * Overloading constructor of the Bush class. Bush will be represented by the char 'w'.
     */
    public Bush(){
        super('w');
        isRaining = false;
        currentTurn = 1;
    }

    /**
     * Removes the fruit from the array list
     */
    public void removeFruit(){
        fruits.remove(fruits.size()-1);
    }

    /**
     * Tick updates the current turn of game for the class object
     * @param location The location of the Ground
     */
    public void tick(Location location){
        super.tick(location);

        currentTurn++;
        double possibility = Math.random();
        if (possibility<= FRUIT_GROWTH_PROBABILITY && fruits.size() <= 5){
            fruits.add(new Fruit());
        }

        if (currentTurn%RAIN_TURN == 0){
            double rainChance = Math.random();
            if (rainChance <= RAIN_PROBABILITY){
                isRaining = true;
            }
        }else{
            isRaining = false;
        }
    }

    public boolean isRaining() {
        return isRaining;
    }
}
