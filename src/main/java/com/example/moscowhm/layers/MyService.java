package com.example.moscowhm.layers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    MyRepository repository;

    public List<?> getPersons(String city){
        return repository.getPersons(city);
    }
}
