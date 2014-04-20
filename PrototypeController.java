import java.io.*;
import java.util.ArrayList;

//kis és nagybetűk //ok
//5. teszteset addNextTile T2 T0 -> addNextTile T2 T1 //ok
//5. teszteset build T1 Tower 0 -> build T0 Tower 0 //ok
//6. teszteset move E1 -> move E0 //ok
//x3 //WTF?
//fájlba mentés //ok
//részletes adatok, getStatus formátum


public class PrototypeController {

	private static Geometry geometry;
	private static PathGenerator pathGenerator;
	private static Updater updater;
	private static ConstructManager constructManager;
	private static EnemyGenerator enemyGenerator;
	private static int mapSizeX;
	private static int mapSizeY;
	private static boolean endTile;
	private static ArrayList<Tile> tilesOnMap = new ArrayList<Tile>();
	private static ArrayList<Construct> constructsOnMap = new ArrayList<Construct>();
	private static ArrayList<Enemy> enemiesOnMap = new ArrayList<Enemy>(); //enemy generatorból ebbe szépen bele kell majd szopkodni az ellenségeket
	private static boolean outToFile = false;
	private static PrintWriter writer = null;
	
	public static void main(String[] args) throws Exception {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String command;
			endTile = false;
			geometry = new Geometry();
			pathGenerator = new PathGenerator(geometry);
			updater = new Updater();
			enemyGenerator = new EnemyGenerator(pathGenerator, updater);
			constructManager = new ConstructManager(updater);
			updater.setConstructManager(constructManager);
			updater.setEnemyGenerator(enemyGenerator);
			updater.setGeometry(geometry);
			while (true) {
				command = br.readLine();
				commandParser(command); //meghívjuk a parancsétrelmezőt
			}
	}

