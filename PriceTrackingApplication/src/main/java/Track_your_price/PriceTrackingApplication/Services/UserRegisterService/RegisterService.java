package Track_your_price.PriceTrackingApplication.Services.UserRegisterService;


import Track_your_price.PriceTrackingApplication.Models.User;
import Track_your_price.PriceTrackingApplication.RepoDao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    UserDao userDao;

    public String registerUser(User user)
    {
        for (User us : userDao.findAll())
        {
            if (us.getEmail().equals(user.getEmail())) {
                return "User Already Registered";
            }
        }
        try {
            userDao.save(user);
        } catch (Exception e) {
            return "Something went wrong try again later" + e;
        }
        return "User Registered";
    }
}
