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
	}
	
	/*public Geometry(int size) {
		tiles = new Tile[size];
	}*/
	
	/**
	 * Létrehozza egy általunk kért nagyságú két dimmenziós térképet
	 */
	public void setMapSize(int sizex , int sizey) {
		size_x=sizex;
		size_y=sizey;
		tiles = new Tile[sizex][sizey];
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
		
		int center_pozition_x = -1;
		int center_pozition_y = -1;
		
		
		// A center tile pozíciójának megkeresése
		// TODO  baj-e az hogy miután megtalálta a keresett Tile-t akkor még feleslegesn futt.
		for(int x=0 ; x<size_x ; x++){
			for(int y=0 ; y<size_y; y++){
				if(tiles[x][y] == center){
					center_pozition_x = x;
					center_pozition_y = y;
				}
			}
		}
		
		if(center_pozition_x == -1 || center_pozition_y == -1){
			// TODO Ez azt jelentené hogy a center Tile nincs a térképen nem tudom hogy foglalkozni kéne ezzel
		}
		
		ArrayList<PathTile> result = new ArrayList<PathTile>();
		
		// megnézzük a center tile közelében lévő csempéket
		for( int x=center_pozition_x-range     ; x<=center_pozition_x+range ; x++){
			for( int y=center_pozition_x-range ; y<=center_pozition_x+range ; y++){ 
				
				// ha egy csempe a center csempétől vett távolságának kerekített értéke kisebb vagy egyenlő a hatótávolságnál akkor hatótávon belül van
				if( range>=round(sqrt(x*x+y*y)) ){
					result.add(tiles[x][y]);
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
	
	public void addTile(Tile tile, int index_x , int index_y) {
		tiles[index_x][index_y]=tile;
	}
}

