package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Professional;
import br.com.everis.applicant.web.dto.response.ProfessionalResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalResponseConverter implements Converter<Professional, ProfessionalResponse> {

    @Override
    public ProfessionalResponse convert(Professional professional) {
        return ProfessionalResponse.builder()
                .id(professional.getId())
                .name(professional.getName())
                .hasNotebook(professional.getHasNotebook())
                .team(professional.getTeam())
                .motherName(professional.getMotherName())
                .dateBirth(professional.getDateBirth())
                .user(professional.getUser())
                .phone(professional.getPhone())
                .mobile(professional.getMobile())
                .email(professional.getEmail())
                .programmingLanguages(professional.getProgrammingLanguages())
                .build();
    }
}