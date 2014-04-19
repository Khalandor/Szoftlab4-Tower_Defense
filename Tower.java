import java.util.ArrayList;

public class Tower extends Construct {
	private int damage;
	private int fireRate;
	private int rangeModifier;
	private int range;
	private FieldTile towerLocation;

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
		range = 400;
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
		
		Enemy target = null;
		for (int i = 0; i < tilesInRange.size() || target != null; i++) {
			System.out.println(tilesInRange.size());
			if (tilesInRange.get(i) != null) {
				target = tilesInRange.get(i).getEnemy();
			}
			if (target == null && i == tilesInRange.size() - 1) {
				return null;
			}
		}
				
		if (gem!=null)
		{
			String type = target.getType();
			gem.getDamageBonus(type);
		};
		
		target.damage(damage);
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
		throw new UnsupportedOperationException();
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
	public void setRangeModifier(int rangeModifier) {
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
