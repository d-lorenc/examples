package com.dlorenc.examples.bdd.jbehave.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/isAlive")
public class IsAliveResource {

    private static final String I_M_ALIVE = "I'm alive";

    @GET
    public String isAlive() {
        return I_M_ALIVE;
    }
    
}
