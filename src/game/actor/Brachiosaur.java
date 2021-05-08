package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.AttackAction;
import game.action.FeedingAction;
import game.behaviour.WanderBehaviour;
import game.interfaces.NeedsPlayer;
import game.item.*;

import static java.util.Objects.isNull;

public class Brachiosaur extends Dinosaur {
    public static final String SPECIES = "Brachiosaur";
    public static final int ADULT_AGE = 50;
    public static final int MAX_HIT_POINTS=160;
    public static final int HUNGRY_LEVEL = 140;
    public static final int PREGNANT_LENGTH = 30;
    public static final char  BABY_BRACHIOSAUR_DISPLAY = 'b';
    public static final char  ADULT_BRACHIOSAUR_DISPLAY = 'B';

    public Brachiosaur(int age) {
        super(SPECIES, ADULT_BRACHIOSAUR_DISPLAY, age, MAX_HIT_POINTS,100,PREGNANT_LENGTH, ADULT_AGE,ADULT_BRACHIOSAUR_DISPLAY);
        if (age<ADULT_AGE){
            this.displayChar = BABY_BRACHIOSAUR_DISPLAY;
        }
        behaviour = new WanderBehaviour();
    }

    public Brachiosaur(int age, char gender) {
        super(SPECIES, ADULT_BRACHIOSAUR_DISPLAY, gender,age, MAX_HIT_POINTS,100,PREGNANT_LENGTH, ADULT_BRACHIOSAUR_DISPLAY);
        if (age<ADULT_AGE){
            this.displayChar = BABY_BRACHIOSAUR_DISPLAY;
        }

        behaviour = new WanderBehaviour();
    }
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        actions = new Actions();
        actions.add(new AttackAction(this));
        Player player = NeedsPlayer.retrievePlayer(map);
        if (!isNull(player)){
            for(Item item: player.getInventory()){
                if (item instanceof Fruit){
                    actions.add(new FeedingAction(this,((Food)item),Fruit.getFeedPoints()));
                }else if (item instanceof VegetarianMealKit){
                    actions.add(new FeedingAction(this,((Food)item), VegetarianMealKit.getFeedPoints()));
                }
            }
        }
        return actions;

    }



    @Override
    public void assignBehaviour() {

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
        super.turn();
        for (Item item: inventory){
            if (item instanceof BrachiosaurEgg){
                this.isPregnant = true;
                break;
            }else{
                this.isPregnant = false;
            }
        }

        if (this.isPregnant){
            if (this.pregnancyCounter < 20){
                pregnancyTurn();
            }
            else {
                this.isPregnant = false;
                LayEgg(map.locationOf(this));
                removeEgg();
                this.pregnancyCounter = 0;
            }
        }

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

    public void LayEgg(Location location){
        location.addItem(new BrachiosaurEgg());
    }
}
