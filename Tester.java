
public class Tester {
	
	public static void test8_init()
	{
		updater = new Updater();
		geometry = new Geometry();
		updater.setConstructManager(constructManager);
		//PathTile location = new PathTile(geometry);
		updater.getMana().setMana(30);
	}
	
	public static void test8()
	{
		PathTile location = new PathTile(geometry);
		
		clearConsole();
		System.out.println("Teszt indítása");
		int cost = 20;
		if (updater.getMana().hasEnought(cost)) {
			location.getType();
			Barricade barricade = new Barricade();
			location.addConstruct(barricade);
			updater.getMana().decrease(cost);
			updater.addConstruct(barricade);
		}
	}
	
	public static void test5_init()
	{	
		updater = new Updater();
		geometry = new Geometry();
		constructManager = new ConstructManager(updater);
		pathGenerator = new PathGenerator(geometry);
		enemyGenerator = new EnemyGenerator(pathGenerator);
		updater.setGeometry(geometry);
		updater.setEnemyGenerator(enemyGenerator);
		updater.setConstructManager(constructManager);
		
		//egy ellenség létrehozása
		Elf Legolas = new Elf();
		Legolas.health = 10;
		Legolas.manaValue = 5;
		updater.getEnemies().add(Legolas); 
		
		//egy torony létrehozása
		Tower tower1 = new Tower();
		tower1.setDamage(20); 
		updater.getConstructs().add(tower1);
		
		// a 2 szükséges csempe elhelyezése (egyikről lépünk a másikra)
		//geometry = new Geometry();
		PathTile start = new PathTile(geometry);
		PathTile next = new PathTile(geometry);
		FieldTile towerTile = new FieldTile(geometry);
		geometry.getTiles().add(start); //TODO a tesztesethez kell, de a Geometry.tiles valójában private
		geometry.getTiles().add(next);
		start.setNextTile(next); //TODO  a tesztesethez kell, de a PathTile.nextTile valójában private
		
		// ellenség és torony felhelyezése a csempére
		Legolas.currentTile = start;
		towerTile.constructOnTile = tower1;
		tower1.setTowerLocation(towerTile); //TODO a tesztesethez kell, de a Tower.towerLocation valójában private		
	}
	
	public static void test5()
	{
		clearConsole();
		System.out.println("Teszt indítása");
		Enemy Legolas = updater.getEnemies().get(0);
		Legolas.move();
		((Tower)updater.getConstructs().get(0)).shoot();
		// if nélkül is ugyanúgy lefut, de így talán érthetőbb
		if (Legolas.getHealth() <= 0 )
		{
			int manaValue = Legolas.getManaValue();
			PathTile LegolasTile = (PathTile)Legolas.getTile();  //Castolhatunk, mert Legolas biztos PathTile-on áll (ha a Végzet hegyén állna, már vége lenne a játéknak).
			LegolasTile.removeEnemy(Legolas);
			updater.getMana().increase(manaValue);
		}

	}

	
	/**
	 * Törli a rendszer konzol tartalmát Windows, Unix és OSX rendszereken. Egyéb rendszerek konzolján lejjebb görget 30 sort.
	 */
	private static void clearConsole()
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
	
	static Updater updater;
	static Geometry geometry;
	static ConstructManager constructManager;
	static PathGenerator pathGenerator;
	static EnemyGenerator enemyGenerator;
	
	public static void main(String[] args)
	{	
		switch(args[0]) {
		case "1":
			System.out.println(args[0]+". teszteset");
			System.out.println("Inkább halj meg.");
			break;
		case "2":
			System.out.println(args[0]+". teszteset");
			break;
		case "3":
			System.out.println(args[0]+". teszteset");
			break;
		case "4":
			System.out.println(args[0]+". teszteset");
			break;
		case "5":
			System.out.println(args[0]+". teszteset");
			test5_init();
			test5();
			break;
		case "6":
			System.out.println(args[0]+". teszteset");
			break;
		case "7":
			System.out.println(args[0]+". teszteset");
			break;
		case "8":
			System.out.println(args[0]+". teszteset");
			test8_init();
			test8();
			break;
		case "9":
			System.out.println(args[0]+". teszteset");
			break;
		case "10":
			System.out.println(args[0]+". teszteset");
			break;
		default:
			System.out.println("Buta vagy fiam");
			break;
		}
	}
}
