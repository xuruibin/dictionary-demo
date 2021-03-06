package com.demo.dictionary.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * server start
 *
 * @author Dora B
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.dictionary"})
public class DictionaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class, args);
    }
}