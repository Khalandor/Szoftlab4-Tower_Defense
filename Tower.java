import java.util.ArrayList;
import java.util.Random;

public class Tower extends Construct {
	public int damage;
	public int fireRate;
	public double rangeModifier;
	public int range;
	private FieldTile towerLocation;
	public int shootParam = -1;

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
	}
	
	
	/**
	 * Lekérdezi a hatótávon belüli csempéket, majd kér valamelyikről egy ellenséget. 
	 * Ha van varázskő a toronyban, megkérdezi milyen plusz sebzést biztosít a varázskő,
	 * és a saját értékéhez hozzáadva belesebzi azt az ellenségbe, annyiszor, amennyi a fireRate attribútumának az értéke.
	 * Visszatér azzal az ellenséggel, akit meglőtt.
	 * @return a meglőtt ellenség
	 */
	public Enemy shoot() {
		Geometry geometry = towerLocation.getGeometry();
		ArrayList<PathTile> tilesInRange = geometry.getNearby(towerLocation, range);
		Random rand = new Random();
		
		Enemy target = null;
		for (int i = 0; i < tilesInRange.size() && target == null; i++) {
			if (tilesInRange.get(i) != null) {
				target = tilesInRange.get(i).getEnemy();
			}
			if (target == null && i == tilesInRange.size() - 1) {
				return null;
			}
		}
		int damageBonus=0;
		if (gem!=null)
		{
			String type = target.getType();
			damageBonus = gem.getDamageBonus(type);
		};
		
		
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
	
	
}
