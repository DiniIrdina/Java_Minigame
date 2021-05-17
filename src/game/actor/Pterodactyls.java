package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.behaviour.WanderBehaviour;
import game.item.Food;

public class Pterodactyls extends Dinosaur{
    public static final String SPECIES = "Pterodactyl";
    public static final int ADULT_AGE = 50;
    public static final int MAX_HIT_POINTS = 100;
    public static final int HUNGRY_LEVEL = 80;
    public static final int PREGNANT_LENGTH = 20;
    public static final char BABY_PTERODACTYL_DISPLAY = 'p';
    public static final char ADULT_PTERODACTYL_DISPLAY = 'P';
    public static final int BREEDING_LEVEL =50;
    public static final int UNCONSCIOUS_LIMIT =20;

    public Pterodactyls(int age) {
        super(SPECIES, ADULT_PTERODACTYL_DISPLAY, age, MAX_HIT_POINTS, 50,PREGNANT_LENGTH,ADULT_AGE,
                BABY_PTERODACTYL_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT,HUNGRY_LEVEL) ;
        if(age<ADULT_AGE){
            this.displayChar = BABY_PTERODACTYL_DISPLAY;
        }
        behaviour = new WanderBehaviour();
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public boolean canEat(Food food) {
        return false;
    }

    @Override
    public void eatsFood(Food food) {

    }
}
