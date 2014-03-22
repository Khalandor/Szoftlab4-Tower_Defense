public class Enemy {
	protected int health;
	protected int speed;
	protected int manaValue;
	protected String type;
	protected int moveDelay;
	public PathTile unnamed_PathTile_;
	public EndTile unnamed_EndTile_;
	public Updater unnamed_Updater_;
	public Tile _currentTile;

	public Boolean damage(int value) {
		throw new UnsupportedOperationException();
	}

	public void decreaseMoveDelay() {
		throw new UnsupportedOperationException();
	}

	public int getHealth() {
		return this.health;
	}

	public int getManaValue() {
		return this.manaValue;
	}

	public Tile getTile() {
		throw new UnsupportedOperationException();
	}

	public String getType() {
		return this.type;
	}

	public boolean move() {
		throw new UnsupportedOperationException();
	}

	public void setMoveDelay(int delay) {
		throw new UnsupportedOperationException();
	}
}