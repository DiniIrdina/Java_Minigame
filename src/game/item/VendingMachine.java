package game.item;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import game.actor.Player;
import game.item.Fruit;
import game.item.PortableItem;

public class VendingMachine extends Item {
    private int Selection;
    protected Actor Actor;
    protected game.actor.Player Player;


    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public VendingMachine(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public Item purchase(Player player){
        Item selectedItem = null;
        switch(this.Selection){
            case 1:
                //fruit
                selectedItem = spawnFruit(player.EcoPointStorage);
                break;
            case 2:
                //vege meal kit
                selectedItem = spawnVegetarianMealKit(player.EcoPointStorage);
                break;
            case 3:
                //carni meal kit
                selectedItem = spawnCarnivoreMealKit(player.EcoPointStorage);
                break;
            case 4:
                //stego egg
                selectedItem = spawnStegosaurEgg(player.EcoPointStorage);
                break;
            case 5:
                //brachio egg
                selectedItem = spawnBrachiosaurEgg(player.EcoPointStorage);
                break;
            case 6:
                //allosaur egg
                selectedItem = spawnAllosaurEgg(player.EcoPointStorage);
                break;
            case 7:
                //laser gun
                selectedItem = spawnLaserGun(player.EcoPointStorage);
                break;
        }
        return selectedItem;
    }

    public Item spawnFruit(int totalEcoPoints){
        int price = 30;
        try {
            if (totalEcoPoints >= price) {
                Fruit fruit = new Fruit("Fruit", '*');
                this.Player.removeEcoPoints(price);
                return fruit;
            }
        }
        catch(Exception e){
            System.out.print("Insufficient Points");
        }
        return null;
    }

    public Item spawnVegetarianMealKit(int totalEcoPoints){
        int price = 100;
        Item selected = null;
        if (totalEcoPoints >= price) {
            Item vegetarianMealKit = new PortableItem("Vegetarian Meal Kit", '&');
            this.Player.removeEcoPoints(price);
            selected = vegetarianMealKit;
            }
        else {
            System.out.print("Insufficient Points");
        }
        return selected;
    }

    public Item spawnCarnivoreMealKit(int totalEcoPoints){
        int price = 500;
        Item selected = null;
        if (totalEcoPoints >= price) {
            Item carnivoreMealKit = new PortableItem("Carnivore Meal Kit", '^');
            this.Player.removeEcoPoints(price);
            selected = carnivoreMealKit;
        }
        else {
            System.out.print("Insufficient Points");
        }
        return selected;
    }

    public Item spawnStegosaurEgg(int totalEcoPoints){
        int price = 200;
        Item selected = null;
        if (totalEcoPoints >= price) {
            Item stegosaurEgg = new PortableItem("Stegosaur Egg", 's');
            this.Player.removeEcoPoints(price);
            selected = stegosaurEgg;
        }
        else {
            System.out.print("Insufficient Points");
        }
        return selected;
    }

    public Item spawnBrachiosaurEgg(int totalEcoPoints){
        int price = 500;
        Item selected = null;
        if (totalEcoPoints >= price) {
            Item brachiosaurEgg = new PortableItem("Brachiosaur Egg", 'b');
            this.Player.removeEcoPoints(price);
            selected = brachiosaurEgg;
        }
        else {
            System.out.print("Insufficient Points");
        }
        return selected;
    }

    public Item spawnAllosaurEgg(int totalEcoPoints){
        int price = 1000;
        Item selected = null;
        if (totalEcoPoints >= price) {
            Item allosaurEgg = new PortableItem("Allosaur Egg", 'a');
            this.Player.removeEcoPoints(price);
            selected = allosaurEgg;
        }
        else {
            System.out.print("Insufficient Points");
        }
        return selected;
    }

    public Item spawnLaserGun(int totalEcoPoints){
        int price = 500;
        Item selected = null;
        if (totalEcoPoints >= price) {
            Item laserGun = new PortableItem("Laser Gun", 'g');
            this.Player.removeEcoPoints(price);
            selected = laserGun;
        }
        else {
            System.out.print("Insufficient Points");
        }
        return selected;
    }
}
