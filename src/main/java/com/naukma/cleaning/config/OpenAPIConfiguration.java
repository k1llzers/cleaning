package com.naukma.cleaning.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
  info = @Info(
  title = "Cleaning Application",
  description = "TEAM 1 PROJECT"),
  servers = @Server(url = "http://localhost:8080")
)
class OpenAPIConfiguration {
}