package game.item;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	public static Location getItemLocation(GameMap map, Item item) {
		Location location = null;
		for (int x_coordinate : map.getXRange()) {
			for (int y_coordinate : map.getYRange()) {
				if (map.at(x_coordinate, y_coordinate).getItems().contains(item)) {
					location = map.at(x_coordinate, y_coordinate);
				}
			}
		}
		return location;
	}

}
