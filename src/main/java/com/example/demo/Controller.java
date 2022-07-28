package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {


    @GetMapping("/hello")
    public Model hello(String firstName){

        var model = new Model();
        model.setFirstName(firstName);
        return model;

    }
}
