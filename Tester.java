
public class Tester {
	
	public static void test1_init()
	{
		updater = new Updater();		
	}
	
	public static void test1()
	{
		geometry = new Geometry();	
		for(int i = 0; i < 3; i++){				
			geometry.getTilesList().add(new FieldTile(geometry));		//Létrehozunk 3 csempét, melyekre tornyokat lehet építeni
			geometry.getTilesList().add(new PathTile(geometry));		//Létrehozunk 3 útcsempét
		}
		geometry.getTilesList().add(new EndTile(geometry));			//Létrehozzuk a végzet hegyét
		PathGenerator pathGenerator = new PathGenerator(geometry);
		enemyGenerator = new EnemyGenerator(pathGenerator);	
		new ConstructManager(updater);	
	}
	
	public static void test2_init() {
		updater = new Updater();	
		geometry = new Geometry();
		enemyGenerator = new EnemyGenerator(pathGenerator);
		pathGenerator = new PathGenerator(geometry);
		PathTile currentTile = new PathTile(geometry);
	}
	
	public static void test2() {
		
	}
	
	public static void test3_init() {
		updater = new Updater();	
		Hobbit frodo = new Hobbit();
		updater.addEnemy(frodo);
		PathTile currentTile = new PathTile(geometry);
		PathTile nextTile = new PathTile(geometry);
		currentTile.setNextTile(nextTile);
	}
	
	public static void test3() {
		
	}
	
	public static void test4_init() {
		updater = new Updater();	
		geometry = new Geometry();
		Hobbit frodo = new Hobbit();
		updater.addEnemy(frodo);
		PathTile tile1 = new PathTile(geometry);
		PathTile tile2 = new PathTile(geometry);
		tile1.setNextTile(tile2);
		PathTile tile3 = new PathTile(geometry);
		tile2.setNextTile(tile3);
		Barricade barricade = new Barricade();
	}
	
	public static void test4() {
		
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
	
	public static void test6_init() {
		updater = new Updater();
		geometry = new Geometry();
		Hobbit frodo = new Hobbit();
		Tower tower = new Tower();
		FieldTile towerLocation = new FieldTile(geometry);
		PathTile tileInRange = new PathTile(geometry);
		MagicGem gem = new MagicGem();
		
		updater.addEnemy(frodo);
		tower.setTowerLocation(towerLocation);
	}
	
	public static void test6() {
		
	}
	
	public static void test7_init() {
		updater = new Updater();
		ConstructManager constructManager = new ConstructManager(updater);
	}
	
	public static void test7() {
		
	}
	
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
	
	public static void test9_init() {
		updater = new Updater();
		geometry = new Geometry();
		ConstructManager constructManager = new ConstructManager(updater);
		FieldTile towerLocation = new FieldTile(geometry);
	}
	
	public static void test9() {
		
	}
	
	public static void test10_init() {
		updater = new Updater();
		geometry = new Geometry();
		PathGenerator pathGenerator = new PathGenerator(geometry);
		EnemyGenerator enemyGenerator = new EnemyGenerator(pathGenerator);
		Hobbit frodo = new Hobbit();
		updater.addEnemy(frodo);
		Tower tower = new Tower();
		PathTile tileInRange = new PathTile(geometry);
		
	}
	
	public static void test10() {
		
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
			test1_init();
			test1();
			break;
		case "2":
			System.out.println(args[0]+". teszteset");
			test2_init();
			test2();
			break;
		case "3":
			System.out.println(args[0]+". teszteset");
			test3_init();
			test3();
			break;
		case "4":
			System.out.println(args[0]+". teszteset");
			test4_init();
			test4();
			break;
		case "5":
			System.out.println(args[0]+". teszteset");
			test5_init();
			test5();
			break;
		case "6":
			System.out.println(args[0]+". teszteset");
			test6_init();
			test6();
			break;
		case "7":
			System.out.println(args[0]+". teszteset");
			test7_init();
			test7();
			break;
		case "8":
			System.out.println(args[0]+". teszteset");
			test8_init();
			test8();
			break;
		case "9":
			System.out.println(args[0]+". teszteset");
			test9_init();
			test9();
			break;
		case "10":
			System.out.println(args[0]+". teszteset");
			test10_init();
			test10();
			break;
		default:
			System.out.println("Buta vagy fiam");
			break;
		}
	}
}
