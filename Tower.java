import java.util.ArrayList;

public class Tower extends Construct {
	private int damage;
	private int fireRate;
	private int range;
	private FieldTile towerLocation;

	/**
	 * Lekérdezi a hatótávon belüli csempéket, majd kér valamelyikről egy ellenséget. Ha van varázskő a toronyban, megkérdezi milyen plusz sebzést biztosít a varázskő, és a saját értékéhez hozzáadva belesebzi azt az ellenségbe, annyiszor, amennyi a fireRate attribútumának az értéke. Visszatér azzal az ellenséggel, akit meglőtt.
	 * @return a meglőtt ellenség
	 */
	public Enemy shoot() {
		System.out.println("--> Tower.shoot()");
		//FIXME BUG: towerLocationt nem adjuk meg a build diagramban, a konstruktorban kéne
		Geometry geometry = towerLocation.getGeometry();
		ArrayList<PathTile> tilesInRange = geometry.getNearby(towerLocation, range);
		Enemy target = tilesInRange.get(0).getEnemy();
		target.damage(damage);
		System.out.println("<--" + target);
		return target;
	}

	public void setDamage(int damage) {
		throw new UnsupportedOperationException();
	}

	public void setFireRate(int rate) {
		throw new UnsupportedOperationException();
	}

	public void setRange(int range) {
		throw new UnsupportedOperationException();
	}
}
