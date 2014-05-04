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
		
		int center_position_x = -1;
		int center_position_y = -1;
		
		// A center tile pozíciójának megkeresése
		for(int x=0; x<size_x; x++){
			for(int y=0 ; y<size_y; y++){
				if(tiles[x][y] == center){
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
		if(search_x<0){
			search_x=0;
		}
		if(search_y<0){
			search_y=0;
		}
		if(search_condition_x>size_x){
			search_condition_x=size_x;
		}
		if(search_condition_y>size_y){
			search_condition_y=size_y;
		}
		
		// lőhető pathTile-ok keresése
		for( int x=search_x ; x<search_condition_x ; x++){
			for( int y=search_y ; y<search_condition_y ; y++){ 
				
				// ha egy csempe a center csempétõl vett távolságának kerekített értéke kisebb vagy egyenlõ a hatótávolságnál akkor hatótávon belül van
				float distance = (float) Math.sqrt((x-center_position_x)*(x-center_position_x) + (y-center_position_y)*(y-center_position_y));
				if( range>=Math.round(distance)){
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
	
	public void addTile(Tile tile, int index_x , int index_y) {
		tiles[index_x][index_y]=tile;
	}
}

