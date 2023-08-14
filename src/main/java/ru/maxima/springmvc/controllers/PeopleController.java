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
    public String createPerson(@ModelAttribute("newPerson") Person person) {
        PersonDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") Long id) {
        model.addAttribute("editPerson", personDAO.getPersonById(id));
        return "people/edit-person";
    }

    @PostMapping("/{id}")
    public String updatePerson(@ModelAttribute("editPerson") Person person, @PathVariable("id") Long id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }
}
