package com.vm.GWConnector.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Publishes the OpenAPI document and Swagger UI for the connector's public REST API.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gwConnectorOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GWConnector API")
                        .version("1.0")
                        .description("REST API for Guidewire policy, claim, FNOL, and claim-adjustor operations.")
                        .contact(new Contact().name("GWConnector Team"))
                        .license(new License().name("Internal use")))
                .servers(List.of(
                        new Server().url("/").description("Current deployment")));
    }
}
