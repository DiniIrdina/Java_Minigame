package game.item;

import edu.monash.fit2099.engine.Location;
import game.actor.Brachiosaur;
import game.actor.Player;

public class BrachiosaurEgg extends Egg{
    public BrachiosaurEgg() {
        super();
    }

    public void tick(Location location){
        super.tick(location);
        if (age == 20){
            Brachiosaur babyDino = new Brachiosaur(0);
            location.addActor(babyDino);
            Player.updateEcoPoints(1000);
        }
    }
}
