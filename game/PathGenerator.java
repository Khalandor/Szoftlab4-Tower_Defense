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
        setPathStarts();            // Beállítja az útvonalak kezdőpontját
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

    private void removeSameSegments(){
        HashSet<ArrayList<Tile>> newSegments = new HashSet<ArrayList<Tile>>();
        for (ArrayList<Tile> segment : segments){
            ArrayList<Tile> reversedSegment = new ArrayList<Tile>();
            reversedSegment.addAll(segment);
            Collections.reverse(reversedSegment);
            if (!newSegments.contains(segment) && !newSegments.contains(reversedSegment))
                newSegments.add(segment);
        }
        segments = newSegments;
    }

    /**
     * Útszegmenseket hoz létre a pályán.
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
                // pathStarts.add((PathTile) current);
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
                    // pathStarts.add((PathTile) current);
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
    }
    /**
     * Útszegmenseket hoz létre a pályán.
     * Minden szegmens eleje egy elágazás, a vége egy másik elágazás vagy az EndTile
     * Köztük a kettő közti utak vannak SORRENDBEN
     */
    private void createPathSegments2(){
        HashSet<Tile> seen = new HashSet<Tile>();
        // a megtalált, de még nem bejárt szegmensek eleje van benne ebben a formában: [elágazás, szegmens első eleme]
        Queue<Tile[]> segmentStarts = new ArrayDeque<Tile[]>();
        segmentStarts.add( new Tile[]{null,endTile});

        while (!segmentStarts.isEmpty()){
            Tile[] segmentsStart = segmentStarts.poll();
            Tile intersection = segmentsStart[0];
            Tile current = segmentsStart[1];

            // lehetséges, hogy már bejártuk a másik oldalról a szegmens elejét, akkor nem vesszük figyelembe, egyébként:
            if (!seen.contains(current)){
                // a szegmens elején vagyunk, tároljuk az új szegmenst, aminek első eleme az elágazás
                ArrayList<Tile> actualSegment = new ArrayList<Tile>();
                segments.add(actualSegment);
                // az EndTile is egy szegmens eleje, de azt nem előzi meg elágazás
                if (current != endTile)
                    actualSegment.add(intersection);

                // a jelenlegi csúcsból kiinduló, még nem bejárt csúcsban végződő élek keresése
                HashSet<Tile> edgesFromCurrent = new HashSet<Tile>();
                edgesFromCurrent.addAll(edges.get(current));
                edgesFromCurrent.remove(intersection);

                while (edgesFromCurrent.size() == 1){
                    // Felvesszük az aktuális szegmensbe a csúcsot, és jelöljük, hogy bejártuk
                    actualSegment.add(current);
                    seen.add(current);
                    // a jelenlegi csúcsból kiinduló, még nem bejárt csúcsban végződő élek keresése
                    current = edgesFromCurrent.iterator().next();
                    edgesFromCurrent = new HashSet<Tile>();
                    edgesFromCurrent.addAll(edges.get(current));


                    // TODO, ha út elje elágazás, ez lehet szar.
                    // Ha a soron következő csúcs út eleje, akkor itt vesszük fel a szegmensbe a következő ciklus eleje helyett, mert a ciklusnak itt vége
                    if (edgesFromCurrent.size() == 1) {
                        actualSegment.add(current);
                        edgesFromCurrent.removeAll(seen);
                    }
                    else {
                        edgesFromCurrent.removeAll(seen);
                        // Ha a soron következő csúcs elágazás, akkor minden róla induló útnak létre kell hozni egy új szegmenst, és felvenni őket a sorba
                        if (edgesFromCurrent.size() >= 2 || edgesFromCurrent.isEmpty()) {
                            for (Tile startTile : edgesFromCurrent)
                                segmentStarts.add(new Tile[]{current, startTile});
                            seen.add(current);
                            actualSegment.add(current);
                        }
                    }
                }
            }

            // a hurkoknál itt kezeljük, hogy a szegmens vége a csomópont legyen
            // a current egy sima Path, nem csomópont vagy EndTile. a szegmensek végén tehát nem lehet a current
            // tehát ha egy szegmens utolsó eleme most a current, akkor az rossz, a szegmens végéhez adjuk az elágazást, amihez a current csatlakozik.
            //TODO szomszédos csomópontok? (current is csomópont?)
            else
                for (ArrayList<Tile> segment : segments){
                    if (segment.get( segment.size() -1) == current)
                        segment.add(intersection);
                }
        }
        // Létrejöttek a szegmensek, de fordítva vannak, az EndTile-tól az utak kezdete felé sorrendben vannak, megfordítjuk őket.
        for (ArrayList<Tile> segment : segments)
            Collections.reverse(segment);
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
     * Beállítja az útszegmensek alapján az utak kezdetét.
     * Összevonható gyorsításként a setNextTiles-al
     */
    private void setPathStarts(){
        // az utak kezdetei az olyan Tile-ok, amik útszegmensek kezdőpontjai, de nem végpontjai.
        HashSet<PathTile> startPoints = new HashSet<PathTile>();
        HashSet<Tile> endPoints = new HashSet<Tile>();
        for (ArrayList<Tile> segment : segments){
            startPoints.add((PathTile)segment.get(0));
            endPoints.add(segment.get(segment.size() - 1));
        }
        startPoints.removeAll(endPoints);
        pathStarts = new ArrayList<PathTile>(startPoints);
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
        pathStarts.add(pathTile);
    }
}