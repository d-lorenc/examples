package me.lorenc.workshop.rest.beer;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import me.lorenc.workshop.rest.beer.link.Link;

import org.glassfish.jersey.linking.InjectLink;

@Path("/")
public class MainResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Links links() {
        return new Links();
    }

    public static class Links {
        @InjectLink(resource = BeerResource.class)
        private URI beerUri;
        
        public List<Link> getLinks() {
            return Arrays.asList(new Link("gime beer", beerUri));
        }
    }

}
