package game.actor;

import edu.monash.fit2099.engine.*;
import game.WorldBuilder;
import game.action.PickFruitAction;
import game.environment.Bush;
import game.environment.Tree;

/**
 * Class representing the Player.
 */
public class Player extends Actor {
	private Menu menu = new Menu();
	/**
	 * The total EcoPoints the player possesses. Static variable.
	 */
	public static int EcoPointStorage;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		EcoPointStorage = 0;
	}

	/**
	 * Figure out what to do next.
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		Location location = map.locationOf(this);
		Ground ground = location.getGround();
		if (ground instanceof Tree || ground instanceof Bush){
			actions.add(new PickFruitAction());
		}

		//Relocating player between 2 instances of maps.
		Location original_map_north = WorldBuilder.MAPS.get(0).at(map.locationOf(this).x(), 0);
		Location new_map_south = WorldBuilder.MAPS.get(1).at(map.locationOf(this).x(), map.getYRange().max());

		if (map.locationOf(this).y() == 0 && map == WorldBuilder.MAPS.get(0)){
			actions.add(new MoveActorAction(new_map_south, "North", "8"));
		}
		else if (map.locationOf(this).y() == map.getYRange().max() && map == WorldBuilder.MAPS.get(1)){
			actions.add(new MoveActorAction(original_map_north, "South", "2"));
		}

		return menu.showMenu(this, actions, display);
	}

	/**
	 * Getter method to obtain the current eco points the player is holding
	 * @return The total eco points the player is holding
	 */
	public int getEcoPointStorage(){
		return EcoPointStorage;
	}

	/**
	 * Removes the amount of eco points specified.
	 * @param remove the int value to be removed for
	 */
	public void removeEcoPoints(int remove){
		EcoPointStorage = EcoPointStorage - remove;
	}

	/**
	 * Updating the eco points.
	 * @param EcoPoints the ecopoints to be updated for
	 */
	public static void updateEcoPoints(int EcoPoints){
		EcoPointStorage += EcoPoints;
	}

}
