package model.constants;

public class GameConstants {

	public static final int PLAYER_SPEED = 1;
	public static final int MONSTER_SPEED = 1;

	public static final int PLAYER_HP = 300;
	public static final int MONSTER_HP = 50;

	public static final int PLAYER_DAMAGE = 50;
	public static final int MONSTER_MIN_DAMAGE = 0;
	public static final int MONSTER_MAX_DAMAGE = 5;

	public static final int ARMOR_RESISTANCE = 20;

	public static final int AMMUNITION_TANK = 6;

	public static final int GIFT_EXTRA_HP = 20;

	public static final int[] PLAYER_LIFES = new int[] { 2, 3, 4, 5 };

	public static final int[] NUM_MONSTERS_SINGLE_EYED = new int[] { 2, 3, 4, 5 };
	public static final int[] NUM_MONSTERS_DOUBLE_EYED = new int[] { 2, 3, 4, 5 };
	public static final int[] NUM_MONSTERS_TRIPLE_EYED = new int[] { 2, 3, 4, 5 };
	public static final int[] NUM_TREASURE = new int[] { 2, 3, 4, 5 };

	public static final int[] NUM_BULLETS_GIFTS = new int[] { 1, 3, 5, 7 };
	public static final int[] NUM_ARMORS_GIFTS = new int[] { 1, 3, 5, 7 };
	public static final int[] NUM_FIRST_AID = new int[] { 1, 3, 5, 7 };
	public static final int[] NUM_EXTRA_LIFE = new int[] { 1, 3, 5, 7 };
	public static final int[] NUM_CHECK_POINTS = new int[] { 1, 3, 5, 7 };

	private GameConstants() {

	}
}
