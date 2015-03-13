package br.com.pandox.nursery.acl.rest;

import br.com.pandox.nursery.acl.config.Tcp;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.net.URI;

@Service
public class RestClient {


    public void execute(Tcp tcp){

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        URI uri = tcp.toURI();
        WebTarget target = client.target(uri);

        Response response = target.request().get();

        System.out.println("response = " + response.getStatus());
    }
}
