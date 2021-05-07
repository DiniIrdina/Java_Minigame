package game.environment;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');

	}

	public void growBush(Location location){
		Bush bush = new Bush();
		location.setGround(bush);
	}
}
