package br.com.pandox.nursery;

import br.com.pandox.nursery.web.jetty.NurseryServer;

public class Example {

    public static void main(String[] args) throws Exception {
        NurseryServer.start(9092);
        System.out.println("\"SUBIUR\" = " + "SUBIUR");
    }
}
