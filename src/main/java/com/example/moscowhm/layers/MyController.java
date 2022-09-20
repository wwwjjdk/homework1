package com.example.moscowhm.layers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class MyController {

    @Autowired
    MyService service;

    @GetMapping("/by-city/{city}")
    public List<?> getPerson(@PathVariable String city) {
        System.out.println(city);
        return service.getPersons(city);
    }
}
