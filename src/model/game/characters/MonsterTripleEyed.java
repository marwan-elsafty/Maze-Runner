package model.game.characters;

import controller.main.Application;
import model.constants.GameConstants;
import model.game.misc.Bullet;

public class MonsterTripleEyed extends Enemy {

	private static final int escapeRange = 48;

	public MonsterTripleEyed() {

	}

	public void doSomething() {
		super.doSomething();

		trackOrEscapeOrMove(escapeRange);
		shotPlayer();
	}

	private void shotPlayer() {
		if (isReadyToAttack() == false) {
			return;
		}

		Player player = Application.getPlayer();
		if (canSee(player) == false) {
			return;
		}

		Application.getWorld().addBullet(new Bullet(getX(), getY(), player.getX(), player.getY(), getDamage(), player));
		prepareForNextAttack();
	}

	public static int getDamage() {
		return GameConstants.MONSTER_MAX_DAMAGE;
	}
}
