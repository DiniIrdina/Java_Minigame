package game.action;

import edu.monash.fit2099.engine.*;
import game.actor.Dinosaur;
import game.item.Food;
import game.item.Fruit;
import game.item.PortableItem;

import java.util.ArrayList;
import java.util.List;

public class BrachiosaurEatAction extends Action {
    private Food food;

    public BrachiosaurEatAction(Food newFood){
        this.food = food;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        assert actor instanceof Dinosaur;
        Location location = map.locationOf(actor);
        List<Item> items = location.getItems();
        for(Item item: items){
            if (item==food){

            }
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        String output = actor + " ate a " + food;
        return null;
    }
}
