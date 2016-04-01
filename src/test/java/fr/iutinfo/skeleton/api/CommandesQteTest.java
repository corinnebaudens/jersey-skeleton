package fr.iutinfo.skeleton.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CommandesQteTest {

	@Before
	public void initializeDataBase() {
		
		PlatDao daoplat = BDDFactory.getDbi().open(PlatDao.class);
		daoplat.dropPlatTable();
		daoplat.createPlatTable();
		
		CommandeDao daocde = BDDFactory.getDbi().open(CommandeDao.class);
		daocde.dropCommandeTable();
		daocde.createCommandeTable();
	}
	@Test
	public void should_have_a_quantity_ordered_of_6() {
		PlatDao daoplat = BDDFactory.getDbi().open(PlatDao.class);
		CommandeDao daocde = BDDFactory.getDbi().open(CommandeDao.class);
		int idplat = daoplat.insert(new Plat(0, "nom du plat", "cuisinier", 10));
		daocde.insert(new Commande(0, 1, idplat, 1));
		daocde.insert(new Commande(0, 2, idplat, 1));
		Assert.assertEquals(2, daocde.quantiteCommande(idplat));
	}

}
