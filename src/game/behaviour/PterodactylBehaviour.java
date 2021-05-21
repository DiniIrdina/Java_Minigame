package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.actor.Pterodactyl;
import game.environment.Tree;

import game.item.Food;

import java.util.List;
import java.util.Random;

/**
 * Behaviour class specifically for Pterodactyl as it handles flying, landing on trees.
 */
public class PterodactylBehaviour implements Behaviour {
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

        Location nearestTree = getNearestTree(actor, map);
        if (ground instanceof Tree) {
            ((Pterodactyl) actor).resetFlyDuration();
            ((Pterodactyl) actor).setOnGround(false);
        } else {
            if (flyDuration >= 30) {
                ((Pterodactyl) actor).setOnGround(true);
                return WanderBehaviour.moveTo(actor, map, location, nearestTree);
            }
        }

        return WanderBehaviour.moveTo(actor, map, location, nearestTree);

    }

    /**
     * This method locates the nearest tree object relative to the current actor's position.
     *
     * @param actor the current selected actor
     * @param map   the map the actor is in
     * @return the nearest tree object within search radius
     */
    public Location getNearestTree(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        Location nearestTree = null;
        int shortestDistance = 999999;
        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                Location currentLocation = map.at(x, y);
                Ground ground = currentLocation.getGround();
                if (ground instanceof Tree) {
                    int currentDistance = FollowBehaviour.distance(location, currentLocation);
                    if (currentDistance < shortestDistance) {
                        nearestTree = currentLocation;
                        shortestDistance = currentDistance;
                    }
                }
            }
        }
        return nearestTree;
    }
}
