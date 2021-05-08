package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.action.BreedingAction;
import game.actor.Dinosaur;
import game.actor.Player;

import java.util.ArrayList;

public class BreedingBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur mate = null;
        Location actorLocation = map.locationOf(actor);
        ArrayList<Dinosaur> potentialMates = getMates((Dinosaur)actor,map);
        if (potentialMates.isEmpty()){
            return (((Dinosaur) actor).getWanderBehaviour().getAction(actor, map));
        }else{
            int shortestDistance = 9999999;
            for(int i = 0; i < potentialMates.size();i++){
                mate = potentialMates.get(i);
                Location mateLocation = map.locationOf(mate);
                int currentDistance = FollowBehaviour.distance(actorLocation,mateLocation);
                if (currentDistance < shortestDistance){
                    shortestDistance = currentDistance;
                }
            }

            if (shortestDistance > 1){
                return new FollowBehaviour(mate).getAction(actor, map);
            }else{
                return new BreedingAction(mate);
            }
        }
    }

    public ArrayList<Dinosaur> getMates(Dinosaur dino, GameMap map){
        ArrayList<Dinosaur> mateList = new ArrayList<>();
        for (int x: map.getXRange()){
            for (int y: map.getYRange()){
                Location location = map.at(x,y);
                Actor target = map.getActorAt(location);
                if (target instanceof Player){
                    continue;
                }else{
                    if (dino.getSpecies().equals(((Dinosaur)target).getSpecies()) &&
                            dino.getGender() != ((Dinosaur) target).getGender()){
                        mateList.add((Dinosaur)target);
                    }
                }
            }
        }
        return mateList;
    }
}
