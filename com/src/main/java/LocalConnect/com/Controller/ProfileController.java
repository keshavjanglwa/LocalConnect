package LocalConnect.com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import LocalConnect.com.Entity.User;
import LocalConnect.com.Service.UserService;

@Controller
public class ProfileController {
    @Autowired
    private UserService userService;

    private User currentUser(UserDetails principal) {
        return userService.getByEmailOrThrow(principal.getUsername());
    }
    
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetails principal, Model model) {
        model.addAttribute("currentUser", currentUser(principal));
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal UserDetails principal,
                                 @RequestParam String name,
                                 @RequestParam String locality) {
        User user = currentUser(principal);
        userService.updateProfile(user.getId(), name, locality);
        return "redirect:/profile";
    }
}
