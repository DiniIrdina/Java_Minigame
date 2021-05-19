package game.item;

import edu.monash.fit2099.engine.Location;
import game.actor.Player;
import game.actor.Pterodactyl;
import game.environment.Tree;

public class PterodactylEgg extends Egg{
    public static final int PRICE = 200;
    /**
     * The overloaded constructor for the Egg class. All eggs will be represented by the char 'o'.
     */
    public PterodactylEgg() {
        super("Pterodactyl");
    }

    public void tick(Location location){
        super.tick(location);
        if (age == 20){
            Pterodactyl babyDino = new Pterodactyl(0);
            location.addActor(babyDino);
            Tree tree = (Tree)location.getGround();
            tree.removeEgg();
            Player.updateEcoPoints(100);
        }
    }

    public int getPrice(){
        return PRICE;
    }
}
