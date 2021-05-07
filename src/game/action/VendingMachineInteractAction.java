package game.action;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class VendingMachineInteractAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        List<Item> items = location.getItems();
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
