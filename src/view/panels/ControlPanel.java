package view.panels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.State;
import controller.main.Application;
import model.Task;
import model.constants.Layout;
import model.modes.GameMode;
import model.resources.Resource;
import view.Screen;

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final String userManualString = "User Manual";
	private static final String levelChangerString = "Change Level";
	
	private static final String playToolTipString = "Play";
	private static final String pauseToolTipString = "Pause";
	private static final String resumeToolTipString = "Resume";
	private static final String restartToolTipString = "Restart";
	private static final String victoryToolTipString = "Victory";
	private static final String defeatToolTipString = "Defeat";
	private static final String enableFogToolTipString = "Enable Fog";
	private static final String disableFogToolTipString = "Disable Fog";

	private final JButton btnUserManual = new JButton();
	private final JButton btnLevelChanger = new JButton();
	
	private final JButton btnPlayPause = new JButton();
	private final JButton btnRestart = new JButton();
	private final JButton btnChangeFog = new JButton();

	private final JLabel background = new JLabel(Resource.iconBackground);

	private final State state;

	private GameMode gameMode = GameMode.PLAY;

	public ControlPanel(Screen screen) {
		state = Application.getState();

		setPreferredSize(new Dimension(Layout.CONTROL_PANEL_WIDTH, Layout.CONTROL_PANEL_HEIGHT));
		setLayout(null);
		setOpaque(false);

		btnUserManual.setText(userManualString);
		btnUserManual.setOpaque(false);
		btnUserManual.setContentAreaFilled(false);
		btnUserManual.setBorderPainted(false);
		btnUserManual.setFont(new Font("Forte", Font.PLAIN, 22));
		btnUserManual.setBounds(0, 0, Layout.INFO_PANEL_WIDTH, 64);
//		btnUserManual.addActionListener(new ());
		add(btnUserManual);
		
		btnLevelChanger.setText(levelChangerString);
		btnLevelChanger.setOpaque(false);
		btnLevelChanger.setContentAreaFilled(false);
		btnLevelChanger.setBorderPainted(false);
		btnLevelChanger.setFont(new Font("Forte", Font.PLAIN, 22));
		btnLevelChanger.setBounds(0, 64, Layout.INFO_PANEL_WIDTH, 64);
		btnPlayPause.addActionListener(new LevelChooserButtonListener());
		add(btnLevelChanger);

		btnPlayPause.setBounds(0, 128, 64, 64);
		btnPlayPause.setOpaque(false);
		btnPlayPause.setContentAreaFilled(false);
		btnPlayPause.setBorderPainted(false);
		btnPlayPause.setIcon(Resource.iconPlay);
		btnPlayPause.setToolTipText(playToolTipString);
		btnPlayPause.addActionListener(new playPauseButtonListener());
		add(btnPlayPause);

		btnChangeFog.setBounds(64, 128, 64, 64);
		btnRestart.setLocation(64, 128);
		btnRestart.setSize(64, 64);
		btnRestart.setOpaque(false);
		btnRestart.setContentAreaFilled(false);
		btnRestart.setBorderPainted(false);
		btnRestart.setIcon(Resource.iconRestart);
		btnRestart.setToolTipText(restartToolTipString);
		btnRestart.addActionListener(new RestartButtonListener());
		add(btnRestart);

		btnChangeFog.setBounds(128, 128, 64, 64);
		btnChangeFog.setOpaque(false);
		btnChangeFog.setContentAreaFilled(false);
		btnChangeFog.setBorderPainted(false);
		btnChangeFog.setIcon(Resource.iconFogDisable);
		btnChangeFog.setToolTipText(disableFogToolTipString);
		btnChangeFog.addActionListener(new ChangeFogButtonListener());
		add(btnChangeFog);

		background.setBounds(0, 0, Layout.CONTROL_PANEL_WIDTH, Layout.CONTROL_PANEL_HEIGHT);
		add(background);

		screen.addGameOverTask(new GameOverTask());
	}

	private void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
	}

	private class LevelChooserButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Application.getLevelChooser().setVisible(true);
		}
	}

	private class GameOverTask implements Task {
		public void execute() {
			if (state.isGameVictory()) {
				btnPlayPause.setIcon(Resource.iconHappyEnding);
				btnPlayPause.setToolTipText(victoryToolTipString);
				
			} else {
				btnPlayPause.setIcon(Resource.iconBadEnding);
				btnPlayPause.setToolTipText(defeatToolTipString);
			}

			state.setFogEnabled(false);
			btnChangeFog.setIcon(Resource.iconFogEnable);
			btnChangeFog.setToolTipText(enableFogToolTipString);
		}
	}

	private class playPauseButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Application.getLevelChooser().setVisible(false);
			
			if (gameMode == GameMode.PLAY) {
				state.setGuideEnabled(false);
				state.setGameRunning(true);
				setGameMode(GameMode.PAUSE);
				btnPlayPause.setIcon(Resource.iconPause);
				btnPlayPause.setToolTipText(pauseToolTipString);
				
			} else if (gameMode == GameMode.PAUSE) {
				state.setGameRunning(false);
				setGameMode(GameMode.RESUME);
				btnPlayPause.setIcon(Resource.iconPlay);
				btnPlayPause.setToolTipText(resumeToolTipString);
				
			} else if (gameMode == GameMode.RESUME) {
				state.setGameRunning(true);
				setGameMode(GameMode.PAUSE);
				btnPlayPause.setIcon(Resource.iconPause);
				btnPlayPause.setToolTipText(pauseToolTipString);
			}
		}
	}

	private class RestartButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Application.getGame().restartGame();
		}
	}

	private class ChangeFogButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (state.isFogEnabled()) {
				state.setFogEnabled(false);
				btnChangeFog.setIcon(Resource.iconFogEnable);
				btnChangeFog.setToolTipText(enableFogToolTipString);
				
			} else {
				state.setFogEnabled(true);
				btnChangeFog.setIcon(Resource.iconFogDisable);
				btnChangeFog.setToolTipText(disableFogToolTipString);
			}
		}
	}
}
