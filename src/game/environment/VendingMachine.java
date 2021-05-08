package game.environment;

import edu.monash.fit2099.engine.*;
import game.action.VMPurchasingAction;
import game.item.*;

import java.util.ArrayList;

public class VendingMachine extends Ground {
    /***
     * Constructor.
     */
    public VendingMachine() {
        super('V');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        ArrayList<Item> itemArrayList = new ArrayList<Item>();
        itemArrayList.add(new Fruit());
        itemArrayList.add(new VegetarianMealKit());
        itemArrayList.add(new CarnivoreMealKit());
        itemArrayList.add(new StegosaurEgg());
        itemArrayList.add(new BrachiosaurEgg());
        itemArrayList.add(new AllosaurEgg());
        itemArrayList.add(new LaserGun());

        Actions action = new Actions();

        for (Item i: itemArrayList){
            action.add(new VMPurchasingAction(i));
        }
        return action;
    }

}
