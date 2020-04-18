package model.game;

import controller.main.Application;
import model.game.characters.Role;

public abstract class Visible {

	private int x;
	private int y;

	protected Visible() {

	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	protected boolean willHitWall(int speed, int direction) {
		int[][] maze = Application.getMaze();
		switch (direction) {
		case Maze.UP:
			if (x % 32 == 0 && maze[x / 32][(y - speed) / 32] == Maze.PATH) {
				return false;
			} else {
				return true;
			}
		case Maze.LEFT:
			if (y % 32 == 0 && maze[(x - speed) / 32][y / 32] == Maze.PATH) {
				return false;
			} else {
				return true;
			}
		case Maze.RIGHT:
			if (y % 32 == 0 && maze[(x + speed - 1) / 32 + 1][y / 32] == Maze.PATH) {
				return false;
			} else {
				return true;
			}
		case Maze.DOWN:
			if (x % 32 == 0 && maze[x / 32][(y + speed - 1) / 32 + 1] == Maze.PATH) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	private boolean isNoWallAlongY(int anotherY) {
		int xIndex = x / 32;
		int yIndex = y / 32;
		int anotherYIndex = anotherY / 32;
		int[][] maze = Application.getMaze();

		if (yIndex < anotherYIndex) {
			for (int i = yIndex; i <= anotherYIndex; i++) {
				if (maze[xIndex][i] == Maze.WALL) {
					return false;
				}
			}
		} else {
			for (int i = anotherYIndex; i <= yIndex; i++) {
				if (maze[xIndex][i] == Maze.WALL) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isNoWallAlongX(int anotherX) {
		int xIndex = x / 32;
		int yIndex = y / 32;
		int anotherXIndex = anotherX / 32;
		int[][] maze = Application.getMaze();

		if (xIndex < anotherXIndex) {
			for (int i = xIndex; i <= anotherXIndex; i++) {
				if (maze[i][yIndex] == Maze.WALL) {
					return false;
				}
			}
		} else {
			for (int i = anotherXIndex; i <= xIndex; i++) {
				if (maze[i][yIndex] == Maze.WALL) {
					return false;
				}
			}
		}
		return true;
	}

	protected boolean canSee(Role role) {
		if (x == role.getX()) {
			return isNoWallAlongY(role.getY());
		} else if (y == role.getY()) {
			return isNoWallAlongX(role.getX());
		} else {
			return false;
		}
	}

	public abstract boolean isAlive();

	public abstract void doSomething();
}
