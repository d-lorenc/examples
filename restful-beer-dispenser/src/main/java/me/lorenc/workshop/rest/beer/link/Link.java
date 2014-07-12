package me.lorenc.workshop.rest.beer.link;

import java.net.URI;

public class Link {
    
    private String rel;
    private URI href;

    public Link(String rel, URI href) {
        this.rel = rel;
        this.href = href;
    }
    
    public String getRel() {
        return rel;
    }
    
    public URI getHref() {
        return href;
    }
    
}