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

    public PersonBean() {
        log.info("Inicia Object PersonaBean");
    }

    @Autowired
    private PersonService service;

    @GetMapping("/agregaP") //hace map al metodo GET en la url indicada
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        Person p = new Person();
        if (name.equals("")) {
        }
        else{
            p.setNombre(name);
            service.addPerson(p);
            model.addAttribute("pAgregada", p.getNombre());
        }
        return "agregar"; //llama la vista (html) con ese nombre
    }
}