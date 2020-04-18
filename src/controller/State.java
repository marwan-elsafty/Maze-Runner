package controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import controller.main.Application;
import controller.observer.Observer;
import model.Factory;
import model.ScoreDetector;
import model.game.Maze;
import model.game.World;
import model.game.characters.Player;
import model.game.characters.Role;
import model.game.misc.Item;

public class State extends Observer{

	private final boolean[] keyHolding = { false, false, false, false, false };

	private boolean fogEnabled = true;
	private boolean guideEnabled = true;
	private boolean gameRunning = false;
	private boolean gameOver = false;
	private boolean gameVictory = false;

	public State() {

	}

	/*
	 * updater methods
	 */

	public void updateState() {
		Player player = Application.getPlayer();
		World world = Application.getWorld();

		int[][] maze = Application.getMaze();

		int x = player.getX() / 32;
		int y = player.getY() / 32;

		if (isGameRunning()) {
			if (maze[x][y] == Maze.EXIT_CELL) {
				Application.getGame().addToScore(Factory.detect(ScoreDetector.GAME_VICTORY));
				JOptionPane.showMessageDialog(null, Application.getGame().getScore(),"Score",JOptionPane.DEFAULT_OPTION);
				
				setGameRunning(false);
				setGameOver(true);
				setGameVictory(true);

			} else if (player.isAlive() == false) {
//				if (player.haveNoLives()) {
					Application.getGame().addToScore(Factory.detect(ScoreDetector.GAME_LOSE));
					JOptionPane.showMessageDialog(null, Application.getGame().getScore(),"Score",JOptionPane.DEFAULT_OPTION);
					
					setGameRunning(false);
					setGameOver(true);
					setGameVictory(false);
//				} else {
//					player.decreaseLeftLives();
//					Application.getGame().restartGame();
//				}

			} else {
				player.doSomething();

				update(world);
			}
		}
	}

	@Override
	public void update(World world) {
		updateRoles(world.getListMonstersSingleEyed());
		updateRoles(world.getListMonstersDoubleEyed());
		updateRoles(world.getListMonstersTripleEyed());

		updateItems(world.getListBombs());
		updateItems(world.getListBullets());

		updateItems(world.getListBulletGifts());
		updateItems(world.getListArmorsGifts());
		updateItems(world.getListFirstAidGifts());
		updateItems(world.getListCheckPoints());
	}
	
	private void updateRoles(ArrayList<? extends Role> roleList) {
		for (Iterator<? extends Role> iterator = roleList.iterator(); iterator.hasNext();) {
			Role role = iterator.next();
			role.doSomething();
			if (role.isAlive() == false) {
				iterator.remove();
			}
		}
	}

	private void updateItems(ArrayList<? extends Item> itemList) {
		for (Iterator<? extends Item> iterator = itemList.iterator(); iterator.hasNext();) {
			Item item = iterator.next();
			item.doSomething();
			if (item.isAlive() == false) {
				iterator.remove();
			}
		}
	}

	/*
	 * keys checks methods
	 */

	public void setKeyHolding(int direction, boolean holding) {
		keyHolding[direction] = holding;
	}

	public boolean isKeyHolding(int direction) {
		return keyHolding[direction];
	}

	/*
	 * fog methods
	 */
	public void setFogEnabled(boolean enabled) {
		fogEnabled = enabled;
	}

	public boolean isFogEnabled() {
		return fogEnabled;
	}

	/*
	 * guide methods
	 */

	public void setGuideEnabled(boolean enabled) {
		guideEnabled = enabled;
	}

	public boolean isGuideEnabled() {
		return guideEnabled;
	}

	/*
	 * game methods
	 */

	public void setGameRunning(boolean running) {
		gameRunning = running;
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameOver(boolean over) {
		gameOver = over;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameVictory(boolean victory) {
		gameVictory = victory;
	}

	public boolean isGameVictory() {
		return gameVictory;
	}
}