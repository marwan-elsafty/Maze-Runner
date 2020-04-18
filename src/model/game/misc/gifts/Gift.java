package model.game.misc.gifts;

import java.util.Random;

import model.game.Maze;
import model.game.misc.Item;

public abstract class Gift extends Item {

	private static final Random random = new Random();
	private boolean untouched = true;

	public Gift() {
		int xIndex = (random.nextInt(Maze.MAZE_WIDTH - 1) + 1) * 2 + 1;
		int yIndex = (random.nextInt(Maze.MAZE_HEIGHT - 1) + 1) * 2 + 1;
		setX(xIndex * 32);
		setY(yIndex * 32);
	}

	@Override
	public void doSomething() {
		return;
	}

	public void pickUpByPlayer() {
		untouched = false;
	}

	@Override
	public boolean isAlive() {
		return untouched;
	}
}
