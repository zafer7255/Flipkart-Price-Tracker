package Track_your_price.PriceTrackingApplication.Services.UserServices;

import Track_your_price.PriceTrackingApplication.Models.ProductStatus;
import Track_your_price.PriceTrackingApplication.Models.TaskResponse;
import Track_your_price.PriceTrackingApplication.Models.UrlRequest;
import Track_your_price.PriceTrackingApplication.Models.UserProduct;
import Track_your_price.PriceTrackingApplication.RepoDao.ProductStatusDao;
import Track_your_price.PriceTrackingApplication.RepoDao.UserProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class Userservices {

    private String server_url_for_hitScrape = "http://localhost:8000/scrape"; // That give me some
    private boolean lets_check = false;
    @Autowired
    private ProductStatusDao productStatusDao;
    @Autowired
    private UserProductDao userProductDao;

    public TaskResponse HitScrapping(UrlRequest urlRequest)
    {
        UserProduct userProduct = new UserProduct();
        //Let's save all the ProductDetailsdetails
        userProduct.setEmail(urlRequest.getEmail());
        userProduct.setProductURL(urlRequest.getUrl());
        userProduct.setName(urlRequest.getName());
        userProduct.setTarget_Price(urlRequest.getTargetPrice());
        userProductDao.save(userProduct);


        RestTemplate restTemplate = new RestTemplate();
        UrlRequest requestBody = new UrlRequest(urlRequest.getUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UrlRequest > request = new HttpEntity<>(requestBody,headers);
        ResponseEntity<TaskResponse> response = restTemplate.postForEntity(
                server_url_for_hitScrape,
                request,
                TaskResponse.class
        );
        TaskResponse res = response.getBody();

        //Save the product status coming from the scraper
        ProductStatus productStatus = new ProductStatus();
        productStatus.setEmail(urlRequest.getEmail());
        productStatus.setTargetPrice(urlRequest.getTargetPrice());
        productStatus.setProductURL(urlRequest.getUrl());
        productStatus.setStatus(res.getMessage());
        productStatus.setTrackingId(res.getTask_id());
        productStatusDao.save(productStatus);

        System.out.println(res.getTask_id());

        return  res;
    }


}
