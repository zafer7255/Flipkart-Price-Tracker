package Track_your_price.PriceTrackingApplication.RepoDao;

import Track_your_price.PriceTrackingApplication.Models.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusDao extends JpaRepository<ProductStatus,Long> {
    ProductStatus findById(long id);
}
