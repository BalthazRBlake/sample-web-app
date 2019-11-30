package co.com.fhhf.deploymentfullapp.webcontroller;

import co.com.fhhf.deploymentfullapp.model.Person;
import co.com.fhhf.deploymentfullapp.model.User;
import co.com.fhhf.deploymentfullapp.service.PersonService;
import co.com.fhhf.deploymentfullapp.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author FHHF
 */
@Controller
public class UserBean {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PersonService personSerive;

    @GetMapping("/user/new")
    public String initNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userForm";
    }

    @PostMapping("/newUser")
    public String newUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "userForm";
        }
        user.setRoles("ROLE_USER");
        user.setActive(true);
        this.userService.saveUser(user);
        return "login";
    }

    @GetMapping("/userProfile")
    public String userProfile() {
        return "profile";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Principal principal) {
        User user = userService.findByName(principal.getName());
        
        List <Person> people = user.getPeopleList();
        
        for(Person person : people){
            this.personSerive.deletePerson(person);
        }
        
        this.userService.deleteUser(user);

        return "redirect:/userLogout";
    }

    @RequestMapping(value = "/userLogout")
    public String userLogout(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.getContext().setAuthentication(null);

        return "redirect:/login?logout";
    }
}
