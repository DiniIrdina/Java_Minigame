package game.item;

import edu.monash.fit2099.interfaces.VendingMachineItemsInterface;

public class Fruit extends Food implements VendingMachineItemsInterface {
    public static final int price = 30;
    public Fruit(){
        super("Fruit", '*');
    }
    private int age;

}
