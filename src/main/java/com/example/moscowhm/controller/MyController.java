package com.example.moscowhm.controller;

import com.example.moscowhm.model.Person;
import com.example.moscowhm.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/persons")
public class MyController {

    @Autowired
    MyService service;

    @GetMapping("/by-city/{city}")
    public List<Person> getPerson(@PathVariable String city) {
        System.out.println(city);
        return service.getPersons(city);
    }
    @GetMapping("/by-age/{age}")
    public List<Person> gerPersonsByAge(@PathVariable int age){
        return service.gerPersonsByAge(age);
    }

    @GetMapping("/by-person/{name}/{lastname}")
    public List<Person> searchPerson(@PathVariable String name, @PathVariable String lastname) throws Throwable {
        return service.getPersonByNameAndLastName(name,lastname);
    }
 @ExceptionHandler(RuntimeException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
    String exceptionHandlerCustom(RuntimeException e){
        return e.getMessage();
    }
}
