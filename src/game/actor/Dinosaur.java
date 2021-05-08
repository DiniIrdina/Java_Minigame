package game.actor;
import static java.util.Objects.isNull;
import edu.monash.fit2099.engine.*;
import game.action.AttackAction;
import game.behaviour.Behaviour;
import game.behaviour.WanderBehaviour;
import game.interfaces.NeedsPlayer;
import game.item.BrachiosaurEgg;
import game.item.Corpse;
import game.item.Food;


public abstract class Dinosaur extends Actor {
    protected Behaviour behaviour;
    protected Actions actions;
    protected final String SPECIES;
    protected int age;
    protected final int PREGNANT_LENGTH;
    protected final int ADULT_AGE;
    protected final char ADULT_DISPLAY;
    protected char gender;
    protected boolean attackable;
    protected boolean isPregnant;
    protected int pregnancyCounter;


    private double genderProbability = 0.5;

    public Dinosaur(String species, char displayChar, int age,int maxHitPoints,int hitPoints, int pregnant, int adultAge, char
                    adultDisplay) {
        super(species, displayChar, hitPoints);
        double probability = Math.random();
        this.SPECIES = species;
        this.maxHitPoints = maxHitPoints;
        this.PREGNANT_LENGTH = pregnant;
        this.ADULT_AGE = adultAge;
        this.age=age;
        this.attackable = true;
        this.ADULT_DISPLAY = adultDisplay;

        if (probability<=genderProbability){
            this.gender = 'F';
        }
        else{
            this.gender = 'M';
        }
    }

    public Dinosaur(String species, char displayChar,char gender, int age,int maxHitPoints,int hitPoints, int pregnant, int adultAge,
                    char adultDisplay) {
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

        if (probability<=genderProbability){
            this.gender = 'F';
        }
        else{
            this.gender = 'M';
        }
    }

    public char getGender() {
        return gender;
    }

    /**
     * Death creation for dinosaurs
     * @param target represents the current dinosaur
     * @param map represents the current game map
     */
    public void Death(Dinosaur target, GameMap map) {
        if (!target.isConscious()) {
            Corpse corpse = new Corpse(target.getSpecies(), map);
            map.locationOf(target).addItem(corpse);
            map.removeActor(target);

        }
    }
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
     * Indicates if the dinosaur has been attacked or null.
     * @return True if dinosaur been attacked, false if not.
     */
    public boolean setAttackable(){
        return this.attackable = false;
    }

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
    public void turn(){
        Age();
        increaseHunger();

    }

    /**
     * The pregnancy turn counter for keeping track and updating the pregnancy duration.
     */
    public void pregnancyTurn(){
        pregnancyCounter ++;
    }

    /**
     * Reduces the health points of a dinosaur based on a hunger threshold.
     */
    public void increaseHunger(){
        hurt(1);
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
     * The behaviours available for the dinosaur.
     */
    public abstract void assignBehaviour();

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

    /**
     * Returns the current hit points of the dinosaur.
     * @return the current hit points
     */
    public int getHitPoints(){
        return this.hitPoints;
    }


}
