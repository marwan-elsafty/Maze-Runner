package model.game.misc.supply;

import controller.main.Application;
import model.constants.GameConstants;

public class Ammunition implements Supply {

	private int bulletsCount = GameConstants.AMMUNITION_TANK;

	public Ammunition() {

	}

	public int getBulletsCount() {
		return bulletsCount;
	}

	public void decreasBulletsCount() {
		this.bulletsCount--;
		
		Application.getPlayer().decreaseBulletsCount();
	}

	public boolean haveBullets() {
		if (this.bulletsCount <= 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isDead() {
		if (this.bulletsCount <= 0) {
			return true;
		}
		return false;
	}
}
