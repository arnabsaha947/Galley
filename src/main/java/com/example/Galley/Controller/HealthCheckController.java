package com.example.Galley.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "This api is reachable";
    }

    @GetMapping("/restricted/admin")
    public String adminAPI(){
        return "This is admin service";
    }

    @GetMapping("/restricted/users")
    public String userAPI(){
        return "This is user service";
    }
}
