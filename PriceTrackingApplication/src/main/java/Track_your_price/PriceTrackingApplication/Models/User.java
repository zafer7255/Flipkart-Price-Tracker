package Track_your_price.PriceTrackingApplication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {

    private String name;
    @Id
    private String email;
    private String phoneNo;
    private String role;
    private boolean isVerified = false;
}
