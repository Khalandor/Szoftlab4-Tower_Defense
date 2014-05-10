package game;

import java.util.*;

public class PathGenerator {
    private ArrayList<PathTile> pathStarts = new ArrayList<PathTile>();
    private Tile[][] map;
    private HashMap<Tile, HashSet<Tile>> edges = new HashMap<Tile, HashSet<Tile>>();
    private EndTile endTile;
    HashSet<ArrayList<Tile>> segments = new HashSet<ArrayList<Tile>>();

    /**
     * A PathGenerator osztály konstruktora.
     * Létrehozza a kapott geometry segitségével az útvonalakat, melyeken ellensegeket indíthat el.
     */
    public PathGenerator (Geometry geometry){
        map = geometry.getTiles();	//Elkérjük az összes csempét
        buildUnorientedGraph();     // létrehoz egy irányítatlan gráfot
        createPathSegments();       // szegmensekre tördeli a gráfot. Minden szegmens eleje és vége egy elágazás
        setNextTiles();             // Beállítja az útvonalszegmensek alapján a PathTile-ok nextTile értékét
    }

    /**
     * Új, irányítatlan gráfot hoz létre a térkép alapján
     * Gráf csúcsa = PathTile-ok és EndTile
     * Gráf élei = pályán szomszédos mezők. Minden él felvéve oda és vissza is.
     */
    private void buildUnorientedGraph(){
        // a térkép minden PathTile-jának és EndTile-jának megkeressük a szomszédait
        for (Tile[] row: map)
            for (Tile t: row) {
                if (t.getType().equals("PathTile")) {
                    // t szomszédai a t melletti PathTile-ok/EndTile
                    HashSet<Tile> tNeighbours = getNeighbours(t);
                    for (Tile neighbour : tNeighbours){
                        // lekérjük az eddig elmentett szomszédokat, és hozzáírjuk az újat. Ha még nincs elmentett szomszéd, most elmentjük.
                        HashSet<Tile>  savedNeighbours = edges.get(t);
                        if (null == savedNeighbours){
                            savedNeighbours = new HashSet<Tile>();
                            edges.put(t, savedNeighbours);
                        }
                        savedNeighbours.add(neighbour);
                    }
                }
                else if (t.getType().equals("EndTile")) {
                    endTile = (EndTile) t;
                    HashSet<Tile> tNeighbours = getNeighbours(t);
                    for (Tile neighbour : tNeighbours){
                        HashSet<Tile>  savedNeighbours = edges.get(t);
                        if (null == savedNeighbours) {
                            savedNeighbours = new HashSet<Tile>();
                            edges.put(t, savedNeighbours);
                        }
                        savedNeighbours.add(neighbour);
                    }
                }
            }
    }

