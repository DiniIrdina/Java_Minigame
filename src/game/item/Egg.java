package game.item;

import edu.monash.fit2099.engine.Location;

public abstract class Egg extends Food {
    private String species;
    protected int age = 0;
    static final int FEED_POINTS = 10;

    public Egg(String species) {
        super(species+" Egg", 'o');
        setSpecies(species);
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public static int getFeedPoints() {
        return FEED_POINTS;
    }

    public void tick(Location currentLocation){
        super.tick(currentLocation);
        age++;
//        if (age == 20 && species.equals("Stegosaur")){
//            Stegosaur babyDino = new Stegosaur(0);
//            currentLocation.addActor(babyDino);
//            Player.updateEcoPoints(100);
//            }
//        if (age == 50 && species.equals("Allosaur")){
//            Allosaur babyDino = new Allosaur(0);
//            currentLocation.addActor(babyDino);
//            Player.updateEcoPoints(1000);
//        }
//        if (age ==40 && species.equals("Brachiosaur")){
//            Brachiosaur babyDino = new Brachiosaur(0);
//            currentLocation.addActor(babyDino);
//            Player.updateEcoPoints(1000);
//        }

            }
        }


