package com.example.inTest.api;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Контроллер HelloWorld для второго уровня
@Data
@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String HelloWorld() {
        return "HelloWorld";
    }
}
