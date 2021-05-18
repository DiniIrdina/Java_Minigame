package game.action;

import edu.monash.fit2099.engine.*;
import game.actor.Dinosaur;
import game.environment.Lake;

public class DrinkAction extends Action {
    private Location lakeLocation;
    public DrinkAction(Location location){
        lakeLocation = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Ground lakeGround = lakeLocation.getGround();
        ((Lake)lakeGround).reduceSip();
        ((Dinosaur)actor).drinksWater();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" has drank water!";
    }
}