	private static void commandParser(String command) throws Exception {
		String[] parts = command.split(" "); // szóközök mentén feldaraboljuk

		if (parts[0].equals("loadCommandFile")) {
			loadCommandFile(parts[1]);
		} else if (parts[0].equals("beginWriteCommands")) {
			beginWriteCommands(parts[1]);
		} else if (parts[0].equals("help")) {
			help();
		} else if (parts[0].equals("exit")) {
			exit();
		} else if (parts[0].equals("createMap")) {
			createMap(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		} else if (parts[0].equals("addFieldTile")) {
			addFieldTile(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		} else if (parts[0].equals("addPathTile")) {
			addPathTile(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		} else if (parts[0].equals("addEndTile")) {
			addEndTile(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
		} else if (parts[0].equals("getStatus")) {
			getStatus();
		} else if (parts[0].equals("addNextTile")) {
			addNextTile(parts[1], parts[2]);
		} else if (parts[0].equals("setStartTile")) {
			setStartTile (parts[1]);
		} else if (parts[0].equals("setFog")) {
			setFog(parts[1]);
		} else if (parts[0].equals("setMana")) {
			setMana(parts[1]);
		} else if (parts[0].equals("build")) {
			build(parts[1], parts[2], parts[3]);
		} else if (parts[0].equals("upgrade")) {
			upgrade(parts[1], parts[2], parts[3]);
		} else if (parts[0].equals("shoot")) {
			shoot(parts[1], parts[2]);
		} else if (parts[0].equals("generatePaths")) {
			generatePaths();
		} else if (parts[0].equals("setRemainingEnemies")) {
			setRemainingEnemies(parts[1]);
		} else if (parts[0].equals("move")) {
			move(parts[1]);
		} else if (parts[0].equals("simulate")) {
			simulate(parts[1]);
		} else if (parts[0].equals("addEnemy")) {
			addEnemy(parts[1], parts[2]);
		}
	}
	
	private static void println(String output) {
		outToFile = false;
		if (!outToFile)	System.out.println(output);
		else {
			writer.println(output);
		}
	}
	
	private static void print(String output) {
		outToFile = false;
		if (!outToFile)	System.out.print(output);
		else {
			writer.print(output);
		}
	}
	
	private static void println() {
		outToFile = false;
		if (!outToFile)	System.out.println();
		else {
			writer.println();
		}
	}

	private static void addEnemy(String tileID, String type) {
		int target = Integer.parseInt(tileID.substring(1));
		type = type.toLowerCase();
		if (! tilesOnMap.get(target).getType().equals("PathTile")) {
			println(tileID+" nem útcsempe!");
		} else if (type.equals("elf") || type.equals("dwarf") || type.equals("human") || type.equals("hobbit")) {
			Enemy enemy = null;
			if (type.equals("elf")) enemy = new Elf(enemyGenerator);
			else if (type.equals("human")) enemy = new Human(enemyGenerator);
			else if (type.equals("dwarf")) enemy = new Dwarf(enemyGenerator);
			else if (type.equals("hobbit")) enemy = new Hobbit(enemyGenerator);
			tilesOnMap.get(target).addEnemy(enemy);
			enemy.setTile(tilesOnMap.get(target));
			enemiesOnMap.add(enemy);
			println("Sikeresen létrehoztad az E"+ (enemiesOnMap.size() - 1) +" azonosítójú \""+type+"\" típusú ellenséget a "+tileID+" csempén.");
		} else println("Nincs ilyen típusú ellenség!");
	}

	private static void simulate(String count) { //idővel ez is el fog készülni
		for (int i=0; i < Integer.parseInt(count); i++) {
			updater.update();
		}
		println("A szimuláció véget ért. "+count+" ciklus futott le!");
		
		/*
		1.	A szimuláció véget ért. <count> ciklus futott le!
		2.	A szimuláció véget ért. <count> ciklus futott le!
		−	A(z) 1. ciklusban generálódott az E1 azonosítójú ellenség. 
		−	A(z) 5. ciklusban generálódott az E2 azonosítójú ellenség.
		
		3.	A szimuláció véget ért. <count> ciklus futott le!
		−	A(z) 3. ciklusban lépett az E5 azonosítójú ellenség a T1 celláról a T2-re. 
		−	A 3. ciklusban az E5 azonosítójú ellenség a végzet hegyére lépett, vesztettél!
		
		4.	A szimuláció véget ért. <count> ciklus futott le!
		−	A(z) 1. ciklusben generálódott az E4 azonosítójú ellenség.
		−	A(z) 3. ciklusban lőtt a T1 azonosítójú torony az E4 ellenségre, levéve tőle 15 életerőt.
		−	A(z) 3. ciklusban lőtt a T2 azonosítójú torony az E4 ellenségre, mely meghalt, így a varázserőd 6-tal nőtt.
		−	Az utolsó ellenség is meghalt a 2. ciklusban, nyertél!
		 */
	}

	private static void move(String enemyID) {
		int target = Integer.parseInt(enemyID.substring(1));	
		Enemy enemy = enemiesOnMap.get(target);
		//int health = enemy.health;
		int delay = enemy.moveDelay;
		Tile current = enemy.currentTile;
		if (((PathTile) enemy.currentTile).getNextTiles().size()==0) println(enemyID+" nem tudott lépni");
		else {
			enemy.move();
			if (delay>1) println(enemyID+" moveDelay értéke egyel csökkent.");
			else {
				int ID, ID2;
				for (ID=0; ID<tilesOnMap.size() && tilesOnMap.get(ID)==current; ID++);
				for (ID2=0; ID2<tilesOnMap.size() && tilesOnMap.get(ID2)==enemy.currentTile; ID2++);
				print(enemyID+" lépett T"+ID+"-ről T"+ID2+"-re. ");
				if (enemy.currentTile.getConstruct()!=null &&
						enemy.currentTile.getConstruct().getType().equals("Barricade")) {
					print("T"+ID2+"-n akadály van, lelassult. ");
				}
				println("Legközelebb "+enemy.moveDelay+" szimulációs ciklus után lépne.");
			}
		}
	}

	private static void setRemainingEnemies(String value) {
		enemyGenerator.setRemainingEnemies(Integer.parseInt(value));
		println(value+" számú ellenség vár még a legenerálásra!");
	}

	private static void generatePaths() { //ez sincs kész, kellenek még metódusok a pathGenerator osztályba
		boolean sikeres = false;
		if (sikeres) {
			print("Az útvonalak létrehozása sikeres. Kezdőcsempeként használható útcsempék a");
			for (int i = 0; i<pathGenerator.pathStarts.size(); i++) {
				print(" T"+i);
			}
			println();
		} else println("Nem sikerült létrehozni az útvonalakat.");
	}

	private static void shoot(String towerID, String critical) { //a tornyot hogy utasítjuk, hogy milyen lőjön? //hogy tudjuk meg, hogy talált-e ellenséget?
		int target = Integer.parseInt(towerID.substring(1));
		if (target >= constructsOnMap.size() || target < 0 || !constructsOnMap.get(target).getType().equals("Tower")) {
			println("A megadott torony nem létezik.");
			return;
		}
		Tower tower = (Tower) constructsOnMap.get(target);
		tower.shoot();
		//ide meg még végtelen sok visszajelzés kell
	}

	private static void upgrade(String constructID, String gemType, String costsMana) { //ki tudja, hogy melyik kő mibe mehet?
		int target = Integer.parseInt(constructID.substring(1));
		if (target >= constructsOnMap.size() || target < 0) {
			println("A megadott épület nem létezik.");
			return;
		}
		Construct targetConstruct = constructsOnMap.get(target);
		MagicGem gem = new MagicGem(gemType);
		if ((costsMana.equals("1") && true) || costsMana.equals("0")) { //kéne tudni, hogy honnan szopunk árat
			targetConstruct.setMagicGem(gem);
			print(constructID+" épületbe "+gemType+" varázskövet tettél. ");
			if (costsMana.equals("1")) println(constructManager.costs.get(gemType) + " varázserőbe került.");
			else println();
		} else println("incs elég varázserőd "+gemType+" vásárlására!");
	}

	private static void build(String tileID, String type, String costsMana) {
		int target = Integer.parseInt(tileID.substring(1));
		type = type.toLowerCase();
		if (!(tilesOnMap.get(target).getType().equals("FieldTile") && type.equals("tower")) && 
				!(tilesOnMap.get(target).getType().equals("PathTile") && type.equals("barricade"))) {
			println("A megadott csempére nem helyezhető el az épület.");
		} else {
			if ((costsMana.equals("1") && updater.mana.hasEnough(constructManager.costs.get(type))) || costsMana.equals("0")) {
				if (type.equals("tower")) {
					Tower tower = new Tower((FieldTile) tilesOnMap.get(target));
					tilesOnMap.get(target).addConstruct(tower);
					constructsOnMap.add(tower);
				} else if (type.equals("barricade")) {
					Barricade barricade = new Barricade();
					tilesOnMap.get(target).addConstruct(barricade);
					constructsOnMap.add(barricade);
				}
				updater.constructs = constructsOnMap;
				print(tileID+" csempére "+type+" épült. ");
				if (costsMana.equals("1")) println(constructManager.costs.get(type)+" varázserőbe került.");
				else println();
			}	
		}
	}

	private static void setMana(String val) {
		int value = Integer.parseInt(val);
		updater.mana.setMana(value);
		println("A varázserő sikeresen beállítva "+value+" értékre!");
	}

	private static void setFog(String param) {
		if (param.equals("1")) {
			updater.fogDown();
			println("Köd bekapcsolva. Az összes torony hatótávja 0.7-szeresére csökkent.");
		} else {
			updater.fogUp();
			println("Köd kikapcsolva. Az összes torony hatótávja eredeti értékre állt vissza.");
		}
	}

	private static void setStartTile(String ID) {
		int src = Integer.parseInt(ID.substring(1));
		if (tilesOnMap.get(src).getType() != "PathTile")
			println("A megadott csempe nem útcsempe!");
		else {
			println("Sikeresen beállítottad a "+ID+" azonosítójú útcsempét kezdőcsempének.");
			pathGenerator.add(((PathTile) tilesOnMap.get(src)));
		}
		
	}

	private static void addNextTile(String srcID, String destID) {
		println("Hozzáadtad a "+srcID+" azonosítójú útcsempéből elérhető útcsempékhez a "+destID+" csempét.");
		int src = Integer.parseInt(srcID.substring(1));
		int dest = Integer.parseInt(destID.substring(1));
		PathTile srcTile = (PathTile) tilesOnMap.get(src);
		Tile destTile = tilesOnMap.get(dest);
		srcTile.setNextTile(destTile);
	}

	private static void getStatus() { //40 szűznek kell még leszopnia, hogy ezt befejezzem - WIP --csempék megvannak
		println("Pálya: "+mapSizeX+"x"+mapSizeY);
		
		println("Csempék:");
		for (int i = 0; i < tilesOnMap.size(); i++) {
				print("\tT" + i + " " + tilesOnMap.get(i).getType()); //csempe típusa
				
				//pozíció keresése és kiírása
				int idx = -1;
				int idy = -1;
				for (int x = 0; x < mapSizeX; x++) {
					for (int y = 0; y < mapSizeY; y++) {
						if (geometry.tiles[x][y]==tilesOnMap.get(i)) {
							idx = x;
							idy = y;
						}
					}
				}
				print("\tx: "+idx+" y: "+idy);
				
				//út csempe esetén a belőle elérhető csempék kiírása
				if (tilesOnMap.get(i).getType().equals("PathTile")) {
					print("\tSzomszéd Csempék:");
					for (int n = 0; n < ((PathTile) tilesOnMap.get(i)).nextTiles.size(); n++) { //végigmegy az összes elérhető csempén
						Tile next = ((PathTile) tilesOnMap.get(i)).nextTiles.get(n); //az elérhető csempe
						print(" T"+tilesOnMap.indexOf(next)); //a csempe indexének megkeresése és kiírása
					}
					
					//kiinduló csempe?
					print("\tStart: ");
					if ( pathGenerator.pathStarts.contains( ((PathTile) tilesOnMap.get(i)) ) ) print("true");
					else print("false");
				}
				
				println();
		}
		
		println("Épületek:");
		for (int i = 0; i < constructsOnMap.size(); i++) {
			println("\tC" + i + " " + constructsOnMap.get(i).getType());
		}
		
		println();
		println("Ellenségek:");
		for (int i = 0; i < enemiesOnMap.size(); i++) {
			println("\tE" + i + " " + enemiesOnMap.get(i).getType());
		}
		println();
		println("Varázserő: "+updater.mana.getMana());
	}

	private static void addEndTile(int x, int y) {
		if (x >= mapSizeX && y >= mapSizeY)
			println("Nincs ilyen hely a pályán!");
		else if (endTile)
			println("Már van egy végzet hegye csempe, T4 azonosítóval a 2, 3 helyen.");
		else {
			EndTile tile = new EndTile(geometry);
			tilesOnMap.add(tile);
			geometry.addTile(tile, x, y);
			println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú végzet hegye csempét az " + x + ", " + y
					+ " helyre.");
			endTile = true;
		}
	}

	private static void addPathTile(int x, int y) {
		if (x >= mapSizeX || y >= mapSizeY)
			println("Nincs ilyen hely a pályán!");
		else {
			PathTile tile = new PathTile(geometry);
			tilesOnMap.add(tile);
			geometry.addTile(tile, x, y);
			println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú út csempét az " + x + ", " + y + " helyre.");
		}
	}

	private static void addFieldTile(int x, int y) {
		if (x >= mapSizeX || y >= mapSizeY)
			println("Nincs ilyen hely a pályán!");
		else {
			FieldTile tile = new FieldTile(geometry);
			tilesOnMap.add(tile);
			geometry.addTile(tile, x, y);
			println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú terep csempét az " + x + ", " + y
					+ " helyre.");
		}
	}

	private static void createMap(int x, int y) {
		geometry.setMapSize(x, y);
		mapSizeX = x;
		mapSizeY = y;
		println("Sikeresen létrejött az " + x + ", " + y + " méretű pálya!");
	}

	private static void help() throws Exception {
		FileInputStream in = new FileInputStream("help.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String outString = null;
		while ((outString = br.readLine()) != null) {
			println(outString);
		}
		in.close();
	}

	private static void exit() throws Exception {
		if (outToFile) writer.close();
		System.exit(0);
	}

	private static void loadCommandFile(String fileName) throws Exception {
		outToFile = true;
		String temp[] = fileName.split(".txt"); 
		writer = new PrintWriter(temp[0]+"_output.txt");
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String outString = null;
		while ((outString = br.readLine()) != null) {
			commandParser(outString);
		}
		in.close();
	}

	private static void beginWriteCommands(String fileName) throws Exception {
		println("beginWriteCommands <" + fileName + ">");
		PrintWriter writer = new PrintWriter(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = br.readLine();
		while (!command.equals("endWriteCommands")) {
			writer.write(command + "\n");
			command = br.readLine();
		}
		writer.close();
		println("Olvasás vége");
	}
}
