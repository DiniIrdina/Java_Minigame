package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actor.Player;

public class GameCompletionAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        String final_statement;
        if(((Player) actor).isChallengeCompleted()){
            final_statement = "Congratulations! You just completed the game in challenge mode!";
        }
        else{
            final_statement = "You failed to achieve the challenge!";
        }
        return final_statement;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
