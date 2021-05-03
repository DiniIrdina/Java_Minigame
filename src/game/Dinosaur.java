package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;


public abstract class Dinosaur extends Actor {
    protected Behaviour behaviour;
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Death creation for dinosaurs
     * @param target represents the current dinosaur
     * @param map represents the current game map
     */
    public void Death(Dinosaur target, GameMap map) {
        if (!target.isConscious()) {
            Corpse corpse = new Corpse("dead " + target.name, '%');
            map.locationOf(target).addItem(corpse);
            map.removeActor(target);

        }
    }

    /**
     * Specifies the species of the Dinosaur actor
     * @return the species of dinosaur
     */
    public abstract String getSpecies();

    /**
     * Indicates if the dinosaur has been attacked or null.
     * @return True if dinosaur been attacked, false if not.
     */
    public abstract boolean Attackable();

}
