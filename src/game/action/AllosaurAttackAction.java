package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Allosaur;
import game.actor.Dinosaur;

/**
 * Special Action for Allosaurs to attack other dinosaurs.
 */
public class AllosaurAttackAction extends Action {
    /**
     * The dinosaur to be attacked for food source
     */
    protected Dinosaur target;

    private int damage;

    /**
     * The constructor for AllosaurAttackAction class
      * @param target the target parameter represents the target the actor is currently attacking, which is a
     *               Dinosaur class.
     */
    public AllosaurAttackAction(Dinosaur target){
        this.target = target;
    }

    /**
     * This method is used to execute the attack action on the target, specifically for the allosaur dinosaur classes.
     * The allosaur can only attack targets that they have yet encountered before, hence the method will check if
     * the target has been attacked recently.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
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
        return actor + " attacked " + target + " for " + this.damage;
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
