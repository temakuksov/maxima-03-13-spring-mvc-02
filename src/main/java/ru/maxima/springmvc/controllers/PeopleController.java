package ru.maxima.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springmvc.dao.PersonDAO;
import ru.maxima.springmvc.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getPeople(Model model) {
        // получаем всех людей из DAO и передаем их в представление
        model.addAttribute("allPeople", personDAO.getAllPeople());
        return "people/get-all-people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("onePersonById", personDAO.getPersonById(id));
        return "people/get-person";
    }

    @GetMapping("/new")
    public String getFormToCreateNewPerson(Model model) {
        model.addAttribute("newPerson", new Person());
        return "people/new-person";
    }

    @PostMapping()
    public String createPerson(@RequestParam("name") String name) {
        Person person = new Person();
        person.setName(name);
        PersonDAO.save(person);
        return "redirect:/people";
    }

}
