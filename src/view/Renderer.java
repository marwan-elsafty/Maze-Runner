package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;

import controller.State;
import controller.main.Application;
import model.constants.Layout;
import model.game.Maze;
import model.game.World;
import model.game.characters.MonsterDoubleEyed;
import model.game.characters.MonsterSingleEyed;
import model.game.characters.MonsterTripleEyed;
import model.game.characters.Player;
import model.game.characters.Role;
import model.game.misc.Bomb;
import model.game.misc.Bullet;
import model.game.misc.Item;
import model.game.misc.gifts.ArmorGift;
import model.game.misc.gifts.BulletGift;
import model.game.misc.gifts.CheckPoint;
import model.game.misc.gifts.ExtraLife;
import model.game.misc.gifts.FirstAid;
import model.resources.Resource;

public class Renderer {

	private State state;
	private World world;

	public Renderer() {
		this.state = Application.getState();
		this.world = Application.getWorld();
	}

	public void render(Graphics graphics) {
		paintMaze(graphics);
		paintBomb(graphics);

		paintPlayer(graphics);

		paintMonsterDoubleEyed(graphics);
		paintMonsterTripleEyed(graphics);
		paintMonsterSingleEyed(graphics);

		paintBullet(graphics);

		if (state.isFogEnabled()) {
			paintFog(graphics);
		}

		if (state.isGuideEnabled()) {
			paintGuide(graphics);
		} else {
			paintGifts(graphics);
		}

		if (state.isGameOver()) {
			paintGameResult(graphics);
		}
	}

	/*
	 * world rendering
	 */
	private void paintMaze(Graphics graphics) {
		int[][] maze = Application.getMaze();
		for (int i = 0; i < Maze.MAZE_WIDTH * 2 + 1; i++) {
			for (int j = 0; j < Maze.MAZE_HEIGHT * 2 + 1; j++) {
				if (maze[i][j] == Maze.PATH || maze[i][j] == Maze.EXIT_CELL) {
					graphics.drawImage(Resource.imagePath, i * 32, j * 32, null);
				} else {
					graphics.drawImage(Resource.imageWall, i * 32, j * 32, null);
				}
			}
		}
	}

	private void paintItem(Graphics graphics, Item item, Image image) {
		graphics.drawImage(image, item.getX(), item.getY(), null);
	}

	private void paintRole(Graphics graphics, Role role, Image image) {
		graphics.drawImage(image, role.getX(), role.getY(), null);

		if (role.isAlive() == false) {
			graphics.drawImage(Resource.imageSkull, role.getX(), role.getY(), null);
		}

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("Century Gothic", Font.BOLD, 10));

		if (role instanceof Player) {
			paintBulletsCount(graphics, role);
		}

