package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.DrinkAction;
import game.actor.Dinosaur;
import game.environment.Lake;
import game.environment.Tree;

import java.util.List;

public class ThirstyBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        Location nearestLake = getNearestLake(actor,map);
        if (nearestLake != null){
            List<Exit> exitList = map.locationOf(actor).getExits();
            if (exitList.contains(nearestLake)){
                return new DrinkAction(nearestLake);
            }else{
                return WanderBehaviour.moveTo(actor, map,location, nearestLake);
            }
        }
        return null;
    }
    /**
     *
     * This method checks the entire map and returns the location of the nearest lake relative to the actor's location.
     * @param actor the current actor.
     * @param map the map the actor is in.
     * @return location of the nearest lake to the actor
     */
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
}
