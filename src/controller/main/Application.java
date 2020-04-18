package controller.main;

import controller.State;
import model.constants.GameLevel;
import model.game.Maze;
import model.game.World;
import model.game.characters.Player;
import view.Game;
import view.Screen;
import view.frames.LevelChooserFrame;

public class Application {

	private static LevelChooserFrame levelChooserFrame;

	private static int gameLevel;
	private static Game game;
	private static World world;
	private static State state;
	private static Screen screen;
	private static Player player;
	private static Maze maze;

	protected Application() {

	}

	public static LevelChooserFrame getLevelChooser() {
		return levelChooserFrame;
	}

	private static void setLevelChooser(LevelChooserFrame levelChooserFrame) {
		Application.levelChooserFrame = levelChooserFrame;
	}

	public static int getGameLevel() {
		return gameLevel;
	}

	public static void setGameLevel(int gameLevel) {
		Application.gameLevel = gameLevel;
	}

	public static Game getGame() {
		return game;
	}

	private static void setGame(Game game) {
		Application.game = game;
	}

	public static World getWorld() {
		return world;
	}

	private static void setWorld(World world) {
		Application.world = world;
	}

	private static void resetWorld() {
		Application.world = new World();
	}

	public static State getState() {
		return state;
	}

	private static void setState(State state) {
		Application.state = state;
	}

	private static void resetState() {
		Application.state = new State();
	}

	public static Screen getScreen() {
		return screen;
	}

	private static void setScreen(Screen screen) {
		Application.screen = screen;
	}

	private static void resetScreen() {
		Application.screen = new Screen();
	}

	public static Player getPlayer() {
		return player;
	}

	private static void setPlayer(Player player) {
		Application.player = player;
	}

	private static void resetPlayer() {
//		if (player.haveNoLives()) {
			Application.player = new Player();
//		} else {
//			Application.player.reset();
//		}
	}

	public static int[][] getMaze() {
		return maze.getMaze();
	}

	private static void setMaze(Maze maze) {
		Application.maze = maze;
	}

	private static void resetMaze() {
		Application.maze = new Maze();
	}

	public static void reset() {
		resetWorld();
		resetMaze();
		resetPlayer();
		resetState();
		resetScreen();
	}

	protected void start() {
		setLevelChooser(new LevelChooserFrame());

		setGameLevel(GameLevel.MEDIUM);
		setGame(Game.getInstance());

		setWorld(new World());
		setMaze(new Maze());
		setPlayer(new Player());
		setState(new State());
		setScreen(new Screen());

		getGame().startGame();
	}
}
