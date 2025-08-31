package Track_your_price.PriceTrackingApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class PriceTrackingApplication
{
	public static void main(String[] args) {

        SpringApplication.run(PriceTrackingApplication.class, args);
	}
}
