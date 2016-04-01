package fr.iutinfo.skeleton.api;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlatResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new Api();
	}

	@Before
	public void initializeDataBase() {
		PlatDao dao = BDDFactory.getDbi().open(PlatDao.class);
		dao.dropPlatTable();
		dao.createPlatTable();
		dao.insert(new Plat(0, "nom du plat", "cuisinier", 7));
		dao.insert(new Plat(1, "nom du plat", "cuisinier", 5));
	}

	@Test
	public void should_return_many_plats_when_get_plat() {
		List<Plat> plats = target("/plat").request().get(new GenericType<List<Plat>>() {
		});
		Assert.assertEquals(2, plats.size());
	}
	
	@Test
	public void should_have_a_cuisinier_when_get_from_bdd() {
		Plat plats = target("/plat/1").request().get(Plat.class);
		Assert.assertEquals("cuisinier", plats.getCuisinier());
	}
	
	@Test
	public void should_return_many_plats_when_search_plat() {
		// target("/plat?q=nom")
		List<Plat> plats = target("/plat").queryParam("q", "nom").request().get(new GenericType<List<Plat>>() {
		});
		Assert.assertEquals(2, plats.size());
	}
	
}
