package br.com.pandox.nursery.acl.rest;

import br.com.pandox.nursery.acl.config.Tcp;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class RestClient {


    public StatusLine execute(Tcp tcp){

        try {
            StatusLine statusLine = Request.Get(tcp.toURI())
                    .connectTimeout(500)
                    .socketTimeout(500)
                    .execute().returnResponse().getStatusLine();

            return statusLine;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
