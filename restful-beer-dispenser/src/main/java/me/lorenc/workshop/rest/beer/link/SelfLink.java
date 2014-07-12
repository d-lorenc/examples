package me.lorenc.workshop.rest.beer.link;

import java.net.URI;

public class SelfLink extends Link {
    
    public SelfLink(URI href) {
        super("self", href);
    }
    
}