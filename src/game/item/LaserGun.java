package game.item;

import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.interfaces.VendingMachineItemsInterface;

public class LaserGun extends WeaponItem implements VendingMachineItemsInterface {
    public static final int price = 500;
    public LaserGun(){
        super("Laser Gun",'g',100,"zaps");
    }
}
