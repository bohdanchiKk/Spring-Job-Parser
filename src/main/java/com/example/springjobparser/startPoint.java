package com.example.springjobparser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class startPoint {
    @GetMapping("/start")
    public static String start(){
        App app = new App();
        app.main();
        return "Started";
    }
}
