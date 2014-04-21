import java.util.ArrayList;
import java.util.Random;


public class Updater {
    public boolean isFoggy;
    public String result;
	public ConstructManager constructManager;
	public Geometry geometry;
	public EnemyGenerator enemyGenerator;
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Construct> constructs = new ArrayList<Construct>();
	public Mana mana = new Mana();
    public ArrayList<String> log = new ArrayList<String>();

    public Updater(){
        isFoggy = false;
        result = "running";
    }
	/**
	 * Visszadja az összes ellenség listáját
	 * @return ArrayList<Enemy>
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	/**
	 * Visszatér a Constructokkal amiket az Updater tartalmaz
	 * @return ArrayList<Construct>
	 */
	public ArrayList<Construct> getConstructs() {
		return constructs;
	}
	
	/**
	 * Új Construct-ok felvételét teszi lehetővé
	 * @param construct az uj construct
	 */
	public void addConstruct(Construct construct) {
		constructs.add(construct);
	}

	/**
	 * Új Enemy-k felvételét teszi lehetőve
	 * @param enemy
	 */
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	/**
	 * Véget vet a játeknak győzelemmel, ha igaz a parameter, vereséggel ha hamis
	 * @param isover 
	 */
	public void gameOver(Boolean isover) {
		if (isover == true)
			result= "win";
		else
            result = "lose";
    }
	
	/**
	 * Beallitja geometry valtozot
	 * @param geometry ez lesz az uj erteket
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	
	/**
	 * Beallitja a constructManager valtozott 
	 * @param constructManager a constructManager uj erteke
	 */
	public void setConstructManager(ConstructManager constructManager) {
		this.constructManager = constructManager;
	}
	
	/**
	 * Beallitja az enemyGenerator valtozott
	 * @param enemyGenerator a beallitando uj erteke a valtozonak.
	 */
	public void setEnemyGenerator(EnemyGenerator enemyGenerator) {
		this.enemyGenerator = enemyGenerator;
	}
	
	/**
	 * Visszaadja a palyan levo ellensegek szamat
	 * @return az ellensegek szama
	 */
	public int getNumOfEnemies() {
		return enemies.size();
	}

	/**
	 * Visszadja a geometry-t
	 * @return a geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}

    /**
     * Köd leszáll
     */
    public void fogDown(){
        isFoggy = true;
        for (Construct c : constructs)
            if (c.getType().equals("Tower"))
                ((Tower) c).setRangeModifier(0.7);
    }

    /**
     * Köd felszáll
     */
    public void fogUp(){
        isFoggy = false;
        for (Construct c : constructs)
            if (c.getType().equals("Tower"))
                ((Tower) c).setRangeModifier(1.0);
    }

    private int getTowerNr(Tower t)
    {
        int nr = 0;
        for (Construct c : constructs){
            if (c == t)
                return nr;
            if (c.getType().equals("Tower"))
                nr++;
        }
        return 9999;
    }

    private int getConstructNr(Construct c){
        return constructs.indexOf(c);
    }
    /**
     * Ellenségek / tereptárgyak aktiválása
     */
    public void update(){
        // minden ellenség mozgatása, az ellenség jelzi, hogy nyert-e
        for (Enemy e : enemies){
            Tile from = e.currentTile;
            boolean lose = e.move();
            log.add("- A(z) [nr]. ciklusban lépett az " + e.getName() + " azonosítójú ellenség a " + from.getName() + " celláról " + e.getTile().getName() + "-re.");
            if (lose) {
                gameOver(false);
                log.add(new String("− A [nr]. ciklusban az " + e.getName() + " azonosítójú ellenség a végzet hegyére lépett, vesztettél!"));
            }
        }

        // minden torony lő, a halott ellenséget törli
        for (Construct c : constructs) {
            if (c.getType().equals("Tower")) {
                ArrayList<Enemy> enemiesBeforeShoot = enemies;
                Enemy shotEnemy = ((Tower) c).shoot();

                // ha a torony eltalál valakit
                if (shotEnemy != null) {
                    // ha az ellenség belehal a lövésbe, megkapjuk a manat, töröljük a listából és a celláról, logoljuk.
                    if (shotEnemy.getHealth() <= 0) {
                        log.add(new String("- A(z) [nr]. ciklusban lőtt a C" + getConstructNr(c) + " azonosítójú torony az " +
                                shotEnemy.getName() + " ellenségre, mely meghalt, így a varázserőd " +
                                shotEnemy.getManaValue() + "-al nőtt."));
                        mana.increase(shotEnemy.getManaValue());
                        ((PathTile) shotEnemy.getTile()).removeEnemy(shotEnemy);
                        enemies.remove(shotEnemy);
                    }
                }

                // ellenőrzi, hogy felező lövés volt-e (született-e új ellenség hoz létre)
                if (enemies.size() > enemiesBeforeShoot.size()){
                    log.add(new String("C" + getConstructNr(c) +" lőtt, " +
                            shotEnemy.getName() + " azonosítójú ellenségre, felező lövéssel, levéve tőle " +
                            shotEnemy.getHealth() + " életerőt."));
                    log.add(new String("Létrejött " + enemies.get(enemies.size()-1).getName() + " ellenség."));
                }
            }
        }

        // ellenség-generálás
        enemyGenerator.generateEnemies();

        // 10%, hogy le/felszáll a köd
        if (new Random().nextInt(100) < 10)
            isFoggy = !isFoggy;

        // ha nincs több ellenség, akkor győzelem
        if (enemies.isEmpty() && enemyGenerator.isLastEnemyGenerated()) {
            gameOver(true);
            log.add(new String("- Az utolsó ellenség is meghalt a [nr]. ciklusban, nyertél!"));
            return;
        }
    }

    /**
     * Végignézi, hogy van-e halott ellenség a listában, törli, ha van.
     */
    public void removeDeadEnemies()
    {
        ArrayList<Enemy> aliveEnemies = new ArrayList<Enemy>();

        for (Enemy e : enemies)
        {
            if (e.getHealth() <= 0)
            {
                mana.increase(e.getManaValue());
                ((PathTile)e.getTile()).removeEnemy(e);
            }
            else
                aliveEnemies.add(e);
        }
        enemies = aliveEnemies;
}
