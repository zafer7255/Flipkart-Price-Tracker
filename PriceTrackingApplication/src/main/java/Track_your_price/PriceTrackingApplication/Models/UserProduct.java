package Track_your_price.PriceTrackingApplication.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class UserProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    @Column(length = 2000)   // or larger if needed
    private String productURL;
    private Integer target_Price;
    //private String product_origin; // Flipkart or Amazon
}
