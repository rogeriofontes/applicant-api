package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.web.dto.response.ManagerResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ManagerResponseConverter implements Converter<Manager, ManagerResponse> {

    @Override
    public ManagerResponse convert(Manager manager) {
        return ManagerResponse.builder()
                .id(manager.getId())
                .name(manager.getName())
                .hasNotebook(manager.getHasNotebook())
                .motherName(manager.getMotherName())
                .dateBirth(manager.getDateBirth())
                .user(manager.getUser())
                .phone(manager.getPhone())
                .mobile(manager.getMobile())
                .email(manager.getEmail())
                .level(manager.getLevel())
                .build();
    }
}