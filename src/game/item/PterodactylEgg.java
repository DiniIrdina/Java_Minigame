package game.item;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actor.Player;
import game.actor.Pterodactyl;
import game.environment.Tree;

/**
 * Class that represents a PterodactylEgg, an Egg item.
 */
public class PterodactylEgg extends Egg{
    public static final int PRICE = 200;
    /**
     * The overloaded constructor for the Egg class. All eggs will be represented by the char 'o'.
     */
    public PterodactylEgg() {
        super("Pterodactyl");
    }

    /**
     * Tick updates the current turn of game for the class object
     * Will spawn the specified dinosaur object once the condition has been fulfilled.
     * @param location The location of the Ground
     */
    public void tick(Location location){
        super.tick(location);
        /*
        !!!!DINI!!!! PLEASE READ!!!!!!
        When I was debugging, I realised that the egg can be placed on the ground and it'll age. When that
        happens, it crashes the game as you coded it to only spawn in trees. I added this check to prevent the
        egg from aging when it is placed on the floor. It works but we have another issue with placing the egg
        on top of a tree.
        (Java says the class for placing the egg is dinosaur class related, you can test it if you're curious!)
         */
//        if (!(location.getGround() instanceof Tree)){
//            age--;
//            System.out.println("Pterodactyl can't be born on ground.");
//        }

        if (age == 20){
            Pterodactyl babyDino = new Pterodactyl(0);
            location.addActor(babyDino);
            Ground ground = location.getGround();
            if (ground instanceof Tree){
                Tree tree = ((Tree)ground);
                tree.removeEgg();
            }else{
                location.removeItem(this);
            }

            Player.updateEcoPoints(100);
        }
    }

    /**
     * Returns the price of the object.
     * @return the price
     */
    public int getPrice(){
        return PRICE;
    }
}
