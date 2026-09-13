package LocalConnect.com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import LocalConnect.com.Entity.User;
import LocalConnect.com.Service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String index() {
        return "/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User formUser,
                            Model model) {
        try {
            userService.register(formUser.getName(), formUser.getEmail(),
                    formUser.getPassword(), formUser.getLocality());
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
        return "redirect:/login?registered=true";
    }
}
