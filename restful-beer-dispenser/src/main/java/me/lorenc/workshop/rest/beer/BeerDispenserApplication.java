package me.lorenc.workshop.rest.beer;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class BeerDispenserApplication extends ResourceConfig {

    public BeerDispenserApplication() {
        packages("me.lorenc.workshop.rest.beer");
        register(DeclarativeLinkingFeature.class);
    }
    
}
