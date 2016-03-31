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
	}

	@Test
	public void should_return_many_plats_when_get_plat() {
		List<Plat> plats = target("/plat").request().get(new GenericType<List<Plat>>() {
		});
		Assert.assertEquals(1, plats.size());
	}

}
