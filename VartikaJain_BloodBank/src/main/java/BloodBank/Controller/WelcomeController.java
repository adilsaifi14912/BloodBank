package BloodBank.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

}
