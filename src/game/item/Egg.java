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
        if (age == 20 && species.equals("Stegosaur")){
            Stegosaur babyDino = new Stegosaur(0);
            currentLocation.addActor(babyDino);
            }
        if (age == 50 && species.equals("Allosaur")){
            Allosaur babyDino = new Allosaur(0);
            currentLocation.addActor(babyDino);
        }
        if (age ==40 && species.equals("Brachiosaur")){
            Brachiosaur babyDino = new Brachiosaur(0);
            currentLocation.addActor(babyDino);
        }

            }
        }


