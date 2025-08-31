package Track_your_price.PriceTrackingApplication.Controllers.UserController;


import Track_your_price.PriceTrackingApplication.Models.User;
import Track_your_price.PriceTrackingApplication.Services.UserServices.Userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    Userservices userservices;

    /*@PostMapping("/register")
    public ResponseEntity<String> Register_User(@RequestBody User user)
    {

    }*/
}
