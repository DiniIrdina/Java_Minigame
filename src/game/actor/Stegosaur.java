package game.actor;


import edu.monash.fit2099.engine.*;
import game.action.FeedingAction;
import game.behaviour.WanderBehaviour;
import game.action.AttackAction;
import game.interfaces.NeedsPlayer;
import game.item.*;

import static java.util.Objects.isNull;

/**
 * Class creation for Stegosaur, a herbivorous dinosaur.
 */
public class Stegosaur extends Dinosaur {
	// Will need to change this to a collection if Stegosaur gets additional Behaviours.
	static final String SPECIES = "Stegosaur";
	static final int ADULT_AGE = 20;
	static final int MAX_HIT_POINTS=100;
	static final int HUNGRY_LEVEL = 90;
	static final int PREGNANT_LENGTH = 10;
	static final char BABY_STEGOSAUR_DISPLAY = 's';
	static final char ADULT_STEGOSAUR_DISPLAY = 'S';
	static final int BREEDING_LEVEL =50;
	static final int UNCONSCIOUS_LIMIT =20;
	static final int MAX_THIRST = 100;
	static final int STARTING_THIRST = 60;
	static final int THIRSTY_LEVEL = 40;

	/**
	 * First overloaded constructor of the Stegosaur class. All Stegosaurs have 100 hit points. The baby
	 * Stegosaur will have 's' as its display and the adult will have 'S'.
	 * @param age the current age of the Stegosaur to be created for
	 */
	public Stegosaur(int age) {
		super(SPECIES, ADULT_STEGOSAUR_DISPLAY, age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE,
				ADULT_STEGOSAUR_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT, HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST, THIRSTY_LEVEL);
		if (age < ADULT_AGE){
			this.displayChar = BABY_STEGOSAUR_DISPLAY;
		}
		behaviour = new WanderBehaviour();
	}
	/**
	 * Second overloaded constructor of the Stegosaur class.
	 * @param age the current age of the Stegosaur to be created for
	 * @param gender the gender of the Stegosaur to be created for
	 */
	public Stegosaur(int age, char gender) {
		super(SPECIES, ADULT_STEGOSAUR_DISPLAY, gender,age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE,
				ADULT_STEGOSAUR_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT, HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST, THIRSTY_LEVEL);
		if (age < ADULT_AGE){
			this.displayChar = BABY_STEGOSAUR_DISPLAY;
		}
		behaviour = new WanderBehaviour();
	}

	/**
	 * Lists all the actions that the other actor can perform on the current actor.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return           actions
	 */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		actions = new Actions();
		actions.add(new AttackAction(this));
		Player player = NeedsPlayer.retrievePlayer(map);
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
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Action wander = behaviour.getAction(this, map);
		super.turn(map);
		Location location = map.locationOf(this);
		unconsciousPeriod(map);


		for (Item item: inventory){
			if (item instanceof StegosaurEgg){
				this.isPregnant = true;
				break;
			}else{
				this.isPregnant = false;
			}
		}

		if (this.isPregnant){
			if (this.pregnancyCounter < 20){
				pregnancyTurn();
			}
			else {
				this.isPregnant = false;
				LayEgg(map.locationOf(this));
				removeEgg();
				this.pregnancyCounter = 0;
			}
		}

		if (wander != null)
			return wander;

		return new DoNothingAction();
	}

	/**
	 * Specifies the different types of food the dinosaur can consume.
	 * @param food the type of food
	 * @return true if food is edible by current dinosaur
	 */
	@Override
	public boolean canEat(Food food) {
		boolean result = false;
		if (food instanceof Fruit || food instanceof VegetarianMealKit){
			result = true;
		}
		return result;
	}

	/**
	 * The eating action.
	 * @param food the type of food
	 */
	@Override
	public void eatsFood(Food food) {
		if (food instanceof Fruit){
			heal(10);
		}else if (food instanceof VegetarianMealKit){
			heal(maxHitPoints-hitPoints);
		}
	}

	/**
	 * Lays an egg object on the current location of the dinosaur.
	 * @param location the location the dinosaur is current at
	 */
	public void LayEgg(Location location){
		location.addItem(new StegosaurEgg());
	}
}
