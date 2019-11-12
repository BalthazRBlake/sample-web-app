package co.com.fhhf.deploymentfullapp.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author FHHF
 */
@Controller
public class ErrorController {
    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
