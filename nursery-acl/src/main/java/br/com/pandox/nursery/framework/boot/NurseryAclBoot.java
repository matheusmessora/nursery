package br.com.pandox.nursery.framework.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("br.com.pandox.nursery.acl")
@EnableScheduling
public class NurseryAclBoot {

    public NurseryAclBoot() {
        System.out.println("\"OPA\" = " + "OPA");
    }


}
