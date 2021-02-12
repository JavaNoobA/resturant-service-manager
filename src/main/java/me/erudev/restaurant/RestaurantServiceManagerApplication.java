package me.erudev.restaurant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan(basePackages = {"me.erudev.restaurant.dao"})
@SpringBootApplication
public class RestaurantServiceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceManagerApplication.class, args);
    }

}
