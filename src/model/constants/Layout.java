package model.constants;

import model.game.Maze;

public class Layout {

	public static final int RUN_PANEL_WIDTH = (Maze.MAZE_WIDTH * 2 + 1) * 32;
	public static final int RUN_PANEL_HEIGHT = (Maze.MAZE_HEIGHT * 2 + 1) * 32;

	public static final int INFO_PANEL_WIDTH = 192;
	public static final int INFO_PANEL_HEIGHT = RUN_PANEL_HEIGHT;

	public static final int OBSERVING_PANEL_WIDTH = INFO_PANEL_WIDTH;
	public static final int OBSERVING_PANEL_HEIGHT = 460;
	
	public static final int CONTROL_PANEL_WIDTH = INFO_PANEL_WIDTH;
	public static final int CONTROL_PANEL_HEIGHT = 192;

	public static final int SCREEN_WIDTH = RUN_PANEL_WIDTH + INFO_PANEL_WIDTH;
	public static final int SCREEN_HEIGHT = RUN_PANEL_HEIGHT;

	private Layout() {

	}
}
