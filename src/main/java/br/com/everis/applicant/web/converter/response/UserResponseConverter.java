package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.User;
import br.com.everis.applicant.web.dto.response.UserResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter implements Converter<User, UserResponse> {

    @Override
    public UserResponse convert(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
