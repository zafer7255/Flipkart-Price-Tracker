package Track_your_price.PriceTrackingApplication.Services.SendMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailForPriceTrackingUpdate {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendTrackingUpdateforSuccess(String to, String productURL, Integer targetPrice) {
        String subject = "Price Alert: Your Tracked Product is Now Within Budget";
        String body = "Dear User,\n\n" +
                "We are pleased to inform you that the product you have been tracking has now reached your target price.\n\n" +
                "Product Link: " + productURL + "\n" +
                "Your Target Price: ₹" + targetPrice + "\n\n" +
                "We recommend checking the product soon to take advantage of this opportunity.\n\n" +
                "Thank you for using Price Tracker.\n\n" +
                "Best regards,\n" +
                "Price Tracker Team";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);
            System.out.println("✅ Price tracking email sent successfully to: " + to);

        } catch (Exception e) {
            System.err.println("❌ Error sending price tracking email: " + e.getMessage());
        }
    }


    public void sendTrackingUpdateForFailure(String to, String productURL) {
        String subject = "Price Tracker Update Failed – Action Required";
        String body = "Dear User,\n\n" +
                "We encountered an issue while updating the product you are tracking. Unfortunately, something went wrong during the process.\n\n" +
                "Product Link: " + productURL + "\n\n" +
                "Kindly re-add your product URL on our website to continue tracking.\n\n" +
                "We apologize for the inconvenience and appreciate your understanding.\n\n" +
                "Thank you for using Price Tracker.\n\n" +
                "Best regards,\n" +
                "Price Tracker Team";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);
            System.out.println("❌ Failure notification email sent successfully to: " + to);

        } catch (Exception e) {
            System.err.println("⚠️ Error sending failure notification email: " + e.getMessage());
        }
    }

}
