package br.com.pandox.nursery.boot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class NurseryServer {
    private final String CONFIG_LOCATION = "br.com.pandox.nursery.boot";

    public static final int DEFAULT_PORT = 15081;
    public static final String VERSION = "vSNAPSHOT";
    public static final String CONTEXT_PATH = "/api/" + VERSION;
    public static final String MAPPING_URL = "/*";
    public static final String DEFAULT_PROFILE = "staging";

    private int jettyPort;

    private Server server;

    public static void main(String[] args) throws Exception {
        NurseryServer server = new NurseryServer();
        server.start(NurseryServer.DEFAULT_PORT);
    }

    public void start() throws Exception {
        start(0);
    }

    public void start(Integer port) throws Exception {
        server = new Server(port);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
        ServerConnector connector = (ServerConnector) server.getConnectors()[0];
        jettyPort = connector.getLocalPort();
    }

    public void stop() throws Exception {
        server.stop();
        server.destroy();
    }

    private WebAppContext getServletContextHandler(WebApplicationContext context) throws
            IOException {
        WebAppContext webApp = new WebAppContext();
        webApp.setErrorHandler(null);
        webApp.setContextPath(CONTEXT_PATH);
        webApp.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        webApp.addEventListener(new ContextLoaderListener(context));
        webApp.setResourceBase("src/main/webapp/");
        return webApp;
    }

    private WebApplicationContext getContext() throws IOException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }

    public int getJettyPort() {
        return jettyPort;
    }

}
