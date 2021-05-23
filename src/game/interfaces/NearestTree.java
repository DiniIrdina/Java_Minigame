package game.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.behaviour.FollowBehaviour;
import game.environment.Tree;

public interface NearestTree {
    /**
     * This method locates the nearest tree object relative to the current actor's position.
     *
     * @param actor the current selected actor
     * @param map   the map the actor is in
     * @return the nearest tree object within search radius
     */
    public static Location getNearestTree(Actor actor, GameMap map) {
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
