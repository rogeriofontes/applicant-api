package br.com.everis.applicant.conf;


import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    @Value("${app.sendgrid.api.key}")
    private String sendGridApiKey;

    @Bean
    public SendGrid getSendGrid() {
        return new SendGrid(sendGridApiKey);
    }
}
