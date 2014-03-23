
public class Tester {
	
	/**
	 * else teszt eset inicializalasa
	 */
	public static void test1_init()
	{
		System.out.println("Inicializálás:");
		updater = new Updater();	
	}
	
	/**
	 * elso teszt eset
	 * Jatak inditasa.
	 */
	public static void test1()
	{
		System.out.println();
		System.out.println("Teszteset:");
		updater.init();		
	}

	/**
	 * masodik teszt eset inicializalasa
	 */
	public static void test2_init() {
		System.out.println("Inicializálás:");
		updater = new Updater();	
		geometry = new Geometry();
		
		pathGenerator = new PathGenerator(geometry);
		enemyGenerator = new EnemyGenerator(pathGenerator);
		PathTile currentTile = new PathTile(geometry);
		pathGenerator.add(currentTile);
	}
	
	/**
	 * masodik teszt eset
	 * Letrehozunk egy ellenseget.
	 */
	public static void test2() {
		System.out.println();
		System.out.println("Teszteset:");
		Enemy enemy = enemyGenerator.generateEnemies();
		updater.addEnemy(enemy);
	}
	
	/**
	 * harmadik teszt eset inicializalasa
	 */
	public static void test3_init() {
		System.out.println("Inicializálás:");
		updater = new Updater();	
		Hobbit frodo = new Hobbit();
		updater.addEnemy(frodo);
		PathTile currentTile = new PathTile(geometry);
		EndTile nextTile = new EndTile(geometry);
		currentTile.setNextTile(nextTile);
		frodo.setTile(currentTile);
	}
	
	/**
	 * harmadik teszt eset
	 * Egy ellenseg lep, majd mivel a Vegzet hegyere lepett, vesztunk.
	 */
	public static void test3() {
		System.out.println();
		System.out.println("Teszteset:");
		boolean reachedEnd = (updater.enemies.get(0)).move();
		updater.gameOver(reachedEnd);
	}
	
	
	/**
	 * negyedik teszt eset inicializalasa
	 */
	public static void test4_init() {
		System.out.println("Inicializálás:");
		updater = new Updater();	
		geometry = new Geometry();
		
		PathTile tile1 = new PathTile(geometry);
		PathTile tile2 = new PathTile(geometry);
		PathTile tile3 = new PathTile(geometry);
		tile1.setNextTile(tile2);
		tile2.setNextTile(tile3);
		
		Barricade barricade = new Barricade();
		tile2.addConstruct(barricade);
		
		Hobbit frodo = new Hobbit();
		updater.addEnemy(frodo);
		frodo.setTile(tile1);
		

	}
	
	/**
	 * negyedik teszt eset
	 * Egy ellenseg akadalyra lep, majd kesleltetve tovabblep.
	 */
	public static void test4() {
		System.out.println();
		System.out.println("Teszteset:");
		(updater.enemies.get(0)).move();
		(updater.enemies.get(0)).move();
		(updater.enemies.get(0)).move();
	}
	
	/**
	 * otodik teszt eset inicializalasa
	 */
	public static void test5_init()
	{	
		System.out.println("Inicializálás:");
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
		geometry.getTiles().add(start);
		geometry.getTiles().add(next);
		start.setNextTile(next);
		
		// ellenség és torony felhelyezése a csempére
		Legolas.currentTile = start;
		towerTile.constructOnTile = tower1;
		tower1.setTowerLocation(towerTile);
	}
	
