package me.lorenc.workshop.rest.beer;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/beer")
public class BeerResource {

    private static BeerStorage beers = new BeerStorage();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Beer> menu() {
        return beers.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response gime(@PathParam("id") Integer id) {
        Beer beer = beers.findById(id);
        
        if (beer == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        return Response.ok().entity(beer).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response load(Beer beer, @Context UriInfo uriInfo) {
        beers.store(beer);
        URI locationUri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(beer.getId())).build();
        return Response.created(locationUri).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replace(@PathParam("id") Integer id, Beer beer) {
        if (id != beer.getId()) {
            return Response.status(Status.CONFLICT).build();
        }
        
        if (beers.findById(id) == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        beers.update(beer);
        return Response.noContent().build();
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        if (beers.findById(id) == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        beers.remove(id);
        return Response.noContent().build();
    }
    
}
