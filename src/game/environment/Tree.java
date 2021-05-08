package game.environment;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actor.Player;
import game.item.Fruit;

import java.util.ArrayList;

public class Tree extends Ground {
	private int age = 0;
	private ArrayList<Fruit> fruits = new ArrayList<>();

	public Tree() {
		super('+');
	}

	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	public void removeFruit(){
		fruits.remove(fruits.size()-1);
	}

	@Override
	public void tick(Location location) {
		super.tick(location);

		age++;
		if (age == 10)
			displayChar = 't';
		if (age == 20)
			displayChar = 'T';

		double fruitChance = Math.random();
		if (fruitChance >= 0.5 && fruits.size() <= 10){
			Fruit fruit = new Fruit();
			fruits.add(fruit);
			Player.updateEcoPoints(1);
		}

		double fruitDropChance = Math.random();
		if (fruitDropChance <= 0.05 && !fruits.isEmpty()){
			fruits.remove(fruits.size()-1);
			Fruit fruit = new Fruit();
			location.addItem(fruit);
		}
	}

}
