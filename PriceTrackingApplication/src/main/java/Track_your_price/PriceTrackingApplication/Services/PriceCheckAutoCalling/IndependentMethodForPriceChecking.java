package Track_your_price.PriceTrackingApplication.Services.PriceCheckAutoCalling;


import Track_your_price.PriceTrackingApplication.Models.ProductStatus;
import Track_your_price.PriceTrackingApplication.RepoDao.ProductStatusDao;
import Track_your_price.PriceTrackingApplication.Services.SendMail.SendMailForPriceTrackingUpdate;
import Track_your_price.PriceTrackingApplication.Services.UserServices.CheckThePrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IndependentMethodForPriceChecking {

    @Autowired
    ProductStatusDao productStatusDao;
    @Autowired
    CheckThePrice checkThePrice;
    @Autowired
    SendMailForPriceTrackingUpdate sendMailForPriceTrackingUpdate;

    @Scheduled(fixedDelay = 15000) // run again only after finishing
    public void checkPriceOneByOne() {

        for (ProductStatus ps : productStatusDao.findAll()) {
            System.out.println(ps.getStatus());

            if ("Task submitted".equals(ps.getStatus()) ||
                    "Processing".equals(ps.getStatus()) ||
                    "Success".equals(ps.getStatus())) {

                String res = checkThePrice.Scrape_The_Price(ps.getTrackingId(), ps.getId());
                System.out.println("PRICE IS: " + res);

                // Remove all non-digit characters
                String numericPart = res.replaceAll("[^0-9]", "");
                if (!numericPart.isEmpty()) {
                    int price = Integer.parseInt(numericPart);

                    // Example comparison
                    if (price <= ps.getTargetPrice()) {
                        System.out.println("Let's send mail target price " + ps.getTargetPrice() +" Current price "+ price);
                        sendMailForPriceTrackingUpdate.sendTrackingUpdateforSuccess(
                                ps.getEmail(), ps.getProductURL(), ps.getTargetPrice());
                        ps.setStatus("MailSend");
                        productStatusDao.save(ps);
                    }
                } else {
                    System.out.println("Not a valid number, skipping... Value: " + res);
                }

            } else if ("Failed".equals(ps.getStatus())) {
                sendMailForPriceTrackingUpdate.sendTrackingUpdateForFailure(ps.getEmail(),ps.getProductURL());
                productStatusDao.delete(ps);
            } else if ("MailSend".equals(ps.getStatus())) {
                productStatusDao.delete(ps);
            }
        }
    }

}
