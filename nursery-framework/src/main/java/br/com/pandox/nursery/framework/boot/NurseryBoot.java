package br.com.pandox.nursery.framework.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("br.com.pandox.nursery")
public class NurseryBoot {

    public NurseryBoot() {
        System.out.println("\"OPA\"\" = " + "OPA");
    }
}
