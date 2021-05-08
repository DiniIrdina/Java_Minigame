package game.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.action.HerbivoreEatAction;
import game.actor.Allosaur;
import game.actor.Brachiosaur;
import game.actor.Dinosaur;
import game.actor.Stegosaur;
import game.behaviour.Behaviour;
import game.environment.Bush;
import game.environment.Dirt;
import game.environment.Tree;
import game.item.Food;
import game.item.Fruit;

public class WanderBehaviour implements Behaviour {
	
	private Random random = new Random();


	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();

		Location location = map.locationOf(actor);
		List<Item> itemsHere = location.getItems();

		if (!actor.isConscious()){
			return new DoNothingAction();
		}

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
			else if (ground instanceof Bush){
				double killBushChance = Math.random();
				if (killBushChance > 0.5){
					Dirt dirt = new Dirt();
					location.setGround(dirt);
				}
			}
		}

		List<Exit> exitList = map.locationOf(actor).getExits();
		Location nearestBush = getNearestBush(actor,map);
		if (nearestBush != null && actor instanceof Stegosaur){
			if (exitList.contains(nearestBush)){
				return moveTo(actor,map,location,nearestBush);
			}
		}

		Location nearestTree = getNearestTree(actor,map);
			if (nearestTree!=null && !(actor instanceof Allosaur)){
				if (exitList.contains(nearestTree)){
					return moveTo(actor,map,location,nearestTree);
				}

			}



		for (Exit exit : exitList) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
			}
		}

		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		} else {
			return null;
		}

		}


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

	public Action moveTo(Actor actor, GameMap map, Location startLocation, Location endLocation){
		Action action = null;
		List<Exit> exitList = map.locationOf(actor).getExits();
		int distance = FollowBehaviour.distance(startLocation,endLocation);
		for (Exit exit: exitList){
			Location place = exit.getDestination();
			if (place.canActorEnter(actor)){
				if (FollowBehaviour.distance(place,endLocation) < distance){
					String direction = exit.getName();
					action = new MoveActorAction(place, direction);
					break;
				}
			}
		}
		return action;
	}
}





