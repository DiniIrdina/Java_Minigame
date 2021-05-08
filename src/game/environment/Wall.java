package game.environment;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Wall extends Ground {

	/**
	 * Overloading constructor of the Wall class. Wall will be represented by the char '#'.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * If the class allows the actors to pass through.
	 * @param actor the Actor to check
	 * @return boolean
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Blocks projectiles
	 * @return boolean
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
