package game;

public class Egg extends PortableItem{
    private String species;

    public Egg(String name, String species, char displayChar) {
        super(name, displayChar);
        setSpecies(species);
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
