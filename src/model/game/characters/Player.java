package model.game.characters;

import java.util.ArrayList;
import java.util.Iterator;

import controller.main.Application;
import model.Factory;
import model.ScoreDetector;
import model.buffer.GameBuffer;
import model.constants.GameConstants;
import model.game.Maze;
import model.game.misc.Bullet;
import model.game.misc.gifts.ArmorGift;
import model.game.misc.gifts.BulletGift;
import model.game.misc.gifts.CheckPoint;
import model.game.misc.gifts.ExtraLife;
import model.game.misc.gifts.FirstAid;
import model.game.misc.supply.Ammunition;
import model.game.misc.supply.Armor;

public class Player extends Role {

	private static final int DAMAGAE = GameConstants.PLAYER_DAMAGE;
	private static final int RANGE = 32;

	private int bonusMove = 0;

	private int lastMoveDirection = Maze.RIGHT;

	private boolean hasArmor = false;
	private ArrayList<Armor> listArmor = new ArrayList<Armor>();

	private boolean hasAmmunition = false;
	private ArrayList<Ammunition> listAmmunition = new ArrayList<Ammunition>();

	private boolean didShot = false;

	private boolean isDead = false;
	private int startingLifes;
	private int leftLives;

	private int bulletsCount;

	public Player() {
		startingLifes = GameConstants.PLAYER_LIFES[Application.getGameLevel()];
		leftLives = startingLifes;

		setX(1 * 32);
		setY(1 * 32);
		setHP(GameConstants.PLAYER_HP);
		setSpeed(GameConstants.PLAYER_SPEED);
		setAttackInterval(75);

		addAmmunition();
	}

	public void reset() {
		setX(1 * 32);
		setY(1 * 32);

		setHP(GameConstants.PLAYER_HP);
		addAmmunition();
	}

	@Override
	public void doSomething() {
		super.doSomething();

		moveUnderUserControl();
		pickUpGifts();

		if (bonusMove == 0) {
			moveUnderUserControl();
			pickUpGifts();
		}
		bonusMove = (bonusMove + 1) % 3;

		shootEnemy();
	}

	/*
	 * moving
	 */

	private void moveUnderUserControl() {
		if (Application.getState().isGameOver()) {
			return;
		}

		int testDirection = (lastMoveDirection + 1) % 4;
		int testCount = 0;
		while (testCount < 4) {
			if (isOkToMove(testDirection)) {
				move(testDirection);
				lastMoveDirection = testDirection;
				return;
			} else {
				testDirection = (testDirection + 1) % 4;
				testCount++;
			}
		}
	}

