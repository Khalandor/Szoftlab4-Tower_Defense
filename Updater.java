import java.io.PrintStream;
import java.util.ArrayList;


public class Updater {
	public ConstructManager constructManager;
	public Geometry geometry;
	public EnemyGenerator enemyGenerator;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Construct> constructs = new ArrayList<Construct>();
	public Mana mana = new Mana();

	public void addConstruct(Construct construct) {
		throw new UnsupportedOperationException();
	}

	public void addEnemy(Enemy enemy) {
		throw new UnsupportedOperationException();
	}

	public void gameOver(Boolean isover) {
		throw new UnsupportedOperationException();
	}
	
	/**
	*Játék kezdeténél lefutó inicializálás.
	*Létrehozza a szükséges objektumokat és
	*beállítja a referenciákat. Lefutása után
	*készen áll a játék a futásra.
	*/
	public void init() {
		System.out.println("--> Updater.init()");
		geometry = new Geometry();									//Létrehozzuk a pályát
		PathGenerator pathGenerator = new PathGenerator(geometry);	//Létrehozzuk az útvonalgenerátort
		enemyGenerator = new EnemyGenerator(pathGenerator);			//Létrehozzuk az ellenséggenerátort útvonalgenerátor segítségével
		new ConstructManager(this);									//Létrehozzuk az épületkezelőt
	}
	
	/**
	 * Törli a rendszer konzol tartalmát Windows, Unix és OSX rendszereken. Egyéb rendszerek konzolján lejjebb görget 30 sort.
	 */
	private void clearConsole()
	{
		    try
		    {
		        String os = System.getProperty("os.name");
		        if (os.contains("Windows"))
		        {
		            Runtime.getRuntime().exec("cls");
		        }
		        else
		        {
		            Runtime.getRuntime().exec("clear");
		        }
		    }
		    catch (Exception exception)
		    {
		       for (int i = 0; i <30; i++)
		    	   System.out.println();
		    }
	}
	
	/**
	 * Az 5. teszteset környezetének létrehozása
	 */
	public void test5_init()
	{
		//egy ellenség létrehozása
		Elf Legolas = new Elf();
		Legolas.health = 10;
		Legolas.manaValue = 5;
		enemies.add(Legolas); 
		
		//egy torony létrehozása
		Tower tower1 = new Tower();
		tower1.setDamage(20); 
		constructs.add(tower1);
		
		// a 2 szükséges csempe elhelyezése (egyikről lépünk a másikra)
		geometry = new Geometry();
		PathTile start = new PathTile(geometry);
		PathTile next = new PathTile(geometry);
		FieldTile towerTile = new FieldTile(geometry);
		geometry.tiles.add(start); //TODO a tesztesethez kell, de a Geometry.tiles valójában private
		geometry.tiles.add(next);
		start.nextTile = next; //TODO  a tesztesethez kell, de a PathTile.nextTile valójában private
		
		// ellenség és torony felhelyezése a csempére
		Legolas.currentTile = start;
		towerTile.constructOnTile = tower1;
		tower1.towerLocation = towerTile; //TODO a tesztesethez kell, de a Tower.towerLocation valójában private		
	}
	
	/**
	 * Az 5. teszteset futtatása
	 */
	public void test5()
	{
		clearConsole();
		System.out.println("Teszt indítása");
		Enemy Legolas = enemies.get(0);
		Legolas.move();
		((Tower)constructs.get(0)).shoot();
		// if nélkül is ugyanúgy lefut, de így talán érthetőbb
		if (Legolas.getHealth() <= 0 )
		{
			int manaValue = Legolas.getManaValue();
			PathTile LegolasTile = (PathTile)Legolas.getTile();  //Castolhatunk, mert Legolas biztos PathTile-on áll (ha a Végzet hegyén állna, már vége lenne a játéknak).
			LegolasTile.removeEnemy(Legolas);
			mana.increase(manaValue);
		}
		
	}
	
	public static void main(String[] args) {
	}

}
