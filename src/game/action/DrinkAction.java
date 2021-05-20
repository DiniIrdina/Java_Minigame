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
        if (((Lake)lakeGround).getSips() < 0){
            return actor + " cannot drink because lake is empty!";
        }else {
            ((Lake) lakeGround).reduceSip();
            ((Dinosaur) actor).drinksWater();
            return menuDescription(actor);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" drank water!";
    }
}
