package br.com.pandox.nursery.boot.environment;

import br.com.pandox.nursery.monitors.ServletResponseTimeMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@Profile("staging")
@PropertySource("classpath:application-dev.properties")
public class StagingApplicationBoot {


}
