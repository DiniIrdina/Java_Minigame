package game.action;

import edu.monash.fit2099.engine.*;
import game.actor.Allosaur;
import game.actor.Dinosaur;
import game.item.Corpse;
import game.item.Egg;

import java.util.List;

public class AllosaurEatAction extends Action {
    /**
     * The dinosaur to be attacked for food source
     */
    protected Dinosaur target;
    private int damage;

    public AllosaurEatAction(Dinosaur target){
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.getSpecies().equals("Stegosaur")) {
            if (((Allosaur) actor).getAttacked_dinosaur().contains(target)) {
                return "Can't attack same dinosaur";
            } else {
                int damage = 20;
                this.damage = damage;
                target.hurt(damage);
                if (target.getHitPoints() <= 0) {
                    target.Death(target, map);
                }
                else {
                    ((Allosaur) actor).setAttacked_dinosaur(target);
                    ((Allosaur) actor).setAttacked_dinosaur_count(0);
                }
                actor.heal(damage);
            }
        }

        else {
            Location location = map.locationOf(actor);
            List<Item> items = location.getItems();
            for (Item i : items){
                if (i instanceof Corpse){
                    if (((Corpse) i).getSpecies().equals("Allosaur") || ((Corpse) i).getSpecies().equals("Stegosaur")){
                        actor.heal(50);
                    }
                    else if (((Corpse) i).getSpecies().equals("Brachiosaur")){
                        actor.heal(100);
                    }
                    location.removeItem(i);
                }

                else if (i instanceof Egg){
                    actor.heal(10);
                    location.removeItem(i);
                }
            }
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" has eaten and healed for "+damage;
    }
}
