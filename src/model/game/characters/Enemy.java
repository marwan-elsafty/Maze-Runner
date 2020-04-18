package model.game.characters;

import java.util.Random;

import controller.main.Application;
import model.constants.GameConstants;
import model.game.Maze;

public abstract class Enemy extends Role {

	private static final Random random = new Random();

	private int movingDirection = Maze.RIGHT;

	public Enemy() {
		int xIndex = (random.nextInt(Maze.MAZE_WIDTH - 1) + 1) * 2 + 1;
		int yIndex = (random.nextInt(Maze.MAZE_HEIGHT - 1) + 1) * 2 + 1;
		setX(xIndex * 32);
		setY(yIndex * 32);
		setSpeed(GameConstants.MONSTER_SPEED);
		setHP(GameConstants.MONSTER_HP);
		setAttackInterval(75);
	}

	public void doSomething() {
		super.doSomething();
	}

	protected void trackOrEscapeOrMove(int escapeRange) {
		Player player = Application.getPlayer();

		boolean shouldStand = false;

		if (canSee(player)) {
			int distance;
			if (getX() == player.getX()) {
				movingDirection = (getY() < player.getY()) ? Maze.DOWN : Maze.UP;
				distance = Math.abs(getY() - player.getY());
			} else {
				movingDirection = (getX() < player.getX()) ? Maze.RIGHT : Maze.LEFT;
				distance = Math.abs(getX() - player.getX());
			}

			if (distance < escapeRange) {
				movingDirection = (movingDirection + 2) % 4;
				if (willHitWall(movingDirection) && turnLeftOrRight() == false) {
					shouldStand = true;
				}
			} else if (distance == escapeRange) {
				shouldStand = true;
			}
		} else {
			if (turnLeftOrRight() == false && willHitWall(movingDirection)) {
				movingDirection = (movingDirection + 2) % 4;
			}
		}

		if (shouldStand == false) {
			move(movingDirection);
		}
	}

	private boolean canTurnLeft() {
		int leftDirection = (movingDirection + 1) % 4;
		return willHitWall(leftDirection) == false;
	}

	private boolean canTurnRight() {
		int rightDirection = (movingDirection - 1 + 4) % 4;
		return willHitWall(rightDirection) == false;
	}

	private boolean turnLeftOrRight() {
		int oneOrZero = random.nextInt(2);

		boolean ableToTurnLeft = canTurnLeft();
		boolean ableToTurnRight = canTurnRight();
		if (oneOrZero == 1) {
			if (ableToTurnLeft) {
				movingDirection = (movingDirection + 1) % 4;
			} else if (ableToTurnRight) {
				movingDirection = (movingDirection - 1 + 4) % 4;
			}
		} else {
			if (ableToTurnRight) {
				movingDirection = (movingDirection - 1 + 4) % 4;
			} else if (ableToTurnLeft) {
				movingDirection = (movingDirection + 1) % 4;
			}
		}

		return ableToTurnLeft || ableToTurnRight;
	}
}
