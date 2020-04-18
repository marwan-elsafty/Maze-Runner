package model.buffer;

import java.util.ArrayList;

import controller.main.Application;

public final class GameBuffer {

	private GameBuffer() {

	}

	public static void save() {
		// TODO CODE HERE

		ArrayList<Object> gameWorld = new ArrayList<Object>();

		gameWorld.add(Application.getGameLevel());
		gameWorld.add(Application.getWorld());
		gameWorld.add(Application.getMaze());
		gameWorld.add(Application.getScreen());
		gameWorld.add(Application.getState());
		gameWorld.add(Application.getPlayer());

		System.out.println(gameWorld);
	}

	public static void load() {
		// TODO CODE HERE
//		try {
//			FileInputStream fis = new FileInputStream(new File("game.ser"));
//			ObjectInputStream ois = new ObjectInputStream(fis);
//
//			while (ois.available() > -1) {
//				Object object = ois.readObject();
//
//				 if (object instanceof Integer) {
//				 	 Application.setGameLevel((int) object);
//				 }
//				
//
//				if (object instanceof Game) {
//					Game game = Game.getInstance();
//					game = (Game) object;
//					Application.setGame(game);
//				}
//				if (object instanceof World) {
//					World world = new World();
//					world = (World) object;
//					Application.setWorld(world);
//				}
//
//				if (object instanceof Maze) {
//					Maze maze = new Maze();
//					maze = (Maze) object;
//					Application.setMaze(maze);
//				}
//
//				if (object instanceof Screen) {
//					Application.setScreen(new Screen());
//				}
//
//				if (object instanceof State) {
//					State state = new State();
//					state = (State) object;
//					Application.setState(state);
//				}
//
//				if (object instanceof Player) {
//					Player player = new Player();
//					player = (Player) object;
//					Application.setPlayer(player);
//				}
//			}
//			ois.close();
//			fis.close();
//		} catch (Exception e) {
//
//		}
	}
}
