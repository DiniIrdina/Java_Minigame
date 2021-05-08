package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actor.Dinosaur;
import game.item.AllosaurEgg;
import game.item.BrachiosaurEgg;
import game.item.Egg;
import game.item.StegosaurEgg;

public class LayEggAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        String species = ((Dinosaur)actor).getSpecies();
        if (species.equals("Stegosaur")){
            location.addItem(new StegosaurEgg());
        }
        if (species.equals("Brachioaur")){
            location.addItem(new BrachiosaurEgg());
        }
        if (species.equals("Allosaur")){
            location.addItem(new AllosaurEgg());
        }
        ((Dinosaur) actor).removeEgg();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" has laid her egg ";
    }
}
