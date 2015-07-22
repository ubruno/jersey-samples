package com.ericsson.charter.poc.app;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.ericsson.charter.poc.resources.TitlesResource;
 
public class Main {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
 
        int port = 8080;
        if(args != null && args.length > 0 ){
        	if(args[0].contains("port")){
        		port = Integer.parseInt(args[0].split("=")[1]);
        	}
        }
        
        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);
 
        ServletHolder jerseyServlet = context.addServlet(
             org.glassfish.jersey.servlet.ServletContainer.class, "/rest/*");
        jerseyServlet.setInitOrder(0);
 
        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
           "jersey.config.server.provider.classnames",
           TitlesResource.class.getCanonicalName());
 
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}