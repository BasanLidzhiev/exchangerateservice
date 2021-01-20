package ru.lidzhiev.exchangerateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangerateserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangerateserviceApplication.class, args);
    }

}
