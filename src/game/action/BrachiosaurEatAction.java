package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Dinosaur;
import game.item.Fruit;

public class BrachiosaurEatAction extends Action {
    private Fruit food;

    @Override
    public String execute(Actor actor, GameMap map) {
        assert actor instanceof Dinosaur;

    }

    @Override
    public String menuDescription(Actor actor) {
        String output = actor + " ate a " + food;
        return null;
    }
}
