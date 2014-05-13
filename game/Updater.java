package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Updater {
	private boolean isFoggy;
	private String result;
	private ConstructManager constructManager;
	private Geometry geometry;
	private EnemyGenerator enemyGenerator;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Construct> constructs = new ArrayList<Construct>();
	private Mana mana = new Mana();
	private Timer timer;

	public Updater() {
		isFoggy = false;
		result = "running";
		geometry = new Geometry();
		PathGenerator pathGenerator = new PathGenerator(geometry);
		enemyGenerator = new EnemyGenerator(pathGenerator, this);
		constructManager = new ConstructManager(this, mana);
		mana.setMana(150);

		timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				update();
			}
		}, 0, 800);
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
	 */
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	/**
	 * Véget vet a játeknak győzelemmel, ha igaz a parameter, vereséggel ha hamis
	 */
	public void gameOver(Boolean isover) {
		if (isover) {
			result= "win";
			timer.cancel();
		}
		else {
			result = "lose";
			timer.cancel();
		}
	}

	/**
	 * Visszaadja a palyan levo ellensegek szamat
	 * @return az ellensegek szama
	 */
    @Deprecated
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
	 * Ellenségek / tereptárgyak aktiválása
	 */
	public void update() {
		// minden ellenség mozgatása, az ellenség jelzi, hogy nyert-e
		for (Enemy e : enemies) {
			boolean lose = e.move();
			if (lose) {
				gameOver(false);
			}
		}

		// minden torony lő, a halott ellenséget törli
		for (Construct c : constructs) {
			if (c.getType().equals("Tower")) {
				Enemy shotEnemy = ((Tower) c).shoot();
				// ha a torony eltalál valakit
				if (shotEnemy != null) {
					// ha az ellenség belehal a lövésbe, megkapjuk a manat, töröljük a listából és a celláról
					if (shotEnemy.getHealth() <= 0) {
						mana.increase(shotEnemy.getManaValue());
						((PathTile) shotEnemy.getTile()).removeEnemy(shotEnemy);
						enemies.remove(shotEnemy);
					}
				}
			}
		}

		// ellenség-generálás
		enemyGenerator.generateEnemies();

		// 5%, hogy le/felszáll a köd
		if (new Random().nextInt(100) < 5) {
			isFoggy = !isFoggy;
			for (Construct c : constructs) {
				if (c.getType().equals("Tower")) {
					if (isFoggy) ((Tower) c).setRangeModifier(0.75);
					else ((Tower) c).setRangeModifier(1);
				}
			}
		}

		// ha nincs több ellenség, akkor győzelem
		if (enemies.isEmpty() && enemyGenerator.isLastEnemyGenerated())
			gameOver(true);
	}

	public ConstructManager getConstructManager() {
		return constructManager;
	}

	public int getMana() {
		return mana.getMana();
	}

	public String getGameState() {
		return result;

	}

	public boolean getFogStatus() {
		return isFoggy;
	}
}
