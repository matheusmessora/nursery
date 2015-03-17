package br.com.pandox.nursery.web.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    }

    private static WebAppContext getServletContextHandler(WebApplicationContext context) throws
            IOException {
        WebAppContext webApp = new WebAppContext();
        webApp.setErrorHandler(null);
        webApp.setContextPath(CONTEXT_PATH);
        webApp.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        webApp.addEventListener(new ContextLoaderListener(context));
        webApp.setResourceBase("src/main/webapp/");
//        webApp.setExtraClasspath(getPluginsClassPath());

        return webApp;
    }

    private static WebApplicationContext getContext() throws IOException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        context.getEnvironment().setDefaultProfiles(DEFAULT_PROFILE);

//        List<URL> urls = new ArrayList<>();
//        for (File plugin : getPlugins()) {
//            urls.add(plugin.toURL());
//        }
//        URL[] urlsArray = new URL[urls.size()];
//        urls.toArray(urlsArray);
//        context.setClassLoader(new URLClassLoader(urlsArray, NurseryServer.class.getClassLoader()));

        return context;
    }

    public static String getPluginsClassPath() {
        StringBuilder pluginsClasspath = new StringBuilder();
        boolean delim = false;
        List<File> extraClasspath = getPlugins();

        for (File cp : extraClasspath)
        {
            if (delim)
            {
                pluginsClasspath.append(",");
            }
            pluginsClasspath.append(cp.getAbsolutePath());
            delim = true;
        }



        return pluginsClasspath.toString();
    }

    public static List<File> getPlugins() {
        List<File> plugins = new ArrayList<>();

        File pluginsDir = new File("/home/matheus/apps/nursery/");
        for (File jar : pluginsDir.listFiles()) {
            if (!jar.isFile()) {
                continue;
            }
            if (jar.getName().toLowerCase(Locale.ENGLISH).endsWith(".jar")) {
                plugins.add(jar);
            }
        }
        return plugins;
    }
}
