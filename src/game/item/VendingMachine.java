package game.item;

import edu.monash.fit2099.engine.*;
import game.actor.Player;

import java.util.Scanner;

public class VendingMachine extends Ground {
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
        super('V');
        Selection_Purchase();
    }

    public void itemChoices(){
        System.out.println("1. Fruit");
        System.out.println("2. Vegetarian Meal Kit");
        System.out.println("3. Carnivore Meal Kit");
        System.out.println("4. Stegosaur Egg");
        System.out.println("5. Brachiosaur Egg");
        System.out.println("6. Allosaur Egg");
        System.out.println("7. Laser Gun");
        System.out.println("8. Exit");
    }

    public void Selection_Purchase(){
        Scanner scan = new Scanner(System.in);
        itemChoices();
        this.Selection = scan.nextInt();
        scan.nextLine();
        Item item = purchase(Player, this.Selection);
        Player.addItemToInventory(item);
    }

    public Item purchase(Player player, int selection){
        Item selectedItem = null;
        do {
            switch (selection) {
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
        }while(this.Selection != 8);
        return selectedItem;
    }

    public Item spawnFruit(int totalEcoPoints){
        int price = 30;
        try {
            if (totalEcoPoints >= price) {
                Fruit fruit = new Fruit();
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
            VegetarianMealKit vegetarianMealKit = new VegetarianMealKit();
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
            CarnivoreMealKit carnivoreMealKit = new CarnivoreMealKit();
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
            Egg stegosaurEgg = new Egg("Stegosaur Egg");
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
            Egg brachiosaurEgg = new Egg("Brachiosaur Egg");
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
            Egg allosaurEgg = new Egg("Allosaur Egg");
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
            LaserGun laserGun = new LaserGun();
            this.Player.removeEcoPoints(price);
            selected = laserGun;
        }
        else {
            System.out.print("Insufficient Points");
        }
        return selected;
    }
}

