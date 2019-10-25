package co.com.fhhf.deploymentfullapp.webcontroller;

import co.com.fhhf.deploymentfullapp.model.Person;
import co.com.fhhf.deploymentfullapp.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonBean {

    Logger log = LogManager.getLogger();

    @Autowired
    private PersonService servicePerson;
    
    @Autowired
    MessageSource mesageSource;
       
    public PersonBean() {
        log.info("Object PersonaBean Init");
    }
    
    @RequestMapping(value="", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(){
        return "home";
    }
    
    @GetMapping("/addPersonForm")
    public String insertPerson(Model model) {
        Person per = new Person();
        model.addAttribute("newPerson", per);
        return "personForm";
    }
    
    @RequestMapping(value="/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person per){
        servicePerson.insertPerson(per);
        return "redirect:list";
    }
    
    @GetMapping("/list")
    public String listing(Model model){
        model.addAttribute("people", servicePerson.personList());
        return "people";
    }
    
}