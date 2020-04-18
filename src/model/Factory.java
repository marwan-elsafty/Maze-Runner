package model;

import model.game.misc.gifts.ArmorGift;
import model.game.misc.gifts.BulletGift;
import model.game.misc.gifts.CollectedGift;
import model.game.misc.gifts.FirstAid;
import model.game.misc.gifts.Gift;

public class Factory {

	/**
	 * overloading
	 * 
	 * @param CollectedGift
	 * @return Gift
	 */
	public static Gift detect(CollectedGift collectedGift) {
		if (collectedGift == CollectedGift.ARMOR)
			return new ArmorGift();

		if (collectedGift == CollectedGift.BULLET)
			return new BulletGift();

		if (collectedGift == CollectedGift.FIRST_AID)
			return new FirstAid();

		return null;
	}

	/**
	 * overloading
	 * 
	 * @param Score Detector
	 * @return Added Score 
	 */
	public static int detect(ScoreDetector scoreDetector) {
		if (scoreDetector == ScoreDetector.SHOT_MONSTER)
			return 20;
		
		if (scoreDetector == ScoreDetector.GAME_VICTORY)
			return 100;
		
		if (scoreDetector == ScoreDetector.GAME_LOSE)
			return 10;
		
		if (scoreDetector == ScoreDetector.GAME_LOSE)
			return 0;
		
		return 0;
	}
}
