package game.item;

import game.item.PortableItem;

public class Corpse extends Food {
    private String species;
    public Corpse(String species) {
        super("Corpse", '8');
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }
}
