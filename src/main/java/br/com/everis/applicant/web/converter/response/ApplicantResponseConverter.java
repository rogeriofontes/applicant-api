package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.entity.Professional;
import br.com.everis.applicant.web.dto.response.ApplicationResponse;
import br.com.everis.applicant.web.dto.response.ProfessionalResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ApplicantResponseConverter implements Converter<Applicant, ApplicationResponse> {

    @Override
    public ApplicationResponse convert(Applicant applicant) {
        return ApplicationResponse.builder()
                .id(applicant.getId())
                .name(applicant.getName())
                .hasNotebook(applicant.getHasNotebook())
                .motherName(applicant.getMotherName())
                .dateBirth(applicant.getDateBirth())
                .user(applicant.getUser())
                .phone(applicant.getPhone())
                .mobile(applicant.getMobile())
                .email(applicant.getEmail())
                .linkedIn(applicant.getLinkedIn())
                .registeredAt(applicant.getRegisteredAt())
                .build();
    }
}