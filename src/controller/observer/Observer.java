package controller.observer;

import model.game.World;

public abstract class Observer {

	private Subject subject;

	public Observer() {

	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public abstract void update(World world);
}
