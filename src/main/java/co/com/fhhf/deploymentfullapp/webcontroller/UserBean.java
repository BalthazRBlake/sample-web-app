package co.com.fhhf.deploymentfullapp.webcontroller;

import co.com.fhhf.deploymentfullapp.model.User;
import co.com.fhhf.deploymentfullapp.service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author FHHF
 */
@Controller
public class UserBean {

    @Autowired
    private UserService userService;

    @GetMapping("/add/default/users/admin-role-and-user-role")
    public String defaultUserAdmin() {

        if (userService.findByName("admin").getUserName().equals("")) {
            User userAdmin = new User("admin", "admin", "ROLE_ADMIN", true);
            userService.saveUser(userAdmin);
        }
        
        if (userService.findByName("user").getUserName().equals("")) {
            User userUser = new User("user", "user", "ROLE_USER", true);
            userService.saveUser(userUser);
        }
        
        return "redirect:/home";
    }
}
