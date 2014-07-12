package me.lorenc.workshop.rest.beer;

import me.lorenc.workshop.rest.beer.error.JsonFormatErrorHandler;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class BeerDispenserService {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        configureJsonErrorHandler(server);
        addJerseyApplication(server, new BeerDispenserApplication());
        
        server.start();
    }

    private static void addJerseyApplication(Server server, ResourceConfig resourceConfig) {
        ServletContextHandler context = new ServletContextHandler(server, "/");
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));
        context.addServlet(servletHolder, "/*");
    }

    private static void configureJsonErrorHandler(Server server) {
        ErrorHandler errorHandler = new JsonFormatErrorHandler();
        errorHandler.setServer(server);
        server.addBean(errorHandler);
    }
    
}
