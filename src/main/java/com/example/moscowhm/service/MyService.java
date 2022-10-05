package com.example.moscowhm.service;

import com.example.moscowhm.model.Person;
import com.example.moscowhm.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    MyRepository repository;

    public List<Person> getPersons(String city){
        return repository.getPersons(city);
    }

    public List<Person> gerPersonsByAge(int age){
        return  repository.gerPersonsByAge(age);
    }

    public List<Person> getPersonByNameAndLastName(String name, String lastname) throws Throwable {
        return repository.getPersonByNameAndLastName(name,lastname);
    }
}
