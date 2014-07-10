package me.lorenc.rest.beer;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class BeerDispenserApplication extends ResourceConfig {

    public BeerDispenserApplication() {
        packages("me.lorenc.rest.beer");
        register(DeclarativeLinkingFeature.class);
    }
    
}
