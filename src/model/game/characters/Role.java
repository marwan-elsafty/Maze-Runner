package model.game.characters;

import model.game.Maze;
import model.game.Visible;

public abstract class Role extends Visible {

	private int hitPoint;
	private int speed;

	private int attackInterval;
	private int nextAttackTime = 0;

	public Role() {

	}

	public boolean isAlive() {
		if (hitPoint != 0) {
			return true;
		} else {
			return false;
		}
	}

	public void doSomething() {
		if (nextAttackTime > 0) {
			nextAttackTime--;
		}
	}

	public void decreaseHP(int damageGot) {
		hitPoint = hitPoint - damageGot;
		if (hitPoint < 0) {
			hitPoint = 0;
		}
	}

	public int getHP() {
		return hitPoint;
	}

	protected void setHP(int hitpoint) {
		this.hitPoint = hitpoint;
	}

	protected void setAttackInterval(int attackInterval) {
		this.attackInterval = attackInterval;
	}

	protected void prepareForNextAttack() {
		nextAttackTime = attackInterval;
	}

	protected boolean isReadyToAttack() {
		return nextAttackTime == 0;
	}

	protected boolean willHitWall(int direction) {
		return super.willHitWall(speed, direction);
	}

	protected void move(int direction) {
		switch (direction) {
		case Maze.LEFT:
			setX(getX() - speed);
			break;
		case Maze.DOWN:
			setY(getY() + speed);
			break;
		case Maze.RIGHT:
			setX(getX() + speed);
			break;
		case Maze.UP:
			setY(getY() - speed);
			break;
		}
	}

	protected void setSpeed(int speed) {
		if (speed >= 0 && speed <= 32 && 32 % speed == 0)
			this.speed = speed;
	}

	protected int getSpeed() {
		return speed;
	}
}
