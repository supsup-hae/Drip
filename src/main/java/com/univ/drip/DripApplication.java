package com.univ.drip;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DripApplication {
    public static void main(String[] args) {
        SpringApplication.run(DripApplication.class, args);
    }
}
