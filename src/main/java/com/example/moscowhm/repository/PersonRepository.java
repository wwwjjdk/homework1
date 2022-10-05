package com.example.moscowhm.repository;

import com.example.moscowhm.model.City;
import com.example.moscowhm.model.Person;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List <Person> findByCity_Name(String city_name);

    List<Person> findByPersonalData_AgeLessThanOrderByPersonalData_Age(int agePerson);

    Optional<List<Person>> findByPersonalData_NameAndPersonalData_Surname(String name, String surname);
}
