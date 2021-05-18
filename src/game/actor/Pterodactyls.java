package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.behaviour.WanderBehaviour;
import game.item.Corpse;
import game.item.Fish;
import game.item.Food;

public class Pterodactyls extends Dinosaur{
    static final String SPECIES = "Pterodactyl";
    static final int ADULT_AGE = 20;
    static final int MAX_HIT_POINTS=100;
    static final int HUNGRY_LEVEL = 90;
    static final int PREGNANT_LENGTH = 10;
    static final char BABY_PTERODACTYL_DISPLAY = 'p';
    static final char ADULT_PTERODACTYL_DISPLAY = 'P';
    static final int BREEDING_LEVEL =50;
    static final int UNCONSCIOUS_LIMIT =20;
    static final int MAX_THIRST = 100;
    static final int STARTING_THIRST = 60;


    public Pterodactyls(int age) {
        super(SPECIES, ADULT_PTERODACTYL_DISPLAY, age, MAX_HIT_POINTS,100,PREGNANT_LENGTH, ADULT_AGE,
                ADULT_PTERODACTYL_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT,HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST);    }

    public Pterodactyls(int age, char gender) {
        super(SPECIES, ADULT_PTERODACTYL_DISPLAY, gender,age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE,
                ADULT_PTERODACTYL_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT, HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST);
        if (age < ADULT_AGE){
            this.displayChar = BABY_PTERODACTYL_DISPLAY;
        }
        behaviour = new WanderBehaviour();
    }

    @java.lang.Override
    public boolean canEat(Food food) {
        boolean result = false;
        if (food instanceof Fish || food instanceof Corpse){
            result = true;
        }
        return result;
    }

    @java.lang.Override
    public void eatsFood(Food food) {

    }

    @java.lang.Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
