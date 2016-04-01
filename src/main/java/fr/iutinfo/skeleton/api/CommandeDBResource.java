package fr.iutinfo.skeleton.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/cmddb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandeDBResource {
	
	// En cours d'implémentation par merianb/dubromev
	
	private static CommandeDao dao = BDDFactory.getDbi().open(CommandeDao.class);
	final static Logger logger = LoggerFactory.getLogger(CommandeDBResource.class);
	
	
	public CommandeDBResource() {
		try {
			dao.createCommandeTable();
			dao.insert(new Commande(0, 0, 0, 1));
		} catch (Exception e) {
			logger.debug("Table Commande déjà là !");
		}
	}
	
	
	
}
