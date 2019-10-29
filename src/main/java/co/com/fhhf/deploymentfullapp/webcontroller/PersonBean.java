package co.com.fhhf.deploymentfullapp.webcontroller;

/**
 *
 * @author FHHF
 */
import co.com.fhhf.deploymentfullapp.model.Person;
import co.com.fhhf.deploymentfullapp.service.PersonService;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonBean {

    Logger log = LogManager.getLogger();

    @Autowired
    private PersonService servicePerson;

    public PersonBean() {
        log.info("Object PersonaBean Init");
    }

    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "home";
    }

    @GetMapping("/action")
    public String actionPerson(@RequestParam(name = "action", required = true, defaultValue = "0") int action, Model model) {
        Person person = new Person();
        switch (action) {
            case 1:
                model.addAttribute("newPerson", person);
                return "personForm";
            case 2:
                model.addAttribute("fPerson", person);
                return "find";
            default:
                return "home";
        }

    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person person) {
        servicePerson.savePerson(person);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String listing(Model model) {
        model.addAttribute("people", servicePerson.personList());
        return "people";
    }

    @PostMapping("/findPerson")
    public String findPersonById(@ModelAttribute Person person, Model model) {
        Person fPerson = servicePerson.findById(person.getIdPerson());
        model.addAttribute("fPerson", fPerson);
        model.addAttribute("found", "Result: ");
        return "find";
    }

    @GetMapping("/{idPerson}/edit")
    public String initUpdate(@PathVariable("idPerson") int idPerson, Model model) {
        Person uPerson = servicePerson.findById(idPerson);
        model.addAttribute("uPerson", uPerson);
        return "editForm";
    }

    @PostMapping("/{idPerson}/updatePerson")
    public String updatePerson(@Valid Person person, @PathVariable("idPerson") int idPerson) {
        person.setIdPerson(idPerson);
        this.servicePerson.savePerson(person);
        return "redirect:/list";
    }

    @GetMapping("/{idPerson}/deletePerson")
    public String deletePerson(@Valid Person person, @PathVariable("idPerson") int idPerson) {
        person.setIdPerson(idPerson);
        this.servicePerson.deletePerson(person);
        return "redirect:/list";
    }
}
