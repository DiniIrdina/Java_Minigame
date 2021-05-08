package game.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.action.AllosaurEatAction;
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
		if (!itemsHere.isEmpty()) {
			for (Item item : itemsHere) {
				if (item instanceof Food) {
					if (actor instanceof Stegosaur) {
						if (((Dinosaur) actor).canEat((Food) item)) {
							return new HerbivoreEatAction((Food) item);
						}

					} else if (actor instanceof Allosaur) {
						if (((Dinosaur) actor).canEat((Food) item)) {
							return new CarnivoreEatAction((Food) item);
						}
					}
				}
			}
		}
		Ground ground = location.getGround();

		if (actor instanceof Stegosaur) {
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


		for (Exit exit : map.locationOf(actor).getExits()) {
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
	}





