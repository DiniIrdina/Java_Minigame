package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Brachiosaur;
import game.actor.Stegosaur;
import game.item.Food;

public class HerbivoreEatAction extends Action {
    private Food food;

    public HerbivoreEatAction(Food food){
        this.food = food;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Stegosaur){
            ((Stegosaur) actor).eatsFood(this.food);
        }
        else if (actor instanceof Brachiosaur){
            ((Brachiosaur) actor).eatsFood(this.food);
        }
        return actor + "eaten " + this.food;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
