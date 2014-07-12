package me.lorenc.workshop.rest.beer;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import me.lorenc.workshop.rest.beer.link.Link;
import me.lorenc.workshop.rest.beer.link.SelfLink;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;

public class Beer {

    private Integer id;
    private String name;

    @InjectLink(resource = BeerResource.class, method = "gime", bindings = {@Binding(name = "id", value = "${instance.id}")})
    private URI href;
    
    public Beer() {
    }
    
    public Beer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public List<Link> getLinks() {
        return Arrays.asList(new SelfLink(href));
    }
    
}
