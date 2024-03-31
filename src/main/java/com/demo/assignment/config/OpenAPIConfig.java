package com.demo.assignment.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Invoice APIs",
                version = "1.0",
                description = "All the api related to invoice"
        ),
        servers = {
                @Server(
                        url = "http://localhost:2112",
                        description = "Local development server"
                )
        }
)
public class OpenAPIConfig {
}
