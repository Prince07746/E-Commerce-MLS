package com.ecommerceapp.e_Commerce_App.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan("com.ecommerceapp.e_Commerce_App")
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI (){
        return new OpenAPI()
                .info(new Info()
                        .title("E-commerce Order Management API")
                        .version("1.0")
                        .description("API documentation for the E-commerce system"))
                .servers(Arrays.asList(
                        new Server().url("http://localhost:9090").description("Development Server"),
                        new Server().url("https://test.com/api").description("Testing Server"),
                        new Server().url("https://prod.com/api").description("Production Server")
                ));

    }
}

