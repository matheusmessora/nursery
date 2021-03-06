package br.com.pandox.nursery.boot;

import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ComponentScan("br.com.pandox.nursery")
@EnableJpaRepositories({"br.com.pandox.nursery", "br.com.pandox.nursery.domain.monitor.model.repository", "br.com.pandox.nursery.domain.metric.model.repository"})
@EnableTransactionManagement
@EnableScheduling
public class ApplicationBoot {

    private static final Logger LOGGER = LogManager.getLogger();


    public ApplicationBoot() throws Exception {
        LOGGER.debug("Initializing Nursery Application");
    }

    @Autowired
    private Environment env;

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean
    public DriverManagerDataSource dataSource() {
        LOGGER.info("Configurando [dataSource]...");
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(env.getProperty("hibernate.driver"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.user"));
        ds.setPassword(env.getProperty("jdbc.pass"));
        LOGGER.info("Datasource configurado: " + ds.toString());
        return ds;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LOGGER.info("Configurando [entityManagerFactory]...");
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("br.com.pandox.nursery");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);

        Properties hibernateProperties = new Properties();

        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");

        hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.show_sql", "false");

        entityManagerFactoryBean.setJpaProperties(hibernateProperties);

        LOGGER.info(entityManagerFactoryBean);
        return entityManagerFactoryBean;
    }


    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public EventBus getEventBus(){
        return new EventBus();
    }


}
