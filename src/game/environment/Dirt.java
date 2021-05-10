package game.environment;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	final double DEFAULT_BUSH_CHANCE = 	0.01;
	final double TWO_BUSH_CHANCE = 0.1;

	/**
	 * Overloading constructor of the Dirt class. Dirt will be represented by the char '.'
	 */
	public Dirt() {
		super('.');
	}

	/**
	 * Grows a bush if the current dirt object does not have a tree instance
	 * @param location The location of the Ground
	 */
	public void growBush(Location location){
		double possibility = Math.random();
		int bushes = 0;
		List<Exit> exitList = location.getExits();
		for (int count = 0; count < exitList.size();count++){
			Ground nearbyGround = exitList.get(count).getDestination().getGround();
			if (nearbyGround instanceof Bush){
				bushes++;
			}else if (nearbyGround instanceof Tree){
				return;
			}
		}
		if (bushes >= 2){
			if (possibility <= TWO_BUSH_CHANCE){
				Bush bush = new Bush();
				location.setGround(bush);
			}
		}else{
			if (possibility <= DEFAULT_BUSH_CHANCE){
				Bush bush = new Bush();
				location.setGround(bush);
			}
		}
	}

	/**
	 * Tick updates the current turn of game for the class object
	 * @param location The location of the Ground
	 */
	public void tick(Location location){
		super.tick(location);
		Ground ground = location.getGround();
		if (ground instanceof Dirt){
			growBush(location);
		}
	}

}
