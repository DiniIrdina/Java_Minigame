package game.item;

import game.item.PortableItem;

public class Corpse extends Food {
    private String species;
    public Corpse(String name) {
        super(name, '8');
        this.species = species;
    }

    public String getSpecies() {
        return species;
    }
}
