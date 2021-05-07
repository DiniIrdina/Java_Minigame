package game.item;

import edu.monash.fit2099.engine.Location;
import game.item.PortableItem;

public class Corpse extends Food {
    private int timer;
    private int despawn_timer;
    private String species;
    public Corpse(String species) {
        super("Corpse", '8');
        this.species = species;
        this.timer = 0;
        setDespawn_timer();
    }

    public String getSpecies() {
        return species;
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
    }

}

