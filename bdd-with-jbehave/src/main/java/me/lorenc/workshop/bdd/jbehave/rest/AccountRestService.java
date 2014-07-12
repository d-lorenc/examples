package me.lorenc.workshop.bdd.jbehave.rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class AccountRestService {

    private Server server;
    
    public void start() {
        server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new ServletContainer(new ResourceConfig(IsAliveResource.class, AccountResource.class))), "/");
        
        try {
            server.start();
            while (!server.isStarted()) {
                Thread.sleep(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() throws Exception {
        if (server != null) {
            if (server.isRunning()) {
                server.stop();
            }
            server.destroy();
            server = null;
        }
    }
    
	public static void main(String[] args) {
    	new AccountRestService().start(); 
    }

}
