package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/plat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlatResource {
    private static Map<Integer, Plat> plats = new HashMap<>();
    Logger logger = LoggerFactory.getLogger(PlatResource.class);

    @POST

    public Plat createPlat(Plat plat) {
        int id = plats.size();
        plat.setId(id + 1);
        plats.put(plat.getId(), plat);
        return plat;
    }

    @DELETE
    @Path("{id}")
    public Response deletePlat(@PathParam("id") Integer id) {
        if (plats.containsKey(id)) {
            return Response.accepted().status(Status.ACCEPTED).build();
        }
        return Response.accepted().status(Status.NOT_FOUND).build();
    }

    protected Plat find(String name) {
        for (Plat plat : plats.values()) {
            if (plat.getNom().equals(name)) {
                return plat;
            }
        }
        return null;
    }

    protected Plat find(int id) {
        return plats.get(id);
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id,
                               Plat plat) {
        Plat oldPlat = find(id);
        logger.info("Should update plat with id: " + id + " (" + oldPlat + ") to " + plat);
        if (plat == null) {
            throw new WebApplicationException(404);
        }
        oldPlat.setNom(plat.getNom());
        return Response.status(200).entity(oldPlat).build();
    }

    @GET
    @Path("/{name}")
    public Plat getPlat(@PathParam("name") String name) {
        Plat out = find(name);
        if (out == null) {
            throw new WebApplicationException(404);
        }
        return out;
    }

    @GET
    public List<Plat> getPlats(@DefaultValue("10") @QueryParam("limit") int limit) {
        return new ArrayList<>(plats.values());
    }

}
