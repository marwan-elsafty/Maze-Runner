package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Task;
import model.constants.Layout;
import view.panels.GamePanel;
import view.panels.InfoPanel;

public class Screen extends JPanel {

	private static final long serialVersionUID = 1L;

	private final ArrayList<Task> repaintTaskList = new ArrayList<Task>();
	private final ArrayList<Task> gameOverTaskList = new ArrayList<Task>();

	public Screen() {
		GamePanel gamePanel = new GamePanel(this);
		InfoPanel infoPanel = new InfoPanel(this);

		setPreferredSize(new Dimension(Layout.SCREEN_WIDTH, Layout.SCREEN_HEIGHT));
		setLayout(new BorderLayout(0, 0));
		add(gamePanel, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);
	}

	public void addRepaintTask(Task task) {
		repaintTaskList.add(task);
	}

	public void executeRepaintTasks() {
		for (Task task : repaintTaskList) {
			task.execute();
		}
	}

	public void addGameOverTask(Task task) {
		gameOverTaskList.add(task);
	}

	public void executeGameOverTasks() {
		for (Task task : gameOverTaskList) {
			task.execute();
		}
	}
}
