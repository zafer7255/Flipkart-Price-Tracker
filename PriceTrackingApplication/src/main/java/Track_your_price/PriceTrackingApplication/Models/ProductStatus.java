package Track_your_price.PriceTrackingApplication.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStatus {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private Integer targetPrice;
    @Column(length = 2000)   // or larger if needed
    private String productURL;
    private String status;
    private String trackingId;
}
