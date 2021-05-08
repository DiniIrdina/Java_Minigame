package game.item;

import edu.monash.fit2099.engine.Location;
import game.interfaces.VendingMachineItemsInterface;
import game.actor.Brachiosaur;
import game.actor.Player;

public class BrachiosaurEgg extends Egg implements VendingMachineItemsInterface {
    public static final int PRICE = 500;
    public BrachiosaurEgg() {
        super("Brachiosaur");
    }

    public void tick(Location location){
        super.tick(location);
        if (age == 40){
            Brachiosaur babyDino = new Brachiosaur(0);
            location.addActor(babyDino);
            Player.updateEcoPoints(1000);
        }
    }

    public int getPrice(){
        return PRICE;
    }
}
