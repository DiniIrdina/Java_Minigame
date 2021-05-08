package game.item;

import edu.monash.fit2099.engine.Location;
import game.interfaces.VendingMachineItemsInterface;
import game.actor.Allosaur;
import game.actor.Player;


public class AllosaurEgg extends Egg implements VendingMachineItemsInterface {
    public static final int PRICE = 1000;
    public AllosaurEgg() {
        super("Allosaur");
    }

    public void tick(Location location){
        super.tick(location);
        if (age == 50){
            Allosaur babyDino = new Allosaur(0);
            location.addActor(babyDino);
            Player.updateEcoPoints(1000);
        }
    }
}