		paintHP(graphics, role);
	}

	/*
	 * role information rendering
	 */

	private void paintHP(Graphics graphics, Role role) {
		graphics.drawString("" + role.getHP(), role.getX(), role.getY() + 30);
	}

	private void paintBulletsCount(Graphics graphics, Role role) {
		graphics.drawString("" + ((Player) role).getBulletsCount(), role.getX(), role.getY() + 10);
	}

	/*
	 * characters rendering
	 */

	private void paintPlayer(Graphics graphics) {
		Player player = Application.getPlayer();
		paintRole(graphics, player, Resource.imagePlayer);

		if (player.hasArmor()) {
			paintArmor(graphics, player);
		}
	}

	private void paintMonsterSingleEyed(Graphics graphics) {
		for (Iterator<MonsterSingleEyed> iterator = world.getMonstersSingleEyed(); iterator.hasNext();) {
			MonsterSingleEyed monster = iterator.next();
			paintRole(graphics, monster, Resource.imageMonsterSingleEyed);
		}
	}

	private void paintMonsterDoubleEyed(Graphics graphics) {
		for (Iterator<MonsterDoubleEyed> iterator = world.getMonstersDoubleEyed(); iterator.hasNext();) {
			MonsterDoubleEyed monster = iterator.next();
			paintRole(graphics, monster, Resource.imageMonsterDoubleEyed);
		}
	}

	private void paintMonsterTripleEyed(Graphics graphics) {
		for (Iterator<MonsterTripleEyed> iterator = world.getMonstersTripleEyed(); iterator.hasNext();) {
			MonsterTripleEyed monster = iterator.next();
			paintRole(graphics, monster, Resource.imageMonsterTripleEyed);
		}
	}

	/*
	 * attack rendering
	 */

	private void paintBullet(Graphics graphics) {
		for (Iterator<Bullet> iterator = world.getBullets(); iterator.hasNext();) {
			Bullet bullet = iterator.next();
			Image image = null;
			switch (bullet.getDirection()) {
			case Maze.LEFT:
				image = Resource.imageBulletLeft;
				break;
			case Maze.DOWN:
				image = Resource.imageBulletDown;
				break;
			case Maze.RIGHT:
				image = Resource.imageBulletRight;
				break;
			case Maze.UP:
				image = Resource.imageBulletUp;
				break;
			}
			paintItem(graphics, bullet, image);
		}
	}

	/*
	 * gifts rendering
	 */

	private void paintGifts(Graphics graphics) {
		paintBulletGift(graphics);
		paintArmorGift(graphics);
		paintFirstAidGift(graphics);
		paintExtraLifeGift(graphics);
		paintCheckPoint(graphics);
	}

	private void paintBulletGift(Graphics graphics) {
		for (Iterator<BulletGift> iterator = world.getBulletGifts(); iterator.hasNext();) {
			BulletGift bullet = iterator.next();
			paintItem(graphics, bullet, Resource.imageBulletGift);
		}
	}

	private void paintArmorGift(Graphics graphics) {
		for (Iterator<ArmorGift> iterator = world.getArmorsGifts(); iterator.hasNext();) {
			ArmorGift armorGift = iterator.next();
			paintItem(graphics, armorGift, Resource.imageArmorGift);
		}
	}

	private void paintFirstAidGift(Graphics graphics) {
		for (Iterator<FirstAid> iterator = world.getFirstAidGifts(); iterator.hasNext();) {
			FirstAid firstAid = iterator.next();
			paintItem(graphics, firstAid, Resource.imageFirstAid);
		}
	}
	
	private void paintExtraLifeGift(Graphics graphics) {
		for (Iterator<ExtraLife> iterator = world.getExtraLifeGifts(); iterator.hasNext();) {
			ExtraLife extraLife = iterator.next();
			paintItem(graphics, extraLife, Resource.imageExtraLife);
		}
	}

	private void paintCheckPoint(Graphics graphics) {
		for (Iterator<CheckPoint> iterator = world.getCheckPoints(); iterator.hasNext();) {
			CheckPoint checkPoint = iterator.next();
			paintItem(graphics, checkPoint, Resource.imageCheckPoint);
		}
	}

	/*
	 * miscellaneous rendering
	 */

	private void paintBomb(Graphics graphics) {
		for (Iterator<Bomb> iterator = world.getBombs(); iterator.hasNext();) {
			Bomb bomb = iterator.next();
			paintItem(graphics, bomb, Resource.imageBomb);
		}
	}

	private void paintArmor(Graphics graphics, Player player) {
		graphics.drawImage(Resource.imageArmor, player.getX() - 16, player.getY() - 16, null);
	}

	private void paintFog(Graphics graphics) {
		Player player = Application.getPlayer();
		graphics.drawImage(Resource.imageMask, player.getX() - (640 - 16), player.getY() - (640 - 16), null);
	}

	private void paintGuide(Graphics graphics) {
		graphics.drawImage(Resource.imageGuide, 0, 0, null);
	}

	private void paintGameResult(Graphics graphics) {
		if (state.isGameVictory()) {
			graphics.drawImage(Resource.imageVictory, Layout.RUN_PANEL_WIDTH / 8, Layout.RUN_PANEL_HEIGHT / 8, null);
		} else {
			graphics.drawImage(Resource.imageDefeat, Layout.RUN_PANEL_WIDTH / 8, Layout.RUN_PANEL_HEIGHT / 8, null);
		}
	}
}
