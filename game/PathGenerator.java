package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class PathGenerator {
    private ArrayList<PathTile> pathStarts = new ArrayList<PathTile>();
    private Tile[][] allTiles;
    private HashSet<Tile[]> edges;

    /**
     * A PathGenerator osztály konstruktora.
     * Létrehozza a kapott geometry segitségével az útvonalakat, melyeken ellensegeket indíthat el.
     */
    public PathGenerator (Geometry geometry){
        allTiles = geometry.getTiles();	//Elkérjük az összes csempét
        buildUnorientedGraph();


    }

    /**
     * Új, irányítatlan gráfot hoz létre a térkép alapján
     * Gráf csúcsa = PathTile-ok és EndTile
     * Gráf élei = pályán szomszédos mezők. Minden él felvéve oda és vissza is.
     */
    private void buildUnorientedGraph(){
        for (Tile[] row: allTiles)
            for (Tile t: row)
                if (t.getType().equals("PathTile") || t.getType().equals("EndTile")){
                    HashSet<Tile> tNeighbours = getNeighbours(t);
                    for (Tile neighbour : tNeighbours)
                        edges.add(new Tile[]{t, neighbour});
                }
    }

    /**
     * Visszaadja egy Csempével szomszédos PathTile és EndTile mezőket.
     * @param t a csempe
     * @return  a csempével szomszédos PathTile-ok és EndTile-ok.
     */
    private HashSet<Tile> getNeighbours(Tile t){
        int x, y = -1;
        for (x = 0; x < allTiles.length; x++)
            for (y = 0; y < allTiles[x].length; y++)
                if (allTiles[x][y] == t)
                    break;

        HashSet<Tile> neighbours = new HashSet<Tile>();
        if (x > 0)
            if (allTiles[x-1][y].getType().equals("PathTile") ||
                    allTiles[x-1][y].getType().equals("EndTile"))
                neighbours.add(allTiles[x-1][y]);
        if (y > 0)
            if (allTiles[x][y-1].getType().equals("PathTile") ||
                    allTiles[x][y-1].getType().equals("EndTile"))
                neighbours.add(allTiles[x][y-1]);
        if (x < allTiles.length -1)
            if (allTiles[x+1][y].getType().equals("PathTile") ||
                    allTiles[x+1][y].getType().equals("EndTile"))
                neighbours.add(allTiles[x+1][y]);
        if (y < allTiles[x].length -1)
            if (allTiles[x][y+1].getType().equals("PathTile") ||
                    allTiles[x][y+1].getType().equals("EndTile"))
                neighbours.add(allTiles[x][y+1]);
        return neighbours;
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