    /**
     * Visszaadja egy Csempével szomszédos PathTile és EndTile mezőket.
     * @param t a csempe
     * @return  a csempével szomszédos PathTile-ok és EndTile-ok.
     */
    private HashSet<Tile> getNeighbours(Tile t){
        int x = -1;
        int y = -1;

        findCoordinates:
        for (x = 0; x < map.length; x++)
            for (y = 0; y < map[x].length; y++)
                if (map[x][y] == t)
                    break findCoordinates;

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
     * Útszegmenseket hoz létre az élek alapján.
     * Minden szegmens eleje egy elágazás, a vége egy másik elágazás vagy az EndTile
     * Köztük a kettő közti utak vannak SORRENDBEN
     */
    private void createPathSegments(){
        // inicializálás.
        // seenSegmentStarts: a szegmensek kezdetei közül a már bejártak
        HashSet<Tile> seenSegmentStarts = new HashSet<Tile>();
        // segmentStartsLeft: a szegmensek kezdetei közül a már megtaláltak, de még nem bejártak.
        // 2-elemű tömb, Formátuma: [szegmenst megelőző elágazás, szegmens első eleme]
        Queue<Tile[]> segmentStartQueue = new ArrayDeque<Tile[]>();
        // az első szegmens eleje az endTile, megelőző elágazása nincs
        segmentStartQueue.add( new Tile[]{null,endTile});

        while (!segmentStartQueue.isEmpty()){
            Tile[] newStart = segmentStartQueue.poll();
            Tile intersection = newStart[0];
            Tile current = newStart[1];
            Tile previous;

            // a szegmens elején vagyunk, tároljuk az új szegmenst, aminek első eleme az elágazás
            ArrayList<Tile> actualSegment = new ArrayList<Tile>();
            segments.add(actualSegment);
            // az EndTile is egy szegmens eleje, de azt nem előzi meg elágazás
            if (current != endTile)
                actualSegment.add(intersection);

            // az elágazás utáni mezőből elérhető mezők, kivéve az elágazást magát.
            HashSet<Tile> reachableFromCurrent = new HashSet<Tile>();
            reachableFromCurrent.addAll(edges.get(current));
            reachableFromCurrent.remove(intersection);

            // nem lehet innen tovább menni -> ez egy új út kezdete, ami szomszédos egy elágazással, tehát egy szegmens eleje és vége is!
            if (reachableFromCurrent.isEmpty()){
                if (!pathStarts.contains(current))
                    pathStarts.add((PathTile) current);
                actualSegment.add(current);
                seenSegmentStarts.add(current);
                segmentStartQueue.remove(current);
            }
            // 1 irányba lehet továbbmenni -> egy új szegmens kezdete
            else if (reachableFromCurrent.size() == 1)
                seenSegmentStarts.add(current);

            // 2 vagy 3 irányba lehet továbbmenni -> 2 elágazás van szomszédos mezőn, ez nem támogatott
            else if (reachableFromCurrent.size() >= 2){
                System.out.println("Egymás melletti elágazások.");
                // TODO ez nincs lekezelve
            }

            while (reachableFromCurrent.size() == 1){
                //felvesszük a szegmensbe a mezőt
                actualSegment.add(current);
                // itt currentből a while feltétele miatt, garantáltan csak 1 Tile érhető el, ez lesz az új current, az új previous pedig a mostani current
                previous = current;
                current = reachableFromCurrent.iterator().next();

                // megkeressük az új currentből elérhető mezőket
                reachableFromCurrent = new HashSet<Tile>();
                reachableFromCurrent.addAll(edges.get(current));
                reachableFromCurrent.remove(previous);

                // nem lehet innen tovább menni -> current egy új út kezdete, szegmens vége
                if (reachableFromCurrent.isEmpty()){
                    if (!pathStarts.contains(current))
                        pathStarts.add((PathTile) current);
                    actualSegment.add(current);
                }

                // az új current egy elágazás -> szegmens vége, az elágazás még be nem járt kezdeteinek felvétele a sorba
                if (reachableFromCurrent.size() >=2){
                    seenSegmentStarts.add(previous);
                    segmentStartQueue.remove(previous);
                    actualSegment.add(current);
                    for (Tile segmentStart : reachableFromCurrent)
                        if (!seenSegmentStarts.contains(segmentStart))
                            segmentStartQueue.add(new Tile[]{current, segmentStart});
                }
            }
        }
        // dupla utak törlése
        removeSameSegments();
        // Létrejöttek a szegmensek, de fordítva vannak, az EndTile-tól az utak kezdete felé sorrendben vannak, megfordítjuk őket.
        for (ArrayList<Tile> segment : segments)
            Collections.reverse(segment);
        // törli a hurkok miatt keletkező dupla utakat
        removeTwoWaySegments();
    }

    /**
     * Törli az ugyanolyan szegmenseket.
     */
    private void removeSameSegments(){
        HashSet<ArrayList<Tile>> uniqueSegments = new HashSet<ArrayList<Tile>>();
        for (ArrayList<Tile> segment : segments){
            ArrayList<Tile> reversedSegment = new ArrayList<Tile>();
            reversedSegment.addAll(segment);
            Collections.reverse(reversedSegment);
            if (!uniqueSegments.contains(segment))
                uniqueSegments.add(segment);
        }
        segments = uniqueSegments;
    }

    /**
     * Törli a hurkok miatt keletkező dupla élek közül azt, amelyik azt okozza, hogy bent lehessen ragadni a hurokban
     */
    private void removeTwoWaySegments(){
        HashSet<Tile> discovered = new HashSet<Tile>();
        for (Tile p : pathStarts)
            DFSRemoveDouble(p, discovered);
    }

    /**
     * Rekurzív mélységi keresés  függvény
     * Mélységi keresést végez a gráfon, aminek élei az út elejétől az EndTile-ig szegmensek, csúcsai az elágazások
     * A keresés közben törli a megfelelő dupla éleket
     * @param v A gráf pontja, ahonnan a rekurzív mélységi keresés mostani ciklusa fut
     * @param discovered A gráf eddig meglátogatott pontjai
     */
    private void DFSRemoveDouble(Tile v, HashSet<Tile> discovered){
        discovered.add(v);
        // minden itt kezdődő szegmens
        HashSet<ArrayList<Tile>> allSegmentsFromHere = new HashSet<ArrayList<Tile>>();
        for (ArrayList<Tile> segment : segments)
            if (segment.get(0) == v)
                allSegmentsFromHere.add(segment);

        HashSet<Tile> reachableTiles = new HashSet<Tile>();
        // Innen kivezető élek végpontjai:
        for (ArrayList<Tile> segment : allSegmentsFromHere)
            reachableTiles.add(segment.get( segment.size() - 1));

        // Ha visszafele is mutat onnan egy él, azt törlöm
        // Minden a végpontokban kezdődő szegmens:
        HashSet<ArrayList<Tile>> segmentsFromNeighbours = new HashSet<ArrayList<Tile>>();
        for (Tile neighbour : reachableTiles)
            for (ArrayList<Tile> segment : segments)
                if (segment.get(0) == neighbour)
                    segmentsFromNeighbours.add(segment);

        // A végpontokban kezdődő szegmensek, amik ide mutatnak vissza
        HashSet<ArrayList<Tile>> edgesToRemove = new HashSet<ArrayList<Tile>>();
        for (ArrayList<Tile> segment : segmentsFromNeighbours)
            if (segment.get( segment.size() -1) == v)
                edgesToRemove.add(segment);

        if (!edgesToRemove.isEmpty()) {
            HashSet<ArrayList<Tile>> segmentsToKeep = new HashSet<ArrayList<Tile>>();
            for (ArrayList<Tile> segment : segments)
                if (!edgesToRemove.contains(segment))
                    segmentsToKeep.add(segment);
            segments = segmentsToKeep;
        }

        for (Tile w : reachableTiles)
            if (!discovered.contains(w))
                DFSRemoveDouble(w, discovered);
    }

    /**
     * Beállítja az útszegmensek alapján a nextTile értékeket
     */
    private void setNextTiles(){
        // mindig az útszegmensben következő csempe lesz a nextTile értéke (hozzáadja)
        for (ArrayList<Tile> segment : segments)
            for (int i=0; i < segment.size() - 1; i++)
                ((PathTile) segment.get(i)).setNextTile(segment.get(i+1));
    }

    /**
     * A paraméterként kapott ellenséget rárakja valamelyik kezdő csempére
     * @param enemy ezt a példányt rakja rá a csempe-példányra
     */
    public void start(Enemy enemy) {
        int randomStart = new Random().nextInt(pathStarts.size());
        pathStarts.get(randomStart).addEnemy(enemy);
        enemy.setTile( pathStarts.get(randomStart));
    }


    /**
     * A kezdő csempék PathGenerator-ba regisztrálását végzi
     * @param pathTile ez a csempe  amelyet beregisztrál
     */
    // ez nem kell már, megtalálja magának
    @Deprecated
    public void add(PathTile pathTile) {
    }
}