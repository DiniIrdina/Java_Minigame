package game.item;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Class creation for the item Corpse, a food item.
 */
public class Corpse extends Food {
    private int timer;
    private int despawn_timer;
    private String species;
    private GameMap map;

    /**
     * The overloaded constructor for the Corpse class. Will execute the despawn timer when instantiated.
     * @param species the species of the dinosaur
     * @param map the current instance of the map
     */
    public Corpse(String species, GameMap map) {
        super("Corpse", '%');
        this.species = species;
        this.timer = 0;
        this.map = map;
        setDespawn_timer();
    }

    /**
     * Returns the species.
     * @return species
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets the species of the corpse.
     * @param species the specified species
     * @return the updated species
     */
    public String setSpecies(String species) {
        return this.species = species;
    }

    /**
     * The despawn timer for the corpse object removal from the game map when conditions are fulfilled.
     * Each species has a different despawn timer for their relation corpses.
     */
    public void setDespawn_timer(){
        if (species.equals("Allosaur") || species.equals("Stegosaur")){
            despawn_timer = 20;
        }
        else if (species.equals("Brachiosaur")){
            despawn_timer = 40;
        }
    }

    /**
     * Tick updates the current turn of game for the class object. Keeps track of the current timer and if aligns
     * with the despawn timer, removes the corpse object.
     * @param currentLocation The location of the ground on which we lie.
     */
    public void tick(Location currentLocation){
        super.tick(currentLocation);
        this.timer++;
        if (species.equals("Allosaur") || species.equals("Stegosaur")){
            if (timer == despawn_timer) {
                removeCorpse();
            }
        }

        else if (species.equals("Brachiosaur")) {
            if (timer == despawn_timer){
                removeCorpse();
            }
        }
    }

    /**
     * Removes the corpse object from the game map.
     */
    public void removeCorpse(){
        Location location = getLocation(map, this);
        if (location != null) {
            location.removeItem(this);
        }
    }
}

