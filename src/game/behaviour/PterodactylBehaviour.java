package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.actor.Pterodactyl;
import game.environment.Lake;
import game.environment.Tree;

public class PterodactylBehaviour implements Behaviour{
    private int flyDuration;
    @Override
    public Action getAction(Actor actor, GameMap map) {
        flyDuration = ((Pterodactyl)actor).getFlyDuration();
        Location location = map.locationOf(actor);
        Location nearestTree = getNearestTree(actor, map);
        Location nearestLake = getNearestLake(actor, map);
        if (flyDuration>=30){
            ((Pterodactyl)actor).setOnGround(true);
            return WanderBehaviour.moveTo(actor,map,location,nearestTree);
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
}
