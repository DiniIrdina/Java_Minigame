package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.item.Fruit;

public class PickFruitAction extends Action {
    private final double FRUIT_PROBABILITY = 0.6;

    @Override
    public String execute(Actor actor, GameMap map) {
        String output;
        double probability = Math.random();
        if (probability <= FRUIT_PROBABILITY) {
            actor.addItemToInventory(new Fruit());
            output = "Fruit collected!";
        }else{
            output = "You search the tree or bush for fruit, but you can't find any ripe ones.";
        }
        return output;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "You pick Fruit from Tree/Bush";
    }
}
