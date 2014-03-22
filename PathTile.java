import java.util.ArrayList;

public class PathTile extends Tile {
	public PathTile _nextTile;
	public PathTile unnamed_PathTile_;
	public PathGenerator unnamed_PathGenerator_;
	public ArrayList<Enemy> unnamed_Enemy_ = new ArrayList<Enemy>();

	public Enemy getEnemy() {
		throw new UnsupportedOperationException();
	}

	public void getNextTile() {
		throw new UnsupportedOperationException();
	}

	public void removeEnemy(Enemy enemy) {
		throw new UnsupportedOperationException();
	}
}