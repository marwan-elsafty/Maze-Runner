package controller.observer;

import java.util.ArrayList;

import model.game.World;

public class Subject {

	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state , World world) {
		this.state = state;
		notifyAllObservers(world);
	}

	public void attach(Observer observer) {
		observers.add(observer);
	}

	public void notifyAllObservers(World world) {
		for (Observer observer : observers) {
			observer.update(world);
		}
	}
}
