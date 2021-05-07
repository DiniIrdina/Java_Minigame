package game.item;

import edu.monash.fit2099.engine.Location;
import game.actor.Brachiosaur;
import game.actor.Allosaur;
import game.actor.Stegosaur;

public class Egg extends Food {
    private String species;
    private int age = 0;

    public Egg(String species) {
        super("Egg", 'o');
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
                Stegosaur babyDino = new Stegosaur(0);
                currentLocation.addActor(babyDino);
            }
            else if (species.equals("Brachiosaur")){
                Brachiosaur babyDino = new Brachiosaur(0);
                currentLocation.addActor(babyDino);
            }
            else{
                Allosaur babyDino = new Allosaur(0);
                currentLocation.addActor(babyDino);
            }
        }
    }
}
