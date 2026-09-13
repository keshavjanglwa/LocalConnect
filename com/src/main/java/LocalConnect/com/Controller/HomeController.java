package LocalConnect.com.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    
    @GetMapping("/home-page")
    public String HomePage(){
        return "YOU ARE IN THE HOME PAGE";
    }
    
}


