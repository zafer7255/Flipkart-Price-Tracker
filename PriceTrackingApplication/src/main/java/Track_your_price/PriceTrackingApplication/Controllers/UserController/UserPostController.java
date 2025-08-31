package Track_your_price.PriceTrackingApplication.Controllers.UserController;

import Track_your_price.PriceTrackingApplication.Models.TaskResponse;
import Track_your_price.PriceTrackingApplication.Models.UrlRequest;
import Track_your_price.PriceTrackingApplication.Services.UserServices.Userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserPostController {

    @Autowired
    Userservices userservices;

    @PostMapping("/scrape")
    public ResponseEntity<?> scrape_start(@RequestBody UrlRequest urlRequest)
    {
        TaskResponse taskResponse = userservices.HitScrapping(urlRequest);

        if (taskResponse.getTask_id() == null || taskResponse.getTask_id().isEmpty()) {
            TaskResponse taskResponse1 = new TaskResponse();
            taskResponse1.setMessage("Something Went Wrong");
            return new ResponseEntity<>(taskResponse1.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskResponse.getMessage(),HttpStatus.OK);
    }
}
