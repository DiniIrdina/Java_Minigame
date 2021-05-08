package game.action;

import edu.monash.fit2099.engine.*;
import game.item.Corpse;
import game.item.Egg;
import game.item.Food;

public class CarnivoreEatAction extends Action {
    private Food food;

    public CarnivoreEatAction(Food food){
        this.food = food;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
