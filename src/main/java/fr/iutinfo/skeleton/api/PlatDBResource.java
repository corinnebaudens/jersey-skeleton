package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/plat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlatDBResource {
	private static PlatDao dao = BDDFactory.getDbi().open(PlatDao.class);
    final static Logger logger = LoggerFactory.getLogger(PlatDBResource.class);


    public PlatDBResource() {
		try {
			dao.createPlatTable();
			dao.insert(new Plat(0,"Margaret Thatcher", "la Dame de fer", 1));
		} catch (Exception e) {
			System.out.println("Table déjà là !");
		}
	}
	
	@POST
	public Plat createPlat(Plat plat) {
        // plat.resetPasswordHash();
        int id = dao.insert(plat);
        plat.setId(id);
		return plat;
	}

	@GET
	@Path("/{name}")
	public Plat getPlat(@PathParam("name") String name) {
		Plat plat = dao.findByName(name);
		if (plat == null) {
			throw new WebApplicationException(404);
		}
		return plat;
	}

	@GET
	public List<Plat> getAllPlats() {
		return dao.all();
	}

}
