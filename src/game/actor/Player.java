package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.FeedingAction;
import game.action.PickFruitAction;
import game.environment.Bush;
import game.environment.Tree;
import game.item.*;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();
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
