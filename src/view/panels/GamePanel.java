package view.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import controller.main.Application;
import model.Task;
import model.constants.Layout;
import model.game.Maze;
import view.Renderer;
import view.Screen;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String[] charPress = { "A", "S", "D", "W", "B" };
	private static final String[] charRelease = { "released A", "released S", "released D", "released W",
			"released B" };

	private static final String[] directionPress = { "LEFT", "DOWN", "RIGHT", "UP", "B" };
	private static final String[] directionRelease = { "released LEFT", "released DOWN", "released RIGHT",
			"released UP", "released B" };

	private final AbstractAction[] pressAction = { new PressLeftAction(), new PressDownAction(), new PressRightAction(),
			new PressUpAction(), new PressSpaceAction() };
	private final AbstractAction[] releaseAction = { new ReleaseLeftAction(), new ReleaseDownAction(),
			new ReleaseRightAction(), new ReleaseUpAction(), new ReleaseSpaceAction() };

	public GamePanel(Screen screen) {

		setPreferredSize(new Dimension(Layout.RUN_PANEL_WIDTH, Layout.RUN_PANEL_HEIGHT));

		InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		for (int i = 0; i < charPress.length; i++) {
			inputMap.put(KeyStroke.getKeyStroke(charPress[i]), directionPress[i]);
			inputMap.put(KeyStroke.getKeyStroke(directionPress[i]), directionPress[i]);
			getActionMap().put(directionPress[i], pressAction[i]);
		}
		for (int i = 0; i < charRelease.length; i++) {
			inputMap.put(KeyStroke.getKeyStroke(charRelease[i]), directionRelease[i]);
			inputMap.put(KeyStroke.getKeyStroke(directionRelease[i]), directionRelease[i]);
			getActionMap().put(directionRelease[i], releaseAction[i]);
		}
		screen.addRepaintTask(new RepaintTask());
	}

	private class RepaintTask implements Task {
		public void execute() {
			repaint();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		new Renderer().render(g);
	}

	private class PressLeftAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.LEFT, true);
		}
	}

	private class PressDownAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.DOWN, true);
		}
	}

	private class PressRightAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.RIGHT, true);
		}
	}

	private class PressUpAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.UP, true);
		}
	}

	private class PressSpaceAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.SPACE, true);
		}
	}

	private class ReleaseLeftAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.LEFT, false);
		}
	}

	private class ReleaseDownAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.DOWN, false);
		}
	}

	private class ReleaseRightAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.RIGHT, false);
		}
	}

	private class ReleaseUpAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.UP, false);
		}
	}

	private class ReleaseSpaceAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
			Application.getState().setKeyHolding(Maze.SPACE, false);

			Application.getPlayer().stoppedShooting();
		}
	}
}
