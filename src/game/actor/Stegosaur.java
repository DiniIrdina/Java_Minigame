package game.actor;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.behaviour.WanderBehaviour;
import game.action.AttackAction;
import game.item.Food;
import game.item.Fruit;
import game.item.VegetarianMealKit;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	String species = "Stegosaur";
	public static final int ADULT_AGE = 20;
	public static final int MAX_HIT_POINTS=100;
	public static final int PREGNANT_LENGTH = 10;
	public static final char BABY_STEGOSAUR_DISPLAY = 's';
	public static final char ADULT_STEGOSAUR_DISPLAY = 'S';


	/** 
	 * Constructor.
	 * All Stegosaurs are represented by a 'd' and have 100 hit points.
	 *
	 */
	public Stegosaur(int age) {
		super("Stegosaur", ADULT_STEGOSAUR_DISPLAY, age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE );
		if (age < ADULT_AGE){
			this.displayChar = BABY_STEGOSAUR_DISPLAY;
		}
		behaviour = new WanderBehaviour();
	}
	public Stegosaur(int age, char gender) {
		super("Stegosaur", ADULT_STEGOSAUR_DISPLAY, gender,age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE );
		if (age < ADULT_AGE){
			this.displayChar = BABY_STEGOSAUR_DISPLAY;
		}
		behaviour = new WanderBehaviour();
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(this));
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
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Action wander = behaviour.getAction(this, map);
		if (wander != null)
			return wander;
		
		return new DoNothingAction();
	}

	@Override
	public String getSpecies() {
		return null;
	}

	@Override
	public boolean Attackable() {
		return false;
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
