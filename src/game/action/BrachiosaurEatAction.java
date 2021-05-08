package game.action;

import edu.monash.fit2099.engine.*;
import game.actor.Dinosaur;
import game.environment.Tree;
import game.item.Food;
import game.item.Fruit;
import game.item.PortableItem;

import java.util.ArrayList;
import java.util.List;

public class BrachiosaurEatAction extends Action {
    private Food food;

    public BrachiosaurEatAction(Food newFood) {
        this.food = food;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
//        assert actor instanceof Dinosaur;
//        Location location = map.locationOf(actor);
//        Ground ground = location.getGround();
//        if (ground instanceof Tree) {
//            List<Fruit> fruits = ((Tree) ground).getFruits();
//            if (!fruits.isEmpty()){
//                for (Fruit fruit: fruits) {
//                    actor.heal(5);
//            }}
//        }
        return menuDescription(actor);
    }
    @Override
    public String menuDescription (Actor actor){
        String output = actor + " ate a " + food;
        return null;
    }
}
