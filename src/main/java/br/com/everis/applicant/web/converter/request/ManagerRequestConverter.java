package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Manager;
import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.UserService;
import br.com.everis.applicant.web.dto.request.ManagerRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ManagerRequestConverter implements Converter<ManagerRequest, Manager> {

    private UserService userService;

    public ManagerRequestConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Manager convert(ManagerRequest request) {
        Optional<User> user = userService.findById(request.getUserId());

        return Manager.builder()
                .name(request.getName())
                .hasNotebook(request.getHasNotebook())
                .motherName(request.getMotherName())
                .dateBirth(request.getDateBirth())
                .user(user.isPresent() ? user.get() : new User())
                .phone(request.getPhone())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .level(request.getLevel())
                .build();
    }
}
