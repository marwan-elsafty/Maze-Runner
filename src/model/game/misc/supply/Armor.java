package model.game.misc.supply;

import model.constants.GameConstants;

public class Armor implements Supply {

	private int resistance = GameConstants.ARMOR_RESISTANCE;

	public Armor() {

	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	@Override
	public boolean isDead() {
		if (this.resistance <= 0) {
			return true;
		}
		return false;
	}
}
