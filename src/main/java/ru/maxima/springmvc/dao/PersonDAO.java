package ru.maxima.springmvc.dao;

import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static long PEOPLE_COUNT = 0L;
    private static List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Aram", "Great"));
        people.add(new Person(++PEOPLE_COUNT, "Bogdan", "Titomir"));
        people.add(new Person(++PEOPLE_COUNT, "Artem", "Ivanov"));
        people.add(new Person(++PEOPLE_COUNT, "Victor", "Mentor"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonById(long id) {
        return people.stream().filter(person -> person.getId() == (id)).findAny().orElse(null);
    }

    public static void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(long id, Person updatedPerson) {
        Person personToBeUpd = getPersonById(id);
        personToBeUpd.setName(updatedPerson.getName());
        personToBeUpd.setSurname(updatedPerson.getSurname());
    }

    public void delete(long id) {
        people.removeIf(person -> person.getId() == id);
    }
}
