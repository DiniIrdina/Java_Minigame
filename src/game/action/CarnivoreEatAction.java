package game.action;

import edu.monash.fit2099.engine.*;
import game.item.Corpse;
import game.item.Egg;
import game.item.Food;

/**
 * Special eat action specified for carnivore dinosaurs.
 */
public class CarnivoreEatAction extends Action {
    private Food food;

    /**
     * The constructor for the CarnivoreEatAction class.
     * @param food the type of food the current actor is trying to eat.
     */
    public CarnivoreEatAction(Food food){
        this.food = food;
    }

    /**
     * This method is used to execute the eat action on the target. Carnivores can only take in foods that are non
     * vegetative. The method will check if the food object is of a specified species, adjusting the different
     * food levels for each unique object.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.food instanceof Corpse){
            if (((Corpse) this.food ).getSpecies().equals("Allosaur") || ((Corpse) this.food ).getSpecies().equals("Stegosaur")){
                actor.heal(50);
            }
            else if (((Corpse) this.food ).getSpecies().equals("Brachiosaur")){
                actor.heal(100);
            }
            ((Corpse) this.food).removeCorpse();
        }
        else if (this.food instanceof Egg){
            actor.heal(10);
            map.locationOf(actor).removeItem(this.food);
        }
        return actor + " ate " + this.food;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
