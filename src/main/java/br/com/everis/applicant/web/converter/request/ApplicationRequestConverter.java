package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.exceptions.UserNotFoundException;
import br.com.everis.applicant.model.entity.Applicant;
import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.model.service.UserService;
import br.com.everis.applicant.web.dto.request.ApplicantRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationRequestConverter implements Converter<ApplicantRequest, Applicant> {
    private UserService userService;

    public ApplicationRequestConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Applicant convert(ApplicantRequest request) {
        Optional<User> user = userService.findById(request.getUserId());
        if(!user.isPresent()) {
            throw new UserNotFoundException("User id does not exists!");
        }

        return Applicant.builder()
                .name(request.getName())
                .hasNotebook(request.getHasNotebook())
                .motherName(request.getMotherName())
                .dateBirth(request.getDateBirth())
                .user(user.isPresent() ? user.get() : new User())
                .phone(request.getPhone())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .linkedIn(request.getLinkedIn())
                .registeredAt(request.getRegisteredAt())
                .build();
    }
}