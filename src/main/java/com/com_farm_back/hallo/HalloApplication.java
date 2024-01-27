package com.com_farm_back.hallo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HalloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HalloApplication.class, args);
        System.out.println("crops okay");
    }

}
