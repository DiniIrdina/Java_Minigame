package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.action.DrinkAction;
import game.action.HerbivoreEatAction;
import game.actor.Allosaur;
import game.actor.Dinosaur;
import game.actor.Pterodactyl;
import game.actor.Stegosaur;
import game.environment.Lake;
import game.environment.Tree;
import game.item.Corpse;
import game.item.Egg;
import game.item.Fish;
import game.item.Food;

import java.util.ArrayList;
import java.util.List;

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
        Location nearestLake = getNearestLake(actor, map);
        if (ground instanceof Tree) {
            pterodactylActor.resetFlyDuration();
            pterodactylActor.setOnGround(false);
        }else{
            if (flyDuration >= 30 ) {
                pterodactylActor.setOnGround(true);
                return WanderBehaviour.moveTo(actor, map, location, nearestTree);
            }
        }

        if (ground instanceof Lake){
            catchFish(location);
            return new DrinkAction(location);
        }


        return null;

        }

    /**
     * This method locates the nearest tree object relative to the current actor's position.
     * @param actor the current selected actor
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
        int shortestDistance =999999;
        for (int x: map.getXRange()){
            for (int y: map.getYRange()){
                Location currentLocation = map.at(x, y);
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

    public void catchFish(Location location){
        double catchOneChance = 0.33;
        double catchTwoChance = 0.66;
        Lake lake = (Lake)location.getGround();
        double fishChance = Math.random();
        if (lake.getFishPopulation().size() > 2){
            if (fishChance <=0.33){
                lake.reduceFishPopulation(1);
                pterodactylActor.eatsFood(new Fish());
            }else if(fishChance >= 0.66){
                lake.reduceFishPopulation(2);
                pterodactylActor.eatsFood(new Fish());
                pterodactylActor.eatsFood(new Fish());
            }
        }else if (lake.getFishPopulation().size() == 1){
            lake.reduceFishPopulation(1);
            pterodactylActor.eatsFood(new Fish());
        }
    }

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
