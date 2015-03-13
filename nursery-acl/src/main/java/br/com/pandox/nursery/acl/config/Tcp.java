package br.com.pandox.nursery.acl.config;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Tcp {

    private static final int SSL_PORT = 443;
    private String url;
    private Integer port;

    public Tcp(String url) {
        this.url = url.split(":")[0];
        this.port = Integer.valueOf(url.split(":")[1]);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public URI toURI() {
        try {
            String protocol = isSSL() ? "https" : "http";
            return new URL(protocol, getUrl(), getPort(), "/").toURI();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isSSL(){
        return getPort().equals(SSL_PORT);
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Tcp{");
        sb.append("url='").append(url).append('\'');
        sb.append(", port=").append(port);
        sb.append('}');
        return sb.toString();
    }
}
