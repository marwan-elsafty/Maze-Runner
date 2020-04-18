package controller.memento;

import java.util.ArrayList;

public class CareTaker {

	private ArrayList<Memento> mementoList = new ArrayList<Memento>();

	public void add(Memento state) {
		this.mementoList.add(state);
	}

	public Memento get(int index) {
		return this.mementoList.get(index);
	}
}