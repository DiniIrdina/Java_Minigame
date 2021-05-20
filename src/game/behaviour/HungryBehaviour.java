package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.action.HerbivoreEatAction;
import game.actor.Allosaur;
import game.actor.Brachiosaur;
import game.actor.Dinosaur;
import game.actor.Stegosaur;
import game.environment.Bush;
import game.environment.Dirt;
import game.environment.Lake;
import game.environment.Tree;
import game.item.Food;
import game.item.Fruit;

import java.util.ArrayList;
import java.util.List;

public class HungryBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        Location location = map.locationOf(actor);
        List<Item> itemsHere = location.getItems();

        if (!itemsHere.isEmpty()) {
            for (Item item : itemsHere) {
                if (item instanceof Food) {
                    if (actor instanceof Stegosaur) {
                        if (((Dinosaur) actor).canEat((Food) item)) {
                            return new HerbivoreEatAction((Food) item);
                        }

                    }else if(actor instanceof Allosaur){
                        if (((Dinosaur)actor).canEat((Food)item)){
                            return new CarnivoreEatAction((Food)item);
                        }
                    }
                }
            }
        }
        Ground ground = location.getGround();
        if (actor instanceof Brachiosaur) {
            if (ground instanceof Tree) {
                List<Fruit> fruits = ((Tree) ground).getFruits();
                if (!fruits.isEmpty()) {
                    for (Fruit fruit : fruits) {
                        return new HerbivoreEatAction(fruit);
                    }
                }
            }
        }

        List<Exit> exitList = map.locationOf(actor).getExits();
        Location nearestBush = getNearestBush(actor,map);
        if (nearestBush != null && actor instanceof Stegosaur){
            if (exitList.contains(nearestBush)){
                return WanderBehaviour.moveTo(actor,map,location,nearestBush);
            }
        }

        Location nearestTree = getNearestTree(actor,map);
        if (nearestTree!=null && !(actor instanceof Allosaur)){
            if (exitList.contains(nearestTree)){
                return WanderBehaviour.moveTo(actor,map,location,nearestTree);
            }
        }
        return null;
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

    /**
     * This method locates the nearest tree object relative to the current actor's position.
     * The method is only applicable for the Brachiosaur dinosaur and it returns the nearest tree object.
     * @param actor the current selected actor, always a Brachiosaur.
     * @param map the current instance of the map
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

}
