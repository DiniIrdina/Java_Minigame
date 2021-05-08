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
    protected int pregnencyCounter;


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
        this.pregnencyCounter = 0;

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

    public abstract boolean canEat(Food food);

    public boolean isPregnant() {
        return isPregnant;
    }

    public void removeEgg(){
        for(Item item: inventory){
            if (item.toString().equals(this.getSpecies()+"Egg")){
                this.removeItemFromInventory(item);
                break;
            }
        }
    }

    public void turn(){
        Age();
        increaseHunger();

    }

    public void pregnencyTurn(){
        pregnencyCounter ++;
    }

    public void increaseHunger(){
        hurt(1);
    }

    public void Age(){
        age++;
        if(age== ADULT_AGE){
            displayChar = ADULT_DISPLAY;
        }
    }

    public abstract void assignBehaviour();

    public WanderBehaviour getWanderBehaviour(){
        return new WanderBehaviour();
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

//    @Override
//    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
//        turn();
//        return new DoNothingAction();
//    }

    public abstract void eatsFood(Food food);

    public int getHitPoints(){
        return this.hitPoints;
    }


}
