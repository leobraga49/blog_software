package com.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${PROD_DB_HOST}")
    private String dbHost;

    @Value("${PROD_DB_PORT}")
    private String dbPort;

    @Value("${PROD_DB_NAME}")
    private String dbName;

    @Value("${PROD_DB_USERNAME}")
    private String dbUsername;

    @Value("${PROD_DB_PASSWORD}")
    private String dbPassword;

    @GetMapping("/env")
    public String showEnv() {
        return String.format("DB Host: %s, DB Port: %s, DB Name: %s, DB Username: %s, DB Password: %s",
                dbHost, dbPort, dbName, dbUsername, dbPassword);
    }
}