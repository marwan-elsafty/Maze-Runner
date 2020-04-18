package model.resources;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Resource {

	public static final Image imageGameIcon = new ImageIcon("resource/icon.png").getImage();

	public static final Image imageMask = new ImageIcon("resource/misc/mask.png").getImage();
	public static final Image imageGuide = new ImageIcon("resource/misc/guide.png").getImage();

	public static final Image imagePath = new ImageIcon("resource/themes/path.png").getImage();
	public static final Image imageWall = new ImageIcon("resource/themes/wall.png").getImage();

	public static final ImageIcon iconBackground = new ImageIcon("resource/backgrounds/background.png");
	public static final ImageIcon iconBackgroundSmall = new ImageIcon("resource/backgrounds/backgroundSmall.png");

	public static final ImageIcon iconPlay = new ImageIcon("resource/buttons/play.png");
	public static final ImageIcon iconPause = new ImageIcon("resource/buttons/pause.png");
	public static final ImageIcon iconRestart = new ImageIcon("resource/buttons/restart.png");
	public static final ImageIcon iconHappyEnding = new ImageIcon("resource/buttons/happyEnding.png");
	public static final ImageIcon iconBadEnding = new ImageIcon("resource/buttons/badEnding.png");
	public static final ImageIcon iconFogEnable = new ImageIcon("resource/buttons/lock.png");
	public static final ImageIcon iconFogDisable = new ImageIcon("resource/buttons/unlock.png");

	public static final Image imagePlayer = new ImageIcon("resource/characters/player.png").getImage();
	
	public static final Image imageMonsterSingleEyed = new ImageIcon("resource/characters/monsterSingleEyed.png").getImage();
	public static final Image imageMonsterDoubleEyed = new ImageIcon("resource/characters/monsterDoubleEyed.png").getImage();
	public static final Image imageMonsterTripleEyed = new ImageIcon("resource/characters/monsterTripleEyed.png").getImage();
	
	public static final ImageIcon imageMonsterSingleEyedLarge = new ImageIcon("resource/characters/monsterSingleEyedLarge.png");
	public static final ImageIcon imageMonsterDoubleEyedLarge = new ImageIcon("resource/characters/monsterDoubleEyedLarge.png");
	public static final ImageIcon imageMonsterTripleEyedLarge = new ImageIcon("resource/characters/monsterTripleEyedLarge.png");

	public static final Image imageAttackLeft = new ImageIcon("resource/attacks/attackLeft.png").getImage();
	public static final Image imageAttackDown = new ImageIcon("resource/attacks/attackDown.png").getImage();
	public static final Image imageAttackRight = new ImageIcon("resource/attacks/attackRight.png").getImage();
	public static final Image imageAttackUp = new ImageIcon("resource/attacks/attackUp.png").getImage();

	public static final Image imageBulletLeft = new ImageIcon("resource/bullets/bulletLeft.png").getImage();
	public static final Image imageBulletDown = new ImageIcon("resource/bullets/bulletDown.png").getImage();
	public static final Image imageBulletRight = new ImageIcon("resource/bullets/bulletRight.png").getImage();
	public static final Image imageBulletUp = new ImageIcon("resource/bullets/bulletUp.png").getImage();

	public static final Image imageArmorGift = new ImageIcon("resource/misc/gifts/armorGift.png").getImage();
	public static final Image imageBulletGift = new ImageIcon("resource/misc/gifts/bulletGift.png").getImage();
	public static final Image imageFirstAid = new ImageIcon("resource/misc/gifts/firstAidGift.png").getImage();
	public static final Image imageExtraLife = new ImageIcon("resource/misc/gifts/extraLifeGift.png").getImage();
	public static final Image imageCheckPoint = new ImageIcon("resource/misc/gifts/checkPoint.png").getImage();
	
	public static final Image imageArmor = new ImageIcon("resource/misc/armor.png").getImage();
	public static final Image imageBomb = new ImageIcon("resource/misc/bomb.png").getImage();
	
	public static final Image imageSkull = new ImageIcon("resource/misc/endings/skull.png").getImage();
	
	public static final Image imageVictory = new ImageIcon("resource/misc/endings/victory.png").getImage();
	public static final Image imageDefeat = new ImageIcon("resource/misc/endings/defeat.png").getImage();

	private Resource() {

	}
}