	private boolean isOkToMove(int direction) {
		if (Application.getState().isGameOver()) {
			return false;
		}

		if (Application.getState().isKeyHolding(direction) && willHitWall(direction) == false) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean willHitWall(int speed, int direction) {
		if (Application.getState().isGameOver()) {
			return false;
		}

		int[][] maze = Application.getMaze();
		switch (direction) {
		case Maze.UP:
			if (getX() % 32 == 0 && (maze[getX() / 32][(getY() - speed) / 32] == Maze.PATH)
					|| maze[getX() / 32][(getY() - speed) / 32] == Maze.EXIT_CELL) {
				return false;
			} else {
				return true;
			}
		case Maze.LEFT:
			if (getY() % 32 == 0 && (maze[(getX() - speed) / 32][getY() / 32] == Maze.PATH)
					|| maze[(getX() - speed) / 32][getY() / 32] == Maze.EXIT_CELL) {
				return false;
			} else {
				return true;
			}
		case Maze.RIGHT:
			if (getY() % 32 == 0 && (maze[(getX() + speed - 1) / 32 + 1][getY() / 32] == Maze.PATH)
					|| maze[(getX() + speed - 1) / 32 + 1][getY() / 32] == Maze.EXIT_CELL) {
				return false;
			} else {
				return true;
			}
		case Maze.DOWN:
			if (getX() % 32 == 0 && (maze[getX() / 32][(getY() + speed - 1) / 32 + 1] == Maze.PATH)
					|| maze[getX() / 32][(getY() + getSpeed() - 1) / 32 + 1] == Maze.EXIT_CELL) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean willHitWall(int direction) {
		return this.willHitWall(getSpeed(), direction);
	}

	/*
	 * shooting
	 */

	private void shootEnemy() {
		if (hasAmmunition()) {
			Application.getGame().addToScore(Factory.detect(ScoreDetector.SHOT_MONSTER));

			if (didShoot()) {
				return;
			}

			if (Application.getState().isKeyHolding(Maze.SPACE) == false) {
				return;
			}

			for (Iterator<MonsterSingleEyed> iterator = Application.getWorld().getMonstersSingleEyed(); iterator
					.hasNext();) {
				MonsterSingleEyed monsterSingleEyed = iterator.next();
				shootEnemy(monsterSingleEyed);
				if (isReadyToAttack() == false) {
					return;
				}
			}

			for (Iterator<MonsterTripleEyed> iterator = Application.getWorld().getMonstersTripleEyed(); iterator
					.hasNext();) {
				MonsterTripleEyed monsterTripleEyed = iterator.next();
				shootEnemy(monsterTripleEyed);
				if (isReadyToAttack() == false) {
					return;
				}
			}

			for (Iterator<MonsterDoubleEyed> iterator = Application.getWorld().getMonstersDoubleEyed(); iterator
					.hasNext();) {
				MonsterDoubleEyed monsterDoubleEyed = iterator.next();
				shootEnemy(monsterDoubleEyed);
				if (isReadyToAttack() == false) {
					return;
				}
			}
			getAmmunition().decreasBulletsCount();

			if (getAmmunition().isDead()) {
				removeAmmunition();
			}
		} else
			return;

		doneShooting();

		// Application.getGame().addToScore(Factory.detect(ScoreDetector.SHOT_MONSTER));
	}

	private void shootEnemy(Enemy enemy) {
		if (canSee(enemy) == false) {
			int targetX = 0;
			int targetY = 0;

			switch (lastMoveDirection) {
			case Maze.DOWN:
				targetX = getX();
				targetY = getY() + 1;
				break;
			case Maze.LEFT:
				targetX = getX() - 1;
				targetY = getY();
				break;
			case Maze.RIGHT:
				targetX = getX() + 1;
				targetY = getY();
				break;
			case Maze.UP:
				targetX = getX();
				targetY = getX() - 1;
				break;
			}
			Application.getWorld().addBullet(new Bullet(getX(), getY(), targetX, targetY, DAMAGAE));

		} else if (canSee(enemy) == true) {
			if (hasAmmunition()) {

				Application.getWorld()
						.addBullet(new Bullet(getX(), getY(), enemy.getX(), enemy.getY(), DAMAGAE, enemy));

				// Application.getGame().addToScore(Factory.detect(ScoreDetector.SHOT_MONSTER));

				if (getX() == enemy.getX() && Math.abs(getY() - enemy.getY()) <= RANGE) {
					enemy.decreaseHP(DAMAGAE);
					prepareForNextAttack();

				} else if (getY() == enemy.getY() && Math.abs(getX() - enemy.getX()) <= RANGE) {
					enemy.decreaseHP(DAMAGAE);
					prepareForNextAttack();
				}

				getAmmunition().decreasBulletsCount();

				if (getAmmunition().isDead()) {
					removeAmmunition();
				}
			}
		}
	}

	/*
	 * gifts collecting actions
	 */

	private void pickUpGifts() {
		Application.getGame().addToScore(Factory.detect(ScoreDetector.PICKED_GIFT));

		pickUpBulletGift();
		pickUpArmorGift();
		pickUpFirstAid();
		pickUpExtraLife();
		pickUpCheckPoint();
	}

	private void pickUpBulletGift() {
		for (Iterator<BulletGift> iterator = Application.getWorld().getBulletGifts(); iterator.hasNext();) {
			BulletGift bulletGift = iterator.next();
			if (getX() == bulletGift.getX() && getY() == bulletGift.getY()) {
				bulletGift.pickUpByPlayer();
				addAmmunition();
				return;
			}
		}
	}

	private void pickUpArmorGift() {
		for (Iterator<ArmorGift> iterator = Application.getWorld().getArmorsGifts(); iterator.hasNext();) {
			ArmorGift armorGift = iterator.next();
			if (getX() == armorGift.getX() && getY() == armorGift.getY()) {
				armorGift.pickUpByPlayer();
				addArmor();
				return;
			}
		}
	}

	private void pickUpFirstAid() {
		for (Iterator<FirstAid> iterator = Application.getWorld().getFirstAidGifts(); iterator.hasNext();) {
			FirstAid armor = iterator.next();
			if (getX() == armor.getX() && getY() == armor.getY()) {
				armor.pickUpByPlayer();
				setHP(getHP() + GameConstants.GIFT_EXTRA_HP);
				return;
			}
		}
	}
	
	private void pickUpExtraLife() {
		for (Iterator<ExtraLife> iterator = Application.getWorld().getExtraLifeGifts(); iterator.hasNext();) {
			ExtraLife extraLife = iterator.next();
			if (getX() == extraLife.getX() && getY() == extraLife.getY()) {
				extraLife.pickUpByPlayer();
				return;
			}
		}
	}

	private void pickUpCheckPoint() {
		for (Iterator<CheckPoint> iterator = Application.getWorld().getCheckPoints(); iterator.hasNext();) {
			CheckPoint checkPoint = iterator.next();
			if (getX() == checkPoint.getX() && getY() == checkPoint.getY()) {
				checkPoint.pickUpByPlayer();
				GameBuffer.save();
				return;
			}
		}
	}

	/*
	 * armor
	 */

	public Armor getArmor() {
		int LAST = this.listArmor.size() - 1;
		return this.listArmor.get(LAST);
	}

	public boolean hasArmor() {
		return this.hasArmor;
	}

	public void addArmor() {
		this.listArmor.add(new Armor());
		this.hasArmor = true;
	}

	public void removeArmor() {
		int LAST = this.listArmor.size() - 1;
		this.listArmor.remove(LAST);

		if (this.listArmor.isEmpty()) {
			this.hasArmor = false;
		}
	}

	/*
	 * ammunition
	 */

	public Ammunition getAmmunition() {
		int LAST = this.listAmmunition.size() - 1;
		return this.listAmmunition.get(LAST);
	}

	public boolean hasAmmunition() {
		return this.hasAmmunition;
	}

	public void addAmmunition() {
		this.listAmmunition.add(new Ammunition());
		this.hasAmmunition = true;

		this.bulletsCount += GameConstants.AMMUNITION_TANK;
	}

	public void removeAmmunition() {
		int LAST = this.listAmmunition.size() - 1;

		if (LAST < 0) {
			this.hasAmmunition = false;
		}

		this.listAmmunition.remove(LAST);

		if (this.listAmmunition.isEmpty()) {
			this.hasAmmunition = false;
		}
	}

	public int getBulletsCount() {
		return this.bulletsCount;
	}

	public void decreaseBulletsCount() {
		if (this.bulletsCount < 0) {
			this.bulletsCount = 0;
		}
		this.bulletsCount--;
	}

	/*
	 * shooting checks
	 */

	private void doneShooting() {
		this.didShot = true;
	}

	public void stoppedShooting() {
		this.didShot = false;
	}

	public boolean didShoot() {
		return this.didShot;
	}

	/*
	 * lives
	 */

	public int getLeftLives() {
		return this.leftLives;
	}

	public void decreaseLeftLives() {
		this.leftLives--;

		if (leftLives <= 0) {
			setDead();
		}
		System.out.println("ll: " + leftLives);
	}

	public void setDead() {
		this.isDead = true;
	}

	public boolean haveNoLives() {
		return this.isDead;
	}
}
