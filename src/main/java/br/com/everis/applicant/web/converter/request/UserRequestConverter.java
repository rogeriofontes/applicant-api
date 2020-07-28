package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.web.dto.request.UserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRequestConverter implements Converter<UserRequest, User> {

    @Override
    public User convert(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }
}