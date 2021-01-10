package com.myorg.technical.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.myorg.technical.service")
@EntityScan("com.myorg.technical.service.entity")
public class ServiceConfiguration {
}
