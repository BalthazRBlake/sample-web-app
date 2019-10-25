package co.com.fhhf.deploymentfullapp.webcontroller;

import co.com.fhhf.deploymentfullapp.model.Person;
import co.com.fhhf.deploymentfullapp.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonBean {

    Logger log = LogManager.getLogger();

    @Autowired
    private PersonService servicePerson;
    
    public PersonBean() {
        log.info("Object PersonaBean Init");
    }

    @GetMapping("/addP") //hace map al metodo GET en la url indicada
    public String adding(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        Person p = new Person();
        if (name.equals("")) {
        }
        else{
            p.setName(name);
            servicePerson.insertPerson(p);
            model.addAttribute("personAdded", p.getName());
        }
        return "people"; //llama la vista (html) con ese nombre
    }
    
    @GetMapping("/list")
    public String listing(Model model){
        model.addAttribute("people", servicePerson.personList());
        return "people";
    }
    
}