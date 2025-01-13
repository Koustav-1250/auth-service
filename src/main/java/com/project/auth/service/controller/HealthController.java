package com.project.auth.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> getHealth(){
        return ResponseEntity.ok().body("service is up and running");
    }
}
