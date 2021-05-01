package game;

import edu.monash.fit2099.engine.Actor;

public abstract class Dinosaur extends Actor {
    protected Behaviour behaviour;
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
}
