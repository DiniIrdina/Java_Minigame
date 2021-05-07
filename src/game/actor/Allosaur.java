package game.actor;

import edu.monash.fit2099.engine.*;
import game.behaviour.WanderBehaviour;
import game.action.AttackAction;
import game.item.*;

import java.util.ArrayList;

public class Allosaur extends Dinosaur {
    public static final String SPECIES = "Allosaur";
    public static final int ADULT_AGE = 50;
    public static final int MAX_HIT_POINTS = 100;
    public static final int HUNGRY_LEVEL = 80;
    public static final int PREGNANT_LENGTH = 20;
    public static final char BABY_ALLOSAUR_DISPLAY = 'a';
    public static final char ADULT_ALLOSAUR_DISPLAY = 'A';
    private ArrayList<Dinosaur> attacked_dinosaur = new ArrayList<Dinosaur>();
    private ArrayList<Integer> attacked_dinosaur_count = new ArrayList<Integer>();

    public Allosaur(int age) {
        super(SPECIES, ADULT_ALLOSAUR_DISPLAY, age, MAX_HIT_POINTS, 50,PREGNANT_LENGTH,ADULT_AGE,ADULT_ALLOSAUR_DISPLAY) ;
        if(age<ADULT_AGE){
            this.displayChar = BABY_ALLOSAUR_DISPLAY;
        }
        behaviour = new WanderBehaviour();
    }

    public Allosaur(int age, char gender) {
        super(SPECIES, ADULT_ALLOSAUR_DISPLAY,gender, age, MAX_HIT_POINTS, 50,PREGNANT_LENGTH,ADULT_AGE,ADULT_ALLOSAUR_DISPLAY);
        if(age<ADULT_AGE){
            this.displayChar = BABY_ALLOSAUR_DISPLAY;
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
     * FIXME: Allosaur wanders around at random, or if no suitable MoveActions are available, it
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
        if (food instanceof Egg || food instanceof CarnivoreMealKit || food instanceof Corpse){
            result = true;
        }
        return result;
    }

    @Override
    public void eatsFood(Food food) {
        if (food instanceof Corpse){

        }
    }

    public void setAttacked_dinosaur(Dinosaur dinosaur){
        this.attacked_dinosaur.add(dinosaur);
    }

    public ArrayList<Dinosaur> getAttacked_dinosaur(){
        return this.attacked_dinosaur;
    }

    public void setAttacked_dinosaur_count(int count){
        this.attacked_dinosaur_count.add(count);
    }

    public ArrayList<Integer> getAttacked_dinosaur_count(){
        return this.attacked_dinosaur_count;
    }
}
