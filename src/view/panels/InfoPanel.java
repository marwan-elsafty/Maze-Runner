package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Task;
import model.constants.Layout;
import model.resources.Resource;
import view.Screen;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel background;

	private ObservingPanel observingPanel;
	private ControlPanel controlPanel;

	public InfoPanel(Screen screen) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(Layout.INFO_PANEL_WIDTH, Layout.INFO_PANEL_HEIGHT));
		setLayout(new BorderLayout(0, 0));

		background = new JLabel(Resource.iconBackground);
		add(background, BorderLayout.CENTER);

		observingPanel = new ObservingPanel();
		add(observingPanel, BorderLayout.NORTH);

		controlPanel = new ControlPanel(screen);
		add(controlPanel, BorderLayout.SOUTH);

		screen.addRepaintTask(new RepaintTask());
	}

	private class RepaintTask implements Task {
		public void execute() {
			repaint();
		}
	}

	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
	}
}
