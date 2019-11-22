package co.com.fhhf.deploymentfullapp.webcontroller;

/**
 *
 * @author FHHF
 */
import co.com.fhhf.deploymentfullapp.model.Person;
import co.com.fhhf.deploymentfullapp.model.User;
import co.com.fhhf.deploymentfullapp.service.PersonService;
import co.com.fhhf.deploymentfullapp.service.UserService;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonBean {

    Logger log = LogManager.getLogger();

    @Autowired
    private PersonService personSerive;
    
    @Autowired
    private UserService userService;

    public PersonBean() {
        log.info("Object PersonaBean Init");
    }

    @GetMapping("/{action}/action")
    public String actionPerson(@PathVariable("action") int action, Model model) {
        Person person = new Person();
        switch (action) {
            case 1:
                model.addAttribute("person", person);
                return "personForm";
            case 2:
                model.addAttribute("person", person);
                return "find";
            default:
                return "home";
        }

    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@Valid Person person, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "personForm";
        }
        User user = userService.findByName(principal.getName());
        person.setUser(user);
        this.personSerive.savePerson(person);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listing(Model model, Principal principal) {
        User user = userService.findByName(principal.getName());
        
        model.addAttribute("people", user.getPeopleList());
        model.addAttribute("principal", principal.getName());
        return "people";
    }

    @PostMapping("/findPerson")
    public String findPersonById(Person fPerson, Model model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "find";
        }

        if (fPerson.getIdPerson() == null) {
            return "redirect:list";
        }

        Person person = this.personSerive.findById(fPerson.getIdPerson());
        if (person.getSurname().equals("Not Found")) {
            return "redirect:list";
        }

        model.addAttribute("person", person);
        model.addAttribute("found", "Result: ");
        return "find";
    }

    @GetMapping("/{idPerson}/edit")
    public String initUpdate(@PathVariable("idPerson") int idPerson, Model model) {
        Person person = personSerive.findById(idPerson);
        model.addAttribute("person", person);
        return "editForm";
    }

    @PostMapping("/{idPerson}/updatePerson")
    public String updatePerson(@Valid Person person, BindingResult bindingResult, @PathVariable("idPerson") int idPerson) {

        if (bindingResult.hasErrors()) {
            return "editForm";
        }

        person.setIdPerson(idPerson);
        this.personSerive.savePerson(person);
        return "redirect:/list";
    }

    @GetMapping("/{idPerson}/deletePerson")
    public String deletePerson(Person person, @PathVariable("idPerson") int idPerson) {
        person.setIdPerson(idPerson);
        this.personSerive.deletePerson(person);
        return "redirect:/list";
    }
}
