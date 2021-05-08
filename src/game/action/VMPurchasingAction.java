package game.action;

import edu.monash.fit2099.engine.*;
import game.interfaces.VendingMachineItemsInterface;
import game.actor.Player;
import game.item.*;

public class VMPurchasingAction extends Action {
    enum Types {
        Fruit,
        VegetarianMealKit,
        CarnivoreMealKit,
        StegosaurEgg,
        BrachiosaurEgg,
        AllosaurEgg,
        LaserGun
    }


    Item selection;

    public VMPurchasingAction(Item selection) {
        this.selection = selection;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player)actor;
        int itemPrice = 0;
        String message;

        Types whichSelection = Types.valueOf(selection.toString());

        switch (whichSelection){
            case Fruit:
                itemPrice = ((Fruit) selection).getPrice();
                break;
            case VegetarianMealKit:
                itemPrice = ((VegetarianMealKit) selection).getPrice();
                break;
            case CarnivoreMealKit:
                itemPrice = ((CarnivoreMealKit) selection).getPrice();
                break;
            case StegosaurEgg:
                itemPrice = ((StegosaurEgg) selection).getPrice();
                break;
            case BrachiosaurEgg:
                itemPrice = ((BrachiosaurEgg) selection).getPrice();
                break;
            case AllosaurEgg:
                itemPrice = ((AllosaurEgg) selection).getPrice();
                break;
            case LaserGun:
                itemPrice = ((LaserGun) selection).getPrice();
                break;
        }

        if (Player.EcoPointStorage >= itemPrice) {
            player.addItemToInventory(selection);
            player.removeEcoPoints(itemPrice);
            message = selection.toString() + " purchased." + "(Current EcoPoints: " + Player.EcoPointStorage +").";
        }
        else {
            message = "Insufficient EcoPoints to purchase " +
                    selection.toString() +
                    " (Item Price: " + itemPrice + ", Current EcoPoints: " + Player.EcoPointStorage +").";
            }
        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Item: " + selection.toString();
    }
}
