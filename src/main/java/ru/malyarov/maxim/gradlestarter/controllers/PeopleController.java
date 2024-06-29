package ru.malyarov.maxim.gradlestarter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.malyarov.maxim.gradlestarter.models.Person;
import ru.malyarov.maxim.gradlestarter.dao.PersonDAO;

import java.util.UUID;

@Controller
@RequestMapping("/person")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("person", new Person());
        return "new";
    }

    @PostMapping
    public String add(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/person";
    }

}
