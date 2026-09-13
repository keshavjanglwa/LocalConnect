package LocalConnect.com.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserConroller {

    @GetMapping("/home")
    public String UserHome(){
        return "YOU ARE IN THE USER PAGE";
    }
    
}
