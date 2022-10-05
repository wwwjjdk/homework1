package com.example.moscowhm.repository;

import com.example.moscowhm.model.City;
import com.example.moscowhm.model.Person;
import com.example.moscowhm.model.PersonalData;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class MyRepository implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final CityRepository cityRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var cities = Stream.of("Москва", "Волгоград", "Питер")
                .map(n -> City.builder()
                        .name(n)
                        .build()).toList();

        cityRepository.saveAll(cities);

        var persons = List.of("Георгий Савченко", "Григорий Москаленко", "Павел Бубнов");
        Random random = new Random();

        IntStream.range(0, 25).forEach(
                i -> {
                    var who = persons.get(random.nextInt(persons.size())).split(" ");
                    Person person = Person.builder().personalData(PersonalData.builder()
                                    .name(who[0])
                                    .surname(who[1])
                                    .age(random.nextInt(25))
                                    .build())
                            .phoneNumber(1234)
                            .city(cities.get(random.nextInt(cities.size())))
                            .build();
                    personRepository.save(person);
                });
    }

    public List<Person> getPersons(String city) {

        return personRepository.findByCity_Name(city);
    }

   public List<Person> gerPersonsByAge(int age){
        return personRepository.findByPersonalData_AgeLessThanOrderByPersonalData_Age(age);
    }

    public List<Person> getPersonByNameAndLastName(String name, String lastName) throws Throwable {
        return personRepository.findByPersonalData_NameAndPersonalData_Surname(name,lastName)
                .orElseThrow(() -> new RuntimeException("пользователь не найден"));
    }
}
