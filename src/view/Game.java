package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import controller.State;
import controller.main.Application;
import model.GameTimer;
import model.constants.Layout;
import model.resources.Resource;

public class Game {

	private static final String titleString = "Maze Runner";
	private static final int frameRate = 100;
	private static final int timeInterval = 1000 / frameRate;

	private JFrame frame = new JFrame();
	private ActionListener listener;
	private Timer timer;
	
	private GameTimer gameTimer;
	
	private int score = 0;

	private Game() {
		gameTimer = new GameTimer();
		gameTimer.start();
		
		frame.setTitle(titleString);
		frame.setIconImage(Resource.imageGameIcon);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - Layout.SCREEN_WIDTH / 2, dim.height / 2 - Layout.SCREEN_HEIGHT / 2);
	}

	public void startGame() {
		State state = Application.getState();
		Screen screen = Application.getScreen();

		listener = new TimerListener(state, screen);
		timer = new Timer(timeInterval, listener);

		frame.setContentPane(screen);
		frame.revalidate();
		frame.repaint();
		frame.pack();

		timer.start();
	}

	public void restartGame() {
		timer.stop();

		Application.reset();

		timer.removeActionListener(listener);
		listener = new TimerListener(Application.getState(), Application.getScreen());
		timer.addActionListener(listener);

		frame.getContentPane().removeAll();
		frame.setContentPane(Application.getScreen());
		frame.revalidate();
		frame.repaint();
		frame.pack();

		timer.start();
	}

	public JFrame getFrame() {
		return frame;
	}

	private class TimerListener implements ActionListener {
		private final State state;
		private final Screen screen;
		private boolean calledGameOverTasks = false;

		public TimerListener(State state, Screen screen) {
			this.state = state;
			this.screen = screen;
		}

		public void actionPerformed(ActionEvent e) {
			state.updateState();
			screen.executeRepaintTasks();
			if (state.isGameOver() && calledGameOverTasks == false) {
				screen.executeGameOverTasks();
				calledGameOverTasks = true;
			}
		}
	}

	/*
	 * score
	 */
	
	public int getScore() {
		return score;
	}

	public void addToScore(int score) {
		this.score += score;
	}
	
	public void resetScore(int score) {
		this.score = 0;
	}
	
	/*
	 * game time
	 */

	public GameTimer getGameTimer() {
		return gameTimer;
	}
	
	/**
	 * Singleton class
	 */
	private static Game instance = null;

	public static Game getInstance() {
		synchronized (Game.class) {
			if (instance == null)
				instance = new Game();
		}
		return instance;
	}
}
