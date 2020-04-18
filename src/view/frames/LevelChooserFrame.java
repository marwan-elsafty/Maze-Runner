package view.frames;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.main.Application;
import model.constants.GameLevel;
import model.resources.Resource;

public class LevelChooserFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String stringTitle = "<HTML><center>Level<br>Chooser<center/><HTML/>";
	private static final String stringExit = "<HTML><center>X<center/><HTML/>";
	
	private static final String stringEasy = "<HTML><center>Easy<center/><HTML/>";
	private static final String stringMedium = "<HTML><center>Medium<center/><HTML/>";
	private static final String stringHard = "<HTML><center>Hard<center/><HTML/>";
	private static final String stringLegendary = "<HTML><center>Legendary<center/><HTML/>";
	

	private JLabel lblTitle;
	private JLabel btnBackground;

	private JButton btnEasy;
	private JButton btnMedium;
	private JButton btnHard;
	private JButton btnLegendary;
	private JButton btnExit;

	private JPanel contentPane;

	public LevelChooserFrame() {
		setSize(204, 302);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTitle = new JLabel(stringTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.NORTH);
		lblTitle.setFont(new Font("Forte", Font.PLAIN, 30));
		lblTitle.setBounds(49, 38, 112, 96);
		contentPane.add(lblTitle);

		btnEasy = createButton(stringEasy);
		btnEasy.setBounds(28, 148, 152, 35);
		contentPane.add(btnEasy);

		btnMedium = createButton(stringMedium);
		btnMedium.setBounds(28, 180, 152, 35);
		contentPane.add(btnMedium);

		btnHard = createButton(stringHard);
		btnHard.setBounds(28, 212, 152, 35);
		contentPane.add(btnHard);

		btnLegendary = createButton(stringLegendary);
		btnLegendary.setBounds(28, 244, 152, 35);
		contentPane.add(btnLegendary);

		btnExit = createButton(stringExit);
		btnExit.setBounds(154, 10, 50, 30);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(btnExit);

		btnBackground = new JLabel(Resource.iconBackgroundSmall);
		btnBackground.setBounds(0, 0, 204, 302);
		contentPane.add(btnBackground);
	}

	private class LevelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = (String) e.getActionCommand();
			if (command.equals(stringEasy)) {
				Application.setGameLevel(GameLevel.EASY);

			} else if (command.equals(stringMedium)) {
				Application.setGameLevel(GameLevel.MEDIUM);

			} else if (command.equals(stringHard)) {
				Application.setGameLevel(GameLevel.HARD);

			} else if (command.equals(stringLegendary)) {
				Application.setGameLevel(GameLevel.LEGENDARY);
			}
			Application.getLevelChooser().setVisible(false);
		}
	}

	private JButton createButton(String text) {
		JButton button = new JButton();

		button.setText(text);
		button.setFont(new Font("Forte", Font.PLAIN, 18));
		button.setVerticalAlignment(SwingConstants.TOP);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		
		button.addActionListener(new LevelListener());

		return button;
	}
}
