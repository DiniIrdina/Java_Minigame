package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.actor.Pterodactyl;
import game.environment.Tree;

import game.interfaces.NearestTree;
import game.item.Food;

import java.util.List;
import java.util.Random;

/**
 * Behaviour class specifically for Pterodactyl as it handles flying, landing on trees.
 */
public class PterodactylBehaviour implements Behaviour, NearestTree {
    private Random random = new Random();


    private int flyDuration;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        flyDuration = ((Pterodactyl) actor).getFlyDuration();
        Location location = map.locationOf(actor);
        Ground ground = location.getGround();
        List<Item> itemsHere = location.getItems();
        for (Item item : itemsHere) {
            if (item instanceof Food) {
                if (((Pterodactyl) actor).canEat((Food) item)) {
                    ((Pterodactyl) actor).setOnGround(true);
                    return new CarnivoreEatAction((Food) item);
                }
            }
        }

        Location nearTree = NearestTree.getNearestTree(actor, map);
        if (ground instanceof Tree) {
            ((Pterodactyl) actor).resetFlyDuration();
            ((Pterodactyl) actor).setOnGround(false);
        } else {
            if (flyDuration >= 30) {
                ((Pterodactyl) actor).setOnGround(true);
                return WanderBehaviour.moveTo(actor, map, location, nearTree);
            }
        }

        return WanderBehaviour.moveTo(actor, map, location, nearTree);

    }

}
