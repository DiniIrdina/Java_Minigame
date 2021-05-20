package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.action.DrinkAction;
import game.actor.Pterodactyl;
import game.environment.Lake;
import game.environment.Tree;
import game.item.Corpse;
import game.item.Egg;
import game.item.Fish;
import game.item.Food;
import java.util.List;

/**
 * Behaviour class specifically for Pterodactyl as it handles flying, landing on trees, catching fish, and going to nearest
 * food source (either lake, corpse, or egg).
 */
public class PterodactylBehaviour implements Behaviour{
    private int flyDuration;
    private Pterodactyl pterodactylActor;
    @Override
    public Action getAction(Actor actor, GameMap map) {
        flyDuration = ((Pterodactyl)actor).getFlyDuration();
        pterodactylActor = (Pterodactyl) actor;
        Location location = map.locationOf(actor);
        Ground ground = location.getGround();
        List<Item> itemsHere = location.getItems();
        for (Item item : itemsHere) {
            if (item instanceof Food) {
                    if (pterodactylActor.canEat((Food) item)) {
                        pterodactylActor.setOnGround(true);
                        return new CarnivoreEatAction((Food) item);
                    }
            }
        }

        Location nearestTree = getNearestTree(actor, map);
        Location nearestFoodSource = getNearestFoodSource(actor, map);
        if (ground instanceof Tree) {
            pterodactylActor.resetFlyDuration();
            pterodactylActor.setOnGround(false);
        }else{
            if (flyDuration >= 30 ) {
                pterodactylActor.setOnGround(true);
                return WanderBehaviour.moveTo(actor, map, location, nearestTree);
            }
        }

        return new DoNothingAction();

        }

    /**
     * This method locates the nearest tree object relative to the current actor's position.
     * @param actor the current selected actor
     * @param map the map the actor is in
     * @return the nearest tree object within search radius
     */
    public Location getNearestTree(Actor actor, GameMap map){
        Location location = map.locationOf(actor);
        Location nearestTree = null;
        int shortestDistance = 999999;
        for (int x: map.getXRange()){
            for (int y: map.getYRange()){
                Location currentLocation = map.at(x,y);
                Ground ground = currentLocation.getGround();
                if (ground instanceof Tree){
                    int currentDistance = FollowBehaviour.distance(location,currentLocation);
                    if (currentDistance < shortestDistance){
                        nearestTree = currentLocation;
                        shortestDistance = currentDistance;
                    }
                }
            }
        }
        return nearestTree;
    }


    /**
     * This method checks the entire map and returns the location of the nearest food source relative to the actor. The
     * food source in this case is either an egg or a corpse item.
     * @param actor the current actor
     * @param map the map the actor is in
     * @return location of the nearest food source
     */
    public Location getNearestFoodSource(Actor actor, GameMap map){
        Location location = map.locationOf(actor);
        Location nearestFood = null;
        int shortestDistance =999999;
        boolean containFood = false;
        for (int x: map.getXRange()){
            for (int y: map.getYRange()){
                Location currentLocation = map.at(x, y);
                List<Item> locationItems = currentLocation.getItems();
                for (Item item: locationItems){
                    if (item instanceof Corpse || item instanceof Egg){
                        containFood = true;
                        break;
                    }
                }
                if (containFood){
                    int currentDistance = FollowBehaviour.distance(location,currentLocation);
                    if (currentDistance < shortestDistance){
                        nearestFood = currentLocation;
                        shortestDistance = currentDistance;
                    }
                }
                containFood = false;
            }
        }
        return nearestFood;
    }
}
