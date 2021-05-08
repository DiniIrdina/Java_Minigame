package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.AllosaurEatAction;
import game.action.AttackAction;
import game.actor.Dinosaur;
import game.actor.Stegosaur;

import java.util.List;

public class AllosaurAttackBehaviour extends WanderBehaviour{
    static final int RADIUS = 40;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Exit> exitList = map.locationOf(actor).getExits();
        Actor nearbyTarget = null;
        for (Exit exit: exitList){
            Location location = exit.getDestination();
            if (location.containsAnActor()){
                nearbyTarget = location.getActor();
                if (nearbyTarget instanceof Stegosaur){
                    break;
                }
            }
        }
        if (nearbyTarget != null){
            return new AllosaurEatAction((Dinosaur)nearbyTarget);
        }
        return null;
    }

    public Dinosaur findTargetInRadius(Dinosaur actor, GameMap map){
        Location location = map.locationOf(actor);
        Dinosaur closestTarget = null;
        int shortestDistance = RADIUS;
        int targetDistance;
        for(int x: map.getXRange()){
            for(int y: map.getYRange()){
                Location targetLocation = map.at(x,y);
                if (targetLocation.containsAnActor() && targetLocation.getActor() instanceof Stegosaur){
                    targetDistance = FollowBehaviour.distance(location,targetLocation);
                    Dinosaur target = (Dinosaur)targetLocation.getActor();
                    if (targetDistance < shortestDistance){
                        shortestDistance = targetDistance;
                        closestTarget = target;
                    }
                }
            }
        }
        return closestTarget;
    }
}
