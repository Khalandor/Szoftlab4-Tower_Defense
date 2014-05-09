package game;

import java.util.ArrayList;
import java.util.Random;

public class Tower extends Construct {
	private int damage;
	private int fireRate;
	private double rangeModifier;
	private int range;
	private FieldTile towerLocation;
	private int shootParam = -1;
	private int shootDelay;

	/**
	 * A Tower osztály konstruktora.
	 */
	public Tower (FieldTile towerLocation)
	{
		this.towerLocation = towerLocation;
		type = "Tower";										//Beállítjuk a saját típusát
		damage = 20;
		fireRate = 3;
		rangeModifier = 1;
		range = 3;
		shootDelay = 3;
	}
	
	
	/**
	 * Lekérdezi a hatótávon belüli csempéket, majd kér valamelyikről egy ellenséget. 
	 * Ha van varázskő a toronyban, megkérdezi milyen plusz sebzést biztosít a varázskő,
	 * és a saját értékéhez hozzáadva belesebzi azt az ellenségbe, annyiszor, amennyi a fireRate attribútumának az értéke.
	 * Visszatér azzal az ellenséggel, akit meglőtt.
	 * @return a meglőtt ellenség
	 */
	public Enemy shoot() {
		if (shootParam == -1) {
			shootDelay--;
			if (shootDelay > 0) return null;
			else shootDelay = fireRate;
		}
		
		Geometry geometry = towerLocation.getGeometry();
		ArrayList<PathTile> tilesInRange = geometry.getNearby(towerLocation, (int) (range * rangeModifier));
		Random rand = new Random();
		
		Enemy target = null;
		for (int i = 0; i < tilesInRange.size() && target == null; i++) {
			if (tilesInRange.get(i) != null) {
				target = tilesInRange.get(i).getEnemy();
			}
		}
		if (target == null) {
			return null;
		}
		int damageBonus=0;
		if (gem!=null)
		{
			String type = target.getType();
			damageBonus = gem.getDamageBonus(type);
		};
		
		//TODO kicsit értelmesebbé kéne tenni a a felező lövések számát
		shootParam = 0;
		switch (shootParam)
		{
		case 1  : target.damageHalf();
				  break;				 		
		
		case 0  : target.damage(damage+damageBonus);
				  break;
		
		case -1 : if (rand.nextInt(10) < 5)	  {
						target.damageHalf();
						} else {
						target.damage(damage+damageBonus);
					}
				  break;
		}
		
		shootParam = -1;
		return target;
	}

	/**
	 * Beállítja a damage attribútumot
	 * @param damage a torony egy lövésének sebzése
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Beallitja a torony tuzgyorsasagat
	 * @param rate ez a tuzgyorsasag uj erteke
	 */
	public void setFireRate(int rate) {
		fireRate = rate;
	}

	/**
	 * Beallitja a torony hatotavolsagat
	 * @param range a torony uj hatotavolsaga
	 */
	public void setRange(int range) {
		this.range = range;
	}
	
	/**
	 * Beállítja a torony hatótávolságának módosítóját
	 * @param rangeModifier a torony hatótávának módosítója
	 */
	public void setRangeModifier(double rangeModifier) {
		this.rangeModifier = rangeModifier;
	}
	
	/**
	 * Beallitja azta referenciat amely megmondja hogy a torony melyik csempen van.
	 * @param loc a csempe melyre a torony epult
	 */
	public void setTowerLocation(FieldTile loc) {
		towerLocation = loc;
	}
	/**
	 * Visszaadja azt a Tile-t amin a torony van
	 * @return a tile ami tartalmazza a tornyot
	 */

	public Tile getTile() {
		return towerLocation;
	}
	
	
}
