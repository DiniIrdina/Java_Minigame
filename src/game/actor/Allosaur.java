package game.actor;

import edu.monash.fit2099.engine.*;
import game.behaviour.WanderBehaviour;
import game.action.AttackAction;
import game.item.*;

public class Allosaur extends Dinosaur {
    String species = "Allosaur";
    public Allosaur() {
        super("Allosaur", 'A', 100);
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
}
