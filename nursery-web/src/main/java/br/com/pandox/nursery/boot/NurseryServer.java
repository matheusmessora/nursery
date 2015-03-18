package br.com.pandox.nursery.boot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import java.io.IOException;

public class NurseryServer {
    private static final String CONFIG_LOCATION = "br.com.pandox.nursery.boot";

    private static final int DEFAULT_PORT = 15081;
    private static final String VERSION = "vSNAPSHOT";
    private static final String CONTEXT_PATH = "/" + VERSION;
    private static final String MAPPING_URL = "/*";
    private static final String DEFAULT_PROFILE = "staging";

    private static int jettyPort;

    public static void main(String[] args) throws Exception {
        start(DEFAULT_PORT);
    }

    public static void start(Integer port) throws Exception {
        jettyPort = port;
        start();
    }

    private static void start() throws Exception {
        Server server = new Server(jettyPort);
        server.setHandler(getServletContextHandler(getContext()));
        server.start();
    }

    private static WebAppContext getServletContextHandler(WebApplicationContext context) throws
            IOException {
        WebAppContext webApp = new WebAppContext();
        webApp.setErrorHandler(null);
        webApp.setContextPath(CONTEXT_PATH);
        webApp.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        webApp.addEventListener(new ContextLoaderListener(context));
        webApp.setResourceBase("src/main/webapp/");
        return webApp;
    }

    private static WebApplicationContext getContext() throws IOException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        return context;
    }
}
