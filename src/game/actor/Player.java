package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.FeedingAction;
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
		EcoPointStorage = 100;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Getter method to obtain the current eco points the player is holding
	 * @return The total eco points the player is holding
	 */
	public int getEcoPointStorage(){
		return EcoPointStorage;
	}

	public void removeEcoPoints(int remove){
		EcoPointStorage = EcoPointStorage - remove;
	}

	public static void updateEcoPoints(int EcoPoints){
		EcoPointStorage += EcoPoints;
	}

}
