package br.com.pandox.nursery.web.jetty;

import br.com.pandox.nursery.framework.boot.NurseryBoot;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class NurseryServer {
    private static final String CONFIG_LOCATION = "br.com.pandox.nursery.framework.boot";

    private static final int DEFAULT_PORT = 15081;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";
    private static final String DEFAULT_PROFILE = "dev";

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
        server.join();
    }

    private static WebAppContext getServletContextHandler(WebApplicationContext context) throws
        IOException {
        WebAppContext contextHandler = new WebAppContext();
        contextHandler.setErrorHandler(null);
        contextHandler.setContextPath(CONTEXT_PATH);
        contextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        contextHandler.addEventListener(new ContextLoaderListener(context));
        contextHandler.setResourceBase("src/main/webapp/");
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);
        context.setClassLoader(NurseryServer.class.getClassLoader());
        return context;
    }
}
