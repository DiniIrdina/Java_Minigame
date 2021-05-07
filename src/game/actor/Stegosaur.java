package game.actor;


import edu.monash.fit2099.engine.*;
import game.action.FeedingAction;
import game.behaviour.WanderBehaviour;
import game.action.AttackAction;
import game.interfaces.NeedsPlayer;
import game.item.Food;
import game.item.Fruit;
import game.item.VegetarianMealKit;

import static java.util.Objects.isNull;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	public static final String SPECIES = "Stegosaur";
	public static final int ADULT_AGE = 20;
	public static final int MAX_HIT_POINTS=100;
	public static final int HUNGRY_LEVEL = 90;
	public static final int PREGNANT_LENGTH = 10;
	public static final char BABY_STEGOSAUR_DISPLAY = 's';
	public static final char ADULT_STEGOSAUR_DISPLAY = 'S';

	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 *
	 */
	public Stegosaur(int age) {
		super(SPECIES, ADULT_STEGOSAUR_DISPLAY, age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE,ADULT_STEGOSAUR_DISPLAY);
		if (age < ADULT_AGE){
			this.displayChar = BABY_STEGOSAUR_DISPLAY;
		}
		behaviour = new WanderBehaviour();
	}
	public Stegosaur(int age, char gender) {
		super(SPECIES, ADULT_STEGOSAUR_DISPLAY, gender,age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE,ADULT_STEGOSAUR_DISPLAY);
		if (age < ADULT_AGE){
			this.displayChar = BABY_STEGOSAUR_DISPLAY;
		}
		behaviour = new WanderBehaviour();
	}

	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		actions = new Actions();
		actions.add(new AttackAction(this));
		Player player = NeedsPlayer.getPlayer(map);
		if (!isNull(player)){
			for(Item item: player.getInventory()){
				if (item instanceof Fruit){
					actions.add(new FeedingAction(this,((Food)item),Fruit.getFeedPoints()));
				}else if (item instanceof VegetarianMealKit){
					actions.add(new FeedingAction(this,((Food)item), VegetarianMealKit.getFeedPoints()));
				}
			}
		}
		return actions;

	}

	/**
	 * Figure out what to do next.
	 * 
	 * FIXME: Stegosaur wanders around at random, or if no suitable MoveActions are available, it
	 * just stands there.  That's boring.
	 * 
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */

	@Override
	public void turn(){

	}

	@Override
	public void assignBehaviour() {

	}

	@Override
	public boolean canEat(Food food) {
		boolean result = false;
		if (food instanceof Fruit || food instanceof VegetarianMealKit){
			result = true;
		}
		return result;
	}

	@Override
	public void eatsFood(Food food) {
		if (food instanceof Fruit){
			heal(10);
		}else if (food instanceof VegetarianMealKit){
			heal(maxHitPoints-hitPoints);
		}
	}


}
