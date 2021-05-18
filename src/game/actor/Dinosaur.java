package game.actor;
import edu.monash.fit2099.engine.*;
import game.behaviour.Behaviour;
import game.behaviour.BreedingBehaviour;
import game.behaviour.WanderBehaviour;
import game.interfaces.NeedsPlayer;
import game.item.Corpse;
import game.item.Food;

/**
 * Abstract class creation of Dinosaur. Used as the template for all relation types of dinosaurs
 */
public abstract class Dinosaur extends Actor implements NeedsPlayer {
    final int HUNGRY_LEVEL;
    protected Behaviour behaviour;
    protected Actions actions;
    final String SPECIES;
    int age;
    final int PREGNANT_LENGTH;
    final int ADULT_AGE;
    final char ADULT_DISPLAY;
    char gender;
    boolean isPregnant;
    final int BREEDING_LEVEL;
    final int UNCONSCIOUS_LIMIT;
    int pregnancyCounter;
    int deathTimer;
    final int MAX_THIRST;
    final int THIRSTY_LEVEL;
    int thirst;
    final int CORPSE_HEALTH;

    /**
     * Default gender probability.
     */
    private double genderProbability = 0.5;

    /**
     * First constructor of the Dinosaur class. Has multiple intake variables to help build separate dinosaur types.
     * @param species the species specified
     * @param displayChar the display char of the species
     * @param age the age
     * @param maxHitPoints the maximum hit points
     * @param hitPoints the current hit points
     * @param pregnant if the dinosaur is pregnant
     * @param adultAge the age of a fully grown dinosaur
     * @param adultDisplay the display char of the fully grown dinosaur
     */
    public Dinosaur(String species, char displayChar, int age,int maxHitPoints,int hitPoints, int pregnant, int adultAge, char
                    adultDisplay, int breed, int limit, int hunger, int maxThirst, int thirst, int thirstyLevel, int corpse_health) {
        super(species, displayChar, hitPoints);
        double probability = Math.random();
        this.SPECIES = species;
        this.maxHitPoints = maxHitPoints;
        this.PREGNANT_LENGTH = pregnant;
        this.ADULT_AGE = adultAge;
        this.age=age;
        this.ADULT_DISPLAY = adultDisplay;
        this.BREEDING_LEVEL = breed;
        this.UNCONSCIOUS_LIMIT = limit;
        this.HUNGRY_LEVEL = hunger;
        this.MAX_THIRST = maxThirst;
        this.thirst = thirst;
        this.THIRSTY_LEVEL = thirstyLevel;
        this.CORPSE_HEALTH = corpse_health;

        if (probability<=genderProbability){
            this.gender = 'F';
        }
        else{
            this.gender = 'M';
        }
    }

    /**
     * Second constructor of the Dinosaur class. Has multiple intake variables to help build separate dinosaur types.
     * @param species the species specified
     * @param displayChar the display char of the species
     * @param gender the gender of the dinosaur
     * @param age the age
     * @param maxHitPoints the maximum hit points
     * @param hitPoints the current hit points
     * @param pregnant if the dinosaur is pregnant
     * @param adultAge the age of a fully grown dinosaur
     * @param adultDisplay the display char of the fully grown dinosaur
     */
    public Dinosaur(String species, char displayChar,char gender, int age,int maxHitPoints,int hitPoints, int pregnant, int adultAge,
                    char adultDisplay, int breed, int limit, int hunger, int maxThirst, int thirst, int thirstyLevel, int corpse_health) {
        super(species, displayChar, hitPoints);
        double probability = Math.random();
        this.maxHitPoints = maxHitPoints;
        this.SPECIES = species;
        this.PREGNANT_LENGTH = pregnant;
        this.ADULT_AGE = adultAge;
        this.ADULT_DISPLAY = adultDisplay;
        this.gender = gender;
        this.age=age;
        this.pregnancyCounter = 0;
        this.deathTimer=0;
        this.BREEDING_LEVEL = breed;
        this.UNCONSCIOUS_LIMIT = limit;
        this.HUNGRY_LEVEL = hunger;
        this.MAX_THIRST = maxThirst;
        this.thirst = thirst;
        this.THIRSTY_LEVEL = thirstyLevel;
        this.CORPSE_HEALTH = corpse_health;

    }

