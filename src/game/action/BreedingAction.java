package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Dinosaur;
import game.item.Egg;

public class BreedingAction extends Action {
    private Dinosaur target;

    public BreedingAction(Dinosaur dino){
        this.target = dino;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.getGender() != ((Dinosaur)actor).getGender()){
            if (target.getName().equals(((Dinosaur)actor).getName())){
                if (((Dinosaur)actor).getGender() == 'F' && target.getGender()=='M'){
                    actor.addItemToInventory(new Egg(target.getSpecies()));
                }
                else{
                    target.addItemToInventory(new Egg(target.getSpecies()));
                }
            }
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor +" has finished mating with " + target;
    }
}
