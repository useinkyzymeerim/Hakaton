package com.jtbc.weeklymenu.controller.util;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {
    @Bean
    public OpenAPI configure() {
        return new OpenAPI()
                .info(new Info()
                        .title("WeeklyMenu")
                        .description("У нас тут готовое меню на неделю")
                        .version("1.0.0")
                        .contact(new Contact().name("Aijan, Meerim, Elhan")
                                .email("aijan@mail"))
                );
    }
}
