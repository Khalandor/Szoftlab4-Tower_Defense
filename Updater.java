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

    /**
     * Ellenségek / tereptárgyak aktiválása
     */
    public void update(){
        // ha nincs több ellenség, akkor győzelem
        if (enemies.isEmpty() && enemyGenerator.isLastEnemyGenerated())
            gameOver(false);

        // minden ellenség mozgatása, az ellenség jelzi, hogy nyert-e
        for (Enemy e : enemies)
            if (e.move())
                gameOver(true);

        // minden torony lő
        for (Construct c : constructs)
            if (c.getType().equals("Tower"))
                ((Tower) c).shoot();

        // ellenség-generálás
        enemyGenerator.generateEnemies();

        // 10%, hogy le/felszáll a köd
        int random = new Random().nextInt(100);
        if (random < 10)
            isFoggy = !isFoggy;
    }

}
