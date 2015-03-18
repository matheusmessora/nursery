package br.com.pandox.nursery.boot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("br.com.pandox.nursery")
public class ApplicationBoot extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LogManager.getLogger();

    public ApplicationBoot() throws Exception {
        LOGGER.debug("Initializing Nursery Application");
    }
}
