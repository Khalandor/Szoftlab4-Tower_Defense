import java.io.PrintStream;
import java.util.ArrayList;


public class Updater {
	public ConstructManager constructManager;
	public Geometry geometry;
	public EnemyGenerator enemyGenerator;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Construct> constructs = new ArrayList<Construct>();
	public Mana mana = new Mana();

	/**
	 * Visszadja az enemies erteket
	 * @return visszaadott ertek
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public Updater() {
	}
	
	
	/**
	 * Visszaadja a Mana osztaly peldanyat
	 * @return Mana osztallyal
	 */
	/*public Mana getMana() {
		return mana;
	}*/
	
	/**
	 * Visszater a Constructokkal amiket az Updater tartalmaz   
	 * @return constructokkat tartalmazo Arraylisttel
	 */
	public ArrayList<Construct> getConstructs() {
		return constructs;
	}
	
	/**
	 * Uj Construct-ok felvételét teszi lehetőve
	 * @param construct az uj construct erteke
	 */
	public void addConstruct(Construct construct) {
		System.out.println("--> Updater.addConstruct(" + construct + ")");
		constructs.add(construct);
		//System.out.println("<--");
	}

	/**
	 * Uj Enemy-k felvetelet teszi lehetove 
	 * @param enemy
	 */
	public void addEnemy(Enemy enemy) {
		System.out.println("--> Updater.addEnemy(" + enemy + ")");
		System.out.println("<--");
		enemies.add(enemy);
	}

	/**
	 * Veget vet a jateknak gyozelemmel, ha igaz a parameter, vereseggel ha hamis 
	 * @param isover 
	 */
	public void gameOver(Boolean isover) {
		System.out.println("--> Updater.gameOver(Boolean)");
	}
	
	/**
	 * Beallitja geometry valtozot
	 * @param geometry ez lesz az uj erteket
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	
	/**
	 * Beallitja a constructManager valtozott 
	 * @param constructManager a constructManager uj erteke
	 */
	public void setConstructManager(ConstructManager constructManager) {
		this.constructManager = constructManager;
	}
	
	/**
	 * Beallitja az enemyGenerator valtozott
	 * @param enemyGenerator a beallitando uj erteke a valtozonak.
	 */
	public void setEnemyGenerator(EnemyGenerator enemyGenerator) {
		this.enemyGenerator = enemyGenerator;
	}
	
	/**
	*Játék kezdeténél lefutó inicializálás.
	*Létrehozza a szükséges objektumokat és
	*beállítja a referenciákat. Lefutása után
	*készen áll a játék a futásra.
	*/
	/*public void init() {
		System.out.println("--> Updater.init()");
		geometry = new Geometry();		//Létrehozzuk a pályát
		for(int i = 0; i < 3; i++){				
			geometry.getTilesList().add(new FieldTile(geometry));		//Létrehozunk 3 csempét, melyekre tornyokat lehet építeni
			geometry.getTilesList().add(new PathTile(geometry));		//Létrehozunk 3 útcsempét
		}
		geometry.getTilesList().add(new EndTile(geometry));			//Létrehozzuk a végzet hegyét
		PathGenerator pathGenerator = new PathGenerator(geometry);	//Létrehozzuk az útvonalgenerátort
		enemyGenerator = new EnemyGenerator(pathGenerator);			//Létrehozzuk az ellenséggenerátort útvonalgenerátor segítségével
		new ConstructManager(this);								//Létrehozzuk az épületkezelőt
	}*/

	/**
	 * Visszaadja a palyan levo ellensegek szamat
	 * @return az ellensegek szama
	 */
	public int getNumOfEnemies() {
		System.out.println("--> Updater.getNumOfEnemies()");
		System.out.println("<-- " + enemies.size());
		return enemies.size();
	}

	/**
	 * Visszadja a geometry-t
	 * @return a geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}
}
