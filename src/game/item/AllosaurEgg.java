package game.item;

import edu.monash.fit2099.engine.Location;
import game.actor.Allosaur;
import game.actor.Player;


public class AllosaurEgg extends Egg{
    public AllosaurEgg() {
        super();
    }

    public void tick(Location location){
        super.tick(location);
        if (age == 20){
            Allosaur babyDino = new Allosaur(0);
            location.addActor(babyDino);
            Player.updateEcoPoints(1000);
        }
    }
}
