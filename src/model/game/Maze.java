package model.game;

import java.awt.Point;
import java.util.Random;

public class Maze {

	public static final int MAZE_WIDTH = 10;
	public static final int MAZE_HEIGHT = 10;

	public static final int LEFT = 0;
	public static final int DOWN = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int SPACE = 4;

	public static final char WALL = 'W';
	public static final char PATH = 'P';
	public static final char EXIT_CELL = 'E';

	private static final char NON_WALL = 'N';

	private final int[][] maze;

	public Maze() {
		maze = new int[MAZE_WIDTH * 2 + 1][MAZE_HEIGHT * 2 + 1];

		for (int i = 0; i < MAZE_WIDTH * 2 + 1; i++) {
			for (int j = 0; j < MAZE_HEIGHT * 2 + 1; j++) {
				if ((i % 2 == 1) && (j % 2 == 1)) {
					maze[i][j] = NON_WALL;
				} else {
					maze[i][j] = WALL;
				}
			}
		}
		Point start = new Point(1, 1);
		maze[start.x][start.y] = PATH;
		buildMaze(start);
	}

	public int[][] getMaze() {
		return maze;
	}

	private void buildMaze(Point current) {
		int randomDirection = searchRandomDirection(current);

		while (randomDirection >= 0) {
			Point next = null;
			switch (randomDirection) {
			case LEFT:
				maze[current.x - 1][current.y] = PATH;
				next = new Point(current.x - 2, current.y);
				break;

			case RIGHT:
				maze[current.x + 1][current.y] = PATH;
				next = new Point(current.x + 2, current.y);
				break;

			case UP:
				maze[current.x][current.y - 1] = PATH;
				next = new Point(current.x, current.y - 2);
				break;

			case DOWN:
				maze[current.x][current.y + 1] = PATH;
				next = new Point(current.x, current.y + 2);
				break;
			}
			maze[next.x][next.y] = PATH;
			buildMaze(next);
			randomDirection = searchRandomDirection(current);
		}

		maze[MAZE_WIDTH * 2][MAZE_HEIGHT * 2 - 1] = EXIT_CELL;
	}

	private int searchRandomDirection(Point current) {
		int randomDirection = new Random().nextInt(4);
		int neighborCount = 0;

		while (neighborCount < 4) {
			switch (randomDirection) {
			case LEFT:
				if (current.x - 2 >= 0 && maze[current.x - 2][current.y] == NON_WALL) {
					return LEFT;
				}
				break;

			case RIGHT:
				if (current.x + 2 <= MAZE_WIDTH * 2 && maze[current.x + 2][current.y] == NON_WALL) {
					return RIGHT;
				}
				break;

			case UP:
				if (current.y - 2 >= 0 && maze[current.x][current.y - 2] == NON_WALL) {
					return UP;
				}
				break;

			case DOWN:
				if (current.y + 2 <= MAZE_HEIGHT * 2 && maze[current.x][current.y + 2] == NON_WALL) {
					return DOWN;
				}
				break;
			}
			randomDirection = (randomDirection + 1) % 4;
			neighborCount++;
		}
		return -1;
	}
}
