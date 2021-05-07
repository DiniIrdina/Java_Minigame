package game.actor;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.behaviour.Behaviour;
import game.item.Corpse;
import game.item.Food;


public abstract class Dinosaur extends Actor {
    protected Behaviour behaviour;

    protected String species;

    protected char gender;

    private double genderProbability = 0.5;

    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        double probability = Math.random();
        if (probability<=genderProbability){
            this.gender = 'F';
        }
        else{
            this.gender = 'M';
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Death creation for dinosaurs
     * @param target represents the current dinosaur
     * @param map represents the current game map
     */
    public void Death(Dinosaur target, GameMap map) {
        if (!target.isConscious()) {
            Corpse corpse = new Corpse(target.getSpecies());
            map.locationOf(target).addItem(corpse);
            map.removeActor(target);

        }
    }
    public String getName(){
        return this.name;
    }

    /**
     * Specifies the species of the Dinosaur actor
     * @return the species of dinosaur
     */
    public String getSpecies(){
        return species;}
    /**
     * Indicates if the dinosaur has been attacked or null.
     * @return True if dinosaur been attacked, false if not.
     */
    public abstract boolean Attackable();

    public abstract boolean canEat(Food food);

    public void removeEgg(){
        for(Item item: inventory){
            if (item.toString().equals("Egg")){
                this.removeItemFromInventory(item);
                break;
            }
        }
    }

    public abstract void eatsFood(Food food);
}
