package com.example.Galley.GalleyDatabase.Config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "galleyEntityManagerFactory",
        transactionManagerRef = "galleyTransactionManager",
        basePackages = {"com.example.Galley.GalleyDatabase.Repository"} )
public class GalleyDBConfig {

    @Autowired
    Environment env;

    // to return datasource properties of desired datasource
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(name = "galleyDataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
        //return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "galleyEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("galleyDataSource") DataSource dataSource){

        return builder.dataSource(dataSource).packages("com.example.Galley.GalleyDatabase.Entity")
                .persistenceUnit("UserRegistrationEntity").build();

    }

    // This Method is executed.
    @Primary
    @Bean(name = "galleyTransactionManager")
    public PlatformTransactionManager db2TransactionManager(@Qualifier("galleyEntityManagerFactory")EntityManagerFactory db2EntityManagerFactory){
        return new JpaTransactionManager(db2EntityManagerFactory);
    }

}
