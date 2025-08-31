package Track_your_price.PriceTrackingApplication.RepoDao;

import Track_your_price.PriceTrackingApplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
}
