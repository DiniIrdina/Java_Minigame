package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Allosaur;
import game.actor.Dinosaur;

public class AllosaurAttackAction extends Action {
    /**
     * The dinosaur to be attacked for food source
     */
    protected Dinosaur target;
    private int damage;

    public AllosaurAttackAction(Dinosaur target){
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
        return actor + " attacked " + target + " for " + this.damage;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
