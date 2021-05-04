package game.item;

import edu.monash.fit2099.engine.Location;
import game.actor.Brachiosaur;
import game.actor.Allosaur;
import game.actor.Stegosaur;

public class Egg extends PortableItem {
    private String species;
    private int age = 0;

    public Egg(String name, String species, char displayChar) {
        super(name, 'o');
        setSpecies(species);
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    public void tick(Location currentLocation){
        super.tick(currentLocation);
        age++;
        if (age == 10){
            if (species.equals("Stegosaur")){
                Stegosaur babyDino = new Stegosaur("Stegosaur");
            }
            else if (species.equals("Brachiosaur")){
                Brachiosaur babyDino = new Brachiosaur("Brachiosaur");
            }
            else{
                Allosaur babyDino = new Allosaur("Allosaur");
            }
        }

    }

}
