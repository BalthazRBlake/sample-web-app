package co.com.fhhf.deploymentfullapp.webcontroller;

import co.com.fhhf.deploymentfullapp.model.User;
import co.com.fhhf.deploymentfullapp.service.UserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author FHHF
 */
@Controller
public class UserBean {

    @Autowired
    private UserService userService;
       
    @GetMapping("/user/new")
    public String initNewUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "userForm";
    }
    
    @PostMapping("/newUser")
    public String newUser(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "userForm";
        }
        user.setRoles("ROLE_USER");
        user.setActive(true);
        this.userService.saveUser(user);
        return "login";
    }

}
