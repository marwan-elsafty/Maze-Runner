package model.game.characters;

import controller.main.Application;
import model.constants.GameConstants;
import model.game.misc.Bomb;

public class MonsterDoubleEyed extends Enemy {

	private static final int escapeRange = Integer.MAX_VALUE;

	public MonsterDoubleEyed() {
		setAttackInterval(750);
		prepareForNextAttack();
	}

	public void doSomething() {
		super.doSomething();

		trackOrEscapeOrMove(escapeRange);
		setupBomb();
	}

	private void setupBomb() {
		if (isReadyToAttack() == false) {
			return;
		}

		if (getX() % 32 != 0 || getY() % 32 != 0) {
			return;
		}

		Application.getWorld().addBomb(new Bomb(getX(), getY()));
		prepareForNextAttack();
	}

	public static int getDamage() {
		return GameConstants.MONSTER_MAX_DAMAGE;
	}
}
