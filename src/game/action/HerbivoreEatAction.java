package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Brachiosaur;
import game.actor.Stegosaur;
import game.item.Food;

/**
 * Special eat action specified for herbivore dinosaurs.
 */
public class HerbivoreEatAction extends Action {
    private Food food;

    /**
     * The constructor for the HerbivoreEatAction class.
     * @param food the type of food the current actor is trying to eat.
     */
    public HerbivoreEatAction(Food food){
        this.food = food;
    }

    /**
     * This method is used to execute the eat action on the target. Herbivores can only take in foods that are
     * vegetative. The method will check the current species of the dinosaur actor, before determining the correct
     * food restoration values associated with each dinosaur's unique requirement.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Stegosaur){
            ((Stegosaur) actor).eatsFood(this.food);
        }
        else if (actor instanceof Brachiosaur){
            ((Brachiosaur) actor).eatsFood(this.food);
        }
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has eaten a " + this.food;
    }
}
