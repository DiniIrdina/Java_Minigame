package game;

import edu.monash.fit2099.engine.*;

public class AllosaurEatAction extends Action {
    /**
     * The dinosaur to be attacked for food source
     */
    protected Dinosaur target;
    protected Dinosaur actor;
    protected boolean AttackCooldown;

    public AllosaurEatAction(Dinosaur target){
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        boolean status;

        if (map.locationOf(actor) == map.locationOf(target) && target.getSpecies().equals("Stegosaur")) {
            int damage = 20;
            target.hurt(damage);
            target.Death(target, map);
            actor.heal(damage);
        }

        //INCOMPLETE, needs GETITEM METHOD to be implemented!!!!!

        return "Allosaur has Eaten";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