	/**
	 * otodik teszt eset
	 * Egy ellenseg lep, majd ralo egy torony, es meghal.
	 */
	public static void test5()
	{
		System.out.println();
		System.out.println("Teszteset:");
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
	 * hatodik teszt eset inicializalasa
	 */
	public static void test6_init() {
		System.out.println("Inicializálás:");
		updater = new Updater();
		geometry = new Geometry();
		FieldTile towerLocation = new FieldTile(geometry);
		
		
		//Torony letrehozasa es felvetele a constructok koze
		Tower tower = new Tower();
		updater.getConstructs().add(tower);
		tower.setTowerLocation(towerLocation);
		
		
		//Varazsko letrehozasa
		MagicGem gem = new MagicGem();
		//Ellenseg letrehozasa
		Hobbit frodo = new Hobbit();
		frodo.health = 50;
		updater.getEnemies().add(frodo);
		 
		 //Csempe letrehozasa 
		 PathTile tile = new PathTile(geometry);
		 geometry.getTiles().add(tile);
		 
		 frodo.currentTile = tile;
		 tile.addEnemy(frodo);
		 tower.setMagicGem(gem);
		
	}
	
	/**
	 * hatodik teszt eset
	 * Loves varazskovel ellatott toronnyal.
	 */
	public static void test6() {
		System.out.println();
		System.out.println("Teszteset:");
		((Tower) updater.constructs.get(0)).shoot();
		updater.enemies.get(0).getHealth();
	}
	
	/**
	 * hetedik teszt eset inicializalasa
	 */
	public static void test7_init() {
		System.out.println("Inicializálás:");
		updater = new Updater();
		updater.getMana().setMana(10);
		constructManager = new ConstructManager(updater);
		constructManager.setMana(updater.getMana());
	}
	
	/**
	 * hetedik teszt eset
	 * A jatekos  epiteni akar valamit, de nincs ra eleg varazsero.
	 */
	public static void test7() {
		System.out.println();
		System.out.println("Teszteset:");
		constructManager.build("Tower", new Tile());	
	}
	
	
	/**
	 * nyolcadik teszt eset inicializalasa
	 */
	public static void test8_init()
	{
		System.out.println("Inicializálás:");
		updater = new Updater();
		geometry = new Geometry();
		constructManager = new ConstructManager(updater);
		updater.getMana().setMana(100);
		updater.setConstructManager(constructManager);
		constructManager.setMana(updater.getMana());
	}
	
	/**
	 * nyolcadik teszt eset
	 * A jatekos  epit egy akadalyt.
	 */
	public static void test8()
	{
		PathTile location = new PathTile(geometry);
		System.out.println();
		System.out.println("Teszteset:");
		constructManager.build("Barricade", location);
	}
	
	
	/**
	 * kilencedik teszt eset inicializalasa
	 */
	public static void test9_init() {
		System.out.println("Inicializálás:");
		updater = new Updater();
		geometry = new Geometry();
		Mana mana = new Mana();
		mana.setMana(1000);
		constructManager = new ConstructManager(updater);
		constructManager.setMana(mana);		
	}
	/**
	 * kilencedik teszt eset
	 * A jatekos  epit egy tornyot, majd vasarol bele egy (hatotav novelo) varazskovet.
	 */
	public static void test9() {
		FieldTile towerLocation = new FieldTile(geometry);
		System.out.println();
		System.out.println("Teszteset:");
		constructManager.build("Tower", towerLocation);
		constructManager.upgrade("Range", updater.getConstructs().get(updater.getConstructs().size()-1));
	}
	
	
	/**
	 * tizedik teszt eset inicializalasa
	 */
	public static void test10_init() {
		System.out.println("Inicializálás:");
		updater = new Updater();
		geometry = new Geometry();
		PathGenerator pathGenerator = new PathGenerator(geometry);
		updater.enemyGenerator = new EnemyGenerator(pathGenerator);
		Hobbit frodo = new Hobbit();
		frodo.health = 0;
		frodo.manaValue = 30;
		updater.addEnemy(frodo);
		//Tower tower = new Tower();
		PathTile tileInRange = new PathTile(geometry);
		tileInRange.addEnemy(frodo);
		frodo.currentTile = tileInRange;
		
	}
	
	
	/**
	 * tizedik teszt eset
	 * Egy torony lo egy ellensegre, aki meghal, majd mivel ez volt az utolso ellenseg, gyozunk.
	 */
	public static void test10() {	
		System.out.println();
		System.out.println("Teszteset. A teszteset loves utan kezdodik.");
		Hobbit Frodo = (Hobbit) updater.enemies.get(0);
		// if nélkül is ugyanúgy lefut, de így talán érthetőbb
		if (Frodo.getHealth() <= 0 )
		{
			int manaValue = Frodo.getManaValue();
			PathTile FrodoTile = (PathTile)Frodo.getTile();  //Castolhatunk, mert az ellenség biztos PathTile-on áll (ha a Végzet hegyén állna, már vége lenne a játéknak).
			// itt egy private method törli az updater enemies listájából a halott ellenséget
			updater.enemies.clear();
			FrodoTile.removeEnemy(Frodo);
			updater.getMana().increase(manaValue);
		}
		
		// ha minden ellenség a pályára került, és nincs a pályán több ellenség, akkor nyert a játékos
		boolean lastWave = updater.enemyGenerator.isLastEnemyGenerated();
		lastWave = true; //ebben a tesztben nincs több ellenség.
		if (lastWave)
		{
			int numOfEnemies = updater.getNumOfEnemies();
			if (numOfEnemies == 0)
				updater.gameOver(true);	
		}	
	}

	
	static Updater updater;
	static Geometry geometry;
	static ConstructManager constructManager;
	static PathGenerator pathGenerator;
	static EnemyGenerator enemyGenerator;

	/**
	 * A teszt esetek elinditasaert felelos. Parancssori argumentumkent megkapott
	 * ertek szerinti teszt eset fog lefutni
	 * @param args parancssori argomentumok
	 */
	public static void main(String[] args)
	{	
		if (args.length == 0 || !args[0].matches("[0-9].*"))
		{
			System.out.println("Adja meg parameterkent a teszteset szamat! (1-10)");
			return;
		}
		
		int teszt = Integer.parseInt(args[0]);
		switch(teszt) {
		case 1:
			System.out.println(args[0]+". teszteset");
			test1_init();
			test1();
			break;
		case 2:
			System.out.println(args[0]+". teszteset");
			test2_init();
			test2();
			break;
		case 3:
			System.out.println(args[0]+". teszteset");
			test3_init();
			test3();
			break;
		case 4:
			System.out.println(args[0]+". teszteset");
			test4_init();
			test4();
			break;
		case 5:
			System.out.println(args[0]+". teszteset");
			test5_init();
			test5();
			break;
		case 6:
			System.out.println(args[0]+". teszteset");
			test6_init();
			test6();
			break;
		case 7:
			System.out.println(args[0]+". teszteset");
			test7_init();
			test7();
			break;
		case 8:
			System.out.println(args[0]+". teszteset");
			test8_init();
			test8();
			break;
		case 9:
			System.out.println(args[0]+". teszteset");
			test9_init();
			test9();
			break;
		case 10:
			System.out.println(args[0]+". teszteset");
			test10_init();
			test10();
			break;
		default:
			System.out.println("Adja meg parameterkent a teszteset szamat! (1-10)");
			break;
		}
		
	}
}
