package game.item;

import edu.monash.fit2099.engine.Location;
import game.interfaces.VendingMachineItemsInterface;
import game.actor.Player;
import game.actor.Stegosaur;

public class StegosaurEgg extends Egg implements VendingMachineItemsInterface {
    public static final int PRICE = 200;
    public StegosaurEgg() {
        super("Stegosaur");
    }

    public void tick(Location location){
        super.tick(location);
        if (age == 20){
            Stegosaur babyDino = new Stegosaur(0);
            location.addActor(babyDino);
            Player.updateEcoPoints(100);
        }
    }
}
