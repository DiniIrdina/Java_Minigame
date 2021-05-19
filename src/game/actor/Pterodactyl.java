package game.actor;

import edu.monash.fit2099.engine.*;
import game.behaviour.WanderBehaviour;
import game.environment.Tree;
import game.item.*;

public class Pterodactyl extends Dinosaur{
    static final String SPECIES = "Pterodactyl";
    static final int ADULT_AGE = 20;
    static final int MAX_HIT_POINTS=100;
    static final int HUNGRY_LEVEL = 90;
    static final int PREGNANT_LENGTH = 10;
    static final char BABY_PTERODACTYL_DISPLAY = 'p';
    static final char ADULT_PTERODACTYL_DISPLAY = 'P';
    static final int BREEDING_LEVEL =50;
    static final int UNCONSCIOUS_LIMIT =20;
    static final int MAX_THIRST = 100;
    static final int STARTING_THIRST = 60;
    static final int THIRSTY_LEVEL = 40;
    static final int CORPSE_HEALTH = 30;


    public Pterodactyl(int age) {
        super(SPECIES, ADULT_PTERODACTYL_DISPLAY, age, MAX_HIT_POINTS,100,PREGNANT_LENGTH, ADULT_AGE,
                ADULT_PTERODACTYL_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT,HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST, THIRSTY_LEVEL, CORPSE_HEALTH);    }

    public Pterodactyl(int age, char gender) {
        super(SPECIES, ADULT_PTERODACTYL_DISPLAY, gender,age, MAX_HIT_POINTS,50,PREGNANT_LENGTH, ADULT_AGE,
                ADULT_PTERODACTYL_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT, HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST, THIRSTY_LEVEL, CORPSE_HEALTH);
        if (age < ADULT_AGE){
            this.displayChar = BABY_PTERODACTYL_DISPLAY;
        }
        behaviour = new WanderBehaviour();
    }

    @java.lang.Override
    public boolean canEat(Food food) {
        boolean result = false;
        if (food instanceof Fish || food instanceof Corpse){
            result = true;
        }
        return result;
    }

    @java.lang.Override
    public void eatsFood(Food food) {

    }

    @Override
    public void drinksWater() {
        this.thirst += 30;
    }

    @java.lang.Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        super.turn(map);

        Location location = map.locationOf(this);


        for (Item item: inventory){
            if (item instanceof PterodactylEgg){
                this.isPregnant = true;
                break;
            }else{
                this.isPregnant = false;
            }
        }

        if (this.isPregnant){
            if (this.pregnancyCounter < 20){
                pregnancyTurn();
            }
            else if (this.pregnancyCounter >= 20 && location.getGround() instanceof Tree){
                this.isPregnant = false;
                LayEgg(map.locationOf(this));
                removeEgg();
                this.pregnancyCounter = 0;
            }
        }
        return null;
    }

    @Override
    public void LayEgg(Location location) {
        Tree tree = (Tree) location.getGround();
        tree.addEgg();
    }
}
