package LocalConnect.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import LocalConnect.com.Entity.ActivityPost;
import LocalConnect.com.Entity.User;
import LocalConnect.com.Service.ActivityService;
import LocalConnect.com.Service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    ActivityService activityService;

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails principal, Model model) {
        User currentUser = userService.getByEmailOrThrow(principal.getUsername());
        List<ActivityPost> feed = activityService.getFeedForLocality(currentUser.getLocality());

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("feed", feed);
        return "home";
    }
    
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
