package model.game.characters;

import controller.main.Application;
import model.constants.GameConstants;
  
public class MonsterSingleEyed extends Enemy  {

	private static final int escapeRange = Integer.MIN_VALUE;

	private static final int damage = 20;
	private static final int range = 32;

 
	public MonsterSingleEyed() {

	}

	public void doSomething() {
		super.doSomething();

		trackOrEscapeOrMove(escapeRange);
		attackPlayer();
	}

	public boolean isAttacking() {
		return isReadyToAttack() == false;
	}

 

	private void attackPlayer() {
		if (isReadyToAttack() == false) {
			return;
		}

		Player player = Application.getPlayer();
		if (canSee(player) == false) {
			return;
		}

		if (getX() == player.getX() && Math.abs(getY() - player.getY()) <= range) {
			if (player.hasArmor()) {
				player.getArmor().setResistance(player.getArmor().getResistance() - damage);

				if (player.getArmor().isDead()) {
					player.removeArmor();
				}
			} else {
				player.decreaseHP(damage);
			}
 			prepareForNextAttack();
			
		} else if (getY() == player.getY() && Math.abs(getX() - player.getX()) <= range) {
			if (player.hasArmor()) {
				player.getArmor().setResistance(player.getArmor().getResistance() - damage);

				if (player.getArmor().isDead()) {
					player.removeArmor();
				}
			} else {
				player.decreaseHP(damage);
			}
 			prepareForNextAttack();
		}
	}

	public static int getDamage() {
		return GameConstants.MONSTER_MIN_DAMAGE;
	}

	 
}
