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

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String command;
		//ezeknek lehet kéne egy külön init blokk, ha esetleg tesztelés közben ki szeretnénk dobálni minde objektumot
		endTile = false;
		geometry = new Geometry();
		pathGenerator = new PathGenerator(geometry);
		enemyGenerator = new EnemyGenerator(pathGenerator);
		updater = new Updater();
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
		} else if (parts[0].equals("buiild")) {
			build(parts[1], parts[2], parts[3]);
		} 
	}

	private static void build(String tileID, String type, String costsMana) {
		int target = Integer.parseInt(tileID.substring(1));
		if (!((tilesOnMap.get(target).getType().equals("FieldTile") && type.equals("tower"))) || 
				(tilesOnMap.get(target).getType().equals("PathTile") && type.equals("barricade"))) {
			System.out.println("A megadott csempére nem helyezhető el az épület.");
		} else { //ide kell még az az eset, ha nincs elég varázskövünk
			System.out.println(tileID+" csempére "+type+" épült.");
			if (costsMana.equals("1"))
				System.out.println("20 varázserőbe került."); //ki kell nyerni az árat valahonnan
		}
	}

	private static void setMana(String val) {
		int value = Integer.parseInt(val);
		updater.mana.setMana(value);
		System.out.println("A varázserő sikeresen beállítva "+value+" értékre!");
	}

	private static void setFog(String param) {
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

	private static void getStatus() {
		
		for (int i = 0; i < tilesOnMap.size(); i++) {
				System.out.println(i+1+": T" + i + ": " + tilesOnMap.get(i).getType());
		}
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
			geometry.addTile(tile, x + y * mapSizeX);
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
			geometry.addTile(tile, x + y * mapSizeX);
			System.out.println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú út csempét az " + x + ", " + y + " helyre.");
		}
	}

	private static void addFieldTile(int x, int y) {
		if (x >= mapSizeX || y >= mapSizeY)
			System.out.println("Nincs ilyen hely a pályán!");
		else {
			FieldTile tile = new FieldTile(geometry);
			tilesOnMap.add(tile);
			geometry.addTile(tile, x + y * mapSizeX);
			System.out.println("Sikeresen hozzáadtad a T" + (tilesOnMap.size()-1) + " azonosítójú terep csempét az " + x + ", " + y
					+ " helyre.");
		}
	}

	private static void createMap(int x, int y) {
		geometry.setMapSize(x*y);
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
