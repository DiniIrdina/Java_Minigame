package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class GameQuitAction extends Action {
    protected String hotKey;

    public GameQuitAction(String hotKey){
        this.hotKey = hotKey;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Thank you for playing the game!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " can choose to quit game.";
    }

    /**
     * Returns this Action's hotkey.
     *
     * @return the hotkey
     */
    @Override
    public String hotkey() {
        return hotKey;
    }
}
