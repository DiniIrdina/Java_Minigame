package game.action;

import edu.monash.fit2099.engine.*;
import game.interfaces.VendingMachineItemsInterface;
import game.actor.Player;
import game.item.*;

public class VMPurchasingAction extends Action {
    VendingMachineItemsInterface selection;

    public VMPurchasingAction(VendingMachineItemsInterface selection) {
        this.selection = selection;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player)actor;
        player.addItemToInventory((PortableItem)selection);
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Item: " + selection.toString();
    }
}
