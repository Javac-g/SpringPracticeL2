package com.company.Configurations;

import com.company.Controllers.Controller;
import com.company.Services.Services;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class SpringConfigurationFactory {

    @Bean(name = "Controller")
    @Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Controller controllerBean(){
        Controller controller=  new Controller(servicesBean());
        return controller;
    }
    @Bean("Services")
    public Services servicesBean(){
        return new Services();
    }
}
