package Track_your_price.PriceTrackingApplication.Services.UserServices;


import Track_your_price.PriceTrackingApplication.Models.ProductStatus;
import Track_your_price.PriceTrackingApplication.RepoDao.ProductStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CheckThePrice {

    private String server_url_for_scrape_price = "http://localhost:8000/result/";
    @Autowired
    ProductStatusDao productStatusDao;
    public String Scrape_The_Price(String track_id , long productId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = server_url_for_scrape_price + track_id;
        //Get Json as a Map
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Map<String, Object> body = response.getBody();
            String status = (String) body.get("status");

            // find product by id
            ProductStatus productStatus = productStatusDao.findById(productId);
            if (productStatus == null) {
                throw new RuntimeException("Product not found with id: " + productId);
            }
            if ("Success".equals(status)) {

                // ✅ update status to SUCCESS
                productStatus.setStatus(status);
                productStatusDao.save(productStatus);
                return body.get("price").toString();

            } else {
                if (body.containsKey("reason")) {
                    System.out.println("Reason: " + body.get("reason"));
                }

                // ✅ update status with whatever came back
                productStatus.setStatus(status);
                productStatusDao.save(productStatus);
                return status;
            }
        } else {
            return "Error: Unable to fetch result";
        }
    }

}
