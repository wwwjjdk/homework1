package com.example.moscowhm.layers;

import com.example.moscowhm.model.City;
import com.example.moscowhm.model.Person;
import com.example.moscowhm.model.PersonalData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Repository
public class MyRepository implements CommandLineRunner {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var cities = Stream.of("Москва", "Волгоград", "Питер")
                .map(n -> City.builder()
                        .name(n)
                        .build()).toList();
        for (City city : cities) {
            entityManager.persist(city);
        }

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
                    entityManager.persist(person);
                });
    }

    public List<?> getPersons(String city) {
        var answer = entityManager.createQuery("select p from Person p where p.city.name = :city")
                .setParameter("city", city).getResultList();

        return answer;
    }
}
