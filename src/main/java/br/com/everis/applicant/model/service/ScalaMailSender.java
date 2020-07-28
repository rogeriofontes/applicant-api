package br.com.everis.applicant.model.service;

import br.com.everis.applicant.web.dto.TeamManageHomeOfficeDTO;
import com.sendgrid.Response;

public interface ScalaMailSender {
    Response sendEmail(TeamManageHomeOfficeDTO teamManageHomeOfficeDTO);
}
