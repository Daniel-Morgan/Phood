package com.doughepi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                SecurityAutoConfiguration.class
        })
@ComponentScan(basePackages = {"com.doughepi"})
@EntityScan(basePackages = {"com.doughepi"})
@ImportResource("classpath:/spring-config.xml")
@SpringBootApplication
public class PhoodApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(PhoodApplication.class, args);
    }
}
