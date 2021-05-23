package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.DrinkAction;
import game.actor.Dinosaur;
import game.environment.Lake;
import game.environment.Tree;
import game.interfaces.NearestLake;

import java.util.List;

/**
 * A behaviour class that is called when a Dinosaur gets thirsty. It will search the entire map and get the nearest lake
 * location to the actor.
 */
public class ThirstyBehaviour implements Behaviour, NearestLake{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        Location nearLake = NearestLake.getNearestLake(actor,map);
        if (nearLake != null){
            List<Exit> exitList = map.locationOf(actor).getExits();
            if (exitList.contains(nearLake)){
                return new DrinkAction(nearLake);
            }else{
                return WanderBehaviour.moveTo(actor, map,location, nearLake);
            }
        }
        return null;
    }

}
