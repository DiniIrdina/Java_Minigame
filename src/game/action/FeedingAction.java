package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Dinosaur;
import game.actor.Player;
import game.item.Food;
import game.item.Fruit;

public class FeedingAction extends Action {
    protected Dinosaur target;
    protected Food food;
    protected int foodPoints;
    public FeedingAction(Dinosaur target, Food food, int foodPoints){
        this.foodPoints = foodPoints;
        this.target = target;
        this.food = food;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(food);
        target.heal(foodPoints);
        if (food instanceof Fruit){
            Player.updateEcoPoints(10);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Player feeds food to "+target;
    }
}
