package com.portability.framework.adapters.config;

import com.portability.PortabilityApplication;
import com.portability.application.ports.out.PortabilityRepository;
import com.portability.application.services.PortabilityServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PortabilityApplication.class)
public class ServiceConfig {

    @Bean
    PortabilityServiceImp portabilityService(PortabilityRepository repository, ModelMapper mapper) {
        return new PortabilityServiceImp(repository, mapper);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
