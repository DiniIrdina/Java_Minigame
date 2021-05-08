package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.action.AllosaurAttackAction;
import game.action.AttackAction;
import game.actor.Allosaur;
import game.actor.Dinosaur;
import game.actor.Stegosaur;

import java.util.List;

public class AllosaurAttackBehaviour extends WanderBehaviour{
    static final int RADIUS = 40;

    /**
     * This behaviour allows the Allosaur to find and
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        List<Exit> exitList = map.locationOf(actor).getExits();
        Actor nearbyTarget = null;
        Action move = null;
        for (Exit exit: exitList){
            Location location = exit.getDestination();
            if (location.containsAnActor()){
                Actor currentTarget = location.getActor();
                if (currentTarget instanceof Stegosaur){
                    if (!((Allosaur)actor).getAttacked_dinosaur().contains(currentTarget)){
                        nearbyTarget = currentTarget;
                        break;
                    }
                }
            }
        }
        if (nearbyTarget != null){
            return new AllosaurAttackAction((Dinosaur)nearbyTarget);
        }

        Dinosaur target = findTargetInRadius((Dinosaur)actor,map);
        if (target != null){
            return new FollowBehaviour(target).getAction(actor, map);
        }

        for (Exit exit: exitList){
            Location goTowards = exit.getDestination();
            if (goTowards.canActorEnter(actor)){
                 move = exit.getDestination().getMoveAction(actor, "around", exit.getHotKey());
            }
        }
        return move;
    }

    public Dinosaur findTargetInRadius(Dinosaur actor, GameMap map){
        Location location = map.locationOf(actor);
        Dinosaur closestTarget = null;
        int shortestDistance = RADIUS;
        int targetDistance;

        for(int x: map.getXRange()){
            for(int y: map.getYRange()){
                Location targetLocation = map.at(x,y);
                if (targetLocation.containsAnActor() && targetLocation.getActor() instanceof Stegosaur) {
                    targetDistance = FollowBehaviour.distance(location, targetLocation);
                    Dinosaur target = (Dinosaur) targetLocation.getActor();
                    if (target instanceof Stegosaur && !((Allosaur)actor).getAttacked_dinosaur().contains(target)){
                        if (targetDistance < shortestDistance) {
                            shortestDistance = targetDistance;
                            closestTarget = target;
                        }
                }
                }
            }
        }
        return closestTarget;
    }
}
