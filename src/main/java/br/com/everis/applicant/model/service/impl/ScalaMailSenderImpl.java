package br.com.everis.applicant.model.service.impl;

import br.com.everis.applicant.model.service.ScalaMailSender;
import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.web.dto.EmailDTO;
import br.com.everis.applicant.web.dto.TeamManageHomeOfficeDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ScalaMailSenderImpl implements ScalaMailSender {

    private SendGrid sendGrid;

    @Value("${app.sendgrid.template.id.key}")
    private String sendGridTemplateId;

    public ScalaMailSenderImpl(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public Response sendEmail(TeamManageHomeOfficeDTO teamManageHomeOfficeDTO) {
        var developerName = teamManageHomeOfficeDTO.getDeveloperName();
        var teamName = teamManageHomeOfficeDTO.getTeamName();
        var emailDTO = teamManageHomeOfficeDTO.getEmailDTO();
        var scales = teamManageHomeOfficeDTO.getScales();
        var leader = teamManageHomeOfficeDTO.getLeader();

        Mail mail = configMail(emailDTO);
        mail.setReplyTo(new Email(emailDTO.getFrom()));

        Personalization personalization = getPersonalization(teamName, leader, developerName, scales, emailDTO);

        mail.addPersonalization(personalization);
        mail.setTemplateId(sendGridTemplateId);

        Request request = new Request();
        Response response = null;
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sendGrid.api(request);
            log.info("Status Code: " + response.getStatusCode());
            log.info("Body: " + response.getBody());
            log.info("Headers: " + response.getHeaders());
        } catch (IOException ex) {
           log.info("error" + ex.getMessage());
        }
        return response;
    }

    private Personalization getPersonalization(String teamName, Manager leader, String developerName, List<String> scales, EmailDTO emailDTO) {
        Personalization personalization = new Personalization();
        personalization.addTo(new Email(emailDTO.getTo()));

        personalization.addDynamicTemplateData("name", developerName);

        personalization.addDynamicTemplateData("project", teamName);
        personalization.addDynamicTemplateData("scales", scales);
        personalization.addDynamicTemplateData("leaderName", leader.getName());
        personalization.addDynamicTemplateData("leaderEmail", leader.getEmail());
        personalization.addDynamicTemplateData("leaderPhone", leader.getPhone());

        return personalization;
    }

    private Mail configMail(EmailDTO emailDTO) {
        var from = emailDTO.getFrom();
        var to = emailDTO.getTo();
        var subject = emailDTO.getSubject();
        var content = new Content(emailDTO.getContentType(), emailDTO.getBody());

        return  new Mail(new Email(from), subject, new Email(to), content);
    }
}
