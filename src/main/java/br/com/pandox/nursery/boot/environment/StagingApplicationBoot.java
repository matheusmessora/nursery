package br.com.pandox.nursery.boot.environment;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("staging")
@PropertySource("classpath:application-dev.properties")
public class StagingApplicationBoot {


}
