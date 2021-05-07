package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actor.Dinosaur;
import game.item.Egg;

public class LayEggAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        String species = ((Dinosaur)actor).getSpecies();
        location.addItem(new Egg(species));
        ((Dinosaur) actor).removeEgg();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" has laid her egg ";
    }
}
