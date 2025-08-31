package Track_your_price.PriceTrackingApplication.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UrlRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private Integer targetPrice;
    private String name;
    @Column(length = 2000)   // or larger if needed
    private String url;
    public UrlRequest(String url) {
        this.url = url;
    }
}
