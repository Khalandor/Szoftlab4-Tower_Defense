import java.util.ArrayList;

public class PathTile extends Tile {
	public PathTile NextTile;
	public PathTile unnamed_PathTile_;
	public PathGenerator unnamed_PathGenerator_;
	public ArrayList<Enemy> EnemyList = new ArrayList<Enemy>();

	/**
	 * Beállítja a csempe típusát
	 */
	public PathTile()
	{
		Type = "PathTile";
	}

	/**
	 * Visszaad egy, az ezen a csempén lévő ellenséget
	 * @return Az úton lévő ellenség
	 */
	public Enemy getEnemy() {
		return EnemyList[0];
	}

	/**
	 * Visszaadja a következő útcsempét
	 * @return Az úton ezután következő csempe
	 */
	public Tile getNextTile() {
		return NextTile;
	}

	/**
	 * Törli a csempéről a paraméterül átadott ellenséget
	 * @param enemy a törölt ellenség
	 */
	public void removeEnemy(Enemy enemy) {
		EnemyList.remove(enemy);
	}

}
