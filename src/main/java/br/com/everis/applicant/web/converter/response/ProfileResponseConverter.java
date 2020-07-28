package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Profile;
import br.com.everis.applicant.web.dto.response.ProfileResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileResponseConverter implements Converter<Profile, ProfileResponse> {

    @Override
    public ProfileResponse convert(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
              .role(profile.getRole()).build();
    }
}