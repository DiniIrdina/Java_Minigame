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

    /**
     * The constructor for the Breeding Action
     * @param dino
     */
    public BreedingAction(Dinosaur dino){
        this.target = dino;
    }

    /**
     * This method is used for breeding between dinosaurs. It checks if the dinosaurs are of different genders and if they
     * are of the same species. If these conditions are met, the appropriate Egg object will be created and added to the
     * female's inventory.
     * @param actor The actor executing the action.
     * @param map The map the actor is currently on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (target.getGender() != ((Dinosaur)actor).getGender()){
            if (target.getSpecies().equals(((Dinosaur)actor).getSpecies())){
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

    /**
     * Returns a String that describes the action executed.
     * @param actor The actor performing the action.
     * @return a String that will be displayed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +" has finished mating with " + target;
    }
}
