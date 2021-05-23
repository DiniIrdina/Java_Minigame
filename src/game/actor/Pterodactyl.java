package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.AttackAction;
import game.action.FeedingAction;
import game.behaviour.PterodactylBehaviour;
import game.behaviour.WanderBehaviour;
import game.environment.Tree;
import game.interfaces.NearestTree;
import game.interfaces.NeedsPlayer;
import game.item.*;

import static java.util.Objects.isNull;

/**
 * Class creation for Pterodactyl, a carnivorous dinosaur.
 */
public class Pterodactyl extends Dinosaur implements NearestTree {
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
    static final int STARTING_HIT_POINTS = 50;

    private int flyDuration;
    private boolean onGround;

    /**
     * First overloaded constructor of the Pterodactyl class. All Pterodactyl have 100 hit points. The baby
     * Pterodactyl will have 'p' as its display and the adult will have 'P'.
     * @param age the current age of the Pterodactyl to be created for
     */
    public Pterodactyl(int age) {
        super(SPECIES, ADULT_PTERODACTYL_DISPLAY, age, MAX_HIT_POINTS,STARTING_HIT_POINTS,PREGNANT_LENGTH, ADULT_AGE,
                ADULT_PTERODACTYL_DISPLAY, BABY_PTERODACTYL_DISPLAY,BREEDING_LEVEL, UNCONSCIOUS_LIMIT,HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST, THIRSTY_LEVEL, CORPSE_HEALTH);
        flyDuration = 0;
        onGround = false;
    }

    /**
     * Second overloaded constructor of the Pterodactyl class.
     * @param age the current age of the Pterodactyl to be created for
     * @param gender the gender of the Pterodactyl to be created for
     */
    public Pterodactyl(int age, char gender) {
        super(SPECIES, ADULT_PTERODACTYL_DISPLAY, gender,age, MAX_HIT_POINTS,STARTING_HIT_POINTS,PREGNANT_LENGTH, ADULT_AGE,
                ADULT_PTERODACTYL_DISPLAY,BABY_PTERODACTYL_DISPLAY, BREEDING_LEVEL, UNCONSCIOUS_LIMIT, HUNGRY_LEVEL, MAX_THIRST, STARTING_THIRST, THIRSTY_LEVEL, CORPSE_HEALTH);
        flyDuration = 0;
        onGround = false;
    }

    /**
     * Specifies the different types of food the dinosaur can consume.
     * @param food the type of food
     * @return true if food is edible by current dinosaur
     */
    @java.lang.Override
    public boolean canEat(Food food) {
        boolean result = false;
        if (food instanceof Fish || food instanceof Corpse){
            result = true;
        }
        return result;
    }

    /**
     * The eating action.
     * @param food the type of food
     */
    @java.lang.Override
    public void eatsFood(Food food) {
        if (food instanceof Fish){
            heal(5);
        }else if (food instanceof Corpse){
            heal(10);
        }
    }

    /**
     * Lists all the actions that the other actor can perform on the current actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return           actions
     */
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        actions = new Actions();
        actions.add(new AttackAction(this));
        Player player = NeedsPlayer.retrievePlayer(map);
        if (!isNull(player)){
            for(Item item: player.getInventory()){
                if (item instanceof Egg){
                    actions.add(new FeedingAction(this,((Food)item), Egg.getFeedPoints()));
                }else if (item instanceof CarnivoreMealKit){
                    actions.add(new FeedingAction(this,((Food)item), CarnivoreMealKit.getFeedPoints()));
                }
            }
        }
        return actions;

    }

    /**
     * The drinking water action executed.
     */
    @Override
    public void drinksWater() {
        this.thirst += 30;
    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Pterodactyl wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @java.lang.Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action wander = behaviour.getAction(this, map);
        super.turn(map);
        Location location = map.locationOf(this);

        if (!onGround){
            flyDuration++;
        }

        if (flyDuration < 5){
            setBehaviour(new PterodactylBehaviour());
        }


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
            else if (this.pregnancyCounter >= 20){
                Ground ground = location.getGround();
                if (ground instanceof Tree){
                    Tree tree = (Tree)ground;
                    if (!tree.isOccupied()){  //if there is no other egg occupying the tree
                        this.isPregnant = false;
                        LayEgg(map.locationOf(this));
                        tree.addEgg();
                        removeEgg();
                        this.pregnancyCounter = 0;
                    }else{  //if there is, find another tree
                        Location nearTree = NearestTree.getNearestTree(this, map);
                        return WanderBehaviour.moveTo(this, map, location, nearTree);
                    }
                }else{
                    Location nearTree = NearestTree.getNearestTree(this, map);
                    return WanderBehaviour.moveTo(this, map, location, nearTree);
                }

            }
        }
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }

    /**
     * Lays an egg object on the current location of the dinosaur.
     * @param location the location the dinosaur is current at
     */
    @Override
    public void LayEgg(Location location) {
        Tree tree = (Tree) location.getGround();
        tree.addEgg();
    }

    /**
     * Getter method that returns the current Flying duration of the dinosaur.
     * @return the current flying duration
     */
    public int getFlyDuration() {
        return flyDuration;
    }

    /**
     * Setter method that resets the current flying duration of the dinosaur.
     */
    public void resetFlyDuration() {
        this.flyDuration = 0;
    }

    /**
     * Checks if the dinosaur is on the ground
     * @return returns true if dinosaur is on the ground, false otherwise.
     */
    public boolean isOnGround() {
        return onGround;
    }

    /**
     * Setter method that sets if the dinosaur is on ground.
     * @param onGround input true if its on ground, false otherwise
     */
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
