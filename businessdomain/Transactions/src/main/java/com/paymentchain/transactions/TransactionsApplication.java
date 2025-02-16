package com.paymentchain.transactions;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionsApplication.class, args);
    }

    @Bean
    public OpenAPI CustomOpenApi() {
        return new OpenAPI().components(new Components()).info(new Info().title("Transactions API").version("1.0.0"));
    }
}
