package model.game.misc;

import model.game.Maze;
import model.game.characters.Enemy;
import model.game.characters.Player;
import model.game.characters.Role;

public class Bullet extends Item {

	private static final int RANGE = 24;
	private static final int SPEED = 4;
	
	private int damage;

	private final int direction;
	private boolean untouched = true;
	
	private Role role = null;

	public Bullet(int x, int y, int targetX, int targetY, int damage, Role role) {
		setX(x);
		setY(y);

		if (x == targetX) {
			direction = (y < targetY) ? Maze.DOWN : Maze.UP;
		} else {
			direction = (x < targetX) ? Maze.RIGHT : Maze.LEFT;
		}
		this.role = role;
		this.damage = damage;
	}

	public Bullet(int x, int y, int targetX, int targetY, int damage) {
		setX(x);
		setY(y);

		if (x == targetX) {
			direction = (y < targetY) ? Maze.DOWN : Maze.UP;
		} else {
			direction = (x < targetX) ? Maze.RIGHT : Maze.LEFT;
		}
		this.damage = damage;
	}

	public int getDirection() {
		return direction;
	}

	@Override
	public void doSomething() {
		if (role != null) {
			if (role instanceof Player) {
				Player player = (Player) role;

				if (getX() == player.getX() && Math.abs(getY() - player.getY()) <= RANGE) {
					if (player.hasArmor()) {
						player.getArmor().setResistance(player.getArmor().getResistance() - damage);

						if (player.getArmor().isDead()) {
							player.removeArmor();
						}
						
					} else {
						player.decreaseHP(damage);
					}
					untouched = false;

				} else if (getY() == player.getY() && Math.abs(getX() - player.getX()) <= RANGE) {
					
					if (player.hasArmor()) {
						player.getArmor().setResistance(player.getArmor().getResistance() - damage);

						if (player.getArmor().isDead()) {
							player.removeArmor();
						}
						
					} else {
						player.decreaseHP(damage);
					}
					untouched = false;

				} else if (willHitWall(SPEED, direction)) {
					untouched = false;

				} else {
					switch (direction) {
					case Maze.LEFT:
						setX(getX() - SPEED);
						break;
					case Maze.DOWN:
						setY(getY() + SPEED);
						break;
					case Maze.RIGHT:
						setX(getX() + SPEED);
						break;
					case Maze.UP:
						setY(getY() - SPEED);
						break;
					}
				}
			} else if (role instanceof Enemy) {

				if (getX() == role.getX() && Math.abs(getY() - role.getY()) <= RANGE) {
					role.decreaseHP(damage);
					untouched = false;
					
				} else if (getY() == role.getY() && Math.abs(getX() - role.getX()) <= RANGE) {
					role.decreaseHP(damage);
					untouched = false;
					
				} else if (willHitWall(SPEED, direction)) {
					untouched = false;
					
				} else {
					switch (direction) {
					case Maze.LEFT:
						setX(getX() - SPEED);
						break;
					case Maze.DOWN:
						setY(getY() + SPEED);
						break;
					case Maze.RIGHT:
						setX(getX() + SPEED);
						break;
					case Maze.UP:
						setY(getY() - SPEED);
						break;
					}
				}
			}
		} else if (role == null) {
			
			if (willHitWall(SPEED, direction)) {
				untouched = false;
				
			} else {
				switch (direction) {
				case Maze.LEFT:
					setX(getX() - SPEED);
					break;
				case Maze.DOWN:
					setY(getY() + SPEED);
					break;
				case Maze.RIGHT:
					setX(getX() + SPEED);
					break;
				case Maze.UP:
					setY(getY() - SPEED);
					break;
				}
			}
		}
	}

	@Override
	public boolean isAlive() {
		return untouched;
	}
}
