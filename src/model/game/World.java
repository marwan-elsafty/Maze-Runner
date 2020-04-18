package model.game;

import java.util.ArrayList;
import java.util.Iterator;

import controller.main.Application;
import model.constants.GameConstants;
import model.game.characters.MonsterDoubleEyed;
import model.game.characters.MonsterSingleEyed;
import model.game.characters.MonsterTripleEyed;
import model.game.misc.Bullet;
import model.game.misc.Bomb;
import model.game.misc.gifts.ArmorGift;
import model.game.misc.gifts.BulletGift;
import model.game.misc.gifts.CheckPoint;
import model.game.misc.gifts.ExtraLife;
import model.game.misc.gifts.FirstAid;

public class World {

	private static int NUM_MONSTERS_SINGLE_EYED;
	private static int NUM_MONSTERS_DOUBLE_EYED;
	private static int NUM_MONSTERS_TRIPLE_EYED;

	private static int NUM_BULLETS_GIFTS;
	private static int NUM_ARMORS_GIFTS;
	private static int NUM_FIRST_AID;
	private static int NUM_EXTRA_LIFE;
	private static int NUM_CHECK_POINTS;

	private final ArrayList<MonsterSingleEyed> listMonsterSingleEyed = new ArrayList<MonsterSingleEyed>();
	private final ArrayList<MonsterDoubleEyed> listMonsterDoubleEyed = new ArrayList<MonsterDoubleEyed>();
	private final ArrayList<MonsterTripleEyed> listMonsterTripleEyed = new ArrayList<MonsterTripleEyed>();

	private final ArrayList<Bomb> listBomb = new ArrayList<Bomb>();
	private final ArrayList<Bullet> listBullet = new ArrayList<Bullet>();

	private final ArrayList<BulletGift> listBulletGift = new ArrayList<BulletGift>();
	private final ArrayList<ArmorGift> listArmorGift = new ArrayList<ArmorGift>();
	private final ArrayList<FirstAid> listFirstAidGift = new ArrayList<FirstAid>();
	private final ArrayList<ExtraLife> listExtraLifeGift = new ArrayList<ExtraLife>();
	private final ArrayList<CheckPoint> listCheckPoint = new ArrayList<CheckPoint>();

	public World() {
		int gameLevel = Application.getGameLevel();

		NUM_MONSTERS_SINGLE_EYED = GameConstants.NUM_MONSTERS_SINGLE_EYED[gameLevel];
		NUM_MONSTERS_DOUBLE_EYED = GameConstants.NUM_MONSTERS_DOUBLE_EYED[gameLevel];
		NUM_MONSTERS_TRIPLE_EYED = GameConstants.NUM_MONSTERS_TRIPLE_EYED[gameLevel];

		NUM_BULLETS_GIFTS = GameConstants.NUM_BULLETS_GIFTS[gameLevel];
		NUM_ARMORS_GIFTS = GameConstants.NUM_ARMORS_GIFTS[gameLevel];
		NUM_FIRST_AID = GameConstants.NUM_FIRST_AID[gameLevel];
		NUM_EXTRA_LIFE = GameConstants.NUM_EXTRA_LIFE[gameLevel];
		NUM_CHECK_POINTS = GameConstants.NUM_CHECK_POINTS[gameLevel];

		for (int i = 0; i < NUM_MONSTERS_SINGLE_EYED; i++)
			listMonsterSingleEyed.add(new MonsterSingleEyed());

		for (int i = 0; i < NUM_MONSTERS_DOUBLE_EYED; i++)
			listMonsterDoubleEyed.add(new MonsterDoubleEyed());

		for (int i = 0; i < NUM_MONSTERS_TRIPLE_EYED; i++)
			listMonsterTripleEyed.add(new MonsterTripleEyed());

		for (int i = 0; i < NUM_BULLETS_GIFTS; i++)
			listBulletGift.add(new BulletGift());

		for (int i = 0; i < NUM_ARMORS_GIFTS; i++)
			listArmorGift.add(new ArmorGift());

		for (int i = 0; i < NUM_FIRST_AID; i++)
			listFirstAidGift.add(new FirstAid());
		
		for (int i = 0; i < NUM_EXTRA_LIFE; i++)
			listExtraLifeGift.add(new ExtraLife());

		for (int i = 0; i < NUM_CHECK_POINTS; i++)
			listCheckPoint.add(new CheckPoint());
	}

	/*
	 * objects adders methods
	 */

	public void addBullet(Bullet bullet) {
		listBullet.add(bullet);
	}

	public void addBomb(Bomb bomb) {
		listBomb.add(bomb);
	}

	/*
	 * objects list getters methods
	 */

	public ArrayList<MonsterSingleEyed> getListMonstersSingleEyed() {
		return listMonsterSingleEyed;
	}

	public ArrayList<MonsterDoubleEyed> getListMonstersDoubleEyed() {
		return listMonsterDoubleEyed;
	}

	public ArrayList<MonsterTripleEyed> getListMonstersTripleEyed() {
		return listMonsterTripleEyed;
	}

	public ArrayList<Bomb> getListBombs() {
		return listBomb;
	}

	public ArrayList<Bullet> getListBullets() {
		return listBullet;
	}

	public ArrayList<BulletGift> getListBulletGifts() {
		return listBulletGift;
	}

	public ArrayList<ArmorGift> getListArmorsGifts() {
		return listArmorGift;
	}

	public ArrayList<FirstAid> getListFirstAidGifts() {
		return listFirstAidGift;
	}
	
	public ArrayList<ExtraLife> getListExtraLifeGifts() {
		return listExtraLifeGift;
	}

	public ArrayList<CheckPoint> getListCheckPoints() {
		return listCheckPoint;
	}

	/*
	 * objects iterators getters methods
	 */

	public Iterator<MonsterSingleEyed> getMonstersSingleEyed() {
		return listMonsterSingleEyed.iterator();
	}

	public Iterator<MonsterDoubleEyed> getMonstersDoubleEyed() {
		return listMonsterDoubleEyed.iterator();
	}

	public Iterator<MonsterTripleEyed> getMonstersTripleEyed() {
		return listMonsterTripleEyed.iterator();
	}

	public Iterator<Bomb> getBombs() {
		return listBomb.iterator();
	}

	public Iterator<Bullet> getBullets() {
		return listBullet.iterator();
	}

	public Iterator<BulletGift> getBulletGifts() {
		return listBulletGift.iterator();
	}

	public Iterator<ArmorGift> getArmorsGifts() {
		return listArmorGift.iterator();
	}

	public Iterator<FirstAid> getFirstAidGifts() {
		return listFirstAidGift.iterator();
	}
	
	public Iterator<ExtraLife> getExtraLifeGifts() {
		return listExtraLifeGift.iterator();
	}

	public Iterator<CheckPoint> getCheckPoints() {
		return listCheckPoint.iterator();
	}
}
