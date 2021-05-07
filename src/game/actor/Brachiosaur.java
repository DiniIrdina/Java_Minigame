package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.AttackAction;
import game.actor.Dinosaur;
import game.behaviour.WanderBehaviour;
import game.item.Food;
import game.item.Fruit;
import game.item.VegetarianMealKit;

public class Brachiosaur extends Dinosaur {
    public static final String SPECIES = "Brachiosaur";
    public static final int ADULT_AGE = 50;
    public static final int MAX_HIT_POINTS=160;
    public static final int HUNGRY_LEVEL = 140;
    public static final int PREGNANT_LENGTH = 30;
    public static final char  BABY_BRACHIOSAUR_DISPLAY = 'b';
    public static final char  ADULT_BRACHIOSAUR_DISPLAY = 'B';

    public Brachiosaur(int age) {
        super(SPECIES, ADULT_BRACHIOSAUR_DISPLAY, age, MAX_HIT_POINTS,100,PREGNANT_LENGTH, ADULT_AGE);
        if (age<ADULT_AGE){
            this.displayChar = BABY_BRACHIOSAUR_DISPLAY;
        }
        behaviour = new WanderBehaviour();
    }

    public Brachiosaur(int age, char gender) {
        super(SPECIES, ADULT_BRACHIOSAUR_DISPLAY, gender,age, MAX_HIT_POINTS,100,PREGNANT_LENGTH, ADULT_AGE);
        if (age<ADULT_AGE){
            this.displayChar = BABY_BRACHIOSAUR_DISPLAY;
        }

        behaviour = new WanderBehaviour();
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(this));
    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Brachiosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }

    @Override
    public boolean canEat(Food food) {
        boolean result = false;
        if (food instanceof Fruit || food instanceof VegetarianMealKit){
            result = true;
        }
        return result;
    }

    @Override
    public void eatsFood(Food food) {
        if (food instanceof Fruit){
            heal(5);
        }else if(food instanceof VegetarianMealKit){
            heal(maxHitPoints-hitPoints);
        }
    }
}
