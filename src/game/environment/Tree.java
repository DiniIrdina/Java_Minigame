package game.environment;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actor.Player;
import game.behaviour.FollowBehaviour;
import game.item.Egg;
import game.item.Fruit;
import game.item.PterodactylEgg;

import java.util.ArrayList;

/**
 * Class creation for the Tree objects found in the game. Trees will generate fruits based upon
 * a set probability.
 */
public class Tree extends Ground {
	private int age = 0;
	final double RAIN_PROBABILITY = 0.2;
	private boolean isRaining;
	private boolean occupied;
	private Egg egg;

	/**
	 * Array list to store the fruit objects
	 */
	private ArrayList<Fruit> fruits = new ArrayList<>();

	/**
	 * Overloading constructor of the Tree class. Tree will be represented by the char '+'.
	 */
	public Tree() {
		super('+');
		isRaining = false;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public void addEgg(){
		egg = new PterodactylEgg();
		setOccupied(true);
	}

	public void removeEgg(){
		egg = null;
		setOccupied(false);
	}

	/**
	 * Returns the fruits list.
	 * @return Returns the fruits list
	 */
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	/**
	 * Removes the fruit from the array list
	 */
	public void removeFruit(){
		fruits.remove(fruits.size()-1);
	}

	/**
	 * Tick updates the current turn of game for the class object
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		double fruitChance = Math.random();
		if (fruitChance >= 0.5 && fruits.size() <= 10){
			Fruit fruit = new Fruit();
			fruits.add(fruit);
			Player.updateEcoPoints(1);
		}

		double fruitDropChance = Math.random();
		if (fruitDropChance <= 0.05 && !fruits.isEmpty()){
			fruits.remove(fruits.size()-1);
			Fruit fruit = new Fruit();
			location.addItem(fruit);
		}

		if (age%10 == 0){
			double rainChance = Math.random();
			if (rainChance <= RAIN_PROBABILITY){
				isRaining = true;
			}
		}else{
			isRaining = false;
		}
	}

	public boolean isRaining() {
		return isRaining;
	}

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
