import java.io.PrintStream;
import java.util.ArrayList;


public class Updater {
	public ConstructManager constructManager;
	public Geometry geometry;
	public EnemyGenerator enemyGenerator;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Construct> constructs = new ArrayList<Construct>();
	public Mana mana = new Mana();

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public Mana getMana() {
		return mana;
	}
	public ArrayList<Construct> getConstructs() {
		return constructs;
	}
	
	public void addConstruct(Construct construct) {
		//throw new UnsupportedOperationException();
		System.out.println("--> Updater.addConstruct(" + construct + ")");
		//System.out.println("<--");
	}

	public void addEnemy(Enemy enemy) {
		System.out.println("--> Updater.addEnemy(" + enemy + ")");
		System.out.println("<--");
		enemies.add(enemy);
	}

	public void gameOver(Boolean isover) {
		System.out.println("--> Updater.gameOver(" + isover + ")");
	}
	
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	
	public void setConstructManager(ConstructManager constructManager) {
		this.constructManager = constructManager;
	}
	
	public void setEnemyGenerator(EnemyGenerator enemyGenerator) {
		this.enemyGenerator = enemyGenerator;
	}
	
	/**
	*Játék kezdeténél lefutó inicializálás.
	*Létrehozza a szükséges objektumokat és
	*beállítja a referenciákat. Lefutása után
	*készen áll a játék a futásra.
	*/
	public void init() {
		System.out.println("--> Updater.init()");
		/*geometry = new Geometry();									//Létrehozzuk a pályát
		PathGenerator pathGenerator = new PathGenerator(geometry);	//Létrehozzuk az útvonalgenerátort
		enemyGenerator = new EnemyGenerator(pathGenerator);			//Létrehozzuk az ellenséggenerátort útvonalgenerátor segítségével
		new ConstructManager(this);		*/							//Létrehozzuk az épületkezelőt
	}

	public int getNumOfEnemies() {
		System.out.println("--> Updater.getNumOfEnemies()");
		System.out.println("<-- " + enemies.size());
		return enemies.size();
	}
}
