package game.item;

import edu.monash.fit2099.engine.WeaponItem;

public class LaserGun extends WeaponItem {
    public static final int PRICE = 500;
    public LaserGun(){
        super("LaserGun",'g',100,"zaps");
    }

    public int getPrice(){
        return PRICE;
    }
}
