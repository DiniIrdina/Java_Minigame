package game.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.action.CarnivoreEatAction;
import game.action.HerbivoreEatAction;
import game.actor.*;
import game.behaviour.Behaviour;
import game.environment.Bush;
import game.environment.Dirt;
import game.environment.Tree;
import game.item.Food;
import game.item.Fruit;

/**
 * A class that figures out the default behaviour for all dinosaurs, which is wandering around.
 * The class will utilize the methods associated to call upon and execute all the
 * different behaviors dependent on the species of the dinosaur. Behaviours that could be invoked includes
 * BreedingBehaviour, FollowBehaviour and so forth. The movement is automatically generated and is randomized.
 */
public class WanderBehaviour implements Behaviour {
	
	private Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null. It checks the actor's current location to get any food instances
	 * on the ground. The actor will proceed to eat if suitable. If not, it will check the adjacent area around it for
	 * food sources.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();

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

	/**
	 * The moveTo method executes the movement orders based on the FollowBehaviour.
	 * @param actor the current actor executing the behaviour
	 * @param map the current instance of the map
	 * @param startLocation the current start location before moving
	 * @param endLocation the location to move and end at
	 * @return the MoveActorAction, which moves the actor towards the correct direction
	 */
	public static Action moveTo(Actor actor, GameMap map, Location startLocation, Location endLocation){
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


