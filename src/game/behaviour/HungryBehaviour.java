package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.action.CatchFishAction;
import game.action.HerbivoreEatAction;
import game.actor.*;
import game.environment.Bush;
import game.environment.Lake;
import game.environment.Tree;
import game.interfaces.NearestBush;
import game.interfaces.NearestLake;
import game.interfaces.NearestTree;
import game.item.Corpse;
import game.item.Egg;
import game.item.Food;
import game.item.Fruit;
import java.util.List;

public class HungryBehaviour implements Behaviour, NearestTree, NearestLake, NearestBush {
    @Override
    public Action getAction(Actor actor, GameMap map) {

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
                Location nearestTree = NearestTree.getNearestTree(actor,map);
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
            if (ground instanceof Bush){
                if ((((Bush)ground).getFruits().size() > 0)){
                    ((Bush)ground).removeFruit();
                    return new HerbivoreEatAction(new Fruit());
                }
            }
            Location nearBush = NearestBush.getNearestBush(actor, map);
            if (nearBush!=null){
                return WanderBehaviour.moveTo(actor,map,location,nearBush);
            }
        }

        if (actor instanceof Pterodactyl){
            Location nearLake = NearestLake.getNearestLake(actor, map);
            Location nearestFoodSource = getNearestFoodSource(actor, map);
            Location chosenSource = null;

            for (Item item : itemsHere){
                if (item instanceof Food && ((Pterodactyl)actor).canEat((Food)item)){
                    ((Pterodactyl)actor).setOnGround(true);
                    return new CarnivoreEatAction((Food)item);
                }
            }

            if (ground instanceof Lake){
                return new CatchFishAction();
            }

            if (nearLake!=null && nearestFoodSource!=null){
                if (FollowBehaviour.distance(location,nearestFoodSource) < FollowBehaviour.distance(location, nearLake)){
                    chosenSource = nearestFoodSource;
                }else{
                    chosenSource = nearLake;
                }
            }
            else if (nearLake!=null){
                chosenSource = nearLake;
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
