package game.action;

import edu.monash.fit2099.engine.*;
import game.actor.Player;
import game.environment.Bush;
import game.environment.Tree;
import game.item.Fruit;

/**
 * This class represents an Action that allows the player to pick fruits from
 */
public class PickFruitAction extends Action {
    private final double FRUIT_PROBABILITY = 0.6;

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        Ground ground = location.getGround();

        String output;
        double probability = Math.random();
        if (probability <= FRUIT_PROBABILITY) {
            actor.addItemToInventory(new Fruit());
            Player.updateEcoPoints(10);
            if (ground instanceof Tree){
                ((Tree)ground).removeFruit();
            }else if (ground instanceof Bush){
                ((Bush)ground).removeFruit();
            }
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
