package model.game.misc;

import controller.main.Application;
import model.game.characters.Player;

public class Bomb extends Item {

	private static final int damage = 5;
	private boolean untouched = true;

	public Bomb(int x, int y) {
		setX(x);
		setY(y);
	}

	public void doSomething() {
		Player player = Application.getPlayer();
		if (getX() == player.getX() && getY() == player.getY()) {
			if (player.hasArmor()) {
				player.removeArmor();
			} else {
				player.decreaseHP(damage);
			}
			untouched = false;
		}
	}

	public boolean isAlive() {
		return untouched;
	}
}
