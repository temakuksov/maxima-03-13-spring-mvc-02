package ru.maxima.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static Long PEOPLE_COUNT = 0L;
    private static final List<Person> people;

    static {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Aram"));
        people.add(new Person(++PEOPLE_COUNT, "Bogdan"));
        people.add(new Person(++PEOPLE_COUNT, "Artem"));
        people.add(new Person(++PEOPLE_COUNT, "Victor"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonById(Long id) {
        return people.stream().filter(person -> person.getId().equals(id)).findAny().orElse(null);
    }

    public static void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
