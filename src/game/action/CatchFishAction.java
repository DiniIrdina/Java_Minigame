package game.action;

import edu.monash.fit2099.engine.*;
import game.actor.Dinosaur;
import game.actor.Pterodactyl;
import game.environment.Lake;
import game.item.Fish;


/**
 * An action class used mainly by the Pterodactyl to catch fish in a lake. It uses a random number generator to decide if it catches
 * either one or two fish. In addition, it also allows the Pterodactyl to drink water from the lake if there are enough
 * sips in it.
 *
 * @see Lake#getFishPopulation()
 * @see Dinosaur#drinksWater()
 */
public class CatchFishAction extends Action {
    private int numberOfFish = 0;
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        Lake lake = (Lake) location.getGround();
        double catchOneChance = 0.33;
        double catchTwoChance = 0.66;

        double fishChance = Math.random();
        if (lake.getFishPopulation().size() > 2){
            if (fishChance <=catchOneChance){
                lake.reduceFishPopulation(1);
                ((Pterodactyl)actor).eatsFood(new Fish());
                numberOfFish = 1;
            }else if(fishChance >= catchTwoChance){
                lake.reduceFishPopulation(2);
                ((Pterodactyl)actor).eatsFood(new Fish());
                ((Pterodactyl)actor).eatsFood(new Fish());
                numberOfFish = 2;
            }
        }else if (lake.getFishPopulation().size() == 1){
            lake.reduceFishPopulation(1);
            ((Pterodactyl)actor).eatsFood(new Fish());
            numberOfFish = 1;
        }

        if (lake.getSips() > 0){
            ((Pterodactyl)actor).drinksWater();
            System.out.println(actor + " drank water!");
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " ate " + numberOfFish + " fish!";
    }
}