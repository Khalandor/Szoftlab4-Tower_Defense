import java.io.*;
import java.util.ArrayList;

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
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command;
		//ezeknek lehet kéne egy külön init blokk, ha esetleg tesztelés közben ki szeretnénk dobálni minden objektumot
		endTile = false;
		geometry = new Geometry();
		pathGenerator = new PathGenerator(geometry);
		updater = new Updater();
		enemyGenerator = new EnemyGenerator(pathGenerator, updater);
		constructManager = new ConstructManager(updater);
		updater.setConstructManager(constructManager);
		updater.setEnemyGenerator(enemyGenerator);
		updater.setGeometry(geometry);
		while (true) { // igen, tudom, hogy undorító
			command = br.readLine();
			commandParser(command); // meghívjuk a parancsétrelmezőt
		}
	}

	private static void commandParser(String command) throws Exception {
		String[] parts = command.split(" "); // szóközök mentén feldaraboljuk

		// baszottul undorító if halmaz, nincs jobb ötletem, mivel switch-ben
		// nem tudunk stringet összehasonlítani
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

	private static void addEnemy(String tileID, String type) {
		int target = Integer.parseInt(tileID.substring(1));
		if (! tilesOnMap.get(target).getType().equals("PathTile")) {
			System.out.println(tileID+" nem útcsempe!");
		} else if (type.equals("Elf") || type.equals("Dwarf") || type.equals("Hobbit") || type.equals("Hobbit")) {
			Enemy enemy = null;
			if (type.equals("Elf")) enemy = new Elf();
			else if (type.equals("Human")) enemy = new Human();
			else if (type.equals("Dwarf")) enemy = new Dwarf();
			else if (type.equals("Hobbit")) enemy = new Hobbit();
			tilesOnMap.get(target).addEnemy(enemy);
			enemiesOnMap.add(enemy);
			System.out.println("Sikeresen létrehoztad az E"+enemiesOnMap.size()+" azonosítójú \""+type+"\" típusú ellenséget a "+tileID+" csempén.");
		} else System.out.println("Nincs ilyen típusú ellenség!");
	}

	private static void simulate(String count) {
		for (int i=0; i < Integer.parseInt(count); i++) { //idővel ez is el fog készülni
			//updater.update();
		}
		System.out.println("A szimuláció véget ért. "+count+" ciklus futott le!");
		
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
		int health = enemy.health;
		int delay = enemy.moveDelay;
		Tile current = enemy.currentTile;
		enemy.move();
		if (((PathTile) enemy.currentTile).getNextTiles().size()==0) System.out.println(enemyID+" nem tudott lépni");
		else {
			if (delay>0) System.out.println(enemyID+" moveDelay értéke egyel csökkent.");
			else {
				int ID, ID2;
				for (ID=0; ID<tilesOnMap.size() || tilesOnMap.get(ID)==current; ID++);
				for (ID2=0; ID2<tilesOnMap.size() || tilesOnMap.get(ID2)==enemy.currentTile; ID2++);
				System.out.print(enemyID+" lépett T"+ID+"-ről T"+ID2+"-re. ");
				if (enemy.currentTile.getConstruct()!=null &&
						enemy.currentTile.getConstruct().getType().equals("barricade")) {
					System.out.print("T"+ID2+"-n akadály van, lelassult. ");
				}
				System.out.println("Legközelebb +"+enemy.moveDelay+" szimulációs ciklus után lépne.");
			}
		}
	}

	private static void setRemainingEnemies(String value) {
		enemyGenerator.setRemainingEnemies(Integer.parseInt(value));
		System.out.println(value+" számú ellenség vár még a legenerálásra!");
	}

	private static void generatePaths() { //ez sincs kész, kellenek még metódusok a pathGenerator osztályba
		boolean sikeres = false;
		if (sikeres) {
			System.out.print("Az útvonalak létrehozása sikeres. Kezdőcsempeként használható útcsempék a");
			for (int i = 0; i<pathGenerator.pathStarts.size(); i++) {
				System.out.print(" T"+i);
			}
			System.out.println();
		} else System.out.println("Nem sikerült létrehozni az útvonalakat.");
	}

	private static void shoot(String towerID, String critical) { //a tornyot hogy utasítjuk, hogy milyen lőjön? //hogy tudjuk meg, hogy talált-e ellenséget?
		int target = Integer.parseInt(towerID.substring(1));
		if (target >= constructsOnMap.size() || target < 0 || !constructsOnMap.get(target).getType().equals("tower")) {
			System.out.println("A megadott torony nem létezik.");
			return;
		}
		Tower tower = (Tower) constructsOnMap.get(target);
		tower.shoot();
		//ide meg még végtelen sok visszajelzés kell
	}

	private static void upgrade(String constructID, String gemType, String costsMana) { //ki tudja, hogy melyik kő mibe mehet?
		int target = Integer.parseInt(constructID.substring(1));
		if (target >= constructsOnMap.size() || target < 0) {
			System.out.println("A megadott épület nem létezik.");
			return;
		}
		Construct targetConstruct = constructsOnMap.get(target);
		MagicGem gem = new MagicGem(gemType);
		if ((costsMana.equals("1") && true) || costsMana.equals("0")) { //kéne tudni, hogy honnan szopunk árat
			targetConstruct.setMagicGem(gem);
			System.out.print(constructID+" épületbe "+gemType+" varázskövet tettél.. ");
			if (costsMana.equals("1")) System.out.println("-Faszomtudjamennyi- varázserőbe került.");
			else System.out.println();
		} else System.out.println("incs elég varázserőd "+gemType+" vásárlására!");
	}

	private static void build(String tileID, String type, String costsMana) {
		int target = Integer.parseInt(tileID.substring(1));
		if (!((tilesOnMap.get(target).getType().equals("FieldTile") && type.equals("tower"))) || 
				(tilesOnMap.get(target).getType().equals("PathTile") && type.equals("barricade"))) {
			System.out.println("A megadott csempére nem helyezhető el az épület.");
		} else {
			if ((costsMana.equals("1") && updater.mana.hasEnough(constructManager.costs.get(type))) || costsMana.equals("0")) { //ez kicsit kétséges, hogy működik-e
				if (type.equals("tower")) {
					Tower tower = new Tower();
					tilesOnMap.get(target).addConstruct(tower);
					constructsOnMap.add(tower);
				} else if (type.equals("barricade")) {
					Barricade barricade = new Barricade();
					tilesOnMap.get(target).addConstruct(barricade);
					constructsOnMap.add(barricade);
				}
				System.out.print(tileID+" csempére "+type+" épült. ");
				if (costsMana.equals("1")) System.out.println(constructManager.costs.get(type)+" varázserőbe került.");
				else System.out.println();
			}	
		}
	}

	private static void setMana(String val) {
		int value = Integer.parseInt(val);
		updater.mana.setMana(value);
		System.out.println("A varázserő sikeresen beállítva "+value+" értékre!");
	}

	private static void setFog(String param) { //tisztázni kell, hogy mit takar a modifier
		if (param.equals("1")) {
			System.out.println("Köd bekapcsolva. Az összes torony hatótávja 0.7-szeresére csökkent.");
			ArrayList<Construct> constructs = updater.getConstructs();
			for (Construct item : constructs) {
				if (item.getType() == "tower") ((Tower) item).setRangeModifier(7);
			}
		} else 
			System.out.println("Köd kikapcsolva. Az összes torony hatótávja eredeti értékre állt vissza.");
	}

	private static void setStartTile(String ID) {
		int src = Integer.parseInt(ID.substring(1));
		if (tilesOnMap.get(src).getType() != "PathTile")
			System.out.println("A megadott csempe nem útcsempe!");
		else {
			System.out.println("Sikeresen beállítottad a "+ID+" azonosítójú útcsempét kezdőcsempének.");
			pathGenerator.add(((PathTile) tilesOnMap.get(src)));
		}
		
	}

	private static void addNextTile(String srcID, String destID) {
		System.out.println("Hozzáadtad a "+srcID+" azonosítójú útcsempéből elérhető útcsempékhez a "+destID+" csempét.");
		int src = Integer.parseInt(srcID.substring(1));
		int dest = Integer.parseInt(destID.substring(1));
		PathTile srcTile = (PathTile) tilesOnMap.get(src);
		PathTile destTile = (PathTile) tilesOnMap.get(dest);
		srcTile.setNextTile(destTile);
	}

	private static void getStatus() { //40 szűznek kell még leszopnia, hogy ezt befejezzem
		System.out.println("Pálya: "+mapSizeX+"x"+mapSizeY);
		
		System.out.println();
		System.out.println("Csempék:");
		for (int i = 0; i < tilesOnMap.size(); i++) {
				System.out.println(i+1+": T" + i + ": " + tilesOnMap.get(i).getType());
		}
		
		System.out.println();
		System.out.println("Épületek");
		for (int i = 0; i < constructsOnMap.size(); i++) {
			System.out.println(i+1+": C" + i + ": " + constructsOnMap.get(i).getType());
		}
		
		System.out.println();
		System.out.println("Ellenségek:");
		for (int i = 0; i < enemiesOnMap.size(); i++) {
			System.out.println(i+1+": C" + i + ": " + enemiesOnMap.get(i).getType());
		}
		System.out.println();
		System.out.println("Varázserő: "+updater.mana.getMana());
	}

	private static void addEndTile(int x, int y) {
		if (x >= mapSizeX && y >= mapSizeY)
			System.out.println("Nincs ilyen hely a pályán!");
		else if (endTile)
			System.out.println("Már van egy végzet hegye csempe, T4 azonosítóval a 2, 3 helyen.");
		else {
			EndTile tile = new EndTile(geometry);
			tilesOnMap.add(tile);
			geometry.addTile(tile, x, y);
			System.out.println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú végzet hegye csempét az " + x + ", " + y
					+ " helyre.");
			endTile = true;
		}
	}

	private static void addPathTile(int x, int y) {
		if (x >= mapSizeX || y >= mapSizeY)
			System.out.println("Nincs ilyen hely a pályán!");
		else {
			PathTile tile = new PathTile(geometry);
			tilesOnMap.add(tile);
			geometry.addTile(tile, x, y);
			System.out.println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú út csempét az " + x + ", " + y + " helyre.");
		}
	}

	private static void addFieldTile(int x, int y) {
		if (x >= mapSizeX || y >= mapSizeY)
			System.out.println("Nincs ilyen hely a pályán!");
		else {
			FieldTile tile = new FieldTile(geometry);
			tilesOnMap.add(tile);
			geometry.addTile(tile, x, y);
			System.out.println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú terep csempét az " + x + ", " + y
					+ " helyre.");
		}
	}

	private static void createMap(int x, int y) {
		//TODO: ez nem lesz így jó, csak a területet kapja meg a geometry, ebből nem tudja melyik oldal mekkora
		geometry.setMapSize(x, y);
		mapSizeX = x;
		mapSizeY = y;
		System.out.println("Sikeresen létrejött az " + x + ", " + y + " méretű pálya!");
	}

	private static void help() throws Exception {
		FileInputStream in = new FileInputStream("help.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String outString = null;
		while ((outString = br.readLine()) != null) {
			System.out.println(outString);
		}
	}

	private static void exit() {
		System.out.println("Exit function");
		System.exit(0);
	}

	private static void loadCommandFile(String fileName) throws Exception {
		FileInputStream in = new FileInputStream(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String outString = null;
		while ((outString = br.readLine()) != null) {
			// System.out.println(outString);
			commandParser(outString);
		}
	}

	private static void beginWriteCommands(String fileName) throws Exception {
		System.out.println("beginWriteCommands <" + fileName + ">");
		PrintWriter writer = new PrintWriter(fileName);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command = br.readLine();
		while (!command.equals("endWriteCommands")) {
			writer.write(command + "\n");
			command = br.readLine();
		}
		writer.close();
		System.out.println("Olvasás vége");
	}
}
