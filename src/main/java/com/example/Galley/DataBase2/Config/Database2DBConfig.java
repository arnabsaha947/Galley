package com.example.Galley.DataBase2.Config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "db2EntityManagerFactory",
        transactionManagerRef = "db2TransactionManager",
        basePackages = {"com.example.Galley.DataBase2.Repository"} )
public class Database2DBConfig {

    @Autowired
    Environment env;

    // to return datasource properties of desired datasource

    @ConfigurationProperties(prefix = "database2.datasource")
    @Bean(name = "database2DataSource")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("database2.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("database2.datasource.url"));
        dataSource.setUsername(env.getProperty("database2.datasource.username"));
        dataSource.setPassword(env.getProperty("database2.datasource.password"));

        return dataSource;
        //return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("database2DataSource") DataSource dataSource){

        return builder.dataSource(dataSource).packages("com.example.Galley.DataBase2.Entity")
                .persistenceUnit("Database2Entity").build();

    }

    // This Method is executed.
    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(@Qualifier("db2EntityManagerFactory")EntityManagerFactory db2EntityManagerFactory){
        return new JpaTransactionManager(db2EntityManagerFactory);
    }

}
