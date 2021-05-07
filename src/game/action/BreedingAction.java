package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Dinosaur;
import game.item.AllosaurEgg;
import game.item.BrachiosaurEgg;
import game.item.Egg;
import game.item.StegosaurEgg;

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
                    if (target.getSpecies().equals("Stegosaur")){
                        actor.addItemToInventory(new StegosaurEgg());
                    }
                    else if (target.getSpecies().equals("Brachiosaur")){
                        actor.addItemToInventory(new BrachiosaurEgg());
                    }
                    else if (target.getSpecies().equals("Allosaur")){
                        actor.addItemToInventory(new AllosaurEgg());
                    }

                }
                else{
                    if (target.getSpecies().equals("Stegosaur")){
                        target.addItemToInventory(new StegosaurEgg());
                    }
                    else if (target.getSpecies().equals("Brachiosaur")){
                        target.addItemToInventory(new BrachiosaurEgg());
                    }
                    else if (target.getSpecies().equals("Allosaur")){
                        target.addItemToInventory(new AllosaurEgg());
                    }
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
