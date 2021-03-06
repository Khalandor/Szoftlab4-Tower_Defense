package game;

import java.util.ArrayList;

public class Geometry {
	private Tile[][] tiles;
	private int size_x;
	private int size_y;

	/**
	 * A Geometry osztály konstruktora.
	 * Létrehozza a csempéket.
	 */
	public Geometry() {
		size_x = 20;
		size_y = 20;
		tiles = new Tile[size_x][size_y];
		for (int x = 0; x < size_x; x++)
			for (int y = 0; y < size_y; y++)
				tiles[x][y] = new FieldTile(this);
		createMap1();

	}

	private void createMap1() {
		tiles[10][0] = new EndTile(this);
		for (int y = 1; y <= 4; y++)
			tiles[10][y] = new PathTile(this);

		for (int x = 11; x <= 15; x++)
			tiles[x][4] = new PathTile(this);
		for (int y = 5; y <= 12; y++)
			tiles[15][y] = new PathTile(this);
		for (int y = 12; y <= 14; y++)
			tiles[14][y] = new PathTile(this);
		tiles[13][14] = new PathTile(this);
		for (int y = 14; y <=16; y++)
			tiles[12][y] = new PathTile(this);
		tiles[11][16] = new PathTile(this);
		for (int y = 13; y <=19; y++)
			tiles[10][y] = new PathTile(this);
		for (int x = 6; x <= 9; x++)
			tiles[x][13] = new PathTile(this);
		tiles[6][14] = new PathTile(this);
		for (int y = 14; y <= 16; y++)
			tiles[5][y] = new PathTile(this);
		tiles[4][16] = new PathTile(this);
		for (int y = 16; y <= 19; y++)
			tiles[3][y] = new PathTile(this);
		for (int y = 4; y <= 13; y++)
			tiles[7][y] = new PathTile(this);

		for (int x = 7; x <= 8; x++)
			tiles[x][3] = new PathTile(this);
		for (int x = 8; x <= 9; x++)
			tiles[x][2] = new PathTile(this);

		// Hurok
		tiles[6][3] = new PathTile(this);
		tiles[6][2] = new PathTile(this);
		tiles[5][2] = new PathTile(this);
		for (int x = 2; x <= 5; x++)
			tiles[x][1] = new PathTile(this);
		for (int y = 1; y <= 6; y++)
			tiles[2][y] = new PathTile(this);
		tiles[3][6] = new PathTile(this);
		for (int y = 6; y <= 8; y++)
			tiles[4][y] = new PathTile(this);
		for (int y = 8; y <= 10; y++)
			tiles[5][y] = new PathTile(this);
		tiles[6][10] = new PathTile(this);

		// Összekötés
		for (int x = 7; x <= 15; x++)
			tiles[x][7] = new PathTile(this);

		// Jobb alsó bejárat
		for (int x = 14; x <= 19; x++)
			tiles[x][15] = new PathTile(this);

	}

	/**
	 * Egy csempe adott sugarú körén belüli csempéit adja vissza
	 * @param center a csempe, amelyikről a lövést indítjuk
	 * @param range a lövés hatótáva (kör sugara)
	 * @return azok a csempék, amik elérhetőek az adott pályán a megadott paraméterekkel
	 */
	public ArrayList<PathTile> getNearby(Tile center, int range) {
		// teszteléshez az utolsó cellát adja vissza (ezen álljon az ellenség, ha lőni akarunk rá)

		/*// a teszt target a tiles utolso eleme
		PathTile tesztTarget = (PathTile)tiles.get(tiles.size()-1);
		ArrayList<PathTile> targets = new ArrayList<PathTile>();
		// a targets lista az teszt target egz listaba agyazva
		targets.add(tesztTarget);
		return targets;	*/

		int center_position_x = -1;
		int center_position_y = -1;

		// A center tile pozíciójának megkeresése
		for(int x=0; x<size_x; x++) {
			for(int y=0 ; y<size_y; y++) {
				if(tiles[x][y] == center) {
					center_position_x = x;
					center_position_y = y;
				}
			}
		}


		ArrayList<PathTile> result = new ArrayList<PathTile>();

		int search_x = center_position_x-range;
		int search_y = center_position_y-range;
		int search_condition_x = center_position_x+range;
		int search_condition_y = center_position_y+range;

		// Ha a hatósugara a toronynak "lelógna" a pályáról
		if(search_x<0) {
			search_x=0;
		}
		if(search_y<0) {
			search_y=0;
		}
		if(search_condition_x>size_x) {
			search_condition_x=size_x;
		}
		if(search_condition_y>size_y) {
			search_condition_y=size_y;
		}

		// lőhető pathTile-ok keresése
		for( int x=search_x ; x<search_condition_x ; x++) {
			for( int y=search_y ; y<search_condition_y ; y++) {

				// ha egy csempe a center csempétõl vett távolságának kerekített értéke kisebb vagy egyenlõ a hatótávolságnál akkor hatótávon belül van
				float distance = (float) Math.sqrt((x-center_position_x)*(x-center_position_x) + (y-center_position_y)*(y-center_position_y));
				if( range>=Math.round(distance)) {
					if( tiles[x][y]!=null && tiles[x][y].getType().equals("PathTile")) {
						result.add((PathTile) tiles[x][y]);
					}
				}
			}
		}

		return result;
	}

	/**
	 * Visszaadja az összes csempét
	 * @return Az összes csempe
	 */
	public Tile[][] getTiles() {
		return tiles;							//Visszaadja az összes csempét
	}
}

