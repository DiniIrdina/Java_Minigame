package game.item;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.PortableItem;

public class Corpse extends Food {
    private int timer;
    private int despawn_timer;
    private String species;
    private GameMap map;

    public Corpse(String species, GameMap map) {
        super("Corpse", '8');
        this.species = species;
        this.timer = 0;
        this.map = map;
        setDespawn_timer();
    }

    public String getSpecies() {
        return species;
    }

    public String setSpecies(String species) {
        return this.species = species;
    }

    public void setDespawn_timer(){
        if (species.equals("Allosaur") || species.equals("Stegosaur")){
            despawn_timer = 20;
        }
        else if (species.equals("Brachiosaur")){
            despawn_timer = 40;
        }
    }

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

    public void removeCorpse(){
        Location location = getItemLocation(map, this);
        if (location != null) {
            location.removeItem(this);
        }
    }
}

