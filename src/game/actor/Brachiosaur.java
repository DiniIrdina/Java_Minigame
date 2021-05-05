package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.AttackAction;
import game.actor.Dinosaur;
import game.item.Food;
import game.item.Fruit;
import game.item.VegetarianMealKit;

public class Brachiosaur extends Dinosaur {
    String species = "Brachiosaur";
    public Brachiosaur() {
        super("Brachiosaur", 'B', 160);
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
    public String getSpecies() {
        return null;
    }

    @Override
    public boolean Attackable() {
        return false;
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
