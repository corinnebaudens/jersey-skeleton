package fr.iutinfo.skeleton.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
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
	
	@POST
	public Commande createCommande(Commande commande){
		int id_commande = dao.insert(commande);
		commande.setId_commande(id_commande);
		return commande;
	}
	
//	@GET
//	@Path("/{id_commande}")
//	public Commande getCommande(@PathParam("id") int id_commande) {
//		Commande commande = dao.findById(id_commande);
//		if (commande == null) {
//			throw new WebApplicationException(404);
//		}
//		return commande;
//	}
	
	@GET
	@Path("/{id_plat}")
	public int getQuantiteCde(@PathParam("idplat") int id_plat) {
		int sumQuantiteCde = dao.quantiteCommande(id_plat);
		return sumQuantiteCde;
	}
	
	@GET
	@Path("/{id_client}")
	public List<Commande> getListeCdeParClient(@PathParam("idclient") int id_client) {
		List<Commande> listeCde = dao.listeCdeParClient(id_client);
		return listeCde;
	}
	
//	@GET
//	public List<Commande> getAllCommandes(@QueryParam("q") String query) {
//		logger.debug(query);
//		
//		if (query == null || query.isEmpty())
//			return dao.all();
//		else
//			return dao.listeCdeParClient("%" + query + "%");
//	}
	
}
