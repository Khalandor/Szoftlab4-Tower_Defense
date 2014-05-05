package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class PathGenerator {
	private ArrayList<PathTile> pathStarts = new ArrayList<PathTile>();
    private Tile[][] allTiles;
    private HashSet<Tile> unreachedNodes;

	/**
	 * A PathGenerator osztály konstruktora.
	 * Létrehozza a kapott geometry segitségével az útvonalakat, melyeken ellensegeket indíthat el.
	 */
	public PathGenerator (Geometry geometry){
        allTiles = geometry.getTiles();	//Elkérjük az összes csempét

        EndTile treeRoot = getEndTile();
        Collections.addAll(unreachedNodes,(Tile[])getAllPathTiles() );
        unreachedNodes.add(treeRoot);

        // létrehoz egy fát, aminek a gyökere az EndTile, és minden elemének a NextTile változója az őseire mutat (a szülőre lépni a gyerekről)
        findChildren(treeRoot);
        // TODO levél, pálya szélén van => pathStart

        // TODO levél, nincs a szélén, és 2 őse van => unreachable tile, egyirányusítani kell
        /*
        while (van még unreachable)
            megfordítjuk azt az élét, amelyiket megfordítva kevesebb unreachable lesz a pályán
         */

        // levél, nincs a szélen, és 1 őse van => Zsákutca, nem kell vele csinálni semmit
        // levél, nincs a szélen, és 2,3,4 őse van => ??? szerintem nem lehet ilyen
	}

    private void findChildren(Tile node){
        // törli tile-t a listából
        // ha nincs több szomszédja a listában lévők között, akkor visszatér
        // ha van, akkor azok leszármazottai, mindegyiken beállítja, hogy ő az őse (NextTile-ja), majd az ő leszármazottaikat is beállítja
        unreachedNodes.remove(node);
        HashSet<Tile> neighbours = getNeighbours(node);
        // ez lehet nem kell:
        // Collections.disjoint(A,B) returns true if the two specified collections have no elements in common.
        if (Collections.disjoint(unreachedNodes, neighbours))
            return;
        for (Tile n : neighbours)
            ((PathTile)n).setNextTile(node);
        for (Tile n : neighbours)
            findChildren(n);
    }

    private EndTile getEndTile(){
        //TODO
        return null;
    }

    private PathTile[] getAllPathTiles(){
        //TODO
        return null;
    }

	private HashSet<Tile> getNeighbours(Tile t){
        // TODO
        return null;
    }

    /**
     * Azokkal a Tile-okkal tér vissza, amiknek t a nextTile-ja.
     */
    private HashSet<Tile> getChildren(Tile t){
        // TODO
        return null;
    }

    private boolean isOnEdge(Tile t){
        //TODO
        return false;
    }
	
	/**
	 * A paraméterként kapott ellenséget rárakja valamelyik kezdő csempére
	 * @param enemy ezt a példányt rakja rá a csempe-példányra
	 */
	public void start(Enemy enemy) {
        int randomStart = new Random().nextInt(pathStarts.size());
		pathStarts.get(randomStart).addEnemy(enemy);
	}
	
	
	/**
	 * A kezdő csempék PathGenerator-ba regisztrálását végzi
	 * @param pathTile ez a csempe  amelyet beregisztrál
	 */
    // ez nem kell már, megtalálja magának
    @Deprecated
	public void add(PathTile pathTile) {
		pathStarts.add(pathTile);
	}
}