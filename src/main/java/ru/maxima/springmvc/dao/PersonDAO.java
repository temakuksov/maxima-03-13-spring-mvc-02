package ru.maxima.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(1L, "Aram"));
        people.add(new Person(2L, "Bogdan"));
        people.add(new Person(3L, "Artem"));
        people.add(new Person(4L, "Victor"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonById(Long id) {
        return people.stream().filter(person -> person.getId().equals(id)).findAny().orElse(null);
    }
}
