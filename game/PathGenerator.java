package game;

import java.util.*;

public class PathGenerator {
    private ArrayList<PathTile> pathStarts = new ArrayList<PathTile>();
    private Tile[][] map;
    private HashSet<Tile[]> edges = new HashSet<Tile[]>();
    private HashSet<PathTile> pathTiles = new HashSet<PathTile>();
    private EndTile endTile;

    /**
     * A PathGenerator osztály konstruktora.
     * Létrehozza a kapott geometry segitségével az útvonalakat, melyeken ellensegeket indíthat el.
     */
    public PathGenerator (Geometry geometry){
        map = geometry.getTiles();	//Elkérjük az összes csempét
        buildUnorientedGraph();     // létrehoz egy irányítatlan gráfot
        orientGraph();              // irányítottá teszi a gráfot
        exportGraph();              // beállítja a pathStarts és PathTile.nextTile referenciákat az irányított gráf alapján
    }

    /**
     * Új, irányítatlan gráfot hoz létre a térkép alapján
     * Gráf csúcsa = PathTile-ok és EndTile
     * Gráf élei = pályán szomszédos mezők. Minden él felvéve oda és vissza is.
     */
    private void buildUnorientedGraph(){
        for (Tile[] row: map)
            for (Tile t: row) {
                if (t.getType().equals("PathTile")) {
                    pathTiles.add((PathTile) t);
                    HashSet<Tile> tNeighbours = getNeighbours(t);
                    for (Tile neighbour : tNeighbours)
                        edges.add(new Tile[]{t, neighbour});
                }
                else if (t.getType().equals("EndTile")) {
                    endTile = (EndTile) t;
                    HashSet<Tile> tNeighbours = getNeighbours(t);
                    for (Tile neighbour : tNeighbours)
                        edges.add(new Tile[]{t, neighbour});
                }
            }
    }

    /**
     * Visszaadja egy Csempével szomszédos PathTile és EndTile mezőket.
     * @param t a csempe
     * @return  a csempével szomszédos PathTile-ok és EndTile-ok.
     */
    private HashSet<Tile> getNeighbours(Tile t){
        int x, y = -1;
        for (x = 0; x < map.length; x++)
            for (y = 0; y < map[x].length; y++)
                if (map[x][y] == t)
                    break;

        HashSet<Tile> neighbours = new HashSet<Tile>();
        if (x > 0)
            if (map[x-1][y].getType().equals("PathTile") ||
                    map[x-1][y].getType().equals("EndTile"))
                neighbours.add(map[x-1][y]);
        if (y > 0)
            if (map[x][y-1].getType().equals("PathTile") ||
                    map[x][y-1].getType().equals("EndTile"))
                neighbours.add(map[x][y-1]);
        if (x < map.length -1)
            if (map[x+1][y].getType().equals("PathTile") ||
                    map[x+1][y].getType().equals("EndTile"))
                neighbours.add(map[x+1][y]);
        if (y < map[x].length -1)
            if (map[x][y+1].getType().equals("PathTile") ||
                    map[x][y+1].getType().equals("EndTile"))
                neighbours.add(map[x][y+1]);
        return neighbours;
    }

    /**
     * Törli az összes oda-vissza élpár közül az egyiket.-> Irányított lesz a gráf minden éle
     * Az él arra mutat, amerre lépni lehet.
     */
    private void orientGraph(){
        // Elágazás-sor: olyan út-cellák sora, amik 2-nél több útcellával szomszédosak, már elérte őket az algoritmus, de még nem végzett velük.
        Queue<Tile> intersectionQue = new ArrayDeque<Tile>();
        /*
        Az EndTile-tól visszafele indulva az utak elejéig (az EndTile a sor első eleme):
        Az elágazás minden élén keresztül, ami még kétirányú, eljutunk a következő elágazásig.
        Ha az elágazásnak nincs több kétirányú éle, akkor töröljük a sorból.
        Minden lépésnél, amit a következő elágazás felé megteszünk, töröljük az élet, amin megtettük.
        Ha elérünk egy csomópontot, ami még nincs benne a sorban, a sor végére helyezzük.
         */
        intersectionQue.add(endTile);
        // TODO
    }

    /**
     * Az irányított élek alapján beállítja a pathStarts és PathTile.nextTile referenciákat.
     */
    private void exportGraph(){
        HashSet<PathTile> possiblePathStarts = pathTiles;
        for (Tile[] edge : edges) {
            ((PathTile) edge[0]).setNextTile(edge[1]);
            possiblePathStarts.remove(edge[0]);
        }
        pathStarts = new ArrayList<PathTile>(possiblePathStarts);
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