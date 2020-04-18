package view.panels;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.constants.GameConstants;
import model.constants.Layout;
import model.game.characters.MonsterDoubleEyed;
import model.game.characters.MonsterSingleEyed;
import model.game.characters.MonsterTripleEyed;
import model.resources.Resource;

public class ObservingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JLabel lblTimer;

	public ObservingPanel() {
		setPreferredSize(new Dimension(Layout.OBSERVING_PANEL_WIDTH, Layout.OBSERVING_PANEL_HEIGHT));
		setLayout(null);
		setOpaque(false);

		JLabel lblMonster1 = new JLabel(Resource.imageMonsterSingleEyedLarge);
		lblMonster1.setBounds(20, 119, 64, 64);
		add(lblMonster1);

		JLabel HPMonster1 = new JLabel("HP:");
		HPMonster1.setFont(new Font("Tahoma", Font.BOLD, 12));
		HPMonster1.setBounds(104, 119, 64, 20);
		add(HPMonster1);

		JLabel lblTitle = new JLabel("<HTML>Maze<br>Runner<HTML/>");
		lblTitle.setFont(new Font("Forte", Font.PLAIN, 45));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setVerticalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(20, -20, 152, 128);
		add(lblTitle);

		JLabel HP1 = new JLabel(GameConstants.MONSTER_HP + "");
		HP1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		HP1.setBounds(168, 119, 24, 20);
		add(HP1);

		JLabel damage1 = new JLabel(MonsterSingleEyed.getDamage() + "");
		damage1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		damage1.setBounds(168, 163, 24, 20);
		add(damage1);

		JLabel lblDamage1 = new JLabel("DAMAGE:");
		lblDamage1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDamage1.setBounds(104, 163, 64, 20);
		add(lblDamage1);

		JLabel speed1 = new JLabel(GameConstants.MONSTER_SPEED + "");
		speed1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		speed1.setBounds(168, 141, 24, 20);
		add(speed1);

		JLabel lblSpeed1 = new JLabel("SPEED:");
		lblSpeed1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpeed1.setBounds(104, 141, 64, 20);
		add(lblSpeed1);

		JLabel lblMonster2 = new JLabel(Resource.imageMonsterDoubleEyedLarge);
		lblMonster2.setBounds(20, 223, 64, 64);
		add(lblMonster2);

		JLabel lblHPMonster2 = new JLabel("HP:");
		lblHPMonster2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHPMonster2.setBounds(104, 223, 64, 20);
		add(lblHPMonster2);

		JLabel lblSpeed2 = new JLabel("SPEED:");
		lblSpeed2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpeed2.setBounds(104, 245, 64, 20);
		add(lblSpeed2);

		JLabel lblDamage2 = new JLabel("DAMAGE:");
		lblDamage2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDamage2.setBounds(104, 267, 64, 20);
		add(lblDamage2);

		JLabel damage2 = new JLabel(MonsterDoubleEyed.getDamage() + "");
		damage2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		damage2.setBounds(168, 267, 24, 20);
		add(damage2);

		JLabel speed2 = new JLabel(GameConstants.MONSTER_SPEED + "");
		speed2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		speed2.setBounds(168, 245, 24, 20);
		add(speed2);

		JLabel HPMonster2 = new JLabel(GameConstants.MONSTER_HP + "");
		HPMonster2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		HPMonster2.setBounds(168, 223, 24, 20);
		add(HPMonster2);

		JLabel lblMonster3 = new JLabel(Resource.imageMonsterTripleEyedLarge);
		lblMonster3.setBounds(20, 327, 64, 64);
		add(lblMonster3);

		JLabel lblHPMonster3 = new JLabel("HP:");
		lblHPMonster3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHPMonster3.setBounds(104, 327, 64, 20);
		add(lblHPMonster3);

		JLabel lblSpeed3 = new JLabel("SPEED:");
		lblSpeed3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpeed3.setBounds(104, 349, 64, 20);
		add(lblSpeed3);

		JLabel lblDamage3 = new JLabel("DAMAGE:");
		lblDamage3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDamage3.setBounds(104, 371, 64, 20);
		add(lblDamage3);

		JLabel damage3 = new JLabel(MonsterTripleEyed.getDamage() + "");
		damage3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		damage3.setBounds(168, 371, 24, 20);
		add(damage3);

		JLabel speed3 = new JLabel(GameConstants.MONSTER_SPEED + "");
		speed3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		speed3.setBounds(168, 349, 24, 20);
		add(speed3);

		JLabel HPMonster3 = new JLabel(GameConstants.MONSTER_HP + "");
		HPMonster3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		HPMonster3.setBounds(168, 327, 24, 20);
		add(HPMonster3);
		
		JLabel lblTime = new JLabel("Time :");
		lblTime.setVerticalAlignment(SwingConstants.CENTER);
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Forte", Font.PLAIN, 20));
		lblTime.setBounds(20, 420, 80, 30);
		add(lblTime);
		
		lblTimer = new JLabel();
		lblTimer.setVerticalAlignment(SwingConstants.CENTER);
		lblTimer.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimer.setFont(new Font("Forte", Font.PLAIN, 20));
		lblTimer.setBounds(110, 420, 70, 30);
		add(lblTimer);

		JLabel background = new JLabel(Resource.iconBackground);
		background.setBounds(0, 0, 192, 460);
		add(background);
	}
}
