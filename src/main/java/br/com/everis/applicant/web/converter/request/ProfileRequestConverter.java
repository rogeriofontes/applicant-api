package br.com.everis.applicant.web.converter.request;

import br.com.everis.applicant.model.entity.Profile;
import br.com.everis.applicant.web.dto.request.ProfileRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileRequestConverter implements Converter<ProfileRequest, Profile> {

    @Override
    public Profile convert(ProfileRequest request) {
        return Profile.builder()
                .role(request.getRole())
                .build();

    }
}