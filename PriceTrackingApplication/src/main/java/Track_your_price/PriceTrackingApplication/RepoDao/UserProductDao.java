package Track_your_price.PriceTrackingApplication.RepoDao;

import Track_your_price.PriceTrackingApplication.Models.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProductDao extends JpaRepository<UserProduct,Long> {
    UserProduct findById(long id);
}
