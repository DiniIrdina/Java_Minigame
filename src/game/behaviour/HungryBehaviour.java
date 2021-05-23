package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.action.CatchFishAction;
import game.action.HerbivoreEatAction;
import game.actor.*;
import game.environment.Bush;
import game.environment.Lake;
import game.environment.Tree;
import game.item.Corpse;
import game.item.Egg;
import game.item.Food;
import game.item.Fruit;

import java.util.ArrayList;
import java.util.List;

public class HungryBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        Location location = map.locationOf(actor);
        Ground ground = location.getGround();
        List<Item> itemsHere = location.getItems();

        if (actor instanceof Brachiosaur) {
            if (ground instanceof Tree) {
                List<Fruit> fruits = ((Tree) ground).getFruits();
                if (!fruits.isEmpty()) {
                    for (Fruit fruit : fruits) {
                        return new HerbivoreEatAction(fruit);
                    }
                }
            }
            else{
                Location nearestTree = Tree.getNearestTree(actor,map);
                if (nearestTree!=null){
                    return WanderBehaviour.moveTo(actor,map,location,nearestTree);
                }
            }
        }

        if (actor instanceof Stegosaur){
            for (Item item : itemsHere){
                if (item instanceof Food && ((Stegosaur)actor).canEat((Food)item)){
                    return new HerbivoreEatAction((Food)item);
                }
            }
            Location nearestBush = getNearestBush(actor, map);
            if (nearestBush!=null){
                return WanderBehaviour.moveTo(actor,map,location,nearestBush);
            }
        }

        if (actor instanceof Pterodactyl){
            Location nearestLake = getNearestLake(actor, map);
            Location nearestFoodSource = getNearestFoodSource(actor, map);
            Location chosenSource = null;

            if (ground instanceof Lake){
                return new CatchFishAction();
            }

            if (nearestLake!=null && nearestFoodSource!=null){
                if (FollowBehaviour.distance(location,nearestFoodSource) < FollowBehaviour.distance(location, nearestLake)){
                    chosenSource = nearestFoodSource;
                }else{
                    chosenSource = nearestLake;
                }
            }
            else if (nearestLake!=null){
                chosenSource = nearestLake;
            }
            else if (nearestFoodSource!=null){
                chosenSource = nearestFoodSource;
            }
            if (chosenSource != null) {
                return WanderBehaviour.moveTo(actor, map, location, chosenSource);
            }
        }
        return new DoNothingAction();
    }

    /**
     * This method locates the nearest bush object relative to the current actor's position.
     * The method is only applicable for the Stegosaur dinosaur and it returns the nearest bush object.
     * @param actor the current selected actor, always a Stegosaur.
     * @param map the current instance of the map
     * @return the nearest bush object within search radius
     */
    public Location getNearestBush(Actor actor, GameMap map){
        Location location = map.locationOf(actor);
        Location nearestBush = null;
        int shortestDistance = 999999;
        for (int x: map.getXRange()){
            for (int y: map.getYRange()){
                Location currentLocation = map.at(x,y);
                Ground ground = currentLocation.getGround();
                if (ground instanceof Bush){
                    int currentDistance = FollowBehaviour.distance(location,currentLocation);
                    if (currentDistance < shortestDistance){
                        nearestBush = currentLocation;
                        shortestDistance = currentDistance;
                    }
                }
            }
        }
        return nearestBush;
    }

    public Location getNearestLake(Actor actor, GameMap map){
        Location location = map.locationOf(actor);
        Location nearestLake = null;
        int shortestDistance = 999999;
        for (int x: map.getXRange()){
            for (int y: map.getYRange()){
                Location currentLocation = map.at(x,y);
                Ground ground = currentLocation.getGround();
                if (ground instanceof Lake){
                    int currentDistance = FollowBehaviour.distance(location,currentLocation);
                    if (currentDistance < shortestDistance){
                        nearestLake = currentLocation;
                        shortestDistance = currentDistance;
                    }
                }
            }
        }
        return nearestLake;

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
