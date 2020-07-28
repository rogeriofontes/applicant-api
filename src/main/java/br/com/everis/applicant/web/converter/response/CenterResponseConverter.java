package br.com.everis.applicant.web.converter.response;

import br.com.everis.applicant.model.entity.Center;
import br.com.everis.applicant.web.dto.response.CenterResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CenterResponseConverter implements Converter<Center, CenterResponse> {

    @Override
    public CenterResponse convert(Center center) {
        return CenterResponse.builder()
                .id(center.getId())
                .description(center.getDescription())
                .city(center.getCity())
                .state(center.getState())
                .country(center.getCountry()).build();
    }
}