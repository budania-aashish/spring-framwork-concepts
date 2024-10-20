package com.project.JournalApp.healthcheck;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String getHealthStatus(){
        return healthCheck();
    }
    public String healthCheck(){
        return "Journal App is Healthy!";
    }
}