    /**
     * Returns the corpse's health
     * @return corpse's health
     */
    public int getCorpseHealth() {
        return CORPSE_HEALTH;
    }

    /**
     * Returns the gender
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * Death creation for dinosaurs
     * @param target represents the current dinosaur
     * @param map represents the current game map
     */
    public void Death(Dinosaur target, GameMap map, int health) {
        if (!target.isConscious()) {
            Corpse corpse = new Corpse(target.getSpecies(), map, health);
            map.locationOf(target).addItem(corpse);
            map.removeActor(target);

        }
    }
    public boolean isAdult(){
        if (age >= ADULT_AGE){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns the name of the dinosaur
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Specifies the species of the Dinosaur actor
     * @return the species of dinosaur
     */
    public String getSpecies(){
        return SPECIES;}

    /**
     * Specifies the different types of food the dinosaur can consume.
     * @param food the type of food
     * @return true if food is edible by current dinosaur
     */
    public abstract boolean canEat(Food food);

    /**
     * Checks if the current dinosaur is pregnant.
     * @return true if pregnant, false if not
     */
    public boolean isPregnant() {
        return isPregnant;
    }

    /**
     * Removes the current egg within the dinosaur inventory.
     */
    public void removeEgg(){
        for(Item item: inventory){
            if (item.toString().equals(this.getSpecies()+"Egg")){
                this.removeItemFromInventory(item);
                break;
            }
        }
    }

    /**
     * The generalized turn counter to update age and hungry values.
     */
    public void turn(GameMap map){
        Age();
        increaseHunger(map);
        if(hitPoints >= BREEDING_LEVEL && isAdult() && !isPregnant()){
            setBehaviour(new BreedingBehaviour());
        }else if (isPregnant()){
            setBehaviour(new WanderBehaviour());
        }

    }

    /**
     * The pregnancy turn counter for keeping track and updating the pregnancy duration.
     */
    public void pregnancyTurn(){
        pregnancyCounter ++;
    }

    /**
     * The unconscious timer to determine if death is applicable.
     */
    public void deathTimerUpdate(){
        deathTimer++;
    }

    /**
     * The function to execute death if the dinosaur is unconscious for a certain amount of time.
     * @param map The current instance of the map
     */
    public void unconsciousPeriod(GameMap map){
        if (hitPoints <= 0){
            if (!isConscious() && deathTimer < 15){
                deathTimerUpdate();
            }
            else if (!isConscious() && deathTimer == 15){
                Death(this, map, CORPSE_HEALTH);
            }
        }

        else{
            this.deathTimer = 0;
        }
    }

    /**
     * Reduces the health points of a dinosaur based on a hunger threshold.
     */
    public void increaseHunger(GameMap map){
        Location location = map.locationOf(this);
        if (hitPoints < HUNGRY_LEVEL){
            System.out.println(this +" at (" + location.x()+ ","+ location.y()+ ") is hungry!");
        }
        hurt(1);
    }

    public void increaseThirst(GameMap map){
        Location location = map.locationOf(this);
        if (thirst < HUNGRY_LEVEL){
            System.out.println(this +" at (" + location.x()+ ","+ location.y()+ ") is thirsty!");
        }
    }

    /**
     * Updates the age of the dinosaur.
     */
    public void Age(){
        age++;
        if(age== ADULT_AGE){
            displayChar = ADULT_DISPLAY;
        }
    }

    /**
     * The default behaviour for dinosaurs.
     * @return the wander behaviour class execution
     */
    public WanderBehaviour getWanderBehaviour(){
        return new WanderBehaviour();
    }

    /**
     * Returns the current behaviour of the dinosaur.
     * @return the behaviour
     */
    public Behaviour getBehaviour() {
        return behaviour;
    }

    /**
     * Sets the current behaviour of the dinosaur.
     * @param behaviour the behaviour
     */
    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    /**
     * The eating action.
     * @param food the type of food
     */
    public abstract void eatsFood(Food food);

    public abstract void drinksWater();

    /**
     * Returns the current hit points of the dinosaur.
     * @return the current hit points
     */
    public int getHitPoints(){
        return this.hitPoints;
    }


}